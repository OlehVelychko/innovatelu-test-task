# InnovatelU Test Task

## Description

This project is a solution for a test task, with the task description available in the `task_description` file within the project structure. It demonstrates the implementation of a `DocumentManager` class that manages documents in memory. The focus is on clean, simple, and readable code, following the task requirements.

## Features

1. **Save Documents**: 
   - The `save` method allows you to add or update a document in the in-memory storage.
   - If a document does not have an ID, a unique one is automatically generated.
2. **Search Documents**: 
   - The `search` method supports filtering documents by various criteria, including:
     - Title prefixes
     - Content substrings
     - Author IDs
     - Creation date range
3. **Find by ID**: 
   - The `findById` method retrieves a document by its unique ID.
4. **Human-Readable Output**: 
   - The `DocumentFormatter` utility formats documents and authors for clean console output.

## Technologies Used

- **Java 17**
- **Lombok**: Simplifies POJO class creation (e.g., getters, setters, builders).
- **JUnit 5**: Used for testing the `DocumentManager` methods.
- **Maven**: Project build and dependency management.

## Project Structure

```plaintext
src/main/java/
├── ua/com/alicecompany/
│   ├── App.java               # Entry point of the application
│   ├── DocumentManager.java   # Core logic for document management
│   └── utils/
│       └── DocumentFormatter.java # Utility class for formatting output
src/test/java/
└── ua/com/alicecompany/
    └── DocumentManagerTest.java # Unit tests for DocumentManager

