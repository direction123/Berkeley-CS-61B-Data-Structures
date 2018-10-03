package Hash;// Hash.hashDouble.java
// demonstrates Hash table with double hashing
// to run this program
// Double hashing requires that the size of the Hash table is a prime number
///////////////////////////////////////////////////
class HashTableDouble {
    private DataItem[] hashArray;
    private int arraySize;
    private DataItem nonItem; // for delete items
    //--------------------
    HashTableDouble(int size) {
        arraySize = size;
        hashArray = new DataItem[arraySize];
        nonItem = new DataItem(-1);
    }
    //--------------------
    public void displayTable() {
        System.out.print("Table: ");
        for (int j = 0; j < arraySize; j++) {
            if (hashArray[j] != null) {
                System.out.print(hashArray[j].getKey() + " ");
            } else {
                System.out.print("** ");
            }
        }
    }
    //----------------------
    public int hashFunc1(int key) {
        return key % arraySize;
    }
    //----------------------
    public int hashFunc2(int key) {
        //non-zero, less then array size, different from hF1
        //array size must be relatively prime to 5, 4, 3, and 2
        return 5 - key % 5;
    }
    //----------------------insert a Hash.DataItem----
    public void insert(int key, DataItem item) {
        // assume table not full
        int hashVal = hashFunc1(key); // Hash the key
        int stepSize = hashFunc2(key); // get step size until empty cell or -1
        while (hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1) {
            hashVal += stepSize; // add the step
            hashVal %= arraySize; // for wraparound
        }
        hashArray[hashVal] = item; // insert item
    } // end insert()
    //------------------delete a Hash.DataItem----------------------
    public DataItem delete(int key) {
        int hashVal = hashFunc1(key); // Hash the key
        int stepSize = hashFunc2(key); // get step size until empty cell or -1
        // until empty cell, found the key?
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getKey() == key) {
                DataItem temp = hashArray[hashVal]; // save item
                hashArray[hashVal] = nonItem; // delete item
                return temp; //return item
            }
            hashVal += stepSize; // add the step
            hashVal %= arraySize; // can't find item
        }
        return null; // can't find item
    } // end delete()
    //------------------find item with key---------------------
    public DataItem find(int key) {
        int hashVal = hashFunc1(key); // Hash the key
        int stepSize = hashFunc2(key); // get step size until empty cell or -1
        while (hashArray[hashVal] != null) {
            // until empty cell, found the key?
            if (hashArray[hashVal].getKey() == key) {
                return hashArray[hashVal]; // yes, return item
            }
            hashVal += stepSize; // add the step
            hashVal %= arraySize; // wraparound if necessary
        }
        return null; // can't find item
    }
}