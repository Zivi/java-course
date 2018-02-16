
/**
 * Write a description of Part3 here.
Week2, second group of assignments
 */
public class Part3 {
    
        public int findStopCodon(String dna, int startIndex, String stopCodon){
        String currentDna = dna.substring(startIndex);
        int stopIndex = currentDna.indexOf(stopCodon);
        if ((stopIndex - startIndex) % 3 == 0){
            return stopIndex;
        } else {
            stopIndex = dna.length();
        }
        return stopIndex;
    }
    
    public int findGeneIndex(String dna) {
        int firstOccurence = dna.indexOf("ATG");
        if (firstOccurence == -1) {
            return -1;
        }
        int taaIndex = findStopCodon(dna, firstOccurence, "TAA");
        int tagIndex = findStopCodon(dna, firstOccurence, "TAG");
        int tgaIndex = findStopCodon(dna, firstOccurence, "TGA");
        int temp = Math.min(taaIndex, tagIndex);
        int smallestIndex = Math.min(temp, tgaIndex);
        
        if (smallestIndex == dna.length()) {
            return -1;
        }
        
        return smallestIndex + 3;
    }
    
    public void countGenes(String dna) {
        int startIndex = 0;
        int count = 0;
        while (true){
            startIndex = findGeneIndex(dna, startIndex);
            if (startIndex == -1) {
                break;
            }
            count = count + 1;
        }
        System.out.println("count: " + count);
    }
    
    public void testOn(String dna) {
        System.out.println("testing print all genes: " + dna);
        countGenes(dna);
    }

}
