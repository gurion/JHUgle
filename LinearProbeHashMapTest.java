//Gurion Marks
//gmarks2

/** Instantiate SparseGraph to test. */
public class LinearProbeHashMapTest extends MapTestBase {
	@Override
	protected Map<Integer, Integer> createMap() {
	    return new LinearProbeHashMap<Integer, Integer>();
	}
}