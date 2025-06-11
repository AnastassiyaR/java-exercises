package ee.taltech.iti0202.bookshelf;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private int money;
    private List<Book> books;

    /**
     * Constructor
     */
    public Person(String name, int money) {
        this.name = name;
        this.money = money;
        this.books = new ArrayList<>();
    }
    /**
     * Get name
     */
    public String getName() {
        return name;
    }

    /**
     * Get money
     */
    public int getMoney() {
        return money;
    }

    /**
     * Buy a book
     */
    public boolean buyBook(Book book) {
        if (book == null || book.getOwner() != null || this.money < book.getPrice()) {
            return false;
        }

        this.money -= book.getPrice();
        this.books.add(book);
        book.owner = this;
        return true;
    }

    /**
     * Sell a book
     */
    public boolean sellBook(Book book) {
        if (book == null || !this.books.contains(book)) {
            return false;
        }

        this.money += book.getPrice();
        book.owner = null;
        this.books.remove(book);
        return true;
    }

    public List<Book> getBooks() {
        return new ArrayList<>(this.books);
    }
}
