package ua.com.alicecompany;

import ua.com.alicecompany.utils.DocumentFormatter;

import java.time.Instant;
import java.util.List;

public class App {
    public static void main(String[] args) {
        DocumentManager documentManager = new DocumentManager();

        // Creating authors
        DocumentManager.Author author1 = DocumentManager.Author.builder()
                .id("1")
                .name("Alice")
                .build();

        DocumentManager.Author author2 = DocumentManager.Author.builder()
                .id("2")
                .name("Marharyta")
                .build();

        // Saving documents
        DocumentManager.Document document1 = DocumentManager.Document.builder()
                .title("Java Guide")
                .content("A comprehensive guide to Java programming.")
                .author(author1)
                .created(Instant.now())
                .build();

        DocumentManager.Document document2 = DocumentManager.Document.builder()
                .title("Lombok Tutorial")
                .content("Learn how to use Lombok effectively.")
                .author(author2)
                .created(Instant.now())
                .build();

        documentManager.save(document1);
        documentManager.save(document2);

        // Printing saved documents
        System.out.println("=== Saved Documents ===");
        System.out.println(DocumentFormatter.formatDocument(documentManager.findById(document1.getId()).orElseThrow()));
        System.out.println(DocumentFormatter.formatDocument(documentManager.findById(document2.getId()).orElseThrow()));

        // Searching for documents
        DocumentManager.SearchRequest searchRequest = DocumentManager.SearchRequest.builder()
                .titlePrefixes(List.of("Java"))
                .authorIds(List.of("1"))
                .build();

        List<DocumentManager.Document> searchResults = documentManager.search(searchRequest);

        // Printing search results
        System.out.println("\n=== Search Results ===");
        searchResults.forEach(doc -> System.out.println(DocumentFormatter.formatDocument(doc)));

        // Updating a document
        document1.setContent("An updated comprehensive guide to Java programming.");
        documentManager.save(document1);

        // Printing updated document
        System.out.println("\n=== Updated Document ===");
        System.out.println(DocumentFormatter.formatDocument(documentManager.findById(document1.getId()).orElseThrow()));
    }
}