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
public final class LinearProbeBench {
    private static final int SIZE = 200; // you may need to tweak this...
    private static final Random RAND = new Random();

    private LinearProbeBench() {}

    // First some basic "compound operations" to benchmark. Note that each
    // of these is carefully dimensioned (regarding the range of elements)
    // to allow combining them.

    // Insert a number of "consecutive" strings into the given set.
    private static void insertLinear(Map<Integer, Integer> m) {
        for (int i = 0; i < SIZE; i++) {
            m.insert(i, i);
        }
    }

    private static void fillArray(int[] ra) {
        for (int i = 0; i < SIZE; i++) {
            ra[i] = i;
        }
    }

    private static void makeRandomArray(int[] ra) {
        for (int i = 0; i < SIZE; i++) {
            int temp = ra[i];
            int pos = RAND.nextInt(SIZE);
            ra[i] = ra[pos];
            ra[pos] = temp;
        }
    }

    // Insert a number of "random" strings into the given set.
    private static void insertRandom(Map<Integer, Integer> m, int[] rand) {
        for (int i = 0; i < SIZE; i++) {
            m.insert(rand[i], i);
        }
    }

    // Remove a number of "random" strings from the given set.
    private static void removeRandom(Map<Integer, Integer> m, int[] rand) {
        for (int i = 0; i < SIZE; i++) {
            m.remove(rand[i]);
        }
    }

    private static void removeLinear(Map<Integer, Integer> m) {
        for (int i = 0; i < SIZE; i++) {
            m.remove(i);
        }
    }

    // Lookup a number of "consecutive" strings in the given set.
    private static void lookupLinear(Map<Integer, Integer> m) {
        for (int i = 0; i < SIZE; i++) {
            boolean x = m.has(i);
        }
    }

    // Lookup a number of "random" strings in the given set.
    private static void lookupRandom(Map<Integer, Integer> m, int[] rand) {
        for (int i = 0; i < SIZE; i++) {
            boolean x = m.has(rand[i]);
        }
    }

    private static void getLinear(Map<Integer, Integer> m) {
        for (int i = 0; i < SIZE; i++) {
            int x = m.get(i);
        }
    }

    private static void getRandom(Map<Integer, Integer> m, int[] rand) {
        for (int i = 0; i < SIZE; i++) {
            int x = m.get(rand[i]);
        }
    }

    // Now the benchmarks we actually want to run.

    @Bench
    public static void insertLinearProbe(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Map<Integer, Integer> m = new LinearProbeHashMap<>();
            b.start();
            insertLinear(m);
        }
    }

    @Bench
    public static void insertRandomProbe(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Map<Integer, Integer> m = new LinearProbeHashMap<>();
            int[] random = new int[SIZE];
            fillArray(random);
            makeRandomArray(random);
            b.start();
            insertRandom(m, random);
        }
    }

    @Bench
    public static void removeLinearProbe(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Map<Integer, Integer> m = new LinearProbeHashMap<>();
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
    public static void removeRandomProbe(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Map<Integer, Integer> m = new LinearProbeHashMap<>();
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
    public static void lookupLinearProbe(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Map<Integer, Integer> m = new LinearProbeHashMap<>();
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
    public static void lookupRandomProbe(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Map<Integer, Integer> m = new LinearProbeHashMap<>();
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
    public static void getLinearProbe(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Map<Integer, Integer> m = new LinearProbeHashMap<>();
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
    public static void getRandomProbe(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Map<Integer, Integer> m = new LinearProbeHashMap<>();
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
