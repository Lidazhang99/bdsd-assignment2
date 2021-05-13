package letterfrequenties;

import java.io.IOException;    

import java.util.Iterator;    
import org.apache.hadoop.io.IntWritable;    
import org.apache.hadoop.io.Text;    
import org.apache.hadoop.mapred.MapReduceBase;    
import org.apache.hadoop.mapred.OutputCollector;    
import org.apache.hadoop.mapred.Reducer;    
import org.apache.hadoop.mapred.Reporter;    
    
public class TrainReducerSort extends MapReduceBase implements Reducer<IntWritable,Text,IntWritable,Text> {    
	
	public void reduce(IntWritable key, Iterator<Text> values,OutputCollector<IntWritable,Text> output,    
			Reporter reporter) throws IOException { 
		
		Text character = new Text();
		
		while (values.hasNext()) {    
			character = values.next();  
			output.collect(key, character);
		}
		 
	}    
}  
