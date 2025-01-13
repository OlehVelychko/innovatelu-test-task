package ua.com.alicecompany.utils;

import ua.com.alicecompany.DocumentManager;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DocumentFormatter {

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd 'T' HH:mm:ss")
                    .withZone(ZoneId.of("UTC"));

    /**
     * Formats a document into a human-readable string.
     *
     * @param document the document to format
     * @return formatted string representation of the document
     */
    public static String formatDocument(DocumentManager.Document document) {
        return String.format(
                """
                        Document:
                          ID: %s
                          Title: %s
                          Content: %s
                          Author: %s
                          Created: %s
                        """,
                document.getId(),
                document.getTitle(),
                document.getContent(),
                formatAuthor(document.getAuthor()),
                formatInstant(document.getCreated())
        );
    }

    /**
     * Formats an author into a human-readable string.
     *
     * @param author the author to format
     * @return formatted string representation of the author
     */
    public static String formatAuthor(DocumentManager.Author author) {
        return String.format("ID: %s, Name: %s", author.getId(), author.getName());
    }

    /**
     * Formats an Instant into a human-readable string.
     *
     * @param instant the instant to format
     * @return formatted string representation of the instant
     */
    private static String formatInstant(Instant instant) {
        return DATE_FORMATTER.format(instant);
    }
}