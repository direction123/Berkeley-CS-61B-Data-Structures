package hw6.List;

import hw6.dict.Entry;

public class SList {
    private SListNode first;
    public void SList() {
        first = null;
    }
    public void insertEnd(Entry entry) {
        if (first == null) {
            first = new SListNode(entry);
            return;
        }
        SListNode previous = null;  // start at first
        SListNode current = first;
        // until end of list
        while (current != null) {
            previous = current;
            current = current.next; // go to next item
        }
        previous.next = new SListNode(entry);
    }

    public Entry find(Object key) {
        SListNode current = first;  // start at first

        while (current != null) {
            if (current.entry.key().equals(key)) {
                return current.entry;
            }
            current = current.next;
        }
        return null; // didn't find it
    }

    public Entry delete(Object key) {
        SListNode previous = null;
        SListNode current = first;

        // until end of list, or key == current
        while (current != null && !current.entry.key().equals(key)) {
            previous = current;
            current = current.next; // go to next link
        }
        if (previous == null) {
            first = first.next; // if beginning of list, delete first link
        } else {
            previous.next = current.next;
        } // end delete()
        return current.entry;
    }
}
