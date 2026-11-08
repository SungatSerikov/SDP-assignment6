package org.example;

public class Main {
    public static void main(String[] args) {
        DirectoryNode root = new DirectoryNode("root");
        DirectoryNode docs = new DirectoryNode("docs");

        root.addChild(new FileNode("readme.txt", 80));
        root.addChild(docs);
        docs.addChild(new FileNode("notes.txt", 120));

        System.out.println("root: " + TotalSizeVisitor.calculate(root) + " bytes");
        System.out.println("docs: " + TotalSizeVisitor.calculate(docs) + " bytes");

        System.out.println(SearchVisitor.search(root, ".txt", 100));

        StatisticsVisitor stats = StatisticsVisitor.calculate(root);

        System.out.println("Files: " + stats.getFileCount());
        System.out.println("Directories: " + stats.getDirectoryCount());
        System.out.println("Max depth: " + stats.getMaxDepth());
    }
}