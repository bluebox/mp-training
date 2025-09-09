package com.day5_;

public class Node extends List_Item {

    public Node(Object value) {
        super(value);
    }

    @Override
    List_Item next() {
        return this.next;
    }

    @Override
    List_Item setNext(List_Item item) {
        this.next = item;
        return this.next;
    }

    @Override
    List_Item previous() {
        return this.previous;
    }

    @Override
    List_Item setPrevious(List_Item item) {
        this.previous = item;
        return this.previous;
    }

    @Override
    int compareTo(List_Item item) {
        if (item != null) {
            return ((String) super.getValue()).compareTo((String) item.getValue());
        } else {
            return -1;
        }
    }
}