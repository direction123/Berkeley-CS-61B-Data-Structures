package Heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// heap.java
// demonstrates heaps to run this program
class Node {
    private int iData;
    //----------------------
    public Node(int key) {
        iData = key;
    }
    public int getKey() {
        return iData;
    }
    public void setKey(int id) {
        iData = id;
    }
}// end class Node
//----------------------

class Heap {
    private Node[] heapArray;
    private int maxSize; // size of array
    private int currentSize; // number of nodes in array
    //----------------------
    public Heap(int mx) {
        // constructor
        maxSize = mx;
        currentSize = 0;
        heapArray = new Node[maxSize]; // create array
    }
    //----------------------
    public boolean isEmpty() {
        return currentSize == 0;
    }
    //----------------------
    public boolean insert(int key) {
        if (currentSize == maxSize) {
            return false;
        }
        Node newNode = new Node(key);
        heapArray[currentSize] = newNode;
        trickleUp(currentSize++);
        return true;
    } // end insert()
    //----------------------
    public void trickleUp(int index) {
        int parent = (index - 1)/2;
        Node bottom = heapArray[index];
        while (index > 0 && heapArray[parent].getKey() < bottom.getKey()) {
            heapArray[index] = heapArray[parent]; // move it down
            index = parent;
            parent = (parent - 1)/2;
        } // end while
        heapArray[index] = bottom;
     } // end trickleUp()
    //---------------------------
    public Node remove() {
        // delete item with max key
        Node root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }
    //---------------------------
    public void trickleDown(int index) {
        int largerChild;
        Node top = heapArray[index];  //save root
        while (index < currentSize/2) {
            // while node has at least one child
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;

            // find larger child
            if (rightChild < currentSize && // rightChild exists?
                    heapArray[leftChild].getKey() < heapArray[rightChild].getKey()) {
                largerChild = rightChild;
            } else {
                largerChild = leftChild;
            }

            // top >= largerChild?
            if (top.getKey() >= heapArray[largerChild].getKey())
                break;

            // shift child up
            heapArray[index] = heapArray[largerChild];
            index = largerChild; // go down
        } // end while
        heapArray[index] = top;
    }
    //-----------------------------------
    public boolean change(int index, int newValue) {
        if (index < 0 || index >= currentSize) {
            return false;
        }
        int oldValue = heapArray[index].getKey(); // remember old
        heapArray[index].setKey(newValue); // change to new

        if (oldValue < newValue)  // if raised, trickle it up
            trickleUp(index);
        else
            trickleDown(index);
        return true;
    }
    //-----------------------------------
    public void displayHeap() {
        System.out.print("heapArray: ");
        // array format
        for (int m = 0; m < currentSize; m++) {
            if (heapArray[m] != null)
                System.out.print(heapArray[m].getKey() + " ");
            else
                System.out.print("-- ");
        }
        System.out.println();

        // heap format
        int nBlanks = 32;
        int itemsPerRow = 1;
        int column = 0;
        int j = 0;
        String dots = "...........................";
        System.out.println(dots + dots);

        while (currentSize > 0) {
            if (column == 0)   // first item in row?
                for (int k = 0; k < nBlanks; k++)   //preceding blanks
                    System.out.print(' ');
            // display item
            System.out.print(heapArray[j].getKey());

             if (++j == currentSize) // done?
                break;

             if (++column == itemsPerRow) {
                // end of row?
                nBlanks /= 2;  // half the blanks
                itemsPerRow *= 2;
                column = 0;
                System.out.println();
             } else
                // next item on row
                 for (int k = 0; k < nBlanks *2 - 2; k++)
                     // interim blanks
                    System.out.print(' ');
             } // end for
             System.out.println("\n" + dots + dots); // dotted bottom line
            // end displayHeap()
    }
}
class HeapApp {
    public static void main(String[] args) throws IOException {
        int value, value2;
        Heap theHeap = new Heap(31); // make a Heap, max size 31
        boolean success;

        theHeap.insert(70);
        theHeap.insert(40);
        theHeap.insert(50);
        theHeap.insert(20);
        theHeap.insert(60);
        theHeap.insert(100);
        theHeap.insert(80);
        theHeap.insert(30);
        theHeap.insert(10);
        theHeap.insert(90);

        while(true) {
            //until [Ctrl] - [C]
            System.out.print("Enter first letter of ");
            System.out.print("show, insert, remove, change: ");
            int choice = getChar();
            switch (choice) {
                case 's':  // show
                    theHeap.displayHeap();
                    break;
                case 'i':  // insert
                    System.out.print("Enter value to insert: ");
                    value = getInt();
                    success = theHeap.insert(value);
                    if (!success) {
                        System.out.println("Can't insert; heap full");
                    }
                    break;
                case 'r': // remove
                    if (!theHeap.isEmpty()) {
                        theHeap.remove();
                    } else {
                        System.out.println("Can't remove; heap empty");
                    }
                    break;
                case 'c': // change
                    System.out.print("Enter current index of item: ");
                    value = getInt();
                    System.out.print("Enter new key: ");
                    value2 = getInt();
                    success = theHeap.change(value, value2);
                    if (!success)
                        System.out.println("Invalid index");
                    break;
                default:
                    System.out.println("Invalid entry\n");
            }
        }
    }
    //-----------------------------------------
    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }
    //-----------------------------------------
    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }
    //-----------------------------------------
    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
}
