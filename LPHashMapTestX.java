/** Problem 2 of Homework 8 by Ahmed Faidi - afaidi1@jhu.edu */
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;

public class LPHashMapTestX extends MapTestBaseX {
    @Override
    protected Map<String, String> createMap() {
        return new LPHashMap<>();
    }

    @Test
    public void CheckGrowingToFirstSize() {
        int[] test = new int[4];
        for (int i = 0; i < 4; i++) {
            test[i] = i;
        }

        for(int i : test) {
            map.insert(Integer.toString(i), Integer.toString(i*i));
        }

        int size = 0;
        for(String string : map) {
            size += 1;
        }
        assertEquals(4, size);
        assertEquals(size, map.size());
    }

    @Test
    public void CheckGrowingToSecondSize() {
        int[] test = new int[5];
        for (int i = 0; i < 5; i++) {
            test[i] = i;
        }

        for(int i : test) {
            map.insert(Integer.toString(i), Integer.toString(i*i));
        }

        int size = 0;
        for(String string : map) {
            size += 1;
        }
        assertEquals(5, size);
        assertEquals(size, map.size());
    }

    @Test
    public void CheckGrowingToMassiveSize() {
	int MASSIVE = 360000;
        int[] test = new int[MASSIVE];
        for (int i = 0; i < MASSIVE; i++) {
            test[i] = i;
        }

        for(int i : test) {
            map.insert(Integer.toString(i), Integer.toString(i*i));
        }

        int size = 0;
        for(String string : map) {
            size += 1;
        }
        assertEquals(MASSIVE, size);
        assertEquals(size, map.size());
    }

}
