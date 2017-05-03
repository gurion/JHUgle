/*
Gurion Marks
gmarks2
gurion@jhu.edu
Angelica Walker
awalke57
awalke57@jhu.edu
600.226.02
05/03/17
Assignment 9
*/

import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/** My search engine. */
public final class JHUgle {

    private static ChainHashMap<String, ArrayList<String>> map
        = new ChainHashMap<>();

    /** Shut it checkstyle. */
    private JHUgle() {}

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
        int size = 0;
        boolean quit = false;
        Scanner kb = new Scanner(System.in);

        while (!quit) {
            System.out.print(">");

            //Instantiate Lists
            String command = kb.next();
            ArrayList<String> urls = new ArrayList<>();
            ArrayList<String> one = new ArrayList<>();
            ArrayList<String> two = new ArrayList<>();

            switch (command) {
                case "?":
                    if (size == 0) {
                        break;
                    }
                    urls = search.peek();
                    for (String s : urls) {
                        System.out.println(s);
                    }
                    break;
                case "&&":
                    if (size < 2) {
                        break;
                    }
                    one = search.pop();
                    two = search.pop();
                    for (String s : one) {
                        if (two.contains(s)) {
                            urls.add(s);
                        }
                    }
                    size--;
                    search.push(urls);
                    break;
                case "||":
                    if (size < 2) {
                        break;
                    }
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
                    size--;
                    search.push(urls);
                    break;
                case "!":
                    quit = true;
                    break;
                default:
                    if (map.has(command)) {
                        urls = map.get(command);
                        search.push(urls);
                        size++;
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
