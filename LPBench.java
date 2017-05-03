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
public final class LPBench {
    private static final int SIZE = 200; // you may need to tweak this...
    private static final Random RAND = new Random();

    /** Make checkstyle happy. */
    private LPBench() {}

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

    private static void putLinear(Map<Integer, Integer> m) {
        for (int i = 0; i < SIZE; i++) {
            m.put(i, i);
        }
    }

    private static void putRandom(Map<Integer, Integer> m, int[] rand) {
        for (int i = 0; i < SIZE; i++) {
            m.put(rand[i], i);
        }
    }

    // Now the benchmarks we actually want to run.

    /** insert linear probe.
     *
     * @param b bee.
     */
    @Bench
    public static void insertLinearLP(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Map<Integer, Integer> m = new LinearProbeHashMap<>();
            b.start();
            insertLinear(m);
        }
    }

    /** insert random probe.
     *
     * @param b bee.
     */
    @Bench
    public static void insertRandomLP(Bee b) {
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

    /** remove linear probe.
     *
     * @param b bee.
     */
    @Bench
    public static void removeLinearLP(Bee b) {
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

    /** remove random probe.
     *
     * @param b bee.
     */
    @Bench
    public static void removeRandomLP(Bee b) {
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

    /** lookup linear probe.
     *
     * @param b bee.
     */
    @Bench
    public static void lookupLinearLP(Bee b) {
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

    /** lookup random probe.
     *
     * @param b bee.
     */
    @Bench
    public static void lookupRandomLP(Bee b) {
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

    /** get linear probe.
     *
     * @param b bee.
     */
    @Bench
    public static void getLinearLP(Bee b) {
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

    /** get random probe.
     *
     * @param b bee.
     */
    @Bench
    public static void getRandomLP(Bee b) {
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

    /** put linear probe.
     *
     * @param b bee.
     */
    @Bench
    public static void putLinearLP(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Map<Integer, Integer> m = new LinearProbeHashMap<>();
            insertLinear(m);
            b.start();
            putLinear(m);

        }
    }

    /** put random probe.
     *
     * @param b bee.
     */
    @Bench
    public static void putRandomLP(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Map<Integer, Integer> m = new LinearProbeHashMap<>();
            int[] random = new int[SIZE];
            fillArray(random);
            makeRandomArray(random);
            insertRandom(m, random);
            makeRandomArray(random);
            b.start();
            putRandom(m, random);
        }
    }
}









