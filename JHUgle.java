//Gurion Marks
//Angelica Walker

import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Stack;

public final class JHUgle {

    /** Make checkstyle happy. */
    private JHUgle() {
    }

    private static AvlTreeMap<String, ArrayList<String>> map = new AvlTreeMap<>();
       
    /**
     * Main method.
     * @param args Command line arguments (ignored).
     * @throws IOException in the unlikely event of a loss of input pressure.
     */
    public static void main(String[] args) throws IOException {
		readInput(args[0]);
		System.out.println("Index Created");

		Stack<ArrayList<String>> search = new Stack<>();
		boolean quit = false;
		Scanner kb = new Scanner(System.in);

		while (!quit) {
			System.out.print(">");

			String command = kb.next();
			System.out.print(command);
			ArrayList<String> urls = new ArrayList<>();
		    ArrayList<String> one = new ArrayList<>();
		    ArrayList<String> two = new ArrayList<>();
		    switch(command) {
		    	case "?":
		    		urls = search.peek();
		    		for (String s : urls) {
		    			System.out.println(s);
		    		}
		    		break;
		    	case "&&":
        			one = search.pop();
        			two = search.pop();
        			for (String s : one) {
        				if (two.contains(s)) {
        					urls.add(s);
        				}
        			}
        			search.push(urls);
		    		break;
		    	case "||":
        			one = search.pop();
        			two = search.pop();
        			for (String s : one) {
        				urls.add(s);
        			}
        			for (String s : two) {
        				if (!urls.contains(s)) {
        					urls.add(s);
        				}
        			}
        			search.push(urls);
		    		break;
		    	case "!":
		    		quit = true;
		    		break;
		    	default:
		    		if (map.has(command)) {
		    			urls = map.get(command); 
		    			search.push(urls);
		    		}
		    		break;
			}
		}		
    }

    public static void readInput(String filename) throws IOException {
	    // The regular expression splits strings on whitespace, non-digit,
        // and non-letter characters (anything except 0-9, a-z, and A-Z).
        // Far from perfect, but close enough for this simple program.
        Pattern pattern = Pattern.compile("[\\s+]");

        // If you're wondering why we're not using Scanner instead, you're
        // welcome to try out what happens... :-)
        InputStream inputStream = new FileInputStream(filename);
        InputStreamReader input = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(input);

        int lineCount = 1;
        String line;
        String url = "";
        while ((line = reader.readLine()) != null) {
        	if ((lineCount % 2) != 0) {
				url = line;
			} else {
        		String[] words = pattern.split(line);
 	        	for (String word : words) {
    	    		if (map.has(word)) {
    	    			ArrayList<String> temp = map.get(word);
    	    			temp.add(url);
    	    		} else {
    	    			ArrayList<String> list = new ArrayList<>();
    	    			list.add(url);
    	    			map.insert(word, list);
    	    		}
        		}
        	}
        }
    }
}















