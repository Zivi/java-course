package week3;
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class WeatherColdest {
    public CSVRecord coldestHourInFile(CSVParser parser) {
            CSVRecord coldestSoFar = null;
            for (CSVRecord currentRow : parser) {
                coldestSoFar = getSmallestOfTwo(currentRow, coldestSoFar);
            }
            return coldestSoFar;    
    }
    public CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord coldestSoFar) {
        if (coldestSoFar == null) {
            coldestSoFar = currentRow;
        } else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
            if (currentTemp < coldestTemp && currentTemp != -9999) {
                coldestSoFar = currentRow;
            }
        }
        return coldestSoFar;
    }
    public String fileWithColdestTemperature() {
        // return a string that's the name of the file from selected files that has the
        // coldest temp
        CSVRecord coldestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        String fileName = "";
        String fileContents = "";
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            coldestSoFar = getSmallestOfTwo(currentRow, coldestSoFar);
            if (coldestSoFar == currentRow) {
                fileName = f.getName();
                fileContents = fr.asString();
            }
        }
        String coldestDay = "Coldest day was in file " + fileName + " Coldest temperature on that day was " + coldestSoFar.get("TemperatureF")  + fileContents;
        return coldestDay;
    }
    public void testFileWithColdestTemperature() {
        String coldestFile = fileWithColdestTemperature();
        System.out.println(coldestFile);
    }
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + coldest.get("TemperatureF") +
            " at " + coldest.get("TimeEST"));
    }
}
