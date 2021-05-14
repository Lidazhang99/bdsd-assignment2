package letterfrequenties;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
    
public class MatrixPossibilityReducer extends Reducer<IntWritable,Text,IntWritable,Text> {    
	
	@Override
	public void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {    
	    Text word = new Text();
	
        for (Text value : values) {
            word = value;
            context.write(key, word);
        }

	}
		  
}  
