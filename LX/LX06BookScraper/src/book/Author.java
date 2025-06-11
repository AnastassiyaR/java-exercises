package book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;

public record Author(String firstName, String lastName, @JsonIgnore List<Book> books) {

    public Author(String firstName, String lastName) {
        this(firstName, lastName, new ArrayList<>());
    }

    @Override
    public String toString() {
        return "Author{"
                + "firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + ", booksCount=" + (books != null ? books.size() : 0)
                + '}';
    }

    public void addBook(Book book) {
        books.add(book);
    }

}
