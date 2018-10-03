package hw6.List;


import hw6.dict.Entry;

public class SListNode {
    public Entry entry;
    public SListNode next;

    public SListNode(Entry en) {
        entry = en;
        next = null;
    }
}
