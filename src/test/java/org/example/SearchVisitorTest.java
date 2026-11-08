package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchVisitorTest {

    @Test
    void findsFilesByEndingAndMinimumSize() {
        DirectoryNode root = new DirectoryNode("root");
        root.addChild(new FileNode("notes.txt", 100));
        root.addChild(new FileNode("draft.txt", 99));
        root.addChild(new FileNode("photo.jpg", 200));

        List<String> result = SearchVisitor.search(root, ".txt", 100);

        assertEquals(List.of("notes.txt"), result);
    }

    @Test
    void returnsSortedPathsRelativeToChosenDirectory() {
        DirectoryNode root = new DirectoryNode("root");
        DirectoryNode docs = new DirectoryNode("docs");
        DirectoryNode archive = new DirectoryNode("archive");

        root.addChild(docs);
        docs.addChild(new FileNode("z.txt", 10));
        docs.addChild(archive);
        archive.addChild(new FileNode("a.txt", 20));

        List<String> result = SearchVisitor.search(docs, ".txt", 0);

        assertEquals(List.of("archive/a.txt", "z.txt"), result);


    }

    @Test
    void returnsEmptyListWhenNothingMatches() {
        DirectoryNode root = new DirectoryNode("root");
        root.addChild(new FileNode("photo.jpg", 200));

        List<String> result = SearchVisitor.search(root, ".txt", 100);

        assertEquals(List.of(), result);
    }

    @Test
    void secondSearchReturnsOnlyItsOwnMatches() {
        DirectoryNode root = new DirectoryNode("root");
        root.addChild(new FileNode("notes.txt", 100));
        root.addChild(new FileNode("photo.jpg", 200));

        List<String> first = SearchVisitor.search(root, ".txt", 0);
        List<String> second = SearchVisitor.search(root, ".jpg", 0);

        assertEquals(List.of("notes.txt"), first);
        assertEquals(List.of("photo.jpg"), second);
    }
}