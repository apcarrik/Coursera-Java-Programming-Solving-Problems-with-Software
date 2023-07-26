
/**
 * Write a description of class Part2 here.
 *
 * @author apcarrik
 * @version 7/26/2023
 */
public class Part2
{
    /**
     * Returns the number of (non-overlapping) times string a occurs in string b
     *
     * @param   a   string to match in string b
     * @param   b   string which we search for a in
     * @return    the number of times a occurs in b
     */
    public int howMany(String a, String b)
    {
        int count = 0;
        for (int i = 0; i < b.length()-a.length(); i++) {
            if (b.substring(i,i+a.length()).equals(a)) {
                count++;
            }
        }
        return count;
    }
    
    public void testHowMany() {
        System.out.println("\n- Testing howMany() -");
        
        int i = 0;
        String a = "";
        String b = "";
        int expected = 0;
        int result = howMany(a,b);
        System.out.println("\ntest" + i + ", a=" + a +  ", b=" + b);
        System.out.println("result" + i + " = " + result);
        System.out.println("test" + i + " passed? = " + (result == expected));
        
        i++;
        a = "GAA";
        b = "ATGAACGAATTGAATC";
        expected = 3;
        result = howMany(a,b);
        System.out.println("\ntest" + i + ", a=" + a +  ", b=" + b);
        System.out.println("result" + i + " = " + result);
        System.out.println("test" + i + " passed? = " + (result == expected));
        
        i++;
        a = "AA";
        b = "ATAAAA";
        expected = 2;
        result = howMany(a,b);
        System.out.println("\ntest" + i + ", a=" + a +  ", b=" + b);
        System.out.println("result" + i + " = " + result);
        System.out.println("test" + i + " passed? = " + (result == expected));
        
    }
    
    public static void main(String[] args) {
        Part2 p2 = new Part2();        
        System.out.println("\n\n======\n");
        p2.testHowMany();
    }
}
