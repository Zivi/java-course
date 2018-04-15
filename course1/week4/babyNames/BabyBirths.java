package week4.babyNames;

/**
 * Print out the names for which 100 or fewer babies were born in a chosen CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public int getRank(int year, String name, String gender) {
        int rank = 0;
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                rank += 1;
                if (rec.get(0).equals(name)) {
                    return rank;
                }
            }
        }
        return -1;
    }
    public int yearOfHighestRank(String name, String gender) {
        int highestRank = -1;
        int highestRankYear = -1;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int rank = 0;
            for (CSVRecord rec : fr.getCSVParser(false)) {
                if (rec.get(1).equals(gender)) {
                    rank += 1;
                    if (rec.get(0).equals(name)) {
                        int year = Integer.parseInt(f.getName().substring(3, 7));
                        if (highestRankYear == -1 || highestRank > rank) {
                            highestRankYear = year;
                            highestRank = rank;
                            break;
                        }
                    }
                }
            }
        }
        return highestRankYear;
    }
    public double getAverageRank(String name, String gender) {
        // this may need to be refactored
        double totalRank = 0;
        double counter = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int rank = 0;
            for (CSVRecord rec : fr.getCSVParser(false)) {
                if (rec.get(1).equals(gender)) {
                    rank += 1;
                    if (rec.get(0).equals(name)) {
                        totalRank += rank;
                        counter += 1;
                        break;
                    }
                }
            }
        }
        return totalRank / counter;
    }
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        FileResource fr = new FileResource();
        // returns an integer, the total number of births of those names
        // with the same gender and same year who are ranked higher than
        int totalNumber = 0;
        for (CSVRecord rec: fr.getCSVParser(false)) {
            if (rec.get(0).equals(name) && rec.get(1).equals(gender)) {
                return totalNumber;
            } else if (rec.get(1).equals(gender)) {
                totalNumber += Integer.parseInt(rec.get(2));
            }
        }
        return -1;
    }
    public String getName(int year, int rank, String gender) {
        String name = "NO NAME";
        int position = 0;
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                position += 1;
                if (position == rank) {
                    name = rec.get(0);
                }
            }
        }
        return name;
    }
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) { // false because there is no header
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                    " Gender " + rec.get(1) +
                    " Num Born " + rec.get(2));
            }
        }
    }
    public void totalBirths (FileResource fr) {
        int totalNames = 0;
        int totalBoysNames = 0;
        int totalGirlsNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            totalNames += 1;
            if (rec.get(1).equals("M")) {
                totalBoysNames += 1;
            } else {
                totalGirlsNames += 1;
            }
        }
        System.out.println("total names: " + totalNames);
        System.out.println("total girls: " + totalGirlsNames);
        System.out.println("total boys: " + totalBoysNames);
    }
    public void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    public void testRank(int year, String name, String gender) {
        System.out.println("popularity of " + name + " in " + year + " " +
            getRank(year, name, gender)); 
    }
    public void testGetName(int year, int rank, String gender) {
        System.out.println("Popularity of " + gender + " at rank " + rank + " in " + year + " " + 
            getName(year, rank, gender));
    }
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        // determines what name would have been named if they were born in a
        // different year, based on the same popularity
        // get popularity of name for initial year: use getRank();
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + newName +
            " if born in " + newYear);
        // for the new year, get the name  for that given rank: use getName()
        
    }
}
