package perimeter;

import edu.duke.*;

public class PerimeterRunner {
    
    public double getAverageLength (Shape s) {
        return getPerimeter(s) / getNumPoints(s);
    }
    
    public double getLargestSide (Shape s) {
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
    
    public double getLargestX (Shape s) {
        double largestX = 0.0;
        for (Point currPt : s.getPoints()) {
            double currX = currPt.getX();
            if (currX > largestX) {
                largestX = currX;
            }                
        }
        return largestX;
    }
    
    public int getNumPoints (Shape s) {
        int numPts = 0;
        for (Point currPt: s.getPoints()) {
            numPts ++;
        }
        return numPts;
    }
    
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

    public static void main (String[] args) {
        PerimeterRunner pr = new PerimeterRunner();
        pr.testPerimeter();
    }
}
