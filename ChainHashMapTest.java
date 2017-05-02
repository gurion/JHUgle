//Gurion Marks
//gmarks2

/** Instantiate SparseGraph to test. */
public class ChainHashMapTest extends MapTestBase {
	@Override
	protected Map<Integer, Integer> createMap() {
	    return new ChainHashMap<Integer, Integer>();
	}
}