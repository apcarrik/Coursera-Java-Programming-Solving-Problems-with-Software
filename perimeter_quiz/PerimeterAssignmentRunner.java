package perimeter_quiz;

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
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

    public int getNumPoints (Shape s) {
        int numPts = 0;
        for (Point currPt: s.getPoints()) {
            numPts ++;
        }
        return numPts;
    }

    public double getAverageLength(Shape s) {
        return getPerimeter(s) / getNumPoints(s);
    }

    public double getLargestSide(Shape s) {
        double largest = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if (currDist > largest) {
                largest = currDist;
            }                
        }
        return largest;
    }

    public double getLargestX(Shape s) {
        double largestX = 0.0;
        for (Point currPt : s.getPoints()) {
            double currX = currPt.getX();
            if (currX > largestX) {
                largestX = currX;
            }                
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        double largestPerim = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape newShape = new Shape();
            for (String l : fr.lines()) {
                String[] coords = l.split(",");
                int xCoord = Integer.parseInt(coords[0].replaceAll("\\s",""));
                int yCoord = Integer.parseInt(coords[1].replaceAll("\\s",""));                
                newShape.addPoint(new Point(xCoord,yCoord));
            }
            double newPerim = getPerimeter(newShape);
            
            if (newPerim > largestPerim) {
                largestPerim = newPerim;
            }
            
        }
        
        return largestPerim;
    }

    public String getFileWithLargestPerimeter() {
        //// TODO: Put code here
        //File temp = null;    // replace this code
        //return temp.getName();
        
        double largestPerim = 0.0;
        String largestPerimFileName = "";
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape newShape = new Shape();
            for (String l : fr.lines()) {
                String[] coords = l.split(",");
                int xCoord = Integer.parseInt(coords[0].replaceAll("\\s",""));
                int yCoord = Integer.parseInt(coords[1].replaceAll("\\s",""));                
                newShape.addPoint(new Point(xCoord,yCoord));
            }
            double newPerim = getPerimeter(newShape);
            
            if (newPerim > largestPerim) {
                largestPerim = newPerim;
                largestPerimFileName = f.getName();
            }
            
        }
        
        return largestPerimFileName;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int numPts = getNumPoints(s);
        double avgLength = getAverageLength(s);
        double lgstSide = getLargestSide(s);
        double lgstX = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("num points = " + numPts);
        System.out.println("avg length = " + avgLength);
        System.out.println("largest side = " + lgstSide);
        System.out.println("largest X coord = " + lgstX);
    }
    
    public void testPerimeterMultipleFiles() {
        double LPMF = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter = " + LPMF);
    }

    public void testFileWithLargestPerimeter() {
        String FWLP = getFileWithLargestPerimeter();
        System.out.println("file with largest perimeter = " + FWLP);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }
    
    public void quiz () {
        FileResource fr1 = new FileResource();
        Shape s1 = new Shape(fr1);
        double length1 = getPerimeter(s1);
        System.out.println("1. perimeter = " + length1);
        
        FileResource fr2 = new FileResource();
        Shape s2 = new Shape(fr2);
        double avgLength2 = getAverageLength(s2);
        System.out.println("2.avg length = " + avgLength2);
        
        FileResource fr3 = new FileResource();
        Shape s3 = new Shape(fr3);
        double lgstSide3 = getLargestSide(s3);
        System.out.println("3.largest side = " + lgstSide3);
        
        double LPMF = getLargestPerimeterMultipleFiles();
        System.out.println("4. largest perimeter = " + LPMF);
        
        String FWLP = getFileWithLargestPerimeter();
        System.out.println("5. file with largest perimeter = " + FWLP);
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        //pr.testPerimeterMultipleFiles();
        //pr.testFileWithLargestPerimeter();
        
        // Quiz answers
        pr.quiz();
        
    }
}
