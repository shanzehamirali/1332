import java.util.List;
import java.util.Set;
import java.util.NoSuchElementException;
import java.util.HashSet;
import java.util.ArrayList;

/**
 * Your implementation of a LinearProbingHashMap.
 *
 * @author SHANZEH AMIRALI
 * @version 1.0
 * @userid samirali3
 * @GTID 903683513
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class LinearProbingHashMap<K, V> {

    /**
     * The initial capacity of the LinearProbingHashMap when created with the
     * default constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /**
     * The max load factor of the LinearProbingHashMap
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final double MAX_LOAD_FACTOR = 0.67;

    // Do not add new instance variables or modify existing ones.
    private LinearProbingMapEntry<K, V>[] table;
    private int size;

    /**
     * Constructs a new LinearProbingHashMap.
     *
     * The backing array should have an initial capacity of INITIAL_CAPACITY.
     *
     * Use constructor chaining.
     */
    public LinearProbingHashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Constructs a new LinearProbingHashMap.
     *
     * The backing array should have an initial capacity of initialCapacity.
     *
     * You may assume initialCapacity will always be positive.
     *
     * @param initialCapacity the initial capacity of the backing array
     */
    public LinearProbingHashMap(int initialCapacity) {
        table = new LinearProbingMapEntry[initialCapacity];
        size = 0;
    }

    /**
     * Adds the given key-value pair to the map. If an entry in the map
     * already has this key, replace the entry's value with the new one
     * passed in.
     *
     * In the case of a collision, use linear probing as your resolution
     * strategy.
     *
     * Before actually adding any data to the HashMap, you should check to
     * see if the array would violate the max load factor if the data was
     * added. For example, let's say the array is of length 5 and the current
     * size is 3 (LF = 0.6). For this example, assume that no elements are
     * removed in between steps. If another entry is attempted to be added,
     * before doing anything else, you should check whether (3 + 1) / 5 = 0.8
     * is larger than the max LF. It is, so you would trigger a resize before
     * you even attempt to add the data or figure out if it's a duplicate. Be
     * careful to consider the differences between integer and double
     * division when calculating load factor. Additionally, be very careful to
     * use the correct types when comparing with LF. Comparing types with
     * different precisions may result in unexpected rounding errors.
     *
     * When regrowing, resize the length of the backing table to
     * 2 * old length + 1. You must use the resizeBackingTable method to do so.
     *
     * Return null if the key was not already in the map. If it was in the map,
     * return the old value associated with it.
     *
     * @param key the key to add
     * @param value the value to add
     * @return null if the key was not already in the map. If it was in the
     * map, return the old value associated with it
     * @throws java.lang.IllegalArgumentException if key or value is null
     */
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or value cannot be null");
        }
        if (((size + 1.0) / table.length) > MAX_LOAD_FACTOR) {
            resizeBackingTable(table.length * 2 + 1);
        }
        int hash = Math.abs(key.hashCode() % table.length);
        V removed = null;

        if (table[hash] == null) {
            table[hash] = new LinearProbingMapEntry<>(key, value);
            size++;
            return null;
        }
        LinearProbingMapEntry<K, V> temp = table[hash];
        boolean saved = false;
        int savedInd = -1;
        int ind = hash;

        do {
            if (temp.getKey().equals(key) && !temp.isRemoved()) {
                removed = temp.getValue();
                temp.setValue(value);
                return removed;
            } else if (!temp.getKey().equals(key) && temp.isRemoved()) {
                if (!saved) {
                    savedInd = ind;
                    saved = true;
                }
            } else if (temp.getKey().equals(key) && temp.isRemoved()) {
                if (saved) {
                    table[savedInd] = new LinearProbingMapEntry<>(key, value);
                    size++;
                    return null;
                } else if (!saved) {
                    table[ind] = new LinearProbingMapEntry<>(key, value);
                    size++;
                    return null;
                }
            }
            ind = (ind + 1) % table.length;
            if (table[ind] == null) {
                if (saved) {
                    table[savedInd] = new LinearProbingMapEntry<>(key, value);
                    size++;
                    return null;
                } else {
                    table[ind] = new LinearProbingMapEntry<>(key, value);
                    size++;
                    return null;
                }
            }
            temp = table[ind];
        } while (temp != null && ind != hash);
        return removed;
    }


    /**
     * Removes the entry with a matching key from map by marking the entry as
     * removed.
     *
     * @param key the key to remove
     * @return the value previously associated with the key
     * @throws java.lang.IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException   if the key is not in the map
     */
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        V returned = null;
        int hash = Math.abs(key.hashCode() % table.length);
        int ind = hash;
        LinearProbingMapEntry<K, V> temp = table[hash];
        boolean cont = true;
        boolean found = false;
        do {
            if (temp == null) {
                cont = false;
            } else {
                if (temp.getKey().equals(key) && !temp.isRemoved()) {
                    returned = temp.getValue();
                    temp.setRemoved(true);
                    size--;
                    found = true;
                }
            }
            ind = (ind + 1) % table.length;
            temp = table[ind];
            if (ind == hash) {
                cont = false;
            }
        } while (!found && cont);

        if (found) {
            return returned;
        } else {
            throw new NoSuchElementException("Key not found");
        }
    }


    /**
     * Gets the value associated with the given key.
     *
     * @param key the key to search for in the map
     * @return the value associated with the given key
     * @throws java.lang.IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException   if the key is not in the map
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int hash = Math.abs(key.hashCode() % table.length);
        int ind = hash;
        LinearProbingMapEntry<K, V> temp = table[hash];
        boolean cont = true;
        do {
            if (temp == null) {
                cont = false;
            } else if (temp.getKey().equals(key) && !temp.isRemoved()) {
                return temp.getValue();
            } else if (temp.isRemoved() && temp.getKey().equals(key)) {
                cont = false;
            }
            ind = (ind + 1) % table.length;
            temp = table[ind];
            if (ind == hash) {
                cont = false;
            }
        } while (cont);
        throw new NoSuchElementException("Key not found");
    }

    /**
     * Returns whether or not the key is in the map.
     *
     * @param key the key to search for in the map
     * @return true if the key is contained within the map, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if key is null
     */
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int hash = Math.abs(key.hashCode() % table.length);
        int ind = hash;
        LinearProbingMapEntry<K, V> temp = table[hash];

        do {
            if (temp == null) {
                return false;
            } else if (temp.getKey().equals(key) && temp.isRemoved()) {
                return false;
            } else if (temp.getKey().equals(key) && !temp.isRemoved()) {
                return true;
            }
            ind = (ind + 1) % table.length;
            temp = table[ind];
        } while (ind != hash);
        return false;
    }

    /**
     * Returns a Set view of the keys contained in this map.
     *
     * Use java.util.HashSet.
     *
     * @return the set of keys in this map
     */
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        int keySize = 0;
        for (LinearProbingMapEntry<K, V> map : table) {
            if (map != null && !map.isRemoved()) {
                keySet.add(map.getKey());
                keySize++;
            } else if (keySize == size) {
                break;
            }
        }
        return keySet;
    }

    /**
     * Returns a List view of the values contained in this map.
     *
     * Use java.util.ArrayList or java.util.LinkedList.
     *
     * You should iterate over the table in order of increasing index and add
     * entries to the List in the order in which they are traversed.
     *
     * @return list of values in this map
     */
    public List<V> values() {
        List<V> values = new ArrayList<>();
        int valueSize = 0;
        for (LinearProbingMapEntry<K, V> map : table) {
            if (map != null && !map.isRemoved()) {
                values.add(map.getValue());
                valueSize++;
            } else if (valueSize == size) {
                break;
            }
        }
        return values;
    }

    /**
     * Resize the backing table to length.
     *
     * Disregard the load factor for this method. So, if the passed in length is
     * smaller than the current capacity, and this new length causes the table's
     * load factor to exceed MAX_LOAD_FACTOR, you should still resize the table
     * to the specified length and leave it at that capacity.
     *
     * You should iterate over the old table in order of increasing index and
     * add entries to the new table in the order in which they are traversed. 
     * You should NOT copy over removed elements to the resized backing table.
     *
     * Since resizing the backing table is working with the non-duplicate
     * data already in the table, you shouldn't explicitly check for
     * duplicates.
     *
     * Hint: You cannot just simply copy the entries over to the new array.
     *
     * @param length new length of the backing table
     * @throws java.lang.IllegalArgumentException if length is less than the
     *                                            number of items in the hash
     *                                            map
     */
    public void resizeBackingTable(int length) {
        if (length < size) {
            throw new IllegalArgumentException("Cannot resize");
        }
        LinearProbingMapEntry<K, V>[] temp = new LinearProbingMapEntry[length];
        int tempSize = 0;
        for (LinearProbingMapEntry<K, V> map : table) {
            if (map != null && !map.isRemoved()) {
                K key = map.getKey();
                int index = Math.abs(key.hashCode() % temp.length);
                for (int j = 0; j < temp.length; j++) {
                    int newIndex = (index + j) % temp.length;
                    if (temp[newIndex] == null) {
                        temp[newIndex] = map;
                        tempSize++;
                        break;
                    }
                }
            } else if (tempSize == size) {
                break;
            }
        }
        table = temp;
    }

    /**
     * Clears the map.
     *
     * Resets the table to a new array of the INITIAL_CAPACITY and resets the
     * size.
     *
     * Must be O(1).
     */
    public void clear() {
        table = new LinearProbingMapEntry[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Returns the table of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the table of the map
     */
    public LinearProbingMapEntry<K, V>[] getTable() {
        // DO NOT MODIFY THIS METHOD!
        return table;
    }

    /**
     * Returns the size of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the map
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
