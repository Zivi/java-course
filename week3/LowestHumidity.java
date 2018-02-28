package week3;
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class LowestHumidity {
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        for (CSVRecord currentRow : parser) {
            if (currentRow.get("Humidity") != "N/A") {
                lowestSoFar = getLowestOfTwo(currentRow, lowestSoFar);
            }
        }
        return lowestSoFar;
    }
    public CSVRecord getLowestOfTwo(CSVRecord currentRow, CSVRecord lowestSoFar) {
        if (lowestSoFar == null) {
            lowestSoFar = currentRow;
        } else {
            double currentHum = Double.parseDouble(currentRow.get("Humidity"));
            double lowestHum = Double.parseDouble(lowestSoFar.get("Humidity"));
            if (currentHum < lowestHum) {
                lowestSoFar = currentRow;
            }
        }
        return lowestSoFar;
    }
    public CSVRecord lowestInManyFiles() {
       CSVRecord lowestRow = null; 
       DirectoryResource dr = new DirectoryResource();
       for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            lowestRow = lowestHumidityInFile(fr.getCSVParser());
       }
       return lowestRow;
    }
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    public void  testLowestHumidityInManyFiles() {
        CSVRecord lowestFile = lowestInManyFiles();
        System.out.println("Lowest Humidity was " + lowestFile.get("Humidity") + " at " + lowestFile.get("DateUTC"));
    }
}
