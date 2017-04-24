//Gurion Marks
//Angelica Walker

import java.util.Scanner;
import java.io.FileReader;

public final class JHUgle {

    private static final float MAXLOAD = 0.8;

    /** Make checkstyle happy. */
    private JHUgle() {
    }

    static Scanner keys = new Scanner(System.in);

    public static void main(String[] args) {
	HashMap<K, V> inputMap = readMap(args);
	System.out.println("Index Created");

	String input = "";
	boolean quit = false;
	

	while (!quit && keys.hasNext()) {

	    input = keys.next();

            if (("?").equals(input)) {
                //print map
            }
            else if (("&&").equals(input)) {
		//ands the map
            }
	    else if (("||").equals(input)) {
		//or the map
	    }
            else if (("!").equals(input)) {
                quit = true;
            }
	    else {
		//search map for key
		//if search fails, print error
	    }
	}

	
    }

    public static HashMap<K, V> readMap(String filename) {
	HashMap<K, V> returnMap = new HashMap<K, V>(MAXLOAD);
	Scanner inFile = new Scanner(new FileReader(filename));

	return returnMap;
    }
    
}
