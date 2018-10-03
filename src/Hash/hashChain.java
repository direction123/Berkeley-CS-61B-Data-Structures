package Hash;

// hashChain.java
// demonstrates Hash table with separate chaining to run this program


////////////////////////////////////////////////////////
class Link {
    private int iData;
    public Link next;

    public Link(int it) {
        iData = it;
    }
    public int getKey() {
        return iData;
    }
    public void displayLink() {
        System.out.print(iData + " ");
    }
}
////////////////////////////////////////////////////////
class SortedList {
    private Link first;
    public void SortedList() {
        first = null;
    }
    //--------------------------------------------------------
    public void insert(Link theLink) {
        int key = theLink.getKey();
        Link previous = null;  // start at first
        Link current = first;
        // until end of list, or current > key
        while (current != null && key > current.getKey()) {
            previous = current;
            current = current.next; // go to next item
        }
        if (previous == null) { // if beginning of list, first --> new link
            first = theLink;
        } else {
            previous.next = theLink;
        }
    } // end insert()
    //--------------------------------------------------------
    public void delete(int key) {
        Link previous = null;
        Link current = first;

        // until end of list, or key == current
        while (current != null && key != current.getKey()) {
            previous = current;
            current = current.next; // go to next link
        }
        if (previous == null) {
            first = first.next; // if beginning of list, delete first link
        } else {
            previous.next = current.next;
        } // end delete()
    }
    //--------------------------------------------------------
    public Link find(int key) {
        Link current = first;  // start at first

       while (current != null && current.getKey() <= key) {
           if (current.getKey() == key) {
               return current;
           }
           current = current.next;
       }
       return null; // didn't find it
    }
    //--------------------------------------------------------
    public void displayList() {
        System.out.print("SList (first -> last): ");
        Link current = first;
        while (current != null) {
            current.displayLink();;
            current = current.next;
        }
        System.out.println("");
    } // end class SortedList
}
////////////////////////////////////////////////////////
class HashTableChain {
    private SortedList[] hashArray; // array of lists
    private int arraySize;
    //------------------------------
    public HashTableChain(int size) {
        arraySize = size;
        hashArray = new SortedList[arraySize]; //create array
        for (int j = 0; j < arraySize; j++) {
            hashArray[j] = new SortedList();
        }
    }
    //------------------------------
    public void displayTable() {
        for (int j = 0; j < arraySize; j++) {
            System.out.print(j + ". ");
            hashArray[j].displayList();
        }
    }
    //------------------------------
    public int hashFunc(int key) {
        return key % arraySize;
    }
    //------------------------------
    public void insert(Link theLink) {
        int key = theLink.getKey();
        int hashVal = hashFunc(key); // Hash the key
        hashArray[hashVal].insert(theLink); // insert at hashVal
    } // end insert()
    //------------------------------
    public void delete(int key) {
        int hashVal = hashFunc(key);
        hashArray[hashVal].delete(key); // delete link
    } // end delete()
    //------------------------------
    public Link find(int key) {
        int hashVal = hashFunc(key);
        Link theLink = hashArray[hashVal].find(key);
        return theLink;
    }
}

////////////////////////////////////////////////////////

public class hashChain {

}
