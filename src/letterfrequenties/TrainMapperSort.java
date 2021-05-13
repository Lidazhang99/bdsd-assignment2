package letterfrequenties;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;       
import org.apache.hadoop.io.Text;    
import org.apache.hadoop.mapred.MapReduceBase;    
import org.apache.hadoop.mapred.Mapper;    
import org.apache.hadoop.mapred.OutputCollector;    
import org.apache.hadoop.mapred.Reporter;   

public class TrainMapperSort extends MapReduceBase implements Mapper<Text,Text,IntWritable,Text> {    
    
	IntWritable frequency = new IntWritable();
	
	public void map(Text key, Text value,OutputCollector<IntWritable, Text> output,     
           Reporter reporter) throws IOException { 
		
		//Read output TrainReducer, parse it as (key, value) and swap to (value, char)
		int newVal = Integer.parseInt(value.toString());
		frequency.set(newVal);
		output.collect(frequency, key); 	
	}
}