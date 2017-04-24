//Gurion Marks
//gmarks2

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
* Hash Map implemented by chaining
*
* @param <K> Type for keys.
* @param <V> Type for values.
*/
public class ChainHashMap<K, V> implements Map<K, V> {
	private class Entry {
		K key;
		V value;

		Entry(K k, V v) {
			this.key = k;
			this.value = v;
		}

		public String toString() {
			return "Entry<key: " + this.key + "; value: " + this.value + ">";
		}
	}

	private K[] data;
	private double entries;

	private int hashCode(K key) {
		return 0;
	}

	private int compress(int hashValue) {
		return (hashValue & 0x7FFFFFFF) % this.data.length;
	}

	private Entry find(K k) {
		if (k == null) {
			throw new IllegalArgumentException("Can't handle null key");
		}
		int index = compress(hashCode(k));
		return data[index];
	}

	private void rehash() {
		Entry[] bigger = (Entry[]) new Object[this.data.length * 2];
        for (int i = 0; i < data.length; i++) {

        }
        this.data = bigger;
	}

	}
}