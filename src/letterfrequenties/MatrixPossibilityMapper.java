package letterfrequenties;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;       
import org.apache.hadoop.mapreduce.Mapper;


	public class MatrixPossibilityMapper extends Mapper<LongWritable,Text,Text,String >{   
		// Lijst aan unieke characters
		// Kijken hoeveel combinaties beginnen met characters voor elke character
		// Dan berekenen wat de kans is voor combo om te bestaan
//		IntWritable frequency = new IntWritable();
		List<Text> uniqueCharacters = new ArrayList<Text>();
		
		public void map(Text key, Iterable<Text> value, Context context) throws IOException, InterruptedException {  			
//			int newVal = Integer.parseInt(value.toString());
			Text charKey = new Text();  
			
			for(Text character : value) {
				Text s213123 = character;
				if(!uniqueCharacters.contains(character)) {
					uniqueCharacters.add(character);
					charKey.set(character);
					
				}
			}
			
//			frequency.set(newVal);
			context.write(charKey, "Fuck you Hadoop"); 	
	}
}
