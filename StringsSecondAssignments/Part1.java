import edu.duke.*;
import java.io.*;
import java.lang.String;

public class Part1 {
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
    
    public String findGene(String dna) {
        int firstOccurence = dna.indexOf("ATG");
        if (firstOccurence == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, firstOccurence, "TAA");
        int atgIndex = findStopCodon(dna, firstOccurence, "TAG");
        int tgaIndex = findStopCodon(dna, firstOccurence, "TGA");
        int temp = Math.min(taaIndex, atgIndex);
        int smallestIndex = Math.min(temp, tgaIndex);
        
        if (smallestIndex == dna.length()) {
            return "";
        }
        
        return dna.substring(firstOccurence, smallestIndex);
    }

    public void testFindStopCodon() {
       // String dna = "ATGCCCTAG";
        //int startIndex = 0;
        //String stopCodon = "TAG";
        //int result = findStopCodon(dna, startIndex, stopCodon);
        //*****
        
       String dna = "ATGCCCGGTAG";
       int startIndex = 0;
       String stopCodon = "TAG";
       int result = findStopCodon(dna, startIndex, stopCodon);

       System.out.println("result: " + result);
    }
    
    public void testFindGene() {
       String dna = "AAAATGCCCGGGTAG";
       String result = findGene(dna);
       System.out.println("result: " + result);
    }
    
    public void printAllGenes(String dna) {
        int startIndex = 0;
        while (true){
            String currentGene = findGene(dna.substring(startIndex));
            if (currentGene.isEmpty()) {
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + 
                currentGene.length();
        }
    }
    
    public void testOn(String dna) {
        System.out.println("testing print all genes: " + dna);
        printAllGenes(dna);
    }
    
    public void test() {
        testOn("ATGATCATTTTTATGCTGCAACGGTFAAGA");
        testOn("");
        testOn("ATGATCATAAAGAAGATATAGAGGGCCATGTAA");
    }

}

