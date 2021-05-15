package letterfrequenties;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class Runner {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{    
    	//Job 1
    	Configuration conf1 = new Configuration();
    	Job job1 = Job.getInstance(conf1);
    	job1.setJobName("bigram matrix frequency");
    	
    	job1.setJarByClass(Runner.class);
        job1.setMapperClass(MatrixFrequencyMapper.class); 
        job1.setReducerClass(MatrixFrequencyReducer.class);
        
        job1.setMapOutputKeyClass(Text.class);
        job1.setMapOutputValueClass(IntWritable.class);
        
        FileInputFormat.addInputPath(job1, new Path(args[0]));
        FileOutputFormat.setOutputPath(job1, new Path(args[1])); //output of job 1
        
        job1.waitForCompletion(true);
        
        //Job 2
        Configuration conf2 = new Configuration();
        Job job2 = Job.getInstance(conf2);
    	job2.setJobName("bigram matrix possibility");
    	
    	job2.setJarByClass(Runner.class);
        job2.setMapperClass(MatrixPossibilityMapper.class);   
        job2.setReducerClass(MatrixPossibilityReducer.class);
        
        job2.setMapOutputKeyClass(Text.class);
        job2.setMapOutputValueClass(Text.class);
        
        job2.setInputFormatClass(KeyValueTextInputFormat.class);
        job2.setOutputFormatClass(TextOutputFormat.class);
        
        FileInputFormat.addInputPath(job2, new Path(args[1])); //output of job1
        FileOutputFormat.setOutputPath(job2, new Path(args[2]));
        
        job2.waitForCompletion(true);
        
        
        //Job 3
        Configuration conf3 = new Configuration();
        Job job3 = Job.getInstance(conf3);
    	job3.setJobName("bigram matrix predicter");
    	
    	job3.setJarByClass(Runner.class);
        job3.setMapperClass(PredictMapper.class);   
        job3.setReducerClass(PredictReducer.class);
        
        job3.setMapOutputKeyClass(Text.class);
        job3.setMapOutputValueClass(Text.class);
        
        job3.setInputFormatClass(KeyValueTextInputFormat.class);
        job3.setOutputFormatClass(TextOutputFormat.class);

      //TAB separated input File 1
        MultipleInputs.addInputPath(job3,new Path(args[1]), KeyValueTextInputFormat.class); //output of job1
        //";" separated input file 2
        MultipleInputs.addInputPath(job3, new Path(args[2]), KeyValueTextInputFormat.class); //output of job2
        
        
//        FileInputFormat.addInputPath(job3, new Path(args[1] + "/output2")); 
        FileOutputFormat.setOutputPath(job3, new Path(args[1]+ "/output3"));
        
        System.exit(job3.waitForCompletion(true) ? 0 : 1);
    }
}
