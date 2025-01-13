package ua.com.alicecompany;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DocumentManagerTest {

    @Test
    void testSaveAndFindById() {
        DocumentManager documentManager = new DocumentManager();

        // Creating an author
        DocumentManager.Author author = DocumentManager.Author.builder()
                .id("1")
                .name("Alice")
                .build();

        // Creating a document
        DocumentManager.Document document = DocumentManager.Document.builder()
                .title("Java Guide")
                .content("A comprehensive guide to Java programming.")
                .author(author)
                .created(Instant.now())
                .build();

        // Saving the document
        DocumentManager.Document savedDocument = documentManager.save(document);

        // Asserting the ID is generated
        assertNotNull(savedDocument.getId());
        assertEquals("Java Guide", savedDocument.getTitle());

        // Finding the document by ID
        Optional<DocumentManager.Document> retrievedDocument = documentManager.findById(savedDocument.getId());
        assertTrue(retrievedDocument.isPresent());
        assertEquals(savedDocument, retrievedDocument.get());
    }

    @Test
    void testSearchByTitlePrefix() {
        DocumentManager documentManager = new DocumentManager();

        // Creating an author
        DocumentManager.Author author = DocumentManager.Author.builder()
                .id("1")
                .name("Alice")
                .build();

        // Creating documents
        DocumentManager.Document document1 = DocumentManager.Document.builder()
                .title("Java Guide")
                .content("Guide for Java programming.")
                .author(author)
                .created(Instant.now())
                .build();

        DocumentManager.Document document2 = DocumentManager.Document.builder()
                .title("JavaScript Guide")
                .content("Guide for JavaScript programming.")
                .author(author)
                .created(Instant.now())
                .build();

        // Saving documents
        documentManager.save(document1);
        documentManager.save(document2);

        // Searching documents by title prefix
        DocumentManager.SearchRequest request = DocumentManager.SearchRequest.builder()
                .titlePrefixes(List.of("Java"))
                .build();

        List<DocumentManager.Document> results = documentManager.search(request);
        assertEquals(2, results.size());

        // Searching for only Java-related documents
        request = DocumentManager.SearchRequest.builder()
                .titlePrefixes(List.of("Java "))
                .build();

        results = documentManager.search(request);
        assertEquals(1, results.size());
        assertEquals(document1, results.get(0));
    }

    @Test
    void testSearchByAuthorId() {
        DocumentManager documentManager = new DocumentManager();

        // Creating authors
        DocumentManager.Author author1 = DocumentManager.Author.builder()
                .id("1")
                .name("Alexander")
                .build();

        DocumentManager.Author author2 = DocumentManager.Author.builder()
                .id("2")
                .name("Sophie")
                .build();

        // Creating documents
        DocumentManager.Document document1 = DocumentManager.Document.builder()
                .title("Java Guide")
                .content("Guide for Java programming.")
                .author(author1)
                .created(Instant.now())
                .build();

        DocumentManager.Document document2 = DocumentManager.Document.builder()
                .title("Python Guide")
                .content("Guide for Python programming.")
                .author(author2)
                .created(Instant.now())
                .build();

        // Saving documents
        documentManager.save(document1);
        documentManager.save(document2);

        // Searching documents by author ID
        DocumentManager.SearchRequest request = DocumentManager.SearchRequest.builder()
                .authorIds(List.of("1"))
                .build();

        List<DocumentManager.Document> results = documentManager.search(request);
        assertEquals(1, results.size());
        assertEquals(document1, results.get(0));
    }
}