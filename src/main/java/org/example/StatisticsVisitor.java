package org.example;

public class StatisticsVisitor implements Visitor {
    private int fileCount;
    private int directoryCount;
    private int currentDepth;
    private int maxDepth;

    public static StatisticsVisitor calculate(Element root) {
        StatisticsVisitor stats = new StatisticsVisitor();
        root.accept(stats);
        return stats;
    }

    @Override
    public void visit(FileNode file) {
        fileCount++;
    }

    @Override
    public void visit(DirectoryNode directory) {
        directoryCount++;

        for (Element child : directory.getChildren()) {
            currentDepth++;
            maxDepth = Math.max(maxDepth, currentDepth);
            child.accept(this);
            currentDepth--;
        }
    }

    public int getFileCount() {
        return fileCount;
    }

    public int getDirectoryCount() {
        return directoryCount;
    }

    public int getMaxDepth() {
        return maxDepth;
    }
}