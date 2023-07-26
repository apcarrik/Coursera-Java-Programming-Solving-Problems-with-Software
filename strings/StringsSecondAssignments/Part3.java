
/**
 * Write a description of class Part3 here.
 *
 * @author apcarrik
 * @version 7/26/2023
 */
public class Part3
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
    public void printAllGenes(String dna) {
        for (int i = 0; i <= dna.length()-3; i++) {
            if (dna.substring(i,i+3).equals("ATG")) {
                String gene = findGene(dna.substring(i));
                if (gene.length() == 0) {
                    return;
                }
                System.out.println(gene);
                i = i + gene.length()-1;
            }
        }
        
    }
    
    /**
     * Finds the number of genes found in a snippet of DNA string
     *
     * @param  dna  a string represnting a snippet of DNA
     * @return the number of genes in the DNA snippet
     */    
    public int countGenes(String dna) {
        int count = 0;
        for (int i = 0; i <= dna.length()-3; i++) {
            if (dna.substring(i,i+3).equals("ATG")) {
                String gene = findGene(dna.substring(i));
                if (gene.length() == 0) {
                    break;
                }
                count++;
                i = i + gene.length()-1;
            }
        }        
        return count;
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
    
    public void testPrintAllGenes() {
        System.out.println("\n - Testing printAllGenes() - ");
        
        int i = 1;
        String test = "CCATGCATGCCATGCA";
        System.out.println("\ntest" + i + " = "+ test);
        System.out.println("result = \"");
        printAllGenes(test);
        System.out.println("\"");        
        System.out.println("expected = \"\nATGCATGCCATG\n\"");
        
        i++;
        test = "CCATGCATGCCTAGCA";
        System.out.println("\ntest" + i + " = "+ test);
        System.out.println("result = \"");
        printAllGenes(test);
        System.out.println("\"");        
        System.out.println("expected = \"\nATGCATGCCTAG\n\"");
        
        i++;
        test = "CCATGCATGCCATGCA";
        System.out.println("\ntest" + i + " = "+ test);
        System.out.println("result = \"");
        printAllGenes(test);
        System.out.println("\"");        
        System.out.println("expected = \"\nATGCATGCCATG\n\"");
        
        i++;
        test = "CCATGCATGCCACCCA";
        System.out.println("\ntest" + i + " = "+ test);
        System.out.println("result = \"");
        printAllGenes(test);
        System.out.println("\"");        
        System.out.println("expected = \"\n\"");
        
        i++;
        test = "CCATGCATGCCTAACAATGCCCTAGAATGATGCC";
        System.out.println("\ntest" + i + " = "+ test);
        System.out.println("result = \"");
        printAllGenes(test);
        System.out.println("\"");        
        System.out.println("expected = \"\nATGCATGCCTAA\nATGCCCTAG\nATGATG\"");
        
    }   
    
    public void testCountGenes() {
        System.out.println("\n - Testing countGenes() - ");
        
        int i = 1;
        String test = "ATGTAAGATGCCCTAGT";
        int result = countGenes(test);
        int expected = 2;
        System.out.println("\ntest" + i + " = " + test);
        System.out.println("result" + i + " = " + result);
        System.out.println("test" + i + " passed? = " + (result == expected));
        
        i++;
        test = "CCATGCATGCCTAGCA";
        result = countGenes(test);
        expected = 1;
        System.out.println("\ntest" + i + " = " + test);
        System.out.println("result" + i + " = " + result);
        System.out.println("test" + i + " passed? = " + (result == expected));
        
        i++;
        test = "";
        result = countGenes(test);
        expected = 0;
        System.out.println("\ntest" + i + " = " + test);
        System.out.println("result" + i + " = " + result);
        System.out.println("test" + i + " passed? = " + (result == expected));
        
    }    
    
    public static void main (String[] args) {
        Part3 p3 = new Part3();
        System.out.println("\n\n======\n");
        p3.testFindGene();
        p3.testPrintAllGenes();
        p3.testCountGenes();
    }
}
