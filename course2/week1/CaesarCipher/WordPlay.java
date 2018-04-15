import edu.duke.*;
import java.lang.*;

public class WordPlay {
    public boolean isVowel(Character ch) {
        String vowels = "aeiouAEIOU";
        if (vowels.indexOf(ch) != -1) {
            return true;
        }
        return false;
    }
    public String replaceVowels(String phrase, Character ch) {
        StringBuilder replaced = new StringBuilder(phrase);
        for (int i = 0; i < replaced.length(); i ++) {
            char currentChar = replaced.charAt(i);
            if (isVowel(currentChar)) {
                replaced.setCharAt(i, ch);
            }
        }
        return replaced.toString();
    }
    public String emphasize(String phrase, Character ch) {
        StringBuilder replaced = new StringBuilder(phrase);
        for (int i = 0; i < replaced.length(); i ++) {
            char currentChar = replaced.charAt(i);
            if (currentChar == ch) {
                if (i % 2 == 0) {
                    replaced.setCharAt(i, '*');
                } else {
                    replaced.setCharAt(i, '+');
                }
            }
        }
        return replaced.toString();
    }
}
