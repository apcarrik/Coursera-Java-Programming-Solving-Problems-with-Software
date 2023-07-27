
/**
 * Write a description of class Part1 here.
 *
 * @author apcarrik
 * @version 7-26-2023
 */

import edu.duke.StorageResource;
import edu.duke.FileResource;

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
     * Finds and returns all genes found in a snippet of DNA string as a StorageResource
     *
     * @param  dna  a string represnting a snippet of DNA
     * @return storage resource containing all gene strings
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
    
    /**
     * Calculates the cgRatio for a DNA snippet
     *
     * @param  dna  a string represnting a snippet of DNA
     * @return float representing the cgRatio of the snippet
     */    
    public double cgRatio(String dna) {
        dna = dna.toUpperCase();
        int Cs = dna.length() - dna.replace("C", "").length();
        int Gs = dna.length() - dna.replace("G", "").length();
        return (float) (Cs+Gs)/dna.length();
    }
    
    /**
     * Processes all genes provided by StorageResource to determine information about them.
     *
     * @param  sr   StorageResource containing strings representing genes
     */    
    public void processGenes(StorageResource sr) {
        // print all genes longer than 9 characters
        String longestGene = "";
        int numLongGenes = 0;
        System.out.println("Genes longer than 9 charcters:");
        for (String s : sr.data()) {
            if (s.length() > 9) {
                numLongGenes++;
                System.out.println(s);
            }
            if (s.length() > longestGene.length()){
                longestGene = s;
            }
        }
        // print the number of genes that are longer than 9 characters
        System.out.println("Number of genes longer than 9 characters: " + numLongGenes);
        
        // print genes with cgRatio > 0.35
        int numHighCGRatio = 0;        
        System.out.println("Genes with cgRatio > 0.35:");
        for (String s : sr.data()) {
            if (cgRatio(s) > 0.35) {
                numHighCGRatio++;
                System.out.println(s);
            }
        }
        // print number of genes with cgRation > 0.35
        System.out.println("Number of genes with cgRatio > 0.35: " + numHighCGRatio);
        
        // print length of longest gene
        System.out.println("Longest Gene: " + longestGene);
    }
    
    /**
     * Processes all genes provided by StorageResource to determine information about them.
     *
     * @param  sr   StorageResource containing strings representing genes
     */    
    public void quizProcessGenes() {
        System.out.println("\n === Quiz process genes ===");
        // Get dna from file and get genes as StorageResource
        FileResource fr = new FileResource("brca1line.fa");
        System.out.println(fr);
        String dna = fr.asString().toUpperCase();
        StorageResource sr = getAllGenes(dna);
        
        // print all genes longer than 9 characters
        String longestGene = "";
        int numLongGenes = 0;
        System.out.println("Genes longer than 60 charcters:");
        for (String s : sr.data()) {
            if (s.length() > 60) {
                numLongGenes++;
                System.out.println(s);
            }
            if (s.length() > longestGene.length()){
                longestGene = s;
            }
        }
        // print the number of genes that are longer than 9 characters
        System.out.println("Number of genes longer than 9 characters: " + numLongGenes);
        
        // print genes with cgRatio > 0.35
        int numHighCGRatio = 0;        
        System.out.println("Genes with cgRatio > 0.35:");
        for (String s : sr.data()) {
            if (cgRatio(s) > 0.35) {
                numHighCGRatio++;
                System.out.println(s);
            }
        }
        // print number of genes with cgRation > 0.35
        System.out.println("Number of genes with cgRatio > 0.35: " + numHighCGRatio);
        
        // print length of longest gene
        System.out.println("Longest Gene: " + longestGene);        
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
    
    public void testCgRatio() {
        System.out.println("\n - Testing cgRatio() - ");
        
        int i = 0;
        String test = "ATGCCATAG";
        double cgr = cgRatio(test);
        double expected = 4.0/9.0;
        System.out.println("\ntest" + i + " = "+ test);
        System.out.println("result = " + cgr + ", expected = " + expected);
        System.out.println("test passed? = " + (Math.abs(expected-cgr) <= 0.000001));
        
        
    }
    
    public void testProcessGenes() {
        System.out.println("\n - Testing processGenes() - ");
        int i = 0;
        String test = "CCATGCATGCCTAACAATGCCCTAGAATGATGCC";
        StorageResource sr = getAllGenes(test);        
        System.out.println("\ntest" + i + " = "+ test);
        System.out.println("result = \"");
        processGenes(sr);
        System.out.println("\"");        
        System.out.println("expected = \"");
        System.out.println("Genes longer than 9 charcters:");
        System.out.println("ATGCATGCCTAA");
        System.out.println("Number of genes longer than 9 characters: 1");
        System.out.println("Genes with cgRatio > 0.35:");
        System.out.println("ATGCATGCCTAA\nATGCCCTAG");
        System.out.println("Number of genes with cgRatio > 0.35: 2");
        System.out.println("Longest Gene: ATGCATGCCTAA" );
        System.out.println("\""); 
        
    }
    
    public static void main (String[] args) {
        Part1 p1 = new Part1();
        System.out.println("\n\n======\n");
        p1.testFindGene();
        p1.testGetAllGenes();
        p1.testCgRatio();
        p1.testProcessGenes();
        p1.quizProcessGenes();
    }
}
