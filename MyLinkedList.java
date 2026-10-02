import java.util.Iterator;
import java.util.NoSuchElementException;

/** Singly linked list written from scratch (no java.util collections). */
public class MyLinkedList<T> implements Iterable<T> {
    private static class Node<T> {
        T value;
        Node<T> next;
        Node(T value) { this.value = value; }
    }

    private Node<T> head, tail;
    private int size;

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    /** O(1) */
    public void addFirst(T value) {
        Node<T> n = new Node<>(value);
        n.next = head;
        head = n;
        if (tail == null) tail = n;
        size++;
    }

    /** O(1) thanks to the tail pointer */
    public void addLast(T value) {
        Node<T> n = new Node<>(value);
        if (tail == null) head = tail = n;
        else { tail.next = n; tail = n; }
        size++;
    }

    /** O(1) */
    public T removeFirst() {
        if (head == null) throw new NoSuchElementException("list is empty");
        T v = head.value;
        head = head.next;
        if (head == null) tail = null;
        size--;
        return v;
    }

    /** Removes the first element equal to value. O(n). Returns true if removed. */
    public boolean remove(T value) {
        Node<T> prev = null, cur = head;
        while (cur != null) {
            if (cur.value.equals(value)) {
                if (prev == null) head = cur.next; else prev.next = cur.next;
                if (cur == tail) tail = prev;
                size--;
                return true;
            }
            prev = cur;
            cur = cur.next;
        }
        return false;
    }

    /** O(n) */
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("index " + index);
        Node<T> cur = head;
        for (int i = 0; i < index; i++) cur = cur.next;
        return cur.value;
    }

    public boolean contains(T value) {
        for (T v : this) if (v.equals(value)) return true;
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> cur = head;
            public boolean hasNext() { return cur != null; }
            public T next() {
                if (cur == null) throw new NoSuchElementException();
                T v = cur.value;
                cur = cur.next;
                return v;
            }
        };
    }
}
