# Java Library Manager

A command-line library system in Java built on a **linked list and stack written from scratch** (no `java.util` collections for the core logic).

## Features
- Add books, search by title/author, check out, return, and **undo** the last action (undo uses the custom stack)
- `MyLinkedList<T>`: addFirst/addLast/removeFirst O(1), remove/get O(n), iterator support
- `MyStack<T>`: push/pop/peek O(1), built on the linked list
- 22 automated checks in `Tests.java`

## Run
Needs JDK 17+.
```
mkdir out
javac -d out src/*.java
java -cp out Tests      # runs the checks
java -cp out Main       # starts the CLI
```
Commands: `list`, `add <title>;<author>`, `search <text>`, `out <id>`, `back <id>`, `undo`, `quit`

## Design notes
- A tail pointer makes `addLast` O(1) instead of walking the list.
- The stack reuses the list (`push` = `addFirst`, `pop` = `removeFirst`), so both stay O(1).
- Next steps: move tests to JUnit 5, save the catalogue to a file.
