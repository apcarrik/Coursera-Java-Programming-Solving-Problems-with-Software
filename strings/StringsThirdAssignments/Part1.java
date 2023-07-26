
/**
 * Write a description of class Part1 here.
 *
 * @author apcarrik
 * @version 7-26-2023
 */

import edu.duke.StorageResource;

public class Part1
{
    /**
     * Finds and returns the first single gene found in a snippet of DNA string
     *
     * @param  dna  a string represnting a snippet of DNA
     * @return    A string representing the first gene found. 
     *  If none are found, returns empty string
     */
    public String findGene(String dna) {
        // Search over every 3 characters, checking if they match start or end tags
        dna = dna.toUpperCase();
        int start = -1;
        for (int i = 0; i <= dna.length()-3; i++) {
            // System.out.println("i="+i+" , substr=" + dna.substring(i,i+3));
            if (dna.substring(i,i+3).equals("ATG")) {
                start = i;
                break;
            }
        }
        for (int i = start+3; i <= dna.length()-3; i+=3) {
            // System.out.println("i="+i+" , substr=" + dna.substring(i,i+3) + " , start=" + start);
            if ((dna.substring(i,i+3).equals("TAA")) 
                || (dna.substring(i,i+3).equals("TAG")) 
                || (dna.substring(i,i+3).equals("ATG")) ) {
                // System.out.println("found: substring=" + dna.substring(i,i+3));
                return dna.substring(start,i+3);
            }            
        }
        return "";
    }
    
    /**
     * Finds and prints all genes found in a snippet of DNA string
     *
     * @param  dna  a string represnting a snippet of DNA
     */    
    public StorageResource getAllGenes(String dna) {
        StorageResource sr = new StorageResource();
        for (int i = 0; i <= dna.length()-3; i++) {
            if (dna.substring(i,i+3).equals("ATG")) {
                String gene = findGene(dna.substring(i));
                if (gene.length() == 0) {
                    return sr;
                }
                sr.add(gene);
                i = i + gene.length()-1;
            }
        }
        return sr;
        
    }
    public void testFindGene() {
        System.out.println("\n - Testing findGene() - ");
        
        int i = 1;
        String test = "CCATGCATGCCATGCA";
        String result = findGene(test);
        String expected = "ATGCATGCCATG";
        System.out.println("\ntest" + i + " = " + test);
        System.out.println("result" + i + " = " + result);
        System.out.println("test" + i + " passed? = " + result.equals(expected));
        
        i++;
        test = "CCATGCATGCCTAGCA";
        result = findGene(test);
        expected = "ATGCATGCCTAG";
        System.out.println("\ntest" + i + " = " + test);
        System.out.println("result" + i + " = " + result);
        System.out.println("test" + i + " passed? = " + result.equals(expected));
        
        i++;
        test = "CCATGCATGCCATGCA";
        result = findGene(test);
        expected = "ATGCATGCCATG";
        System.out.println("\ntest" + i + " = " + test);
        System.out.println("result" + i + " = " + result);
        System.out.println("test" + i + " passed? = " + result.equals(expected));
        
        i++;
        test = "CCATGCATGCCAAGCA";
        result = findGene(test);
        expected = "";
        System.out.println("\ntest" + i + " = " + test);
        System.out.println("result" + i + " = " + result);
        System.out.println("test" + i + " passed? = " + result.equals(expected));
        
        i++;
        test = "CCATCCATGCCTAGCA";
        result = findGene(test);
        expected = "";
        System.out.println("\ntest" + i + " = " + test);
        System.out.println("result" + i + " = " + result);
        System.out.println("test" + i + " passed? = " + result.equals(expected));
        
    }
    
    public void testGetAllGenes() {
        System.out.println("\n - Testing getAllGenes() - ");
        
        int i = 1;
        String test = "CCATGCATGCCATGCA";
        StorageResource sr = getAllGenes(test);
        System.out.println("\ntest" + i + " = "+ test);
        System.out.println("result = \"");
        for (String s : sr.data()) {
            System.out.println(s);
        }
        System.out.println("\"");        
        System.out.println("expected = \"\nATGCATGCCATG\n\"");
        
        i++;
        test = "CCATGCATGCCTAGCA";
        sr = getAllGenes(test);
        System.out.println("\ntest" + i + " = "+ test);
        System.out.println("result = \"");
        for (String s : sr.data()) {
            System.out.println(s);
        }
        System.out.println("\"");        
        System.out.println("expected = \"\nATGCATGCCTAG\n\"");
        
        i++;
        test = "CCATGCATGCCATGCA";
        sr = getAllGenes(test);
        System.out.println("\ntest" + i + " = "+ test);
        System.out.println("result = \"");
        for (String s : sr.data()) {
            System.out.println(s);
        }
        System.out.println("\"");        
        System.out.println("expected = \"\nATGCATGCCATG\n\"");
        
        i++;
        test = "CCATGCATGCCACCCA";
        sr = getAllGenes(test);
        System.out.println("\ntest" + i + " = "+ test);
        System.out.println("result = \"");
        for (String s : sr.data()) {
            System.out.println(s);
        }
        System.out.println("\"");        
        System.out.println("expected = \"\n\"");
        
        i++;
        test = "CCATGCATGCCTAACAATGCCCTAGAATGATGCC";
        sr = getAllGenes(test);
        System.out.println("\ntest" + i + " = "+ test);
        System.out.println("result = \"");
        for (String s : sr.data()) {
            System.out.println(s);
        }
        System.out.println("\"");        
        System.out.println("expected = \"\nATGCATGCCTAA\nATGCCCTAG\nATGATG\"");
        
    }   
    
    
    public static void main (String[] args) {
        Part1 p1 = new Part1();
        System.out.println("\n\n======\n");
        p1.testFindGene();
        p1.testGetAllGenes();
    }
}
