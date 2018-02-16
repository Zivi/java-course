
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
        int occurence = 0;
        int index = 0;
        //return an integer indicating how many times stringa appears in
        //string b where each occurence of stringa must not overlap with another
        // occurence
        while (index < stringb.length()) {
            int currentOccurence = stringb.indexOf(stringa, index);
            if (currentOccurence == -1) {
                break;
            } else {
                occurence = occurence + 1;
                index = currentOccurence + 3;
            }
        }
        return occurence;
    }
    
    public void testHowMany(){
       //String a = "GAA";
       //String b = "ATGAACGAATTGAATC";
       //String a = "AA";
       //String b = "ATAAAA";
       String a = "B";
       String b = "ATAAA";
       int result = howMany(a, b);
       System.out.println("number of occurences of string a: " + result);
    }
}
