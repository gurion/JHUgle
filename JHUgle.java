//Gurion Marks
//Angelica Walker

import java.util.Scanner;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.HashSet;
import java.util.Stack;
import java.io.IOException;

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
        HashSet<String> urls1 = null;
	Stack<HashSet<String>> output = new Stack();


        while (!quit && keys.hasNext()) {

	    System.out.println("> ");
            input = keys.next();

            if (("?").equals(input)) {

		if (output.peek().size() == 0) {
		    System.out.println("Error: empty stack");
		}
		else {
		    System.out.println(output.peek());
                }

	    }
            else if (("&&").equals(input)) {

		urls1 = output.pop();
                HashSet<String> urls2 = output.pop();
                urls1.retainAll(urls2);
                output.push(urls1);
		
            }
            else if (("||").equals(input)) {
		urls1 = output.pop();
                HashSet<String> urls2 = output.pop();
                urls1.addAll(urls2);
                output.push(urls1);

	    }
            else if (("!").equals(input)) {
                quit = true;
            }
            else {
		if (hashMap.hasKey(input)) {
		    HashSet<String> urls = hashMap.get(input);
                    output.push(urls);
                }
		else {
		    System.out.println("Error: not found");
                }
            }
        }


    }

    public static void readInput(String filename) {

	HashMap<K, V> returnMap = new HashMap<K, V>(MAXLOAD);
	Scanner inFile = new Scanner(new FileReader(filename));

	int count = 0;
        String url = "";

	while (inFile.hasNextLine()) {
	    if (count % 2 == 0) {
                url = inFile.nextLine();
            }
	    else {
		String line = inFile.nextLine();
                StringTokenizer s = new StringTokenizer(line);
                while (s.hasMoreTokens()) {
                    String word = s.nextToken();
                    if (!returnMap.hasKey(word)) {
                        returnMap.put(word, new HashSet<String>());
                    }
                    returnMap.get(word).add(url);
                }
            }
	    
            count++;
        }
	
        inFile.close();
	
	return returnMap;
    }
    
}
