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

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testing implementations of the Map interface.
 */
public abstract class MapTestBase {
    protected Map<Integer, Integer> map;
    
    protected abstract Map<Integer, Integer> createMap();
    
    @Before
    public void setupMapTest() {
		map = this.createMap();
    }
    
    @Test
    public void testHas() {
	map.insert(1, 1);
	assertEquals(true, map.has(1));		
    }
    
    @Test
    public void testDoesNotHave() {
	map.insert(1,1);
	map.remove(1);
	assertEquals(false, map.has(1));
    }
    
    @Test
    public void testGet() {
	map.insert(1, 1);
	Integer i = map.get(1);
	assertEquals((int) i, 1);
    }
    
    @Test
    public void testPut() {
	map.insert(1, 1);
	map.put(1, 2);
	Integer i = map.get(1);
	assertEquals((int) i, 2);
    }
    
    @Test
    public void testNewEmpty() {
	assertEquals(map.size(), 0);
    }
    
    @Test
    public void testInsert() {
	map.insert(1, 1);
	assertEquals(map.size(), 1);
    }
    
    @Test
    public void testRemove() {
	map.insert(1, 1);
	assertEquals(map.size(), 1);
	map.remove(1);
	assertEquals(map.size(), 0);
    }
    
    @Test
    public void testIterator() {
	int i = 0;
	while (i < 360000) {
	    map.insert(i, i*i);
	    i++;
	}
	int size = 0;
	for (Integer integer : map) {
	    size++;
	}
	assertEquals(size, map.size());
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void insertException() {
	map.insert(1, 1);
	map.insert(1, 1);
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void removeException() {
	map.remove(1);
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void getException() {
	map.get(1);
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void putException() {
	map.put(1, 1);
    }
}
