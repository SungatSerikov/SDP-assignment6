package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class VisitorDispatchTest {

    static class TestVisitor implements Visitor {
        FileNode visitedFile;
        DirectoryNode visitedDirectory;

        @Override
        public void visit(FileNode file) {
            visitedFile = file;
        }

        @Override
        public void visit(DirectoryNode directory) {
            visitedDirectory = directory;
        }
    }

    @Test
    void callsCorrectVisitForFileAndDirectory() {
        FileNode file = new FileNode("notes.txt", 10);
        DirectoryNode folder = new DirectoryNode("docs");
        TestVisitor visitor = new TestVisitor();

        Element element = file;
        element.accept(visitor);
        assertSame(file, visitor.visitedFile);

        element = folder;
        element.accept(visitor);
        assertSame(folder, visitor.visitedDirectory);
    }
}