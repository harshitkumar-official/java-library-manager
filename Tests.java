import java.util.NoSuchElementException;

/** Dependency-free test runner. Run: java -cp out Tests */
public class Tests {
    static int passed = 0, failed = 0;

    static void check(String name, boolean ok) {
        if (ok) passed++; else { failed++; System.out.println("FAIL: " + name); }
    }

    public static void main(String[] args) {
        // MyLinkedList
        MyLinkedList<Integer> l = new MyLinkedList<>();
        check("new list is empty", l.isEmpty());
        l.addLast(2); l.addLast(3); l.addFirst(1);
        check("size after adds", l.size() == 3);
        check("get(0..2) order", l.get(0) == 1 && l.get(1) == 2 && l.get(2) == 3);
        check("contains", l.contains(2) && !l.contains(9));
        check("remove middle", l.remove(2) && l.size() == 2 && l.get(1) == 3);
        check("remove missing returns false", !l.remove(42));
        check("remove tail then addLast", l.remove(3) && l.size() == 1);
        l.addLast(7);
        check("tail pointer stays valid", l.get(1) == 7);
        check("removeFirst", l.removeFirst() == 1 && l.size() == 1);
        l.removeFirst();
        boolean threw = false;
        try { l.removeFirst(); } catch (NoSuchElementException e) { threw = true; }
        check("removeFirst on empty throws", threw);
        threw = false;
        try { l.get(0); } catch (IndexOutOfBoundsException e) { threw = true; }
        check("get out of range throws", threw);

        // MyStack
        MyStack<String> s = new MyStack<>();
        s.push("a"); s.push("b");
        check("peek is last pushed", s.peek().equals("b"));
        check("pop order (LIFO)", s.pop().equals("b") && s.pop().equals("a") && s.isEmpty());

        // Library
        Library lib = new Library();
        Book b1 = lib.addBook("Clean Code", "Robert Martin");
        Book b2 = lib.addBook("Effective Java", "Joshua Bloch");
        check("search by title", lib.search("clean").size() == 1);
        check("search by author", lib.search("bloch").size() == 1);
        check("checkout moves book", lib.checkout(b1.id()) && lib.available().size() == 1 && lib.checkedOut().size() == 1);
        check("cannot checkout twice", !lib.checkout(b1.id()));
        check("return moves book back", lib.giveBack(b1.id()) && lib.checkedOut().isEmpty());
        check("undo return re-checks out", lib.undo() && lib.checkedOut().size() == 1);
        check("undo checkout restores", lib.undo() && lib.available().size() == 2);
        check("undo with empty history", !lib.undo());
        check("unknown id fails", !lib.checkout(999));

        System.out.println(passed + " passed, " + failed + " failed");
        if (failed > 0) System.exit(1);
    }
}
