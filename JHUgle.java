//Gurion Marks
//Angelica Walker

import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.regex.Pattern;

public final class JHUgle {

    /** Make checkstyle happy. */
    private JHUgle() {
    }

    private static HashMap<String, String> map = new HashMap<>();
       
    /**
     * Main method.
     * @param args Command line arguments (ignored).
     * @throws IOException in the unlikely event of a loss of input pressure.
     */
    public static void main(String[] args) throws IOException{
		readInput(args);
		System.out.println("Index Created");


		Stack<String> input = new ArrayStack<>();
		boolean quit = false;
		

		while (!quit && keys.hasNext()) {

		    input.push(keys.next());

            if (("?").equals(input.top())) {
                //print map
            }
            else if (("&&").equals(input.top())) {
		//ands the map
            }
		    else if (("||").equals(input.top())) {
			//or the map
		    }
            else if (("!").equals(input.top())) {
                quit = true;
            }
		    else {
			//search map for key
			//if search fails, print error
		    }
		}

		
    }

    public static void readInput(String filename) {
	    // The regular expression splits strings on whitespace, non-digit,
        // and non-letter characters (anything except 0-9, a-z, and A-Z).
        // Far from perfect, but close enough for this simple program.
        Pattern pattern = Pattern.compile("[\\s[^0-9a-zA-Z]]+");
        Map<String, Integer> data = new TreapMap<>();

        // If you're wondering why we're not using Scanner instead, you're
        // welcome to try out what happens... :-)
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        
		return returnMap;
    }
    
}















