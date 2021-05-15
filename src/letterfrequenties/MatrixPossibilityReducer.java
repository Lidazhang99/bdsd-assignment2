package letterfrequenties;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
    
public class MatrixPossibilityReducer extends Reducer<Text,Text,Text,Text> {    

	public void reduce(Text key, Text value, Context context) throws IOException, InterruptedException {    
		context.write(key, value);
    }
}    
