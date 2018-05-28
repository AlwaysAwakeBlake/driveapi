package drive.fitness.s3.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
 
public class Utility {
	
	public String displayText(InputStream input) throws IOException{
		// Read one text line at a time and display.
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String content = "";
        while ((content += reader.readLine()) != null) {
        	System.out.println(content);
        }
        return content;
	}
}