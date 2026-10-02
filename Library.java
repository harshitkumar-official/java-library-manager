/** Library logic: add, search, check out, return, and undo the last action. */
public class Library {
    private enum Type { CHECKOUT, RETURN }
    private record Action(Type type, Book book) {}

    private final MyLinkedList<Book> available = new MyLinkedList<>();
    private final MyLinkedList<Book> checkedOut = new MyLinkedList<>();
    private final MyStack<Action> history = new MyStack<>();
    private int nextId = 1;

    public Book addBook(String title, String author) {
        Book b = new Book(nextId++, title, author);
        available.addLast(b);
        return b;
    }

    public MyLinkedList<Book> search(String text) {
        MyLinkedList<Book> found = new MyLinkedList<>();
        String q = text.toLowerCase();
        for (Book b : available)
            if (b.title().toLowerCase().contains(q) || b.author().toLowerCase().contains(q)) found.addLast(b);
        return found;
    }

    public boolean checkout(int id) {
        Book b = find(available, id);
        if (b == null) return false;
        available.remove(b);
        checkedOut.addLast(b);
        history.push(new Action(Type.CHECKOUT, b));
        return true;
    }

    public boolean giveBack(int id) {
        Book b = find(checkedOut, id);
        if (b == null) return false;
        checkedOut.remove(b);
        available.addLast(b);
        history.push(new Action(Type.RETURN, b));
        return true;
    }

    /** Reverses the most recent checkout/return. Returns false if there is nothing to undo. */
    public boolean undo() {
        if (history.isEmpty()) return false;
        Action a = history.pop();
        if (a.type() == Type.CHECKOUT) { checkedOut.remove(a.book()); available.addLast(a.book()); }
        else { available.remove(a.book()); checkedOut.addLast(a.book()); }
        return true;
    }

    public MyLinkedList<Book> available() { return available; }
    public MyLinkedList<Book> checkedOut() { return checkedOut; }

    private static Book find(MyLinkedList<Book> list, int id) {
        for (Book b : list) if (b.id() == id) return b;
        return null;
    }
}
