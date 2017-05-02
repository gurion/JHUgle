//Gurion Marks
//gmarks2

public class LinearProbeHashMap<K, V> implements Map<K, V> {
    private class Pair {
	K key;
	V value;
	Pair next;
	
	Pair(K k, V v, Pair n) {
	    this.key = k;
	    this.value = v;
	}
	
	public String toString() {
	    return "Pair<key: " + this.key + "; value: " + this.value + ">";
	}
    }
    
    private Pair[] data;
    private int[] sizes = {2, 5, 11, 17, 33, 67, 127, 257, 509, 1031,
			   2053, 4093, 8191, 16381, 32771, 65537, 131071,
			   262139, 524287, 1048573, 2097143, 4194301, 8388593,
			   16777213, 33554393, 67108859, 134217689, 268435399,
			   536870909, 1073741789, 2147483647};
    private int curSizeIndex;
    private double entries;
    
    private int hash(Pair[] array, K key) {
	return (key.hashCode() & 0x7FFFFFFF) % this.sizes[this.curSizeIndex];
    }
    
    private int rehash(int hashValue) {
	return (hashValue & 0x7FFFFFFF) % (this.sizes[this.curSizeIndex + 1]);
    }
    
    private double load() {
	return this.entries/this.data.length;
	}
    
    private int find(K k) {
	if (k == null) {
	    throw new IllegalArgumentException("Can't handle null key");
	}
	int index = hash(k);
		Pair p = this.data[index];
		while (!p.key.equals(k)) {
		    index++
			if (p.key.equals(k)) {
			    return index;
			}
		}
		return -1;
    }
    
    private void rehash() {
	int newSize = this.sizes[this.curSizeIndex + 1];
	Pair[] bigger = (Pair[]) new Object[newSize];
        for (Pair p : this.data) {
	    put(bigger, p.key, p.value);
        }
        this.data = bigger;
    }
    
	private void put(Pair[] array, K key, V value) {
	    
	}
    
    /**
     * Insert a new key/value pair.
     *
     * @param k The key.
     * @param v The value to be associated with k.
     * @throws IllegalArgumentException If k is null or already mapped.
     */
    @Override
    public void insert(K k, V v) throws IllegalArgumentException {

    }
    
    /**
     * Remove an existing key/value pair.
     *
     * @param k The key.
     * @return The value that was associated with k.
     * @throws IllegalArgumentException If k is null or not mapped.
     */
    @Override
    public V remove(K k) throws IllegalArgumentException {
    	return null;
    }
    
    /**
     * Update the value associated with a key.
     *
     * @param k The key.
     * @param v The value to be associated with k.
     * @throws IllegalArgumentException If k is null or not mapped.
     */
    @Override
    public void put(K k, V v) throws IllegalArgumentException {

    }

    /**
     * Get the value associated with a key.
     *
     * @param k The key.
     * @return The value associated with k.
     * @throws IllegalArgumentException If k is null or not mapped.
     */
    @Override
    public V get(K k) throws IllegalArgumentException {
    	return null;
    }

    /**
     * Check existence of a key.
     *
     * @param k The key.
     * @return True if k is mapped, false otherwise (even for null!).
     */
    @Override
    public boolean has(K k) {
    	Pair p = find(k);
    	if (p == null) {
    		return false;
    	}
    	return true;
    }

    /**
     * Number of mappings.
     *
     * @return Number of key/value pairs in the map.
     */
    @Override
    public int size() {
    	return (int) this.entries;
    }

    public Iterator<K> iterator() {
    	List<K> keys = new ArrayList<K>();
    	Pair<K, V> p;
    	for (int i = 0; i < this.data.length; i++) {
	    if (this.data[i] != null) {
		keys.add(this.data[i]);
	    }
    	}
    }

    private void setupStringBuilder() {
        if (this.stringBuilder == null) {
            this.stringBuilder = new StringBuilder();
        } else {
            this.stringBuilder.setLength(0);
        }
    }

    @Override
    public String toString() {
    	this.setupStringBuilder();
    	this.stringBuilder.append("{");	
    }   
}
