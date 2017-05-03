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

import java.util.Iterator;
import java.util.LinkedList;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Hash Map implemented by chaining.
 *
 * @param <K> Type for keys.
 * @param <V> Type for values.
 */
public class ChainHashMap<K, V> implements Map<K, V> {
    private class Pair<K, V> {
        K key;
        V value;

        Pair(K k, V v) {
            this.key = k;
            this.value = v;
        }

        public boolean equals(Object that) {
            return (that instanceof Pair)
                && (this.key.equals(((Pair) that).key));
        }

        public int hashCode() {
            return this.key.hashCode();
        }

        public String toString() {
            return "Pair<key: " + this.key + "; value: " + this.value + ">";
        }
    }

    // inner class , not nested ! look ma, no static !
    private class HashMapIterator implements Iterator<K> {
        private int returned;
        private Iterator<LinkedList<Pair<K, V>>> outer;
        private Iterator<Pair<K, V>> inner;

        HashMapIterator() {
            this.outer = Arrays.stream(ChainHashMap.this.data).iterator();
            this.inner = this.outer.next().iterator();
        }

        public boolean hasNext() {
            return this.returned < ChainHashMap.this.entries;
        }

        public K next() {
            if (this.inner.hasNext()) {
                this.returned += 1;
                return this.inner.next().key;
            } else {
                while (!this.inner.hasNext() && this.outer.hasNext()) {
                    this.inner = this.outer.next().iterator();
                }
                if (this.inner.hasNext()) {
                    this.returned += 1;
                    return this.inner.next().key;
                } else {
                    return null;
                }
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private LinkedList<Pair<K, V>>[] data;
    private int[] sizes = {3, 7, 31, 127, 8191, 131071, 524287, 2147483647, };
    private int curSizeIndex;
    private double entries;
    private StringBuilder stringBuilder;

    /** make checkstyle happy. */
    public ChainHashMap() {
        this.data = (LinkedList<Pair<K, V>>[])
            Array.newInstance(LinkedList.class, this.sizes[this.curSizeIndex]);
    }

    private int hash(K key) {
        return ((key.hashCode() & 0x7FFFFFFF) % this.sizes[this.curSizeIndex]);
    }

    private int rehashValue(K key) {
        return ((key.hashCode() & 0x7FFFFFFF)
                % this.sizes[this.curSizeIndex + 1]);
    }

    private double load() {
        return (this.entries / this.sizes[this.curSizeIndex]);
    }

    private Pair<K, V> find(K k) {
        if (k == null) {
            throw new IllegalArgumentException("Can't handle null key");
        }
        LinkedList<Pair<K, V>> list = this.data[this.hash(k)];
        if (list == null) {
            return null;
        }
        for (Pair<K, V> p : list) {
            if (p.key.equals(k)) {
                return p;
            }
        }
        return null;

    }

    private Pair<K, V> findForSure(K k) throws IllegalArgumentException {
        Pair<K, V> p = this.find(k);
        if (p == null) {
            throw new IllegalArgumentException();
        }
        return p;
    }

    private void rehash() {
        LinkedList<Pair<K, V>>[] bigger = (LinkedList<Pair<K, V>>[])
            Array.newInstance(LinkedList.class,
                                 this.sizes[this.curSizeIndex + 1]);
        //      for (int i = 0; i < sizes[curSizeIndex + 1]; i++) {
        //    bigger[i] = new LinkedList<Pair<K, V>>();
        //      }
        for (LinkedList<Pair<K, V>> l : this.data) {
            if (l == null) {
                continue;
            }
            for (Pair<K, V> p : l) {
                if (bigger[this.rehashValue(p.key)] == null) {
                    LinkedList<Pair<K, V>> list = new LinkedList<>();
                    bigger[this.rehashValue(p.key)] = list;
                }
                bigger[this.rehashValue(p.key)].add(p);
            }
        }
        this.data = bigger;
        this.curSizeIndex++;
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
        if (k == null || this.has(k)) {
            throw new IllegalArgumentException();
        }
        Pair<K, V> p = new Pair<>(k, v);
        if (this.data[this.hash(k)] == null) {
            LinkedList<Pair<K, V>> t = new LinkedList<Pair<K, V>>();
            t.add(p);
            this.data[this.hash(k)] = t;
        } else {
            this.data[this.hash(k)].add(p);
        }
        this.entries++;

        if (this.load() > .7) {
            this.rehash();
        }
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
        Pair<K, V> p = this.findForSure(k);
        this.data[this.hash(k)].remove(p);
        this.entries--;
        return p.value;
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
        Pair<K, V> p = this.findForSure(k);
        p.value = v;
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
        Pair<K, V> p = this.findForSure(k);
        return p.value;
    }

    /**
     * Check existence of a key.
     *
     * @param k The key.
     * @return True if k is mapped, false otherwise (even for null!).
     */
    @Override
    public boolean has(K k) {
        return this.find(k) != null;
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
        return new HashMapIterator();
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
        for (LinkedList<Pair<K, V>> l: this.data) {
            for (Pair<K, V> p : l) {
                this.stringBuilder.append(p.toString());
            }
        }
        return this.stringBuilder.toString();
    }
}





