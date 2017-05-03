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

/** Instantiate Quadratic probe hash map to test. */
public class QPHashMapTest extends MapTestBase {
	@Override
	protected Map<Integer, Integer> createMap() {
	    return new QPHashMap<Integer, Integer>();
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
}