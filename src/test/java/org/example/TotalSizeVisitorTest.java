package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TotalSizeVisitorTest {

    @Test
    void sumsFilesInNestedDirectories() {
        DirectoryNode root = new DirectoryNode("root");
        DirectoryNode docs = new DirectoryNode("docs");
        root.addChild(docs);

        root.addChild(new FileNode("readme.txt", 80));
        docs.addChild(new FileNode("notes.txt", 120));

        long actualSize = TotalSizeVisitor.calculate(root);

        assertEquals(200L, actualSize);
    }

    @Test
    void repeatedCalculationDoesNotDoubleTheSize() {
        DirectoryNode root = new DirectoryNode("root");
        root.addChild(new FileNode("notes.txt", 120));

        assertEquals(120L, TotalSizeVisitor.calculate(root));
        assertEquals(120L, TotalSizeVisitor.calculate(root));
    }
}