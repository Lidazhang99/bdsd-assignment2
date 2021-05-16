package letterfrequenties;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import objects.Pair;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MatrixPossibilityMapper extends Mapper<Text,Text,Text,Text>{  
	String prevFirstCharacter = null;
	String currentFirstCharacter = null;
	
	List<Pair> currentKeyValuePairs = new ArrayList<Pair>();
	List<Pair> batchedKeyValuePairs = new ArrayList<Pair>();
	
	double totalOccurenceOfFirstCharacter = 0.0;
	
	public void map(Text key, Text value, Context context) throws IOException, InterruptedException {   
		currentFirstCharacter = String.valueOf(key.toString().charAt(0));
		String keyAsString = key.toString();
		Double valueAsDouble = Double.parseDouble(value.toString());
		
		if(prevFirstCharacter != null) {
			if(!(currentFirstCharacter.equals(prevFirstCharacter))) {
				for(Pair pair : currentKeyValuePairs) {
					double frequencyOfOccurence = pair.getValue();
					double chanceOfOccurrence =  frequencyOfOccurence / totalOccurenceOfFirstCharacter;
					pair.setValue(chanceOfOccurrence);
					batchedKeyValuePairs.add(pair);
				}
				currentKeyValuePairs.clear();
				currentKeyValuePairs.add(new Pair(keyAsString, valueAsDouble));
				prevFirstCharacter = currentFirstCharacter;
				totalOccurenceOfFirstCharacter = Double.parseDouble(value.toString());
			} else {
				currentKeyValuePairs.add(new Pair(keyAsString, valueAsDouble));
				totalOccurenceOfFirstCharacter += Double.parseDouble(value.toString());
			}
		} else {
			prevFirstCharacter = currentFirstCharacter;
			currentKeyValuePairs.add(new Pair(keyAsString, valueAsDouble));
			totalOccurenceOfFirstCharacter += Double.parseDouble(value.toString());
		}
    }
	
	@Override
	protected void cleanup(Context context) throws IOException, InterruptedException {
		for(Pair pair : batchedKeyValuePairs) {
			context.write(new Text(pair.getKey()), new Text(pair.getValue().toString()));
		}
		
		for(Pair pair : currentKeyValuePairs) {
			double frequencyOfOccurence = pair.getValue();
			double chanceOfOccurrence =  frequencyOfOccurence / totalOccurenceOfFirstCharacter;
			pair.setValue(chanceOfOccurrence);
			context.write(new Text(pair.getKey()), new Text(pair.getValue().toString()));
		}
	}
}
