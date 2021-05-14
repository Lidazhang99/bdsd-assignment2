package letterfrequenties;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
    
public class MatrixPossibilityReducer extends Reducer<IntWritable,Text,Text,IntWritable> {    
	
//	@Override
//	public void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {    
//	    Text word = new Text();
//	
//        for (Text value : values) {
//            word = value;
//            context.write(key, word);
//        }
//
//	}
	
	public void reduce(Text key, Iterator<IntWritable> values, Context context) throws IOException, InterruptedException {    
		int count=0;    
		while (values.hasNext()) {    
			count+=values.next().get();    
		}    
		context.write(key,new IntWritable(count));
		}      
		  
}  
