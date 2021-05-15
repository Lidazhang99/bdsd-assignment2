package letterfrequenties;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

public class Main {
	public static void main(String[] args) {
		Map<Text, Text> batchedKeyValues = new HashMap<Text, Text>();
		Text key1 = new Text("a b");
		Text value1 = new Text("1");
		Text key2 = new Text("a r");
		Text value2 = new Text("1");
		batchedKeyValues.put(key1, value1);
		batchedKeyValues.put(key2, value2);
		for(Entry<Text, Text> entry : batchedKeyValues.entrySet()) {
    		int countChecker = 20;
    		double chance = (10.0 / 20.0);
    		System.out.println(chance + " " +  entry.getKey().toString());
    	}
	}
}
