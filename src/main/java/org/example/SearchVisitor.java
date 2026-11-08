package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchVisitor implements Visitor {
    private final String ending;
    private final long minSize;
    private final List<String> results = new ArrayList<>();
    private String path = ".";

    private SearchVisitor(String ending, long minSize) {
        this.ending = ending;
        this.minSize = minSize;
    }

    public static List<String> search(Element root, String ending, long minSize) {
        SearchVisitor visitor = new SearchVisitor(ending, minSize);
        root.accept(visitor);
        Collections.sort(visitor.results);
        return visitor.results;
    }

    @Override
    public void visit(FileNode file) {
        if (file.getName().endsWith(ending) && file.getSize() >= minSize) {
            results.add(path);
        }
    }

    @Override
    public void visit(DirectoryNode directory) {
        String parentPath = path;

        for (Element child : directory.getChildren()) {
            if (parentPath.equals(".")) {
                path = child.getName();
            } else {
                path = parentPath + "/" + child.getName();
            }

            child.accept(this);
        }

        path = parentPath;
    }
}