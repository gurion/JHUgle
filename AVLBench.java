//Gurion Marks
//gmarks2

import com.github.phf.jb.Bench;
import com.github.phf.jb.Bee;

import java.util.Random;

/**
 * Compare performance of ArrayMap String, and ListMap.String,
 *
 * Sadly the code here is a tad bit messy since there's no elegant way to
 * instantiate and initialize a number of different set implementations.
 * Your benchmarking code for two classes will also be a bit messy. :-/
 */
public final class AVLBench {
    private static final int SIZE = 300; // you may need to tweak this...
    private static final Random RAND = new Random();

    private AVLBench() {}

    // First some basic "compound operations" to benchmark. Note that each
    // of these is carefully dimensioned (regarding the range of elements)
    // to allow combining them.

    // Insert a number of "consecutive" strings into the given set.
    /**
    * Javadoc Checkstyle.
    * @param m Map.
    */
    private static void insertLinear(Map<Integer, Integer> m) {
        for (int i = 0; i < SIZE; i++) {
            m.insert(i, i);
        }
    }

    /**
    * Javadoc Checkstyle.
    * @param ra array.
    */
    private static void fillArray(int[] ra) {
        for (int i = 0; i < SIZE; i++) {
            ra[i] = i;
        }
    }

    /**
    * Javadoc Checkstyle.
    * @param ra array.
    */
    private static void makeRandomArray(int[] ra) {
        for (int i = 0; i < SIZE; i++) {
            int temp = ra[i];
            int pos = RAND.nextInt(SIZE);
            ra[i] = ra[pos];
            ra[pos] = temp;
        }
    }

    // Insert a number of "random" strings into the given set.
    /**
    * Javadoc Checkstyle.
    * @param ra array.
    * @param m map.
    */
    private static void insertRandom(Map<Integer, Integer> m, int[] rand) {
        for (int i = 0; i < SIZE; i++) {
            m.insert(rand[i], i);
        }
    }

    // Remove a number of "random" strings from the given set.
    /**
    * Javadoc Checkstyle.
    * @param ra array.
    * @param m map.
    */
    private static void removeRandom(Map<Integer, Integer> m, int[] rand) {
        for (int i = 0; i < SIZE; i++) {
            m.remove(rand[i]);
        }
    }
    /**
    * Javadoc Checkstyle.
    * @param m Map.
    */
    private static void removeLinear(Map<Integer, Integer> m) {
        for (int i = 0; i < SIZE; i++) {
            m.remove(i);
        }
    }

    // Lookup a number of "consecutive" strings in the given set.
    /**
    * Javadoc Checkstyle.
    * @param m Map.
    */
    private static void lookupLinear(Map<Integer, Integer> m) {
        for (int i = 0; i < SIZE; i++) {
            boolean x = m.has(i);
        }
    }

    // Lookup a number of "random" strings in the given set.
    /**
    * Javadoc Checkstyle.
    * @param ra array.
    * @param m map.
    */    
    private static void lookupRandom(Map<Integer, Integer> m, int[] rand) {
        for (int i = 0; i < SIZE; i++) {
            boolean x = m.has(rand[i]);
        }
    }

    /**
    * Javadoc Checkstyle.
    * @param m Map.
    */
    private static void getLinear(Map<Integer, Integer> m) {
        for (int i = 0; i < SIZE; i++) {
            int x = m.get(i);
        }
    }

    /**
    * Javadoc Checkstyle.
    * @param ra array.
    * @param m map.
    */
    private static void getRandom(Map<Integer, Integer> m, int[] rand) {
        for (int i = 0; i < SIZE; i++) {
            int x = m.get(rand[i]);
        }
    }

    // Now the benchmarks we actually want to run.

    @Bench
    public static void insertLinearAVL(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Map<Integer, Integer> m = new AvlTreeMap<>();
            b.start();
            insertLinear(m);
        }
    }

    @Bench
    public static void insertRandomAVL(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Map<Integer, Integer> m = new AvlTreeMap<>();
            int[] random = new int[SIZE];
            fillArray(random);
            makeRandomArray(random);
            b.start();
            insertRandom(m, random);
        }
    }

    @Bench
    public static void removeLinearAVL(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Map<Integer, Integer> m = new AvlTreeMap<>();
            int[] random = new int[SIZE];
            fillArray(random);
            makeRandomArray(random);
            insertRandom(m, random);
            makeRandomArray(random);
            b.start();
            removeLinear(m);
        }
    }

    @Bench
    public static void removeRandomAVL(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Map<Integer, Integer> m = new AvlTreeMap<>();
            int[] random = new int[SIZE];
            fillArray(random);
            makeRandomArray(random);
            insertRandom(m, random);
            makeRandomArray(random);
            b.start();
            removeRandom(m, random);
        }
    }

    @Bench
    public static void lookupLinearAVL(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Map<Integer, Integer> m = new AvlTreeMap<>();
            int[] random = new int[SIZE];
            fillArray(random);
            makeRandomArray(random);
            insertRandom(m, random);
            makeRandomArray(random);
            b.start();
            lookupLinear(m);
        }
    }

    @Bench
    public static void lookupRandomAVL(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Map<Integer, Integer> m = new AvlTreeMap<>();
            int[] random = new int[SIZE];
            fillArray(random);
            makeRandomArray(random);
            insertRandom(m, random);
            makeRandomArray(random);
            b.start();
            lookupRandom(m, random);
        }
    }

    @Bench
    public static void getLinearAVL(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Map<Integer, Integer> m = new AvlTreeMap<>();
            int[] random = new int[SIZE];
            fillArray(random);
            makeRandomArray(random);
            insertRandom(m, random);
            makeRandomArray(random);
            b.start();
            getLinear(m);
        }
    }


    @Bench
    public static void getRandomAVL(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Map<Integer, Integer> m = new AvlTreeMap<>();
            int[] random = new int[SIZE];
            fillArray(random);
            makeRandomArray(random);
            insertRandom(m, random);
            makeRandomArray(random);
            b.start();
            getRandom(m, random);
        }
    }
}
