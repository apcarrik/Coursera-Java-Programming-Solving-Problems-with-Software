package csv;


/**
 * Contains my solution to the Baby Names Miniproject.
 *
 * @author apcarrik
 * @version 8-18-2023
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class BabyNames
{

    /**
     * Prints the number of total births for a given year of baby births.
     *
     * @param  fr   FileResource representing file of births
     * @return      int representing the total number of births in the file
     */
    public int totalBirths(FileResource fr)
    {
        int totalBirths = 0;
        for (CSVRecord rec: fr.getCSVParser(false)) {
            totalBirths += Integer.parseInt(rec.get(2));
        }
        return totalBirths;
    }

    /**
     * Tests the method totalBirths.
     */    
    public void testTotalBirths() {
        FileResource fr = new FileResource("csv/us_babynames_test/example-small.csv");
        System.out.println("\n Testing totalBirths()");  
        int test = totalBirths(fr);
        int expected = 1700;
        System.out.println("expected: " + expected );
        System.out.println("actual: " + test );
        System.out.println("test passed? = " + (test == expected));        
    }
    
    
    public static void main (String[] args) {
        System.out.println("\n\n==== main ====");
        BabyNames bn = new BabyNames();
        bn.testTotalBirths();
    }
}
