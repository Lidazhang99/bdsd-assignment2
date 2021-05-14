package letterfrequenties;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


	public class MatrixPossibilityMapper extends Mapper<Text,Text,IntWritable,Text >{   
		List<String> keys = new ArrayList<String>();
        
		// Compare previous characters + persist count
        IntWritable count = new IntWritable(1);
		String prevChar = null;
		
		@Override
		public void map(Text key, Text value, Context context) throws IOException, InterruptedException {  			
			keys.add(key.toString());
	        
			String currentChar = String.valueOf(key.toString().charAt(0));
        	if((prevChar != null)) {
	        	if ((currentChar.equals(prevChar))) {
	        		prevChar = currentChar;
	        		count.set(count.get() + 1);
					context.write(count, key);
	        	} else {
		        	prevChar = currentChar;
		        	context.write(count, key);
		        	count.set(1);
	        	}
        	} else {
	        	prevChar = currentChar;
	        	context.write(count, key);
	        	count.set(1);
        	}
	}
}
