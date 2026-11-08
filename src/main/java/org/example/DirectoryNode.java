package org.example;

import java.util.ArrayList;
import java.util.List;

public class DirectoryNode extends TreeNode {
    private final List<TreeNode> children = new ArrayList<>();

    public DirectoryNode(String name) {
        super(name);
    }

    public void addChild(TreeNode child) {
        for (TreeNode existing : children) {
            if (existing.getName().equals(child.getName())) {
                throw new IllegalArgumentException(
                        "Duplicate name in directory: " + child.getName()
                );
            }
        }

        child.attachTo(this);
        children.add(child);
    }

    public List<TreeNode> getChildren() {
        return List.copyOf(children);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}