import edu.duke.*;

public class PerimeterRunner {
    public double getAverageLength (Shape s) {
        return getPerimeter(s) / getNumPoints(s);
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
        System.out.println("perimeter = " + length);
        System.out.println("num points = " + numPts);
        System.out.println("avg length = " + avgLength);
    }

    public static void main (String[] args) {
        PerimeterRunner pr = new PerimeterRunner();
        pr.testPerimeter();
    }
}
