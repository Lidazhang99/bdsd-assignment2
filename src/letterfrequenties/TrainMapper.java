package letterfrequenties;

import java.io.IOException;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.hadoop.io.IntWritable;    
import org.apache.hadoop.io.LongWritable;    
import org.apache.hadoop.io.Text;    
import org.apache.hadoop.mapred.MapReduceBase;    
import org.apache.hadoop.mapred.Mapper;    
import org.apache.hadoop.mapred.OutputCollector;    
import org.apache.hadoop.mapred.Reporter;    

public class TrainMapper extends MapReduceBase implements Mapper<LongWritable,Text,Text,IntWritable>{    
    public void map(LongWritable key, Text value,OutputCollector<Text,IntWritable> output,     
           Reporter reporter) throws IOException {   
    	
    	//set previous character
    	String prevChar = null;
        
    	//Convert line to readable characters
    	String line = normalizeLine(value);
        
    	//Retrieve the characters from the line
        String  tokenizer[] = line.split("");
        
        List<String> charactersList = Arrays.asList(tokenizer);
        
        Set<String> charactersSet = new HashSet<String>(charactersList);
        
        for(String singleChar : charactersList)  
        {  
        	if(((!singleChar.equals("")) && (singleChar != null) && (singleChar.matches("^[a-zA-Z]*$"))))
        	{
                Text charKey = new Text();  
                IntWritable count = new IntWritable(1);  
                
        		if (prevChar != null) {
        			charKey.set(prevChar + " " + singleChar);
                    output.collect(charKey, count); 
        		}
        		prevChar = singleChar;
        	}
              
        }  
    }   
    
    private String normalizeLine(Text value) {
    	String line = value.toString().trim().replaceAll(" +", "");
        line = Normalizer.normalize(line, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        line = line.toLowerCase().replaceAll("[^A-Za-z ]", "");
        line = line.replaceAll("[\\s|\\u00A0]+", "");
        line = line.replaceAll("\t", "");
    	return line;
    }
    
}
