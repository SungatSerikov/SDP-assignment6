# Assignment 6 — Visitor Pattern

This Java project models files and directories in memory. It uses three visitors:

- `TotalSizeVisitor` calculates the total size of the files in a subtree.
- `SearchVisitor` finds files by filename ending and minimum size. It returns sorted paths relative to the selected root.
- `StatisticsVisitor` counts files and directories and finds the maximum depth. The selected root has depth 0.

## Requirements

- JDK 21
- Apache Maven

Run the following commands from the project directory that contains `pom.xml`.

## Run the tests

```bash
mvn test
```

## Run the application

```bash
mvn compile
java -cp target/classes org.example.Main
```

## How traversal works

Each visitor starts at the selected root. When it visits a directory, it calls
`accept(this)` on each child. When it visits a file, it performs its own
operation. Each calculation or search creates a fresh visitor.