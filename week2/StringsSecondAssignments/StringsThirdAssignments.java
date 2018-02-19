import edu.duke.*;

import java.lang.String;

public class StringsThirdAssignments {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int index = startIndex;
        int stopIndex = 0;
        while (index < dna.length()) {
            stopIndex = dna.indexOf(stopCodon, index);
            if (stopIndex == -1) {
                stopIndex = dna.length();
            } else if ((stopIndex - startIndex) % 3 == 0){
                return stopIndex;
            } 
            index = stopIndex + 1;
        }
        
        return stopIndex;
    }
    
    public String findGene(String dna, int index) {
        int firstOccurenceIndex = dna.indexOf("ATG", index);
        if (firstOccurenceIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, firstOccurenceIndex, "TAA");
        int tagIndex = findStopCodon(dna, firstOccurenceIndex, "TAG");
        int tgaIndex = findStopCodon(dna, firstOccurenceIndex, "TGA");
        int temp = Math.min(taaIndex, tagIndex);
        int smallestIndex = Math.min(temp, tgaIndex);
        
        if (smallestIndex == dna.length()) {
            return "";
        }
        
        return dna.substring(firstOccurenceIndex, smallestIndex + 3);
    }
    
    public float getCgRatio(String dna) {
        float ratio = 0;

        int numC = getNumOccurences(dna, "C");
        int numG = getNumOccurences(dna, "G");
        
        ratio = (numC + numG) / (float)dna.length();

        return ratio;
    }
    
    public int getNumOccurences(String dna, String character) {
        int tracker = 0;
        int index = 0;

        while (true) {
            index = dna.indexOf(character, index);
            if (index == -1) {
                break;
            } else {
                tracker += 1;
                index += 1;
            }
        }

        return tracker;
    }
    
    public int countCTG(String dna) {
        // return the number of times the codon ctg appears in DNA
        return getNumOccurences(dna, "CTG");
    }

    public StorageResource getAllGenes(String dna) {
        int startIndex = 0;
        StorageResource geneList = new StorageResource();
        while (true) {
            String currentGene = findGene(dna, startIndex);
            int geneIndex = dna.indexOf(currentGene, startIndex);
                if (currentGene.isEmpty()) {
                    break;
                }
            geneList.add(currentGene);
            startIndex = currentGene.length() + geneIndex;
        }
        return geneList;
    }
    
    public void processGenes(StorageResource sr) {
    // print all the strings in sr longer than 9 characters
    int numStrings = 0;
    int numCGgt35 = 0;
    int totalGenes = 0;
    String longestString = "";
    int countCtg = 0;
    for (String s: sr.data()) {
        totalGenes += 1;
        if (s.length() > 60) {
            System.out.println("String longer than 60 characters: " + s);
            numStrings += 1;
        }
        float cgRatio = getCgRatio(s);
        if (cgRatio > 0.35) {
            System.out.println("String with C-G ratio > 0.35: " + s);
            numCGgt35 += 1;
        }
        if (s.length() > longestString.length()) {
            longestString = s;
        }
        countCtg = countCtg + countCTG(s);
    }
    System.out.println("Number of Strings longer than 60 characters: "
        + numStrings);
    System.out.println("Number of Strings with C-G ratio greater than 0.35: " 
        + numCGgt35);
    System.out.println("Gene with the longest length: " + longestString);
    System.out.println("longest length: " + longestString.length());
    System.out.println("Total genes: " + totalGenes);
    System.out.println("ctg's: " + countCtg);
    // print all strings longer than 60 characters
    
    // print the number of strngs longer than 60 characters
    }
    
    public void testProcessGenes() {
        FileResource fr = new FileResource("dna/GRch38dnapart.fa");
        String dna = fr.asString().toUpperCase();
        StorageResource sr = getAllGenes(dna);
        processGenes(sr);
    }
    
    public void testOn(String dna) {
        StorageResource genes = getAllGenes(dna);
        for ( String g: genes.data() ) {
            System.out.println(g);
        }
    }
    
    public void test() {
        //testOn("");
        //testOn("AATGTAApppATGAAATGA");
        //testOn("WWATGUUUTAGIIIATGTAG");
        //cgRatio("WWATGUUUTAGIIIATGTAG");
        //float ratio = getCgRatio("ATGCCATAG");
        //float ratio = getCgRatio("GCAAACCC");
        //System.out.println(ratio);
        int ctgCount = countCTG("CTGACTG");
        System.out.println(ctgCount);
    }

}
