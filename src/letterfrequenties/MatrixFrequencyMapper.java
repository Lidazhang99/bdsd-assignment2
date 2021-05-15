package letterfrequenties;

import java.io.IOException;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;    
  

public class MatrixFrequencyMapper extends Mapper<LongWritable,Text,Text,IntWritable>{   
	public void map(LongWritable key, Text value,Context context) throws IOException, InterruptedException {   
    	//set previous character
    	String prevChar = null;
        
    	//Convert line to readable characters
    	String line = normalizeLine(value);
        
    	//Retrieve the characters from the line
        String tokenizer[] = line.split("");
        
        List<String> charactersList = Arrays.asList(tokenizer);
        
        for(String singleChar : charactersList)  
        {  
        	if(((!singleChar.equals("")) && (singleChar != null) && (singleChar.matches("^[a-zA-Z]*$"))))
        	{
                Text charKey = new Text();  
                IntWritable count = new IntWritable(1);  
                
        		if (prevChar != null) {
        			charKey.set(prevChar + " " + singleChar);
					context.write(charKey, count);
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
