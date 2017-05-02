//Gurion Marks
//Angelica Walker

import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/** My search engine. */
public final class JHUgle {

    private static AvlTreeMap<String, ArrayList<String>> map
        = new AvlTreeMap<>();

    private static Stack<ArrayList<String>> search = new Stack<>();
    private static int size = 0;
    private static ArrayList<String> urls = new ArrayList<>();
    private static ArrayList<String> one = new ArrayList<>();
    private static ArrayList<String> two = new ArrayList<>();

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
        boolean quit = false;
        Scanner kb = new Scanner(System.in);

        while (!quit) {
            System.out.print(">");

            //Instantiate Lists
            String command = kb.next();
            switch (command) {
                case "?":
                    this.printTop();
                    break;
                case "&&"
                    this.intersect();
                    break;
                case "||":

                    break;
                case "!":
                    quit = true;
                    break;
                default:
                    this.addToMap(command);
                    break;
            }
        }
    }

    private static void printTop() {
       if (this.size == 0) {
            return;
        }
        this.urls = this.search.peek();
        for (String s : this.urls) {
            System.out.println(s);
        }
    }

    private static void intersect() {
        if (this.size < 2) {
            return;
        }
        this.one = this.search.pop();
        this.two = this.search.pop();
        for (String s : this.one) {
            if (this.two.contains(s)) {
                this.urls.add(s);
            }
        }
        this.size--;
        this.search.push(this.urls);
    }

    private static void union() {
        if (this.size < 2) {
            return;
        }
        this.one = this.search.pop();
        this.two = this.search.pop();
        for (String s : this.one) {
            this.urls.add(s);
        }
        for (String s : this.two) {
            if (!this.urls.contains(s)) {
                this.urls.add(s);
            }
        }
        this.size--;
        this.search.push(this.urls);
    }

    private static void addToMap(String command) {
        if (this.map.has(command)) {
            this.urls = map.get(command);
            this.search.push(this.urls);
            this.size++;
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















