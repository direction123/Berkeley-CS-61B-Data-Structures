package hw6.dict;

import hw6.List.SList;

/**
 *  HashTableChained implements a Dictionary as a Hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the Hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the Hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

    /**
     *  Place any data fields here.
     **/
    private SList[] hashArray;
    private int arraySize;

    /**
     *  Construct a new empty Hash table intended to hold roughly sizeEstimate
     *  entries.  (The precise number of buckets is up to you, but we recommend
     *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
     **/

    public HashTableChained(int sizeEstimate) {
        // Your solution here.
        arraySize = getPrime(sizeEstimate);
        hashArray = new SList[arraySize];
        for (int i = 0; i < arraySize; i++) {
            hashArray[i] = new SList();
        }
    }

    private int getPrime(int n) {
        while (!isPrime(n)) {
            n++;
        }
        return n;
    }

    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        if (n == 2 || n == 3) {
            return true;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     *  Construct a new empty Hash table with a default size.  Say, a prime in
     *  the neighborhood of 100.
     **/

    public HashTableChained() {
        // Your solution here.
        this(100);
    }

    /**
     *  Converts a Hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
     *  to a value in the range 0...(size of Hash table) - 1.
     *
     *  This function should have package protection (so we can test it), and
     *  should be used by insert, find, and remove.
     **/

    int compFunction(int code) {
        // Replace the following line with your solution.
        return Math.abs(code) % arraySize;
    }

    /**
     *  Returns the number of entries stored in the dictionary.  Entries with
     *  the same key (or even the same key and value) each still count as
     *  a separate entry.
     *  @return number of entries in the dictionary.
     **/

    public int size() {
        // Replace the following line with your solution.
        return arraySize;
    }

    /**
     *  Tests if the dictionary is empty.
     *
     *  @return true if the dictionary has no entries; false otherwise.
     **/

    public boolean isEmpty() {
        // Replace the following line with your solution.
        return arraySize == 0;
    }

    /**
     *  Create a new Entry object referencing the input key and associated value,
     *  and insert the entry into the dictionary.  Return a reference to the new
     *  entry.  Multiple entries with the same key (or even the same key and
     *  value) can coexist in the dictionary.
     *
     *  This method should run in O(1) time if the number of collisions is small.
     *
     *  @param key the key by which the entry can be retrieved.
     *  @param value an arbitrary object.
     *  @return an entry containing the key and value.
     **/

    public Entry insert(Object key, Object value) {
        // Replace the following line with your solution.
        arraySize++;
        int hashVal = compFunction(key.hashCode());
        Entry entry = new Entry();
        entry.key = key;
        entry.value = value;
        hashArray[hashVal].insertEnd(entry);
        return entry;
    }

    /**
     *  Search for an entry with the specified key.  If such an entry is found,
     *  return it; otherwise return null.  If several entries have the specified
     *  key, choose one arbitrarily and return it.
     *
     *  This method should run in O(1) time if the number of collisions is small.
     *
     *  @param key the search key.
     *  @return an entry containing the key and an associated value, or null if
     *          no entry contains the specified key.
     **/

    public Entry find(Object key) {
        // Replace the following line with your solution.
        int hashVal = compFunction(key.hashCode());
        return hashArray[hashVal].find(key);
    }

    /**
     *  Remove an entry with the specified key.  If such an entry is found,
     *  remove it from the table and return it; otherwise return null.
     *  If several entries have the specified key, choose one arbitrarily, then
     *  remove and return it.
     *
     *  This method should run in O(1) time if the number of collisions is small.
     *
     *  @param key the search key.
     *  @return an entry containing the key and an associated value, or null if
     *          no entry contains the specified key.
     */

    public Entry remove(Object key) {
        // Replace the following line with your solution.
        int hashVal = compFunction(key.hashCode());
        Entry entry = hashArray[hashVal].delete(key);
        if (entry != null) {
            arraySize--;
        }
        return entry;
    }

    /**
     *  Remove all entries from the dictionary.
     */
    public void makeEmpty() {
        // Your solution here.
        for(int i = 0; i < arraySize; i++){
            hashArray[i] = new SList();
        }
        arraySize = 0;
    }

}