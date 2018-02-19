import edu.duke.*;
import java.io.*;
/**
 * Write a description of StringParse here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurences(String stringa, String stringb) {
        int start1 = stringa.indexOf(stringb);
        if (start1 == -1) {
            return false;
        } else {
             int stopA = stringa.length() - 1;
             String subA = stringa.substring(start1 + 1, stopA);
             int start2 = subA.indexOf(stringb);
             if (start2 > -1) {
                 return true;
            } else {
                return false;
            }
        }
    }
    
    public String lastPart(String stringa, String stringb) {
        int start = stringa.indexOf(stringb);
        int end = start + stringb.length();
        if (start > 0) {
            return stringb + stringa.substring(end, end + 1);
        } else {
            return stringb;
        }
    }
    
    public void testing() {
        //String a = "A story by Abby Long";
        //String b = "by";
        //String a = "banana";
        //String b = "a";
        String a = "ctgtatgta";
        String b = "atg";
        boolean result = twoOccurences(a, b);
        System.out.println("result: " + result);
        
        
        //String c = "banana";
        //String d = "an";
        //String c = "forest";
        //String d = "zoo";
        String c = "catty";
        String d = "at";
        String lastPartResult = lastPart(c, d);
        System.out.println("result of  lastPart: " + lastPartResult);
    }
}
