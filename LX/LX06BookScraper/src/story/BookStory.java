package story;

import book.Author;
import book.Book;
import book.BookSearcher;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.List;
import java.util.stream.Collectors;

import static webscraper.BookDataSaver.scrapeBooksFromWebsites;

public class BookStory implements BookSearcher {

    public BookStory() { }

    @Override
    public List<Book> getBooksByTopic(String topic) throws IOException {
        return getAllBooks().stream().filter(book -> book.getTopics().contains(topic)).collect(Collectors.toList());
    }

    @Override
    public List<Book> getBooksByAuthor(Author author) throws IOException {
        return getAllBooks().stream()
                .filter(book -> book.getAuthor() != null && book.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getAllBooks() throws IOException {
        String jsonContent;
        try {
            jsonContent = new String(Files.readAllBytes(Paths.get("books.json")));
        } catch (IOException e) {
            System.out.println("Got error about json file");
            return scrapeBooksFromWebsites();
        }
        if (jsonContent.isEmpty()) {
            System.out.println("Json content is empty");
            return scrapeBooksFromWebsites();
        } else {
            System.out.println("Reading from Json file..");

            // Create ObjectMapper instance - Jackson's main JSON processing class
            ObjectMapper mapper = new ObjectMapper();

            // Convert JSON string to Java objects
            return mapper.readValue(
                    jsonContent,  // The JSON string to parse

                    // Create type description for proper deserialization:
                    mapper.getTypeFactory().constructCollectionType(
                            List.class,    // We want a List interface as container
                            Book.class    // With Book objects as elements
                    )
            );
        }
    }
}
