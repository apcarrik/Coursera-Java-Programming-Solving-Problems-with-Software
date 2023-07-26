
/**
 * Write a description of class Part2 here.
 *
 * @author apcarrik
 * @version 7/21/2023
 */
public class Part2
{    
    /**
     * Finds and returns the first single gene found in a snippet of DNA string
     *
     * @param  dna  a string represnting a snippet of DNA
     * @return    A string representing the first gene found. 
     *  If none are found, returns empty string
     */
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        // check if upper or lower case
        if (dna.equals(dna.toUpperCase())) {
            startCodon = startCodon.toUpperCase();
            stopCodon = stopCodon.toUpperCase();            
        } else {
            dna = dna.toLowerCase();
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase(); 
        }
        
        // Search over every 3 characters, checking if they match start or end tags
        int start = -1;
        for (int i = 0; i <= dna.length()-3; i++) {
            // System.out.println("i="+i+" , substr=" + dna.substring(i,i+3));
            if (dna.substring(i,i+3).equals(startCodon)) {
                start = i;
                break;
            }
        }
        for (int i = start+3; i <= dna.length()-3; i+=3) {
            // System.out.println("i="+i+" , substr=" + dna.substring(i,i+3) + " , start=" + start);
            if (dna.substring(i,i+3).equals(stopCodon)) {
                // System.out.println("found: substring=" + dna.substring(i,i+3));
                return dna.substring(start,i+3);
            }            
        }
        return "";
    }
    
    public String findSimpleGene(String dna) {
        if (dna.equals(dna.toUpperCase())) {
            return findSimpleGene(dna, "ATG", "TAA");
        } else {
            return findSimpleGene(dna.toLowerCase(), "atg", "taa");
        }
    }
    
    public Void testSimpleGene() {
        String test1 = "ATGCATGC";
        String result1 = findSimpleGene(test1);
        System.out.println("\ntest1 = " + test1);
        System.out.println("result1 = " + result1);
        System.out.println("test1 passed? = " + result1.equals(""));
        
        String test2 = "ATG";
        String result2 = findSimpleGene(test2);
        System.out.println("\ntest2 = " + test2);
        System.out.println("result2 = " + result2);
        System.out.println("test2 passed? = " + result2.equals(""));
        
        String test3 = "TAA";
        String result3 = findSimpleGene(test3);
        System.out.println("\ntest3 = " + test3);
        System.out.println("result3 = " + result3);
        System.out.println("test3 passed? = " + result3.equals(""));
        
        String test4 = "CCATGCCCTAACC";
        String result4 = findSimpleGene(test4);
        System.out.println("\ntest4 = " + test4);
        System.out.println("result4 = " + result4);
        System.out.println("test4 passed? = " + result4.equals("ATGCCCTAA"));
        
        String test5 = "CCATGCCCCTAACC";
        String result5 = findSimpleGene(test5);
        System.out.println("\ntest5 = " + test5);
        System.out.println("result5 = " + result5);
        System.out.println("test5 passed? = " + result5.equals(""));
        
        String test6 = "CCATGCCCCTAACCTAACC";
        String result6 = findSimpleGene(test6);
        System.out.println("\ntest6 = " + test6);
        System.out.println("result6 = " + result6);
        System.out.println("test6 passed? = " + result6.equals("ATGCCCCTAACCTAA"));
        
        String test7 = "ATGGGTTAAGTC";
        String result7 = findSimpleGene(test7);
        System.out.println("\ntest7 = " + test7);
        System.out.println("result7 = " + result7);
        System.out.println("test7 passed? = " + result7.equals("ATGGGTTAA"));
        
        String test8 = "ccatgcccctaacctaacc";
        String result8 = findSimpleGene(test8);
        System.out.println("\ntest8 = " + test8);
        System.out.println("result8 = " + result8);
        System.out.println("test8 passed? = " + result8.equals("atgcccctaacctaa"));
        
        return null;
    }
    
    public static void main (String[] args) {
        Part2 p2 = new Part2();
        System.out.println("\n\n======\n");
        p2.testSimpleGene();
    }
}
