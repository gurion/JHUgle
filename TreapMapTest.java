//Gurion Marks
//gmarks2

/** Instantiate SparseGraph to test. */
public class TreapMapTest extends MapTestBase {
	@Override
	protected Map<Integer, Integer> createMap() {
	    return new TreapMap<Integer, Integer>();
	}
}
