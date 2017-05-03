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

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/** Instantiate SparseGraph to test. */
public class LPHashMapTest extends MapTestBase {
	@Override
	protected Map<Integer, Integer> createMap() {
	    return new LPHashMap<Integer, Integer>();
	}

    @Test
    public void checkRehash() {
        int[] ra = new int[9000];
        for (int i = 0; i < 9000; i++) {
            ra[i] = i;
        }
        for (int i : ra) {
            map.insert((Integer) i, (Integer) i * i);
        }
        int size = 0;
        for (Integer i : map) {
            size++;
        }
        assertEquals(9000, size);
        assertEquals(map.size(), size);
    }

    @Test
    public void testToString() {
        for (int i = 0; i < 5; i++) {
            map.insert((Integer) i, (Integer) i * i);
        }
        String toString = "{Pair<key: 0; value: 0>, Pair<key: 1; value: 1>, Pair<key: 2; value: 4>, Pair<key: 3; value: 9>, Pair<key: 4; value: 16>, }";
        assertEquals(map.toString(), toString);
    }
}
