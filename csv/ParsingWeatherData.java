package csv;


/**
 * Holds my code for Week 3 - Parsing Weather Data programming excercises.
 *
 * @author apcarrik
 * @version 7-28-2023
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class ParsingWeatherData
{

    /**
     * Returns a FileResource from file opened
     *
     * @return    FileResource
     */
    public FileResource tester() {
        return new FileResource();
    }

    /**
     * Returns CSVRecord with the coldest temperature in the file.
     *
     * @param   fr      FileResource of CSV data
     * @return          CSVRecord with the coldest temp in file.
     */
    public CSVRecord coldestHourInFile(FileResource fr) {        
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldest = null;
        double coldestTemp = Double.POSITIVE_INFINITY;
        for (CSVRecord r : parser) {
            Double temp = Double.parseDouble(r.get("TemperatureF"));
            if (temp < coldestTemp && temp > -9999.0) {
                coldestTemp = temp;
                coldest = r;
            }
        }
        return coldest;
    }

    /**
     * Returns a string that is the name of the file from selected files that has the coldest temperature
     *
     * @return          String of filename with the coldest temp of all files.
     */
    public String fileWithColdestTemperature() {          
        DirectoryResource dr = new DirectoryResource();
        String coldestFilename = "";
        double coldestTemp = Double.POSITIVE_INFINITY; 
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            for (CSVRecord r : parser) {
                Double temp = Double.parseDouble(r.get("TemperatureF"));
                if (temp < coldestTemp && temp > -9999.0) {
                    coldestTemp = temp;
                    coldestFilename = f.getName();
                }
            }            
        }
        return coldestFilename;
    }    

    /**
     * Returns CSVRecord with the lowest humidity in the file.
     *
     * @param   fr      FileResource of CSV data
     * @return          CSVRecord with the lowest humidity in file.
     */
    public CSVRecord lowestHumidityInFile(FileResource fr) {        
        CSVParser parser = fr.getCSVParser();
        CSVRecord driest = null;
        double lowestHumidity = Double.POSITIVE_INFINITY;
        for (CSVRecord r : parser) {
            if (!(r.get("Humidity").equals("N/A"))) {
                Double reading = Double.parseDouble(r.get("Humidity"));
                if (reading < lowestHumidity) {
                    lowestHumidity = reading;
                    driest = r;
                }
            }
        }
        return driest;
    }

    /**
     * Returns a string that is the name of the file from selected files that has the lowest humidity.
     *
     * @return          CSVRecord with the lowest humidity hour of all files.
     */
    public CSVRecord lowestHumidityInManyFiles() {          
        DirectoryResource dr = new DirectoryResource();
        CSVRecord driest = null;
        double lowestHumidity = Double.POSITIVE_INFINITY;
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            for (CSVRecord r : parser) {
                if (!(r.get("Humidity").equals("N/A"))) {
                    Double reading = Double.parseDouble(r.get("Humidity"));
                    if (reading < lowestHumidity) {
                        lowestHumidity = reading;
                        driest = r;
                    }
                }
            }            
        }
        return driest;
    }

    /**
     * Returns double representing the average temperature of the file.
     *
     * @param   fr      FileResource of CSV data
     * @return          double representing the average temperature
     */
    public double averageTemperatureInFile(FileResource fr) {    
        CSVParser parser = fr.getCSVParser();
        double avgTemp = 0.0;
        int numReadings = 0;
        for (CSVRecord r : parser) {
            Double temp = Double.parseDouble(r.get("TemperatureF"));
            if (temp > -9999.0) {
                avgTemp += temp;
                numReadings++;
            }
        }
        return avgTemp/numReadings;
    }

    /**
     * Returns double representing the average temperature of readings with humidity greater than value in file.
     *
     * @param   fr      FileResource of CSV data
     * @param   value   Int representing humidity value to compare against
     * @return          double representing the average temperature
     */
    public double averageTemperatureWithHighHumidityInFile(FileResource fr, int value) {    
        CSVParser parser = fr.getCSVParser();
        double avgTemp = 0.0;
        int numReadings = 0;
        for (CSVRecord r : parser) {
            Double temp = Double.parseDouble(r.get("TemperatureF"));
            int humidity = Integer.parseInt(r.get("Humidity"));
            if (temp > -9999.0 && humidity > value) {
                avgTemp += temp;
                numReadings++;
            }
        }
        if (numReadings > 0) {
            return avgTemp/numReadings;
        }
        return 0.0;
    }
    
    /**
     * tests coldestHourInFile method.
     */    
    public void testColdestHourInFile(FileResource fr) {
        System.out.println("\n Testing coldestHourInFile()");  
        System.out.println("testing with \"weather-2015-01-04.csv\"");
        CSVRecord test = coldestHourInFile(fr);
        String testStr = "";
        for (int i = 0; i < test.size(); i++) {
            testStr += test.get(i) + ",";
        }
        String expected = "12:51 AM,48.9,46.9,93,30.15,0.2,Calm,Calm,-,N/A,Fog,Fog,0,2015-01-04 05:51:00,";
        System.out.println("expected: \"" + expected + "\"");
        System.out.println("actual: \"" + testStr + "\"");
        System.out.println("test passed? = " + (testStr.equals(expected)));
        
    }
    
    /**
     * tests coldestHourInFile method.
     */    
    public void testFileWithColdestTemperature() {
        System.out.println("\n Testing fileWithColdestTemperature()");
        System.out.println("testing with files: [\"weather-2013-01-01.csv\",\n\"weather-2013-01-02.csv\",\n\"weather-2013-01-03.csv\"]");
        String test = fileWithColdestTemperature();
        String testStr = "Coldest day was in " + test + "\n";
        // TODO: calculate testStr
        String expected = "weather-2014-01-03.csv"; // "Coldest day was in file weather-2014-01-03.csv\nColdest temperature on that day was 21.9\nAll the Temperatures on the coldest day were:\n2014-01-03 05:51:00: 41.0\n2014-01-03 06:51:00: 39.0\n2014-01-03 07:51:00: 35.1\n2014-01-03 08:51:00: 30.9\n2014-01-03 09:51:00: 28.0\n2014-01-03 10:51:00: 25.0\n2014-01-03 11:51:00: 24.1\n2014-01-03 12:51:00: 23.0\n2014-01-03 13:51:00: 25.0\n2014-01-03 14:51:00: 26.1\n2014-01-03 15:51:00: 28.0\n2014-01-03 16:51:00: 30.0\n2014-01-03 17:51:00: 30.9\n2014-01-03 18:51:00: 33.1\n2014-01-03 19:51:00: 33.1\n2014-01-03 20:51:00: 33.1\n2014-01-03 21:51:00: 30.9\n2014-01-03 22:51:00: 28.9\n2014-01-03 23:51:00: 28.9\n2014-01-04 00:51:00: 26.1\n2014-01-04 01:51:00: 24.1\n2014-01-04 02:51:00: 24.1\n2014-01-04 03:51:00: 23.0\n2014-01-04 04:51:00: 21.9";
        System.out.println("expected: \"" + expected + "\"");
        System.out.println("actual: \"" + test); // testStr + "\"");
        System.out.println("test passed? = " + (test.equals(expected)));
        
    }
    
    /**
     * tests lowestHumidityInManyFiles method.
     */    
    public void testLowestHumidityInFile(FileResource fr) {
        System.out.println("\n Testing lowestHumidityInManyFiles()");  
        System.out.println("testing with \"weather-2014-01-20.csv\"");
        CSVRecord test = lowestHumidityInFile(fr);
        String testStr = "Lowest Humidity was "+test.get("Humidity")+" at " + test.get("DateUTC");
        String expected = "Lowest Humidity was 24 at 2014-01-20 19:51:00";
        System.out.println("expected: \"" + expected + "\"");
        System.out.println("actual: \"" + testStr + "\"");
        System.out.println("test passed? = " + (testStr.equals(expected)));
        
    }
    
    /**
     * tests lowestHumidityInFile method.
     */    
    public void testLowestHumidityInManyFiles() {
        System.out.println("\n Testing lowestHumidityInFile()");
        System.out.println("testing with files: [\"weather-2014-01-19.csv\",\n\"weather-2014-01-20.csv\"]");
        CSVRecord test = lowestHumidityInManyFiles();
        String testStr = "Lowest Humidity was "+test.get("Humidity")+" at " + test.get("DateUTC");
        String expected = "Lowest Humidity was 24 at 2014-01-20 19:51:00";
        System.out.println("expected: \"" + expected + "\"");
        System.out.println("actual: \"" + testStr + "\"");
        System.out.println("test passed? = " + (testStr.equals(expected)));
        
    }

    /**
     * tests coldestHourInFile method.
     */    
    public void testAverageTemperatureInFile(FileResource fr) {
        System.out.println("\n Testing averageTemperatureInFile()");  
        System.out.println("testing with \"weather-2014-01-20.csv\"");
        double test = averageTemperatureInFile(fr);
        String testStr = "Average temperature in file is " + test;
        String expected = "Average temperature in file is 44.93333333333334";
        System.out.println("expected: \"" + expected + "\"");
        System.out.println("actual: \"" + testStr + "\"");
        System.out.println("test passed? = " + (testStr.equals(expected)));
        
    }

    /**
     * test 1 of coldestHourInFile method.
     */    
    public void testAverageTemperatureWithHighHumidityInFile1(FileResource fr) {
        System.out.println("\n Testing averageTemperatureWithHighHumidityInFile()");  
        System.out.println("testing with \"weather-2014-01-20.csv\" and value=80");
        double test = averageTemperatureWithHighHumidityInFile(fr, 80);
        String testStr = "Average Temp when high Humidity is " + test;
        if (test == 0.0) {
            testStr = "No temperatures with that humidity";
        }
        String expected = "No temperatures with that humidity";
        System.out.println("expected: \"" + expected + "\"");
        System.out.println("actual: \"" + testStr + "\"");
        System.out.println("test passed? = " + (testStr.equals(expected)));        
    }

    /**
     * test 2 of coldestHourInFile method.
     */    
    public void testAverageTemperatureWithHighHumidityInFile2(FileResource fr) {
        System.out.println("\n Testing averageTemperatureWithHighHumidityInFile()");  
         
        System.out.println("testing with \"weather-2014-03-20.csv\" and value=80");
        double test = averageTemperatureWithHighHumidityInFile(fr, 80);
        String testStr = "Average Temp when high Humidity is " + test;
        if (test == 0.0) {
            testStr = "No temperatures with that humidity";
        }
        String expected = "Average Temp when high Humidity is 41.78666666666667";
        System.out.println("expected: \"" + expected + "\"");
        System.out.println("actual: \"" + testStr + "\"");
        System.out.println("test passed? = " + (testStr.equals(expected)));
        
    }
    
    
    
    public static void main (String[] args) {
        System.out.println("\n\n==== main ====");
        ParsingWeatherData pwd = new ParsingWeatherData();
        
        FileResource fr = pwd.tester();
        //pwd.testColdestHourInFile(fr);
        
        //pwd.testFileWithColdestTemperature();
        
        //fr = pwd.tester();
        //pwd.testLowestHumidityInFile(fr);
        
        //pwd.testLowestHumidityInManyFiles();
           
        //fr = pwd.tester();
        //pwd.testAverageTemperatureInFile(fr);
        
        //fr = pwd.tester();
        //pwd.testAverageTemperatureWithHighHumidityInFile1(fr);
        
        //fr = pwd.tester();
        //pwd.testAverageTemperatureWithHighHumidityInFile2(fr);
        
        // Quiz
        System.out.println("\n3)");
        pwd.testColdestHourInFile(fr);
        
        System.out.println("\n4)");
        pwd.testFileWithColdestTemperature();
        
        System.out.println("\n5)");
        fr = pwd.tester();
        pwd.testLowestHumidityInFile(fr);
        
        System.out.println("\n6) & 7)");
        pwd.testLowestHumidityInManyFiles();
        
        System.out.println("\n8)");
        fr = pwd.tester();
        pwd.testAverageTemperatureInFile(fr);
        
        System.out.println("\n9)");
        fr = pwd.tester();
        pwd.testAverageTemperatureWithHighHumidityInFile1(fr);   
    
    }
}
