//Gurion Marks
//gmarks2

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/** Instantiate SparseGraph to test. */
public class AvlTreeMapTest extends MapTestBase {
	@Override
	protected Map<Integer, Integer> createMap() {
	    return new AvlTreeMap<Integer, Integer>();
	}

	@Test
	public void testToString() {
		map.insert(0, 0);
		map.insert(1, 1);
		map.insert(2, 4);
		map.insert(3, 9);
		map.insert(4, 16);
		map.insert(5, 25);
		map.insert(6, 36);
		map.insert(7, 49);
		map.insert(8, 64);
		map.insert(9, 81);

		assertEquals(map.toString(), "{((()0:0())1:1(()2:4()))3:9(((()4:16())5:25(()6:36()))7:49(()8:64(()9:81())))}");

		map.remove(0);
		map.remove(1);
		map.remove(2);
		map.remove(3);
		map.remove(4);
		map.remove(5);
		map.remove(6);
		map.remove(7);
		map.remove(8);
		map.remove(9);

		map.insert(3, 0);
		map.insert(0, 1);
		map.insert(5, 4);
		map.insert(8, 9);
		map.insert(2, 16);
		map.insert(9, 25);
		map.insert(7, 36);
		map.insert(4, 49);
		map.insert(1, 64);
		map.insert(6, 81);
		assertEquals(map.toString(), "{((()0:1())1:64(()2:16()))3:0(((()4:49())5:4(()6:81()))7:36(()8:9(()9:25())))}");
	}
}
