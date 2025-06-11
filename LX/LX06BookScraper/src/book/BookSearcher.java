package book;

import java.io.IOException;
import java.util.List;

public interface BookSearcher {

    List<Book> getBooksByTopic(String topic) throws IOException;

    List<Book> getBooksByAuthor(Author author) throws IOException;

    List<Book> getAllBooks() throws IOException;
}
