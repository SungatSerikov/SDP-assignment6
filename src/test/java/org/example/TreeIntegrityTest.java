package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class TreeIntegrityTest {

    @Test
    void rejectsNegativeFileSize() {
        try {
            new FileNode("notes.txt", -1);
            fail("File with a negative size was created");
        } catch (IllegalArgumentException expected) {

        }
    }

    @Test
    void rejectsDuplicateNamesInSameDirectory() {
        DirectoryNode docs = new DirectoryNode("docs");
        docs.addChild(new FileNode("notes.txt", 10));

        try {
            docs.addChild(new FileNode("notes.txt", 20));
            fail("Two files with the same name were added");
        } catch (IllegalArgumentException expected) {
            assertEquals(1, docs.getChildren().size());
        }
    }

    @Test
    void rejectsFileWithTwoParents() {
        DirectoryNode docs = new DirectoryNode("docs");
        DirectoryNode archive = new DirectoryNode("archive");
        FileNode file = new FileNode("notes.txt", 10);
        docs.addChild(file);

        try {
            archive.addChild(file);
            fail("The same file was added to a second directory");
        } catch (IllegalArgumentException expected) {
            assertEquals(1, docs.getChildren().size());
            assertEquals(0, archive.getChildren().size());
        }
    }

    @Test
    void rejectsDirectoryCycle() {
        DirectoryNode root = new DirectoryNode("root");
        DirectoryNode docs = new DirectoryNode("docs");
        root.addChild(docs);

        try {
            docs.addChild(root);
            fail("A directory cycle was created");
        } catch (IllegalArgumentException expected) {
            assertEquals(0, docs.getChildren().size());
        }
    }
}