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
     * @param   fr      FileResource representing file of births
     * @param   mode    int representing wheither to total all genders (0),
     *                  just girls (1), or just boys (2).
     * @return          int representing the total number of births in the file
     */
    public int totalBirths(FileResource fr, int mode)
    {
        int totalBirths = 0;
        for (CSVRecord rec: fr.getCSVParser(false)) {
            if (mode == 0) {
                totalBirths += Integer.parseInt(rec.get(2));
            } else {
                if (mode == 1 && rec.get(1).equals("F")) {
                    totalBirths += Integer.parseInt(rec.get(2));
                } else if (mode == 2 && rec.get(1).equals("M")) {
                    totalBirths += Integer.parseInt(rec.get(2));
                }
            }
        }
        return totalBirths;
    }

    /**
     * Returns the rank of the given name for the given gender in the given file.
     *
     * @param   fr      FileResource representing file of births
     * @param   name    String representing name to match
     * @param   gender  String representing gender to match
     * @return          int representing the rank of the given name in the file
     */
    public int getRank(FileResource fr, String name, String gender)
    {
        int rank = 1;
        for (CSVRecord rec: fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                if (rec.get(0).equals(name)) {
                    return rank;
                } else {
                    rank++;
                }
            }
        }
        return -1;
    }

    /**
     * Returns the name of the given rank of the given gender in the given file.
     *
     * @param   fr      FileResource representing file of births
     * @param   rank    int representing rank to match
     * @param   gender  String representing gender to search
     * @return          String representing the name at the given rank for the given gender
     */
    public String getName(FileResource fr, int rank, String gender)
    {
        int tmpRank = 1;
        for (CSVRecord rec: fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                if (tmpRank == rank) {
                    return rec.get(0);
                } else {
                    tmpRank++;
                }
            }
        }
        return "NO NAME";
    }   
    
    /**
     * Returns the name from fr2 that has the same rank as the given name in fr1.
     *
     * @param   fr1     FileResource representing file of births for initial name
     * @param   fr2     FileResource representing file of births for year to find match
     * @param   name    String representing name to match
     * @param   gender  String representing gender to match
     * @return          String representing the name from fr2 with the same rank as the given name 
     *                  in fr1.
     */
    public String whatIsNameInYear(FileResource fr1, FileResource fr2, String name, String gender)
    {
        int rank = getRank(fr1, name, gender);
        if (rank == -1){
            return "NO NAME";
        }
        return getName(fr2, rank, gender);
    }
    
    /**
     * This method selects a range of files to process and returns an integer, the year with the 
     * highest rank for the name and gender.
     *
     * @param   itr     File iterable representing files to check
     * @param   name    String representing name to match
     * @param   gender  String representing gender to match
     * @return          int representing the year with the highest rank for the given name & gender
     */
    public int yearOfHighestRank(Iterable<File> itr, String name, String gender)
    {
        int highestRankYear = -1;
        int highestRank = Integer.MAX_VALUE;
        for (File f : itr) {
            int thisyear = Integer.parseInt(f.getName().substring(3,7));
            int thisrank = getRank(new FileResource(f), name, gender);
            if (thisrank != -1 && thisrank < highestRank){
                highestRankYear = thisyear;
                highestRank = thisrank;
            }
        }        
        return highestRankYear;
    } 
    
    /**
     * This method selects a range of files to process and returns a double representing the average
     * rank of the name and gender over the selected files.
     *
     * @param   itr     File iterable representing files to check
     * @param   name    String representing name to match
     * @param   gender  String representing gender to match
     * @return          double representing the average rank for given name and gender
     */
    public double getAverageRank(Iterable<File> itr, String name, String gender)
    {
        double avgRank = 0.0;
        int numFiles = 0;
        for (File f : itr) {
            int thisrank = getRank(new FileResource(f), name, gender);
            avgRank += thisrank;
            numFiles++;
        }
        return avgRank / numFiles;
    } 
    
    /**
     * This method returns an integer, the total number of births of those names with the same 
     * gender and same year who are ranked higher than name.
     * 
     * Note: if the year exists in files and the name does not exist for that gender in the file,
     * it returns the total number of children born that year with the same gender.
     *
     * @param   year    int representing year to match
     * @param   name    String representing name to match
     * @param   gender  String representing gender to match
     * @return          int representing the the total number of births
     * 
     */
    public int getTotalBirthsRankedHigher(Iterable<File> itr, int year, String name, String gender)
    {
        int totalBirthsRankedHigher = 0;
        for (File f : itr) {
            int thisyear = Integer.parseInt(f.getName().substring(3,7));
            FileResource fr = new FileResource(f);
            if (thisyear == year) {
                
                for (CSVRecord rec: fr.getCSVParser(false)) {
                    if (rec.get(1).equals(gender)) {
                        if (rec.get(0).equals(name)) {
                            return totalBirthsRankedHigher;
                        } else {
                            totalBirthsRankedHigher += Integer.parseInt(rec.get(2));
                        }
                    }
                }
                
            }
        }
        return totalBirthsRankedHigher;
    } 
    
    
    
    // === Testing Methods ===
    
    /**
     * Tests the method totalBirths.
     */    
    public void testTotalBirths() {
        FileResource fr = new FileResource("csv/us_babynames_test/example-small.csv");
        System.out.println("\n Testing totalBirths()");
        // test all genders total
        int test = totalBirths(fr, 0);
        int expected = 1700;
        System.out.println("expected: " + expected );
        System.out.println("actual: " + test );
        System.out.println("test passed? = " + (test == expected));    
        // test girls total
        test = totalBirths(fr, 1);
        expected = 1500;
        System.out.println("expected: " + expected );
        System.out.println("actual: " + test );
        System.out.println("test passed? = " + (test == expected));    
        // test boys total
        test = totalBirths(fr, 2);
        expected = 200;
        System.out.println("expected: " + expected );
        System.out.println("actual: " + test );
        System.out.println("test passed? = " + (test == expected));     
    }

    /**
     * Tests the method getRank.
     */    
    public void testGetRank() {
        FileResource fr = new FileResource("csv/us_babynames_test/example-small.csv");
        System.out.println("\n Testing getRank()");
        
        int test = getRank(fr, "Olivia", "F");
        int expected = 2;
        System.out.println("expected: " + expected );
        System.out.println("actual: " + test );
        System.out.println("test passed? = " + (test == expected)); 
        
        test = getRank(fr, "William", "M");
        expected = 5;
        System.out.println("expected: " + expected );
        System.out.println("actual: " + test );
        System.out.println("test passed? = " + (test == expected));    
        
        test = getRank(fr, "Jacob", "F");
        expected = -1;
        System.out.println("expected: " + expected );
        System.out.println("actual: " + test );
        System.out.println("test passed? = " + (test == expected));     
    }    
    
    /**
     * Tests the method getName.
     */    
    public void testGetName() {
        FileResource fr = new FileResource("csv/us_babynames_test/example-small.csv");
        System.out.println("\n Testing getName()");
        
        String test = getName(fr, 2, "F");
        String expected = "Olivia";
        System.out.println("expected: " + expected );
        System.out.println("actual: " + test );
        System.out.println("test passed? = " + (test.equals(expected))); 
        
        test = getName(fr, 5, "M");
        expected = "William";
        System.out.println("expected: " + expected );
        System.out.println("actual: " + test );
        System.out.println("test passed? = " + (test.equals(expected)));    
        
        test = getName(fr, 6, "F");
        expected = "NO NAME";
        System.out.println("expected: " + expected );
        System.out.println("actual: " + test );
        System.out.println("test passed? = " + (test.equals(expected)));     
    }

    /**
     * Tests the method whatIsNameInYear.
     */    
    public void testWhatIsNameInYear() {
        FileResource fr1 = new FileResource("csv/us_babynames_test/yob2012short.csv");
        FileResource fr2 = new FileResource("csv/us_babynames_test/yob2014short.csv");
        System.out.println("\n Testing whatIsNameInYear()");
        
        String test = whatIsNameInYear(fr1, fr2, "Isabella", "F");
        String expected = "Sophia";
        System.out.println("expected: " + expected );
        System.out.println("actual: " + test );
        System.out.println("test passed? = " + (test.equals(expected)));   
        
        test = whatIsNameInYear(fr1, fr2, "Ethan", "F");
        expected = "NO NAME";
        System.out.println("expected: " + expected );
        System.out.println("actual: " + test );
        System.out.println("test passed? = " + (test.equals(expected)));  
        
        test = whatIsNameInYear(fr1, fr2, "FOO", "F");
        expected = "NO NAME";
        System.out.println("expected: " + expected );
        System.out.println("actual: " + test );
        System.out.println("test passed? = " + (test.equals(expected)));  
    }

    /**
     * Tests the method yearOfHighestRank.
     */    
    public void testYearOfHighestRank() {
        System.out.println("\n Testing yearOfHighestRank()");
        System.out.println("\n With files: [\"yob2012short.csv\", \"yob2013short.csv\", \"yob2014short.csv\"]");
        
        DirectoryResource dr = new DirectoryResource();
        Iterable<File> itr = dr.selectedFiles();
        
        int test = yearOfHighestRank(itr, "Mason", "M");
        int expected = 2012;
        System.out.println("expected: " + expected );
        System.out.println("actual: " + test );
        System.out.println("test passed? = " + (test == expected));   
        
        test = yearOfHighestRank(itr, "FooBarBaz", "M");
        expected = -1;
        System.out.println("expected: " + expected );
        System.out.println("actual: " + test );
        System.out.println("test passed? = " + (test == expected));   
        
    }

    /**
     * Tests the method getAverageRank.
     */    
    public void testGetAverageRank() {
        System.out.println("\n Testing getAverageRank()");
        System.out.println("\n With files: [\"yob2012short.csv\", \"yob2013short.csv\", \"yob2014short.csv\"]");
        
        DirectoryResource dr = new DirectoryResource();
        Iterable<File> itr = dr.selectedFiles();
        
        double test = getAverageRank(itr, "Mason", "M");
        double expected = 3.0;
        System.out.println("expected: " + expected );
        System.out.println("actual: " + test );
        System.out.println("test passed? = " + (Math.abs(Math.abs(test)-Math.abs(expected)) < 0.00001));   
        
        test = getAverageRank(itr, "Jacob", "M");
        expected = 2.6666666;
        System.out.println("expected: " + expected );
        System.out.println("actual: " + test );
        System.out.println("test passed? = " + (Math.abs(Math.abs(test)-Math.abs(expected)) < 0.00001));
        
        test = getAverageRank(itr, "FooBarBaz", "M");
        expected = -1.0;
        System.out.println("expected: " + expected );
        System.out.println("actual: " + test );
        System.out.println("test passed? = " + (Math.abs(Math.abs(test)-Math.abs(expected)) < 0.00001));
        
    }

    /**
     * Tests the method getTotalBirthsRankedHigher.
     */    
    public void testGetTotalBirthsRankedHigher() {
        System.out.println("\n Testing getTotalBirthsRankedHigher()");
        System.out.println("\n With files: [\"yob2012short.csv\", \"yob2013short.csv\", \"yob2014short.csv\"]");
        
        DirectoryResource dr = new DirectoryResource();
        Iterable<File> itr = dr.selectedFiles();
        
        int test = getTotalBirthsRankedHigher(itr, 2012, "Ethan", "M");
        int expected = 15;
        System.out.println("expected: " + expected );
        System.out.println("actual: " + test );
        System.out.println("test passed? = " + (test == expected));   
        
        test = getTotalBirthsRankedHigher(itr, 2010, "Ethan", "M");
        expected = 0;
        System.out.println("expected: " + expected );
        System.out.println("actual: " + test );
        System.out.println("test passed? = " + (test == expected));  
        
        test = getTotalBirthsRankedHigher(itr, 2013, "FooBarBaz", "M");
        expected = 44;
        System.out.println("expected: " + expected );
        System.out.println("actual: " + test );
        System.out.println("test passed? = " + (test == expected));   
        
    }
    
    public static void main (String[] args) {
        System.out.println("\n\n==== main ====");
        BabyNames bn = new BabyNames();
        bn.testTotalBirths();
        bn.testGetRank();
        bn.testGetName();
        bn.testWhatIsNameInYear();
        bn.testYearOfHighestRank();
        bn.testGetAverageRank();
        bn.testGetTotalBirthsRankedHigher();
    }
}
