import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library lib = new Library();
        lib.addBook("Clean Code", "Robert Martin");
        lib.addBook("Introduction to Algorithms", "Cormen");
        lib.addBook("Effective Java", "Joshua Bloch");

        Scanner in = new Scanner(System.in);
        System.out.println("Library Manager. Commands: list, add <title>;<author>, search <text>, out <id>, back <id>, undo, quit");
        while (true) {
            System.out.print("> ");
            if (!in.hasNextLine()) break;
            String line = in.nextLine().trim();
            String[] p = line.split(" ", 2);
            String arg = p.length > 1 ? p[1] : "";
            try {
                switch (p[0]) {
                    case "list" -> {
                        System.out.println("Available:");
                        for (Book b : lib.available()) System.out.println("  " + b);
                        System.out.println("Checked out:");
                        for (Book b : lib.checkedOut()) System.out.println("  " + b);
                    }
                    case "add" -> {
                        String[] ta = arg.split(";");
                        if (ta.length != 2) { System.out.println("Use: add <title>;<author>"); break; }
                        System.out.println("Added " + lib.addBook(ta[0].trim(), ta[1].trim()));
                    }
                    case "search" -> { for (Book b : lib.search(arg)) System.out.println("  " + b); }
                    case "out" -> System.out.println(lib.checkout(Integer.parseInt(arg)) ? "Checked out." : "Not available.");
                    case "back" -> System.out.println(lib.giveBack(Integer.parseInt(arg)) ? "Returned." : "Not checked out.");
                    case "undo" -> System.out.println(lib.undo() ? "Undone." : "Nothing to undo.");
                    case "quit" -> { return; }
                    default -> System.out.println("Unknown command.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please give a numeric id.");
            }
        }
    }
}
