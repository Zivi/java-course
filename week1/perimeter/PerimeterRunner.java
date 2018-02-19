import edu.duke.*;
import java.io.*;
import java.lang.String;


public class PerimeterRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }
    
    public int getNumPoints (Shape S) {
        int numPoints = 0;
        for (Point currPt : S.getPoints()) {
            numPoints = numPoints + 1;
        }
        return numPoints;
    }
    
    public double getAverageLength(double length, int points) {
        double avgLength = length / points;
        return avgLength;
    }
    
    public double getLargestSide(Shape S) {
        double largestSide = 0.0;
        Point prevPt = S.getLastPoint();
        for (Point currPt : S.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if (currDist > largestSide) {
                largestSide = currDist;
            }
        }
        return largestSide;
    }
    
    public int getLargestX(Shape S) {
        int largestX = 0;
        for (Point currPt: S.getPoints()) {
            int xVal = currPt.getX();
            if (xVal > largestX) {
                largestX = xVal;
            }
        }
        return largestX;
    }
    
    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int points = getNumPoints(s);
        double avgLength = getAverageLength(length, points);
        double largestSide = getLargestSide(s);
        int largestX = getLargestX(s);
        System.out.println("~~~~~~~~~");
        System.out.println("perimeter = " + length);
        System.out.println("points = " + points);
        System.out.println("average length = " + avgLength);
        System.out.println("largest side = " + largestSide);
        System.out.println("largest x = " + largestX);
        System.out.println("^^^^^^^^^^");
    }
    
    public void testPerimeterMultipleFiles() {
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter = " + largestPerimeter);
    }
    
    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if (length > largestPerimeter) {
                largestPerimeter = length;
            }
        }
        return largestPerimeter;
    }
    
    public void testFileWithLargestPerimeter() {
        String fileWithLargestPerimeter = getFileWithLargestPerimeter();
        System.out.println("File name with largest perimeter = " +
            fileWithLargestPerimeter);
    }
    
    public String getFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0.0;
        String fileWithLargestPerimeter = "";
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if (length > largestPerimeter) {
                largestPerimeter = length;
                fileWithLargestPerimeter = f.getName();
            }
        }
        return fileWithLargestPerimeter;
    }

    
   // public void printFileNames () {
   //     DirectoryResource dr = new DirectoryResource();
   //     for (File f : dr.selectedFiles()) {
  //          System.out.println(f.getName());
  //      }
  //  }

    public static void main (String[] args) {
        PerimeterRunner pr = new PerimeterRunner();
        //pr.testPerimeter();
        //pr.printFileNames();
        //pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
