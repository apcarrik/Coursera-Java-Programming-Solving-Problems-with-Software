package csv;


/**
 * Holds my code for Week 3 countries exports programming excercises.
 *
 * @author apcarrik
 * @version 7-28-2023
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class ParsingExportData
{

    /**
     * Returns a CSVParser from file opened with FileResource
     *
     * @return    CSVParser
     */
    public FileResource tester() {
        return new FileResource();
    }

    /**
     * Returns a string of information about the country, or "NOT FOUND" if no information about country in CSV parser.
     *
     * @param   fr      FileResource of CSV data
     * @param   country String representing country to find information about
     * @return          String representing country information
     */
    public String countryInfo(FileResource fr, String country) {
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            if (country.equals(record.get("Country"))) {
                return record.get("Country") + ":" + record.get("Exports") + ":" + record.get("Value (dollars)");
            }
        }
        return "NOT FOUND";
    }
    
    /**
     * Prints the names of countries that have both exportItem1 and exportItem2 in their exports.
     *
     * @param   fr          FileResource of CSV data
     * @param   exportItem1 String representing first export item to match
     * @param   exportItem2 String representing second export item to match
     * @return              String representing country information
     */
    public void listExportersTwoProducts(FileResource fr, String exportItem1, String exportItem2) {
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            if (record.get("Exports").contains(exportItem1) 
            && record.get("Exports").contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
        
    }
    
    /**
     * returns the number of countries with exportItem in their exports.
     *
     * @param   fr          FileResource of CSV data
     * @param   exportItem String representing first export item to match
     * @return              Int representing the number of countries that export exportItem
     */
    public int numberOfExporters(FileResource fr, String exportItem) {
        CSVParser parser = fr.getCSVParser();
        int matches = 0;
        for (CSVRecord record : parser) {
            if (record.get("Exports").contains(exportItem)) {
                matches++;
            }
        }
        return matches;
        
    }
    
    /**
     * prints the details of countries that have a GDP string with length larger than amount.
     *
     * @param   fr          FileResource of CSV data
     * @param   amount      String representing the dollar amount of GDP to check against
     */
    public void bigExporters(FileResource fr, String amount) {
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            if (record.get("Value (dollars)").length() > amount.length()) {
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }
    }    
    
    
    /**
     * tests countryInfo method.
     */
    public void testCountryInfo(FileResource fr) {
        System.out.println("\n Testing countryInfo()");  
        String test = countryInfo(fr, "Germany");
        String expected = "Germany:motor vehicles, machinery, chemicals:$1,547,000,000,000";
        System.out.println("expected: \"" + expected + "\"");
        System.out.println("actual: \"" + test + "\"");
        System.out.println("test passed? = " + (test.equals(expected)));
        
        test = countryInfo(fr, "United States");
        expected = "United States:corn, computers, automobiles, medicines:$1,610,000,000,000";
        System.out.println("expected: \"" + expected + "\"");
        System.out.println("actual: \"" + test + "\"");
        System.out.println("test passed? = " + (test.equals(expected)));
        
        test = countryInfo(fr, "foobar");
        expected = "NOT FOUND";
        System.out.println("expected: \"" + expected + "\"");
        System.out.println("actual: \"" + test + "\"");
        System.out.println("test passed? = " + (test.equals(expected)));
    }
    
    
    /**
     * tests listExportersTwoProducts method.
     */
    public void testListExportersTwoProducts(FileResource fr) {
        System.out.println("\n Testing listExportersTwoProducts()");  
        String expected = "Nambia\nSouth Africa";
        System.out.println("expected: \"\n" + expected + "\n\"");
        System.out.println("actual: \"");
        listExportersTwoProducts(fr, "gold", "diamonds");
        System.out.println("\"");
        
        expected = "";
        System.out.println("expected: \"\n" + expected + "\n\"");
        System.out.println("actual: \"");
        listExportersTwoProducts(fr, "foo", "bar");
        System.out.println("\"");
    }
    
    /**
     * tests numberOfExporters method.
     */
    public void testNumberOfExporters(FileResource fr) {
        System.out.println("\n Testing numberOfExporters()");  
        int test = numberOfExporters(fr, "gold");
        int expected = 3;
        System.out.println("expected: \"" + expected + "\"");
        System.out.println("actual: \"" + test + "\"");
        System.out.println("test passed? = " + (test == expected));
        
        
        test = numberOfExporters(fr, "tobacco");
        expected = 1;
        System.out.println("expected: \"" + expected + "\"");
        System.out.println("actual: \"" + test + "\"");
        System.out.println("test passed? = " + (test == expected));
        
        
        test = numberOfExporters(fr, "foobar");
        expected = 0;
        System.out.println("expected: \"" + expected + "\"");
        System.out.println("actual: \"" + test + "\"");
        System.out.println("test passed? = " + (test == expected));
    }
    
    
    /**
     * tests bigExporters method.
     */
    public void testBigExporters(FileResource fr) {
        System.out.println("\n Testing bigExporters()");  
        String expected = "Germany $1,547,000,000,000\nMacedonia $3,421,000,000\nMalawi $1,332,000,000\nMalaysia $231,300,000,000\nNamibia $4,597,000,000\nPeru $36,430,000,000\nSouth Africa $97,900,000,000\nUnited States $1,610,000,000,000";
        System.out.println("expected: \"\n" + expected + "\n\"");
        System.out.println("actual: \"");
        bigExporters(fr, "$999,999,999");
        System.out.println("\"");
        
        expected = "";
        System.out.println("expected: \"\n" + expected + "\n\"");
        System.out.println("actual: \"");
        bigExporters(fr, "$1,610,000,000,000");
        System.out.println("\"");
    }
    
    
    /**
     * gets answers for quiz.
     */
    public void quiz(FileResource fr) {
        System.out.println("\n Running program for quiz answers"); 
        
        System.out.println("\nCountries that export both Gold and diamonds:");
        listExportersTwoProducts(fr, "gold", "diamonds");
        
        System.out.println("\nNumber of countries that export sugar = " + numberOfExporters(fr, "sugar"));
        
        System.out.println("\nInfo on Nauru:");
        System.out.println(countryInfo(fr, "Nauru"));
        
        System.out.println("\nCountries with GDP >= 1 trillion dollars:");
        bigExporters(fr, "$999,999,999");
        
        
        
        
    }
    
    public static void main (String[] args) {
        System.out.println("==== main ====");
        ParsingExportData ped = new ParsingExportData();
        FileResource fr = ped.tester();
        
        ped.testCountryInfo(fr);
        ped.testListExportersTwoProducts(fr);
        ped.testNumberOfExporters(fr);
        ped.testBigExporters(fr);
        ped.quiz(fr);
    }

}
