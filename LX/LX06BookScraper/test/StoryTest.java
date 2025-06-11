import book.Author;
import book.Book;
import org.junit.jupiter.api.Test;
import story.BookStory;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StoryTest {

    BookStory bookStory = new BookStory();

    @Test
    public void testGetAllBooks() throws IOException {
        List<Book> allBooks = bookStory.getAllBooks();

        assertNotNull(allBooks);
        assertFalse(allBooks.isEmpty());
        System.out.println("Total books scraped: " + allBooks.size());

        for (Book book : allBooks) {
            assertNotNull(book.getTitle());
            assertNotNull(book.getUrl());
            assertTrue(book.getUrl().startsWith("http"));
        }
    }

    @Test
    public void testGetBooksByTopic() throws IOException {
        List<Book> allBooks = bookStory.getAllBooks();

        String existingTopic = findExistingTopic(allBooks);
        assertNotNull(existingTopic, "No books with topics found");

        List<Book> booksByTopic = bookStory.getBooksByTopic(existingTopic);

        assertNotNull(booksByTopic);
        assertFalse(booksByTopic.isEmpty());
    }

    @Test
    public void testGetBooksByAuthor() throws IOException {
        List<Book> allBooks = bookStory.getAllBooks();

        Book bookWithAuthor = findBookWithAuthor(allBooks);
        if (bookWithAuthor == null) {
            System.out.println("No books with authors found");
            return;
        }

        Author testAuthor = bookWithAuthor.getAuthor();
        List<Book> booksByAuthor = bookStory.getBooksByAuthor(testAuthor);
        assertNotNull(booksByAuthor);
    }

    @Test
    public void testGetBooksByNullAuthor() throws IOException {
        List<Book> books = bookStory.getBooksByAuthor(null);
        assertTrue(books.isEmpty());
    }

    private String findExistingTopic(List<Book> books) {
        for (Book book : books) {
            if (book.getTopics() != null && !book.getTopics().isEmpty()) {
                return book.getTopics().getFirst();
            }
        }
        return null;
    }

    private Book findBookWithAuthor(List<Book> books) {
        for (Book book : books) {
            if (book.getAuthor() != null) {
                return book;
            }
        }
        return null;
    }
}
