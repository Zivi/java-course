package week3;
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


public class AverageTemperature {
    public double averageTemperatureInFile(CSVParser parser) {
        double sum = 0;
        double tracker = 0;
        for (CSVRecord record:parser) {
            sum += 1;
            tracker += Double.parseDouble(record.get("TemperatureF"));
        }
        return tracker / sum;
    }
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double sum = 0;
        double tracker = 0;
        double average = 0;
        for (CSVRecord record:parser) {
            int humidity = Integer.parseInt(record.get("Humidity"));
            if (humidity > value) {
                sum += 1;
                tracker += Double.parseDouble(record.get("TemperatureF"));
            }
        }
        if (sum > 0) {
            average = tracker / sum;
        }
        return average;
    }
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        double averageTemp = averageTemperatureInFile(fr.getCSVParser());
        System.out.println(averageTemp);
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        double averageTemp = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if (averageTemp == 0) {
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average Temp with high humidity is " + averageTemp);
        }
    }
}
