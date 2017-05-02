//Gurion Marks
//gmarks2

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 * Hash Map implemented by chaining.
 *
 * @param <K> Type for keys.
 * @param <V> Type for values.
 */
public class ChainHashMap<K, V> implements Map<K, V> {
    private class Pair {
        K key;
        V value;
        Pair next;

        Pair(K k, V v, Pair n) {
            this.key = k;
            this.value = v;
            this.next = n;
        }

        public String toString() {
            return "Pair<key: " + this.key + "; value: " + this.value + ">";
        }
    }

    private Pair[] data;
    private double entries;

    private int hash(K key) {
        return key.hashCode();
    }

    private int compress(int hashValue) {
        return (hashValue & 0x7FFFFFFF) % this.data.length;
    }

    private int rehashCompress(int hashValue) {
        return (hashValue & 0x7FFFFFFF) % (this.data.length * 2);
    }

    private double load() {
        return (this.entries / this.data.length);
    }

    private Pair find(K k) {
        if (k == null) {
            throw new IllegalArgumentException("Can't handle null key");
        }
        int index = this.compress(this.hash(k));
        return this.data[index];
    }

    private void rehash() {
        Pair[] bigger = (Pair[]) new Object[this.data.length * 2];
        for (Pair p : this.data) {
            bigger[this.rehashCompress(this.hash(e.key))] = e;
        }
        this.data = bigger;
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
        return;
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
        return false;
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

    /**
     * HashMap iterator.
     *
     * @return an iterator.
     */
    public Iterator<K> iterator() {
        List<K> keys = new ArrayList<K>();
        Pair<K, V> p;
        for (int i = 0; i < this.data.length; i++) {
            p = this.data[i];
            if (p != null) {
                if (!keys.contains(p.key)) {
                    keys.add(p.key);
                }
            }
            while (p.next != null) {
                p = p.next;
                if (!keys.contains(p.key)) {
                    keys.add(p.key);
                }
            }
        }
        return keys.iterator();
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





