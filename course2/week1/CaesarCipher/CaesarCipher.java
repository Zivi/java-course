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
    }

    public int[] countLetters(String encrypted) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k < encrypted.length(); k++) {
            char ch = Character.toLowerCase(encrypted.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    public int maxIndex(int[] vals) {
        int maxDex = 0;
        for (int k = 0; k < vals.length; k++) {
            if (vals[k] > vals[maxDex]) {
                maxDex = k;
            }
        }
        return maxDex;
    }
    
    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        // location of letter 'e' assuming that e is the most frequently occuring letter
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4-maxDex);
        }
        System.out.println("key used in encryption: " + dkey);
        return cc.encrypt(encrypted, 26-dkey);
    }
    public void eyeballDecrypt(String encrypted) {
        CaesarCipher cipher = new CaesarCipher();
        for (int k = 0; k < 26; k += 1) {
            String s = cipher.encrypt(encrypted, k);
            System.out.println(k + "\t" + s);
        }
    }
    public void testCaesar() {
        //int key1 = 8;
        //int key2 = 21;
        //int key = 15;
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        //String encrypted = encrypt(message, key);
        //System.out.println(encrypted);
        //String decrypted = encrypt(encrypted, 26-key);
        //System.out.println(decrypted);
        //~~~~
        //String encryptedTwoKey = encryptTwoKeys(message, key1, key2);
        //System.out.println(encryptedTwoKey);
        // use case: cases of letters
        
        int key1 = 2;
        int key2 = 20;
        String message = "Top ncmy qkff vi vguv vbg ycpx";
        String encryptedTwoKey = encryptTwoKeys(message, 26-key1, 26-key2);
        System.out.println(encryptedTwoKey);
        
    }
}

