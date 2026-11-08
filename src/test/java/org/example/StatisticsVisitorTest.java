package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatisticsVisitorTest {

    @Test
    void countsFilesDirectoriesAndMaximumDepth() {
        DirectoryNode root = new DirectoryNode("root");
        DirectoryNode docs = new DirectoryNode("docs");
        DirectoryNode archive = new DirectoryNode("archive");

        root.addChild(docs);
        docs.addChild(archive);
        archive.addChild(new FileNode("old.txt", 30));
        root.addChild(new FileNode("readme.txt", 10));

        StatisticsVisitor stats = StatisticsVisitor.calculate(root);

        assertEquals(2, stats.getFileCount());
        assertEquals(3, stats.getDirectoryCount());
        assertEquals(3, stats.getMaxDepth());
    }

    @Test
    void countsEmptyRootAsOneDirectoryAtDepthZero() {
        DirectoryNode root = new DirectoryNode("root");

        StatisticsVisitor stats = StatisticsVisitor.calculate(root);

        assertEquals(0, stats.getFileCount());
        assertEquals(1, stats.getDirectoryCount());
        assertEquals(0, stats.getMaxDepth());
    }
}