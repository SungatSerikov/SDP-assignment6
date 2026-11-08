package org.example;

public abstract class TreeNode implements Element {
    private final String name;
    private DirectoryNode parent;

    protected TreeNode(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    final void attachTo(DirectoryNode directory) {
        if (parent != null) {
            throw new IllegalArgumentException("Node already has a parent");
        }

        for (TreeNode current = directory; current != null; current = current.parent) {
            if (current == this) {
                throw new IllegalArgumentException("Cannot create a cycle");
            }
        }

        this.parent = directory;
    }
}