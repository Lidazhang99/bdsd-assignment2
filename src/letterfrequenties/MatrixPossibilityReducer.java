package letterfrequenties;

import java.io.IOException;    

import java.util.Iterator;    
import org.apache.hadoop.io.IntWritable;    
import org.apache.hadoop.io.Text;         
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.Reducer;    
    
public class MatrixPossibilityReducer extends Reducer<IntWritable,Text,Text,String> {    
	
	public void reduce(IntWritable key, Iterator<Text> values,Context context,    
			Reporter reporter) throws IOException, InterruptedException { 
		
		Text character = new Text();
		
		while (values.hasNext()) {    
			character = values.next();  
			context.write(character, "Fuck you Hadoop");
		}
		 
	}    
}  
