package week3;

import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        //for each row in the CSV File
        for (CSVRecord record:parser) {
            String export = record.get("Exports");
            if (export.contains(exportOfInterest)) {
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }
    public void countryInfo(CSVParser parser, String country) {
        boolean countryFound = false;
        for (CSVRecord record: parser) {
            String export = record.get("Country");
            if (export.contains(country)){
                System.out.print(record.get("Country") + ": ");
                System.out.print(record.get("Exports"));
                System.out.print(" " + record.get("Value (dollars)"));
                countryFound = true;
            }
        }
        if (!countryFound) {
            System.out.println("NOT FOUND");
        }
    }
    public void listExportsTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record: parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem1) && export.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }
    public void numberOfExporters(CSVParser parser, String exportItem) {
        int numCountries = 0;
        for (CSVRecord record: parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem)) {
                numCountries += 1;
            }
        }
        System.out.println("Number of countries exporting " + exportItem + ": " + numCountries);
    }
    public void bigExporters(CSVParser parser, String amount) {
        long minAmount = convString(amount);
        for (CSVRecord record: parser) {
            String recValue = record.get("Value (dollars)");
            long recValInt = convString(recValue);
            if (minAmount < recValInt) {
                System.out.print(record.get("Country") + " ");
                System.out.println(recValue);
            }
        }
    }
    public long convString(String amount) {
        amount = amount.substring(1);
        String newAmount = "";
        int commaIdx = 0;
        int startingIndex = 0;
        // Remove the commas from the amount to convert to integer
        while (commaIdx < amount.length()) {
            commaIdx = amount.indexOf(",", startingIndex);
            if (commaIdx == -1) {
                if (startingIndex < amount.length()-1) {
                    newAmount += amount.substring(startingIndex, amount.length());
                 }
                break;
            }
            newAmount += amount.substring(startingIndex, commaIdx);
            startingIndex = commaIdx + 1;
        }
        return Long.parseLong(newAmount);
    }
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //countryInfo(parser, "Nauru");
        //listExportsTwoProducts(parser, "cotton", "flowers");
        //numberOfExporters(parser, "cocoa");
        bigExporters(parser, "$999,999,999,999");
    }
}
