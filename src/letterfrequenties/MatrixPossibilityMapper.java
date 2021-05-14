package letterfrequenties;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


	public class MatrixPossibilityMapper extends Mapper<Text,Text,IntWritable,Text >{   
		// Lijst aan unieke characters
		// Kijken hoeveel combinaties beginnen met characters voor elke character
		// Dan berekenen wat de kans is voor combo om te bestaan
//		IntWritable frequency = new IntWritable();
//		List<Text> uniqueCharacters = new ArrayList<Text>();
//		int newVal = Integer.parseInt(value.toString());
		
//		for(Text character : value) {
//			Text s213123 = character;
//			if(!uniqueCharacters.contains(character)) {
//				uniqueCharacters.add(character);
//				charKey.set(character);
//				
//			}
//		}
		
		IntWritable frequency = new IntWritable();
		
		@Override
		public void map(Text key, Text value, Context context) throws IOException, InterruptedException {  			
	        int newVal = Integer.parseInt(value.toString());
	        frequency.set(newVal);
	        context.write(frequency, key);
	}
}
