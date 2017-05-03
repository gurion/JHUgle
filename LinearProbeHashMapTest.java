/** 
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

/** Instantiate SparseGraph to test. */
public class LinearProbeHashMapTest extends MapTestBase {
	@Override
	protected Map<Integer, Integer> createMap() {
	    return new LinearProbeHashMap<Integer, Integer>();
	}
}
