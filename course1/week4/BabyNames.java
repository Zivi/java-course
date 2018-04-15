package week4;

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class BabyNames {
    public void readOneFile(int year) {
        String fname = "date/yob" + year + ".txt";
        FileResource fr = new FileResource(fname);
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord rec : parser) {
            // there's no header row so need to create our own headers
            String name = rec.get(0);
            String gender = rec.get(1);
        }
    }
}
