package org.example;

public class TotalSizeVisitor implements Visitor {
    private long totalSize;

    private TotalSizeVisitor() {
    }

    public static long calculate(Element root) {
        TotalSizeVisitor visitor = new TotalSizeVisitor();
        root.accept(visitor);
        return visitor.totalSize;
    }

    @Override
    public void visit(FileNode file) {
        totalSize += file.getSize();
    }

    @Override
    public void visit(DirectoryNode directory) {
        for (Element child : directory.getChildren()) {
            child.accept(this);
        }
    }
}