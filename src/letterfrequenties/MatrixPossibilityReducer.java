package letterfrequenties;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
    
public class MatrixPossibilityReducer extends Reducer<Text,Text,Text,Text> {    

	private Text keyEmit = new Text();
	private Text valEmit = new Text();
	
	public void reduce(Text key, Text value, Context context) throws IOException, InterruptedException {    
		 String line=value.toString();
		 String[] words=line.split("/n");
		 keyEmit.set(words[0]);
		 valEmit.set(words[1]);
		 context.write(keyEmit, valEmit);
    }
}    
