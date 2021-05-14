package letterfrequenties;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
    
public class MatrixPossibilityReducer extends Reducer<IntWritable,Text,IntWritable,Text> {    
	@Override
	public void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {    
	    Text word = new Text();
	
        for (Text value : values) {
        	IntWritable asd = key;
        	Text asdasd = value;
        	String asdasd_text = asdasd.toString();
        	context.write(key, value);
        }
	}
//	@Override
//	public void reduce(Text key, Iterator<IntWritable> values, Context context) throws IOException, InterruptedException {    
//		int count=0;    
//		while (values.hasNext()) {    
//			count+=values.next().get();    
//		}    
//		context.write(key,new IntWritable(count));
//	}        
}  
