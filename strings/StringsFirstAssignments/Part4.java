
/**
 * Write a description of class Part4 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import edu.duke.URLResource;
public class Part4
{
    /**
     * Reads the lines from url and prints each URL that is a youtube.com link.
     *
     * @param   url     string representing URL to visit to search for youtube links.
     * @return  void
     */
    public void findWebLinks(String url)
    {
        // todo: use URLResource to read URL page line by line and print all youtube links
         URLResource ur = new URLResource(url);
         for (String w : ur.words()) {
            if (w.toLowerCase().contains("www.youtube.com")){                
                int first = -1;
                for (int i=0; i < w.length()-1; i++) {
                    if (w.substring(i,i+1).equals("\"")) {
                        first = i+1;
                        break;
                    }
                }
                for (int i = first; i < w.length()-1; i++) {
                    if (w.substring(i,i+1).equals("\"")) {
                        System.out.println(w.substring(first,i));
                        break;
                    }
                }
            }
         }
        return;
    }
    
    public static void main (String[] args) {
        Part4 p4 = new Part4();
        System.out.println("\n\n======\n");
        p4.findWebLinks("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
    }
}
