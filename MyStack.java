/** LIFO stack built on top of MyLinkedList (push/pop/peek are all O(1)). */
public class MyStack<T> {
    private final MyLinkedList<T> items = new MyLinkedList<>();

    public void push(T value) { items.addFirst(value); }
    public T pop() { return items.removeFirst(); }
    public T peek() { return items.get(0); }
    public boolean isEmpty() { return items.isEmpty(); }
    public int size() { return items.size(); }
}
