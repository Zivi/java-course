import edu.duke.*;
import java.util.Arrays;

public class CaesarBreaker {
    CaesarCipher cc = new CaesarCipher();
    public int[] countLetters(String encrypted) {
        return cc.countLetters(encrypted);
    }

    public int maxIndex(int[] vals) {
        return cc.maxIndex(vals);
    }
    
    int decryptKey(int maxIndex) {
        int dkey = maxIndex - 4;
        if (maxIndex< 4) {
            dkey = 26 - (4-maxIndex);
        }
        return dkey;
    }
    
    public String decrypt(String encrypted) {
        return cc.decrypt(encrypted);
    }

    public void testDecrypt() {
        // Just a test string with lots of eeeeeeeeeeeeeeeees
        String input = "Yjhi p ithi higxcv lxiw adih du ttttttttttttttttth";
        System.out.println(decrypt(input));
    }
    
    public String halfOfString(String message, int start) {
        StringBuilder halfString = new StringBuilder();
        for (int i = start; i < message.length(); i += 2) {
            halfString.append(message.charAt(i));
        }
        return halfString.toString();
    }

    public int getKey(String s) {
        int[] counts = cc.countLetters(s);
        return cc.maxIndex(counts);
    }

    public String decryptTwoKeys(String encrypted) {
        String oddHalf = halfOfString(encrypted, 0);
        String evenHalf = halfOfString(encrypted, 1);
        
        int oddLettersCount[] = countLetters(oddHalf);
        int evenLettersCount[] = countLetters(evenHalf);
        
        int oddMaxIndex = maxIndex(oddLettersCount);
        int evenMaxIndex = maxIndex(evenLettersCount);
     
        int oddDecryptKey = decryptKey(oddMaxIndex);
        int evenDecryptKey = decryptKey(evenMaxIndex);
        
        System.out.println("First key is " + oddDecryptKey);
        System.out.println("Second key is " + evenDecryptKey);        

        return cc.encryptTwoKeys(encrypted, 26-oddDecryptKey, 26-evenDecryptKey);
    }
    
    public void testCaesarBreaker() {
        FileResource fr = new FileResource();
        String decrypted = decryptTwoKeys(fr.asString());
        //String decrypted = decryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko");
        System.out.println("decrypted string with two keys " + decrypted);
    }

}
