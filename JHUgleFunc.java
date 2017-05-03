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
public final class JHUgleFunc {

    private static QPHashMap<String, ArrayList<String>> map
        = new QPHashMap<>();

    private static Stack<ArrayList<String>> search = new Stack<>();
    private static ArrayList<String> urls = new ArrayList<>();
    private static ArrayList<String> one = new ArrayList<>();
    private static ArrayList<String> two = new ArrayList<>();
    private static int size;

    /** Shut it checkstyle. */
    private JHUgleFunc() {}

    private static void printTop() {
        if (size == 0) {
            System.err.println("Empty query.");
            return;
        }
        urls = search.peek();
        for (String s : urls) {
            System.out.println(s);
        }
    }

    private static void intersect() {
        if (size < 2) {
            System.err.println("Not enough entries to AND.");
            return;
        }
        one = search.pop();
        two = search.pop();
        ArrayList<String> temp = new ArrayList<>();
        for (String s : one) {
            if (two.contains(s)) {
                temp.add(s);
            }
        }
        urls = temp;
        search.push(urls);
        size--;
    }

    private static void union() {
        if (size < 2) {
            System.err.println("Not enough entries to OR.");
            return;
        }
        one = search.pop();
        two = search.pop();
        ArrayList<String> temp = new ArrayList<>();

        for (String s : one) {
            temp.add(s);
        }

        for (String s : two) {
            if (!temp.contains(s)) {
                temp.add(s);
            }
        }

        for (String s : temp) {
            if (!urls.contains(s)) {
                urls.add(s);
            }
        }
        size--;
        search.push(urls);
    }

    private static void addToMap(String command) {
        if (map.has(command)) {
            urls = map.get(command);
            search.push(urls);
            size++;
        } else {
            System.err.printf("Your keyword, \"%s\", was not", command);
            System.err.print("found in our database.\n");
        }
    }

    /**
     * Main method.
     * @param args Command line arguments (ignored).
     * @throws IOException in the unlikely event of a loss of input pressure.
     */
    public static void main(String[] args) throws IOException {
        //read input
        readInput(args[0]);
        System.out.println("Index Created");

        boolean quit = false;
        Scanner kb = new Scanner(System.in);

        while (!quit) {
            System.out.print(">");

            String command = kb.next();
            switch (command) {
                case "?":
                    printTop();
                    break;
                case "&&":
                    intersect();
                    break;
                case "||":
                    union();
                    break;
                case "!":
                    quit = true;
                    break;
                default:
                    addToMap(command);
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
