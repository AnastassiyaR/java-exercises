package ee.taltech.iti0202.libraryapi;

import ee.taltech.iti0202.libraryapi.api.LibraryApi;
import ee.taltech.iti0202.libraryapi.exception.ApiException;
import ee.taltech.iti0202.libraryapi.struct.Book;

import java.util.Arrays;
import java.util.Optional;


public class Library {
    private final LibraryApi api;

    /**
     * Constructor of library
     */
    public Library() {
        this.api = new LibraryApi("https://cs.taltech.ee/services/library");
        try {
            this.api.authenticate("/auth");
        } catch (ApiException e) {
            throw new RuntimeException("Failed to authenticate: " + e.getMessage());
        }
    }

    /**
     * Create book
     * @param book
     * @return book
     */
    public Optional<Book> createBook(Book book) {
        try {
            Book created = api.post("/library", book, Book.class);
            return Optional.ofNullable(created);
        } catch (ApiException e) {
            return Optional.empty();
        }
    }

    /**
     * Read books by id
     * @param id
     * @return books
     */
    public Optional<Book> readBookById(int id) {
        try {
            Book book = api.get("/library/" + id, Book.class);
            return Optional.ofNullable(book);
        } catch (ApiException e) {
            return Optional.empty();
        }
    }

    /**
     * Read all books
     * @return books
     */
    public Book[] readAllBooks() {
        try {
            Book[] books = api.get("/library", Book[].class);
            return books;
        } catch (ApiException e) {
            return new Book[0];
        }
    }

    /**
     * Read all books by pages
     * @param pageSize
     * @param pageNo
     * @return books
     */
    public Book[] readAllBooks(int pageSize, int pageNo) {
        try {
            Book[] books = api.get("/library?pageSize=" + pageSize + "&pageNo=" + pageNo, Book[].class);
            return books;
        } catch (ApiException e) {
            return new Book[0];
        }
    }

    /**
     * Update book
     * @param id
     * @param book
     * @return updated book
     */
    public Optional<Book> updateBook(int id, Book book) {
        try {
            Book updatedBook = api.put("/library/" + id, book, Book.class);
            return Optional.ofNullable(updatedBook);
        } catch (ApiException e) {
            return Optional.empty();
        }
    }

    /**
     * Delete book
     * @param id
     * @return
     */
    public boolean deleteBook(int id) {
        try {
            api.delete("/library/" + id);
            return true;
        } catch (ApiException e) {
            return false;
        }
    }

    /**
     * Get api
     * @return api
     */
    public LibraryApi getApi() {
        return api;
    }

    /**
     * Main
     */
    public static void main(String[] args) {
        Library library = new Library();

        Optional<Book> optionalBook = library.readBookById(5);
        optionalBook.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Book not found")
        );

        System.out.println(Arrays.toString(library.readAllBooks()));
        System.out.println(Arrays.toString(library.readAllBooks(3, 4)));

        Book book = new Book.Builder()
                .setAuthor("J. D. Rowling")
                .setCountry("United Kingdom")
                .setLanguage("English")
                .setTitle("Harry Potter and the Philosopher's Stone")
                .build();

        try {
            Book updatedBook = library.createBook(book).orElseThrow(()
                    -> new Exception("Book not created but should be"));
            System.out.println(updatedBook);
            updatedBook.setAuthor("J. K. Rowling");
            System.out.println(library.updateBook(updatedBook.getId(), updatedBook).get());
            System.out.println(updatedBook.getId());
            System.out.println(library.deleteBook(updatedBook.getId()));
            System.out.println(library.readBookById(updatedBook.getId()));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        Book book2 = new Book.Builder()
                .setAuthor("J.")
                .setCountry("United Kingdom")
                .setLanguage("English")
                .setTitle("Harry Potter and the Philosopher's Stone")
                .build();
        try {
            library.createBook(book2);
        } catch (ApiException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
