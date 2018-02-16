
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;
import edu.duke.URLResource;

public class Part4 {
    public String urlFinder(String line) {
        String match = "youtube.com";
        String lowerCaseLine = line.toLowerCase();
        int hasYoutube = lowerCaseLine.indexOf(match);
        if (hasYoutube > -1) {
             int urlStart = line.indexOf("\"");
             int lineLastIndex = line.lastIndexOf("\"");
             String trimmedLine = line.substring(urlStart + 1, lineLastIndex);
             return trimmedLine;
        }
        return "";
    }
    
    public void testing() {
        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
            for (String line : ur.lines()) {
                String result = urlFinder(line);
                System.out.println("url: " + result);
            }
    }
}
