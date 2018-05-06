import edu.duke.*;
public class WordLengths {
    public int[] countWordLengths(FileResource resource, int[] counts) {
        // read in the words from resource, and count the number of words
        // of each length for all the words in the resource storing these
        // counts in the array counts, counts[k] should contain the number of
        // words of length k
        // do not count the first, or last character if it's a non-letter
        // for any words >= last index of counts array, count as largest size
        for (String word: resource.words()) {
            StringBuilder sb = new StringBuilder(word);
            // use Character.isLetter to return true for special characters
            if (!Character.isLetter(sb.charAt(0))) {
                sb.deleteCharAt(0);
            }
            if (!Character.isLetter(sb.charAt(sb.length()-1))) {
                sb.deleteCharAt(sb.length()-1);
            }
            String trimmedWord = sb.toString();
            int wordLength = trimmedWord.length();
            if (wordLength >= counts.length) {
                counts[counts.length-1] += 1;
            } else {
                counts[wordLength] += 1;
            }
        }
        return counts;
    }
    
    public void testCountWordLengths() {
        // creates a new FileResource to select a file
        FileResource fr = new FileResource();
        // create a counts int array of size 31
        int[] counts = new int[31];
        // call countWordLengths
        // test on smallHamlet.txt
        int[] newCounts = countWordLengths(fr, counts);
        for (int i = 0; i < 31; i++) {
        System.out.println("Words of length " + i + " " + newCounts[i]);
        }
        // call indexOfMax to determine the most common word length in the file
        int maxIdx = indexOfMax(newCounts);
        System.out.println("Max word length: " + maxIdx 
            + " of length " + newCounts[maxIdx]);
        
    }
    
   public int indexOfMax(int[] values) {
        // returns the index position of the largest element in values
        int maxIdxSize = 0;
        int maxIdx = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > maxIdxSize) {
                maxIdxSize = values[i];
                maxIdx = i;
            }
        }
        return maxIdx;
   }
}
