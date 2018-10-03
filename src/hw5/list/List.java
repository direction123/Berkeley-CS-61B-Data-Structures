package hw5.list;

/* SList.java */

/**
 *  A SList is a mutable list ADT.  No implementation is provided.
 *
 *  DO NOT CHANGE THIS FILE.
 **/

public abstract class List {

    /**
     *  size is the number of items in the list.
     **/

    protected int size;

    /**
     *  isEmpty() returns true if this SList is empty, false otherwise.
     *
     *  @return true if this SList is empty, false otherwise.
     *
     *  Performance:  runs in O(1) time.
     **/
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     *  length() returns the length of this SList.
     *
     *  @return the length of this SList.
     *
     *  Performance:  runs in O(1) time.
     **/
    public int length() {
        return size;
    }

    /**
     *  insertFront() inserts an item at the front of this SList.
     *
     *  @param item is the item to be inserted.
     **/
    public abstract void insertFront(Object item);

    /**
     *  insertBack() inserts an item at the back of this SList.
     *
     *  @param item is the item to be inserted.
     **/
    public abstract void insertBack(Object item);

    /**
     *  front() returns the node at the front of this SList.  If the SList is
     *  empty, return an "invalid" node--a node with the property that any
     *  attempt to use it will cause an exception.
     *
     *  @return a ListNode at the front of this SList.
     */
    public abstract ListNode front();

    /**
     *  back() returns the node at the back of this SList.  If the SList is
     *  empty, return an "invalid" node--a node with the property that any
     *  attempt to use it will cause an exception.
     *
     *  @return a ListNode at the back of this SList.
     */
    public abstract ListNode back();

    /**
     *  toString() returns a String representation of this SList.
     *
     *  @return a String representation of this SList.
     */
    public abstract String toString();

}

