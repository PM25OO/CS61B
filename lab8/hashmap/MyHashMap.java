package hashmap;

import org.w3c.dom.Node;

import java.awt.image.Kernel;
import java.util.*;

/**
 * A hash table-backed Map implementation. Provides amortized constant time
 * access to elements via get(), remove(), and put() in the best case.
 * <p>
 * Assumes null keys will never be inserted, and does not resize down upon remove().
 *
 * @author PM25OO
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!

    private int initialSize;
    private double maxLoad;
    private int number = 0;
    /// number of key-value pairs
    HashSet<K> hashSet = new HashSet<>();

    /**
     * Constructors
     */
    public MyHashMap() {
        initialSize = 16;
        maxLoad = 0.75;
        initBucket();
    }

    public MyHashMap(int initialSize) {
        this.initialSize = initialSize;
        maxLoad = 0.75;
        initBucket();
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad     maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.initialSize = initialSize;
        this.maxLoad = maxLoad;
        initBucket();
    }

    private void initBucket() {
        number = 0;
        buckets = new Collection[this.initialSize];
        for (int i = 0; i < initialSize; i++)
            buckets[i] = createBucket();
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     * <p>
     * The only requirements of a hash table bucket are that we can:
     * 1. Insert items (`add` method)
     * 2. Remove items (`remove` method)
     * 3. Iterate through items (`iterator` method)
     * <p>
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     * <p>
     * Override this method to use different data structures as
     * the underlying bucket type
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return null;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    @Override
    public void clear() {
        initBucket();
    }

    @Override
    public boolean containsKey(K key) {
        return (get(key) != null);
    }

    @Override
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        int index = hash(key);
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) return node.value;
        }
        return null;
    }

    @Override
    public int size() {
        return number;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException();
        if ((double) number / initialSize >= maxLoad) resize(2 * initialSize);
        int index = hash(key);
        if (containsKey(key)) {
            for (Node node : buckets[index]) {
                if (node.key.equals(key)) node.value = value;
            }
        } else {
            number++;
            buckets[index].add(createNode(key, value));
            hashSet.add(key);
        }
    }

    private void resize(int i) {
        MyHashMap<K, V> temp = new MyHashMap<K, V>(i);
        for (int j = 0; j < initialSize; j++) {
            temp.buckets[j] = buckets[j];
        }
        initialSize = temp.initialSize;
        buckets = temp.buckets;
    }

    @Override
    public Set<K> keySet() {
        return hashSet;
    }

    @Override
    public V remove(K key) {
        return remove(key, null);
    }

    @Override
    public V remove(K key, V value) {
        if (!containsKey(key)) return null;
        else {
            int index = hash(key);
            V removeVal = null;
            for (Node node : buckets[index]) {
                if (node.key.equals(key) && (value == null || node.value.equals(value) )) {
                    removeVal = node.value;
                    buckets[index].remove(node);
                    break;
                }
            }
            return removeVal;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return hashSet.iterator();
    }

    private int hash(K key) {
        int h = key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12) ^ (h >>> 7) ^ (h >>> 4);
        return h & (initialSize - 1);
    }
}
