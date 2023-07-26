
/**
 * Write a description of class Part3 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Part3
{
    /**
     * Returns true if string a is found at least twice in string b, or false otherwise.
     *
     * @param   a   string to search for in b
     * @param   b   string to search
     * @return      true if a is found at least twice in b; false otherwise.
     */
    public boolean twoOccurrences(String a, String b) {
        int first = -1;
        if (a.length() == 0 || b.length() == 0) {
            return false;
        }
        for (int i=0; i < b.length()-a.length(); i++) {
            if (b.substring(i,i+a.length()).equals(a)) {
                first = i;
                break;
            }
        }
        for (int i = first+a.length(); i < b.length()-a.length(); i++) {
            
            if (b.substring(i,i+a.length()).equals(a)) {
                return true;
            }
        }
        
        return false;
    }
    
    public String lastPart (String a, String b) {
        int match = -1;
        if (b.length() == 0) {
            return b;
        }
        for (int i=0; i < b.length()-a.length(); i++) {
            if (b.substring(i,i+a.length()).equals(a)) {
                match = i+a.length();
                break;
            }
        }
        if (match == -1) {
            return b;
        }
        return b.substring(match);        
    }
    
    
    public void test_twoOccurrences() {
        System.out.println("\nTesting twoOccurrences()");
        int i = 1;
        String a = "a";
        String b = "banana";
        boolean expected = true;
        boolean result = twoOccurrences(a,b);
        System.out.println("\ntest"+i+"\na = " + a + ", b = " + b + ", result = " + result);
        System.out.println("test"+i+" passed? = " + !(result ^ expected));
        i++;
        
        a = "b";
        b = "banana";
        expected = false;
        result = twoOccurrences(a,b);
        System.out.println("\ntest"+i+"\na = " + a + ", b = " + b + ", result = " + result);
        System.out.println("test"+i+" passed? = " + !(result ^ expected));
        i++;
        
        a = "an";
        b = "banana";
        expected = true;
        result = twoOccurrences(a,b);
        System.out.println("\ntest"+i+"\na = " + a + ", b = " + b + ", result = " + result);
        System.out.println("test"+i+" passed? = " + !(result ^ expected));
        i++;
        
        a = "by";
        b = "A story by Abby Long";
        expected = true;
        result = twoOccurrences(a,b);
        System.out.println("\ntest"+i+"\na = " + a + ", b = " + b + ", result = " + result);
        System.out.println("test"+i+" passed? = " + !(result ^ expected));
        i++;
        
        a = "atg";
        b = "ctgtatgta";
        expected = false;
        result = twoOccurrences(a,b);
        System.out.println("\ntest"+i+"\na = " + a + ", b = " + b + ", result = " + result);
        System.out.println("test"+i+" passed? = " + !(result ^ expected));
        i++;
        
        a = "";
        b = "";
        expected = false;
        result = twoOccurrences(a,b);
        System.out.println("\ntest"+i+"\na = " + a + ", b = " + b + ", result = " + result);
        System.out.println("test"+i+" passed? = " + !(result ^ expected));
        i++;
        
        a = "";
        b = "a";
        expected = false;
        result = twoOccurrences(a,b);
        System.out.println("\ntest"+i+"\na = " + a + ", b = " + b + ", result = " + result);
        System.out.println("test"+i+" passed? = " + !(result ^ expected));
        i++;
    }
    
    public void test_lastPart() {
        System.out.println("\n\nTesting lastPart()");
        int i = 1;
        String a = "an";
        String b = "banana";
        String expected = "ana";
        String result = lastPart(a,b);
        System.out.println("\ntest"+i+"\na = " + a + ", b = " + b + ", result = " + result);
        System.out.println("test"+i+" passed? = " + result.equals(expected));
        i++;
        
        a = "zoo";
        b = "forest";
        expected = b;
        result = lastPart(a,b);
        System.out.println("\ntest"+i+"\na = " + a + ", b = " + b + ", result = " + result);
        System.out.println("test"+i+" passed? = " + result.equals(expected));
        i++;
        
        a = "";
        b = "";
        expected = b;
        result = lastPart(a,b);
        System.out.println("\ntest"+i+"\na = " + a + ", b = " + b + ", result = " + result);
        System.out.println("test"+i+" passed? = " + result.equals(expected));
        i++;
        
        a = "";
        b = "a";
        expected = b;
        result = lastPart(a,b);
        System.out.println("\ntest"+i+"\na = " + a + ", b = " + b + ", result = " + result);
        System.out.println("test"+i+" passed? = " + result.equals(expected));
        i++;
        
    }
    
    public static void main (String[] args) {
        Part3 p3 = new Part3();
        System.out.println("\n\n======\n");
        p3.test_twoOccurrences();
        p3.test_lastPart();
    }
}
