package ee.taltech.iti0202.bookshelf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Book {
    private static int nextId = 0;
    private static Map<String, Map<String, Map<Integer, Book>>> allBooksMap = new HashMap<>();
    private static Map<String, List<Book>> booksByAuthor = new HashMap<>();
    private static Book lastCreatedBook = null; // Track the most recently created book

    private int id;
    private String title;
    private String author;
    private int yearOfPublishing;
    private int price;
    public Person owner;

    /**
     * Constructor
     */
    public Book(String title, String author, int yearOfPublishing, int price) {
        this.id = getAndIncrementNextId();
        this.title = title;
        this.author = author;
        this.yearOfPublishing = yearOfPublishing;
        this.price = price;
        this.owner = null;
    }

    /**
     * Add 1 point to id
     */
    public static int getAndIncrementNextId() {
        return nextId++;
    }

    /**
     * Get a title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get an author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Get a year of publishing
     */
    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    /**
     * Get a price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Get an id
     */
    public int getId() {
        return id;
    }

    /**
     * Get an owner
     */
    public Person getOwner() {
        return owner;
    }

    /**
     * Book buying process
     */
    public boolean buy(Person buyer) {
        if (buyer == null) {
            if (this.owner != null) {
                this.owner.sellBook(this);
                this.owner = null;
            }
            return true;
        }

        if (this.owner == buyer) {
            return false; // Cannot buy a book you already own
        }

        if (buyer.getMoney() < this.price) {
            return false; // Buyer doesn't have enough money
        }

        if (this.owner != null) {
            this.owner.sellBook(this); // Current owner sells the book
        }

        buyer.buyBook(this); // Buyer buys the book
        this.owner = buyer; // Update the owner
        return true;
    }

    /**
     * Check if the book already exists
     */
    public static Book of(String title, String author, int yearOfPublishing, int price) {
        // Normalize keys for case-insensitive comparison
        String normalizedTitle = title.toLowerCase();
        String normalizedAuthor = author.toLowerCase();

        // Check if the book already exists
        if (allBooksMap.containsKey(normalizedTitle)) {
            Map<String, Map<Integer, Book>> authorMap = allBooksMap.get(normalizedTitle);
            if (authorMap.containsKey(normalizedAuthor)) {
                Map<Integer, Book> yearMap = authorMap.get(normalizedAuthor);
                if (yearMap.containsKey(yearOfPublishing)) {
                    return yearMap.get(yearOfPublishing); // Return existing book
                }
            }
        }

        // Create a new book
        Book newBook = new Book(title, author, yearOfPublishing, price);

        // Add the new book to the nested map structure
        allBooksMap
                .computeIfAbsent(normalizedTitle, k -> new HashMap<>())
                .computeIfAbsent(normalizedAuthor, k -> new HashMap<>())
                .put(yearOfPublishing, newBook);

        // Add the new book to the booksByAuthor map
        booksByAuthor.computeIfAbsent(normalizedAuthor, k -> new ArrayList<>()).add(newBook);

        // Update lastCreatedBook
        lastCreatedBook = newBook;

        return newBook;
    }

    /**
     * Add a book by title and price
     */
    public static Book of(String title, int price) {
        if (lastCreatedBook == null) {
            return null; // No previous book exists
        }
        return of(title, lastCreatedBook.getAuthor(), lastCreatedBook.getYearOfPublishing(), price);
    }

    /**
     * Get books by owner
     */
    public static List<Book> getBooksByOwner(Person owner) {
        if (owner == null) {
            return new ArrayList<>();
        }
        return owner.getBooks();
    }

    /**
     * Remove a book
     */
    public static boolean removeBook(Book book) {
        if (book == null) {
            return false;
        }

        // Normalize keys for case-insensitive comparison
        String normalizedTitle = book.getTitle().toLowerCase();
        String normalizedAuthor = book.getAuthor().toLowerCase();
        int yearOfPublishing = book.getYearOfPublishing();

        // Check if the book exists in the nested map structure
        if (!allBooksMap.containsKey(normalizedTitle)
                || !allBooksMap.get(normalizedTitle).containsKey(normalizedAuthor)
                || !allBooksMap.get(normalizedTitle).get(normalizedAuthor).containsKey(yearOfPublishing)) {
            return false; // Book not found
        }

        // Remove the book from the nested map structure
        allBooksMap.get(normalizedTitle).get(normalizedAuthor).remove(yearOfPublishing);

        // Clean up empty maps
        if (allBooksMap.get(normalizedTitle).get(normalizedAuthor).isEmpty()) {
            allBooksMap.get(normalizedTitle).remove(normalizedAuthor);
        }
        if (allBooksMap.get(normalizedTitle).isEmpty()) {
            allBooksMap.remove(normalizedTitle);
        }

        // Remove the book from the booksByAuthor map
        List<Book> authorBooks = booksByAuthor.get(normalizedAuthor);
        if (authorBooks != null) {
            authorBooks.remove(book);
            if (authorBooks.isEmpty()) {
                booksByAuthor.remove(normalizedAuthor); // Clean up if no books left by this author
            }
        }

        // If the book has an owner, sell the book
        if (book.getOwner() != null) {
            book.getOwner().sellBook(book);
        }

        return true;
    }

    /**
     * Get books by author
     */
    public static List<Book> getBooksByAuthor(String author) {
        return booksByAuthor.getOrDefault(author.toLowerCase(), new ArrayList<>());
    }
}
