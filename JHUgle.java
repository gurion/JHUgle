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
		//read input
		readInput(args[0]);
		System.out.println("Index Created");

		Stack<ArrayList<String>> search = new Stack<>();
		boolean quit = false;
		Scanner kb = new Scanner(System.in);

		while (!quit) {
			System.out.print(">");

			//Instantiate Lists
			String command = kb.next();
			ArrayList<String> urls = new ArrayList<>();
		    ArrayList<String> one = new ArrayList<>();
		    ArrayList<String> two = new ArrayList<>();

		    switch(command) {
		    	//Print top of stack
		    	case "?":
		    		//TO DO:
		    		//HANDLE EMPTY STACK
		    		urls = search.peek();
		    		for (String s : urls) {
		    			System.out.println(s);
		    		}
		    		break;
		    	//intersect top two elements of stack and push result
		    	case "&&":
		    		//TO DO:
		    		//HANDLE EMPTY STACK
        			one = search.pop();
        			two = search.pop();
        			for (String s : one) {
        				if (two.contains(s)) {
        					urls.add(s);
        				}
        			}
        			search.push(urls);
		    		break;
		    	//union top two elements of stack and push result
		    	case "||":
		    		//TO DO:
		    		//HANDLE EMPTY STACK		    	
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
		    	//quit
		    	case "!":
		    		quit = true;
		    		break;
		    	//push a new keyword's url list
		    	default:
		    		if (map.has(command)) {
		    			urls = map.get(command); 
		    			search.push(urls);
		    		}
		    		break;
			}
		}		
    }

    //read the input file, fill map
    private static void readInput(String filename) throws IOException {
    	//set up readers
        FileReader file = new FileReader(filename);
        BufferedReader reader = new BufferedReader(file);

        String line;
        String url;
        //keep track of urls and lines at the same time
        while ((url = reader.readLine()) != null) {
        	line = reader.readLine();

    		String[] words = line.split("\\s+");
        	for (String word : words) {
        		//append or add key
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















