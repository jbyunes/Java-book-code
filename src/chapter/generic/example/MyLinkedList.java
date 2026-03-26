package chapter.generic.example;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class MyLinkedList<T> implements MyList<T> {
    private class Node {
        T element;
        Node next;
        public Node(T e) { element = e; next = null; }
    }
    private Node head;
    private Node tail;
    private int size;

    @Override public void add(T e) {
        Node n = new Node(e);
        if (head==null) { head = n; }
        else            { tail.next = n; }
        tail = n;
        this.size++;
    }

    @Override public T get(int idx) {
        Objects.checkIndex(idx, size);
        Node p = head;
        for (int i=1; i<=idx; i++) { p = p.next; }
        return p.element;
    }

    @Override public Iterator<T> iterator() {
        return new Iterator<>() {
            Node current = head;
            @Override public boolean hasNext() { return current!=null; }
            @Override public T next() {
                if (current==null) {
                    throw new NoSuchElementException("Iteration exhausted");
                }
                T e = current.element;
                current = current.next;
                return e;
            }
        };
    }
    @Override public int size() { return this.size; }
}
