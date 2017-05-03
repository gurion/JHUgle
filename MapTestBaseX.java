/** Problem 1 of Homework 8 by Ahmed Faidi - afaidi1@jhu.edu */

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class MapTestBaseX {
    protected Map<String, String> map;

    protected abstract Map<String, String> createMap();

    @Before
    public void setupMapTests() {
        map = this.createMap();
    }

    @Test
    public void newMapIsEmpty() {
        int size = 0;
        for(String string : map) {
            size += 1;
        }
        assertEquals(0, size);
        assertEquals(size, map.size());
    }

    @Test
    public void insertAddsElement() {
        int size = 0;
        map.insert("Hello", "5");
        for(String string : map) {
            size += 1;
        }
        assertEquals(1, size);
        assertEquals(size, map.size());
    }

    @Test
    public void removeTakesElementAway() {
        int size = 0;
        String keyA = "Hello";
        String valueA = "10";
        map.insert(keyA, valueA);
        String keyB = "it's me";
        String valueB = "20";
        map.insert(keyB, valueB);
        map.remove(keyB);
        for(String string : map) {
            size += 1;
        }
        assertEquals(1, size);
        assertEquals(size, map.size());
    }

    @Test
    public void keyExists() {
        String keyA = "Hello";
        String valueA = "10";
        map.insert(keyA, valueA);
        assertTrue(map.has(keyA));
    }

    @Test
    public void keyDoesNotExist() {
        String keyA = "Hello";
        String valueA = "10";
        map.insert(keyA, valueA);
        String keyB = "it's me";
        String valueB = "20";
        map.insert(keyB, valueB);
        map.remove(keyB);
        assertFalse(map.has(keyB));
    }

    @Test
    public void get() {
        String keyA = "Hello";
        String valueA = "10";
        map.insert(keyA, valueA);
        assertTrue(map.has(keyA));
    }

    @Test
    public void getValue() {
        String keyA = "Hello";
        String valueA = "10";
        map.insert(keyA, valueA);
        assertEquals("10", map.get(keyA));
    }

    @Test
    public void updateValue() {
        String keyA = "Hello";
        String valueA = "10";
        map.insert(keyA, valueA);
        assertEquals("10", map.get(keyA));

        String newValueA = "20";
        map.put(keyA, newValueA);
        assertEquals("20", map.get(keyA));
    }

    @Test(expected=IllegalArgumentException.class)
    public void checkInsertNull() {
        String keyA = null;
        String valueA = "10";
        map.insert(keyA, valueA);
    }

    @Test(expected=IllegalArgumentException.class)
    public void checkInsertKeyMapped() {
        String keyA = "Hello";
        String valueA = "10";
        map.insert(keyA, valueA);
        String newValueA = "20";
        map.insert(keyA, newValueA);
    }

    @Test(expected=IllegalArgumentException.class)
    public void checkRemoveNull() {
        String keyA = null;
        map.remove(keyA);
    }

    @Test(expected=IllegalArgumentException.class)
    public void checkRemoveKeyNotMapped() {
        String keyA = "Hello";
        String valueA = "10";
        map.insert(keyA, valueA);
        String keyB = "It's me";
        map.remove(keyB);
    }

    @Test(expected=IllegalArgumentException.class)
    public void checkUpdateNull() {
        String keyA = null;
        String valueA = "10";
        map.put(keyA, valueA);
    }

    @Test(expected=IllegalArgumentException.class)
    public void checkUpdateKeyNotMapped() {
        String keyA = "Hello";
        String valueA = "10";
        map.insert(keyA, valueA);
        String keyB = "It's me";
        String valueB = "20";
        map.put(keyB, valueB);
    }

    @Test(expected=IllegalArgumentException.class)
    public void checkGetNull() {
        String keyA = null;
        map.get(keyA);
    }

    @Test(expected=IllegalArgumentException.class)
    public void checkGetKeyNotMapped() {
        String keyA = "Hello";
        String valueA = "10";
        map.insert(keyA, valueA);
        String keyB = "It's me";
        map.get(keyB);
    }
}
