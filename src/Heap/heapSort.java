package Heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// heapSort.java
// demonstrates heap sort
class heapSort {
    private Node[] heapArray;
    private int maxSize;  // size of array
    private int currentSize; // number of items in array
    //---------------------------------------
    public heapSort(int mx) {
        // constructor
        maxSize = mx;
        currentSize = 0;
        heapArray = new Node[maxSize];
    }
    //---------------------------------------
    public Node remove() {
        // delete item with max key
        // assume non-empty list
        Node root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }
    //---------------------------------------
    public void trickleDown(int index) {
        int largerChild;
        Node top = heapArray[index]; // save root
        while (index < currentSize/2) {
            // not on bottom row
            int leftChild = 2*index + 1;
            int rightChild = leftChild + 1;
            // find larger child
            if (rightChild < currentSize &&
                    heapArray[leftChild].getKey() < heapArray[rightChild].getKey())
                largerChild = rightChild;
            else
                largerChild = leftChild;

            // top >= largerChild?
            if (top.getKey() >= heapArray[largerChild].getKey())
                break;

            heapArray[index] = heapArray[largerChild];
            index = largerChild; // go down
        }
        heapArray[index] = top; // root to index
    } // end trickleDown()
    //------------------------------
    public void insertAt(int index, Node newNode) {
        heapArray[index] = newNode;
    }
    //------------------------------
    public void incrementSize() {
        currentSize++;
    }
    //------------------------------
    public void displayArray() {
        System.out.print("heapArray: ");
        // array format
        for (int m = 0; m < maxSize; m++) {
            if (heapArray[m] != null)
                System.out.print(heapArray[m].getKey() + " ");
            else
                System.out.print("-- ");
        }
        System.out.println();
    }
    //------------------------------
    public void displayHeap() {
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
class HeapSortApp {
    public static void main(String[] args) throws IOException {
        int size, j;
        System.out.print("Enter number of items: ");
        size = getInt();
        heapSort theHeap = new heapSort(size);

        for (j = 0; j < size; j++) {
            // fill array with random nodes
            int random = (int)(java.lang.Math.random()*100);
            Node newNode = new Node(random);
            theHeap.insertAt(j, newNode);
            theHeap.incrementSize();
        }
        System.out.print("Random: ");
        theHeap.displayArray(); // display random array

        for (j = size/2 - 1; j >= 0; j--) //make random array into heap
            theHeap.trickleDown(j);

        System.out.print("Heap: ");
        theHeap.displayArray();
        theHeap.displayHeap();

        for (j = size - 1; j >= 0; j--) {
            // remove from heap and store at array end
            Node biggestNode = theHeap.remove();
            theHeap.insertAt(j, biggestNode);
        }
        System.out.print("Sorted: ");
        theHeap.displayArray();
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
