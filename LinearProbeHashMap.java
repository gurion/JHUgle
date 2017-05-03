//Gurion Marks
//gmarks2

import java.util.Iterator;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Linear probe HashMap implementation.
 *
 * @param <K> Key type.
 * @param <V> Value type.
 */
public class LinearProbeHashMap<K, V> implements Map<K, V> {
    private class Pair<K, V> {
        K key;
        V value;
        boolean tombstone;

        Pair(K k, V v) {
            this.key = k;
            this.value = v;
            this.tombstone = false;
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
        private Iterator<Pair<K, V>> iter;

        HashMapIterator() {
            this.iter = Arrays.stream(LinearProbeHashMap.this.data).iterator();
        }

        public boolean hasNext() {
            return this.returned < LinearProbeHashMap.this.entries;
        }

        public K next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            K k = LinearProbeHashMap.this.data[this.returned].key;
            this.returned++;
            return k;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private Pair<K, V>[] data;
    private int[] sizes = {3, 7, 31, 127, 8191, 131071, 524287, 2147483647, };
    private int curSizeIndex;
    private double entries;
    private StringBuilder stringBuilder;

    private int hash(K key) {
        return (key.hashCode() & 0x7FFFFFFF) % this.sizes[this.curSizeIndex];
    }

    private int rehashValue(K key) {
        return (key.hashCode() & 0x7FFFFFFF) % this.sizes[this.curSizeIndex + 1];
    }

    private double load() {
        return (this.entries / this.sizes[curSizeIndex]);
    }

    private int find(K k) {
        if (k == null) {
            throw new IllegalArgumentException();
        }
        int index = 0;
        int hashValue = this.hash(k);
        int size = this.sizes[this.curSizeIndex];

        Pair<K, V> p;

        while ((this.data[(hashValue + index) % size] != null) && (index <= size)) {
            p = this.data[(hashValue + index) % size];
            if ((p.key).equals(k) && (p.tombstone == false)) {
                    return ((hashValue + index) % size);
            }
            index++;
        }
        return -1;
    }

    private int findForSure(K k) throws IllegalArgumentException {
        int index = this.find(k);
        if (index == -1) {
            throw new IllegalArgumentException();
        }
        return index;
    }

    private void rehash() {
        int newSize = this.sizes[this.curSizeIndex + 1];
        Pair[] bigger = (Pair[]) new Object[newSize];
        for (int i = 0; i < this.sizes[this.curSizeIndex]; i++) {
            if (this.data[i] != null && this.data[i].tombstone == false) {
                int index = 0;
                while (bigger[(hash(this.data[i].key) + index) % newSize] != null) {
                    index++;
                }
                bigger[(hash(this.data[i].key) + index) % newSize] = this.data[i];
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
        int hashValue = this.hash(k);
        int index = 0;
        int size = this.sizes[this.curSizeIndex];
        Pair<K, V> p = new Pair<>(k, v);
        if (this.data[hashValue] == null) {
            this.data[hashValue] = p;
            this.entries++;
            if (this.load() > .7) {
                this.rehash();
            }
            return;
        } else if (this.data[hashValue].tombstone == true) {
            this.data[hashValue] = p;
            this.entries++;
            if (this.load() > .7) {
                this.rehash();
            }
            return;
        } else {
            while (this.data[(hashValue + index) % size] != null) {
                p = this.data[(hashValue + index) % size];
                if (p.tombstone == true) {
                    this.data[(hashValue + index) % size] = p;
                    this.entries++;
                    if (this.load() > .7) {
                        this.rehash();
                    }
                    return;
                } else {
                    index++;
                } 
            }
            this.data[(hashValue + index) % size] = p;
            this.entries++;
            if (this.load() > .7) {
                this.rehash();
            }
            return;
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
        int index = 0;
        V v = null;
        int size = this.sizes[this.curSizeIndex];
        int hashValue = this.hash(k);
        Pair<K, V> p;

        while (v == null && this.data[(hashValue + index) % size] != null) {
            p = this.data[(hashValue + index) % size];
            if ((p.key).equals(k)) {
                if (p.tombstone == true) {
                    return null;
                }
            } else {
                v = p.value;
                p.tombstone = true;
                this.entries--;
            }
            index++;
        }
        return v;
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
        int index = this.findForSure(k);
        this.data[index].value = v;
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
        return this.data[this.findForSure(k)].value;

    }

    /**
     * Check existence of a key.
     *
     * @param k The key.
     * @return True if k is mapped, false otherwise (even for null!).
     */
    @Override
    public boolean has(K k) {
        return (this.find(k) != -1);
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
     * @return Iterator.
     */
    public Iterator<K> iterator() {
        /*
        List<K> keys = new ArrayList<K>();
        Pair<K, V> p;
        for (int i = 0; i < this.data.length; i++) {
            if (this.data[i] != null) {
                keys.add(this.data[i]);
            }
        }
        */
        return new HashMapIterator();
    }

/*
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
        for (Pair<K, V> : this.data) {
            this.stringBuilder.append(p.toString());
        }
        return this.stringBuilder.toString();
    }
*/
}
