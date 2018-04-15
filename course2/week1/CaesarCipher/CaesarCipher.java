import edu.duke.*;

public class CaesarCipher {
    public String encrypt (String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key)+
            alphabet.substring(0,key);
        //Count from 0 to < length of encrypted, (call it i)
        for (int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            boolean isLowerCase = isLowerCase(currChar);
            currChar = Character.toUpperCase(currChar);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(currChar);
            //If currChar is in the alphabet
            if (idx != -1) {
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet.charAt(idx);
                if (isLowerCase) {
                    newChar = Character.toLowerCase(newChar);
                }
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    public boolean isLowerCase(char ch) {
        return Character.isLowerCase(ch);
    }
    public String encryptTwoKeys(String input, int key1, int key2) {
        // set up StringBuilder to change the string
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < encrypted.length(); i += 2) {
            String letter1 = encrypt(input.substring(i, i+1), key1);
            char charLetter1 = letter1.charAt(0);
            encrypted.setCharAt(i, charLetter1);
            
            if (i + 1 < encrypted.length()) {
                String letter2 = encrypt(input.substring(i+1), key2);
                char charLetter2 = letter2.charAt(0);
                encrypted.setCharAt(i+1, charLetter2);
            }
        }
        return encrypted.toString();
        
        // call encrypt for input with key1 to return a string
        // apply the new letter from encrypt to  the new sgring
        // call encrypt for input with key2 to return a string
        
        
    }
    public void testCaesar() {
        int key1 = 8;
        int key2 = 21;
        int key = 15;
        FileResource fr = new FileResource();
        String message = fr.asString();
        //String encrypted = encrypt(message, key);
        //System.out.println(encrypted);
        //String decrypted = encrypt(encrypted, 26-key);
        //System.out.println(decrypted);
        //~~~~
        String encryptedTwoKey = encryptTwoKeys(message, key1, key2);
        System.out.println(encryptedTwoKey);
        // use case: cases of letters
    }
}

