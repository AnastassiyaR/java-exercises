package webscraper;

import book.Book;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static webscraper.ApolloScraper.baseUrl;
import static webscraper.RahvaRaamatScraper.rahvabaseUrl;


public class BookDataSaver {

    static final String JSON_FILE_PATH = "books.json";

    /**
     * Saves the provided list of Book objects to a JSON file.
     * The file path is defined by {@link #JSON_FILE_PATH}.
     * @return books
     */
    public static List<Book> scrapeBooksFromWebsites() throws IOException {
        System.out.println("Scraping books from websites...");
        List<Book> books = null;
        try {
            RahvaRaamatScraper rahvaRaamatScraper = new RahvaRaamatScraper(rahvabaseUrl);
            ApolloScraper apolloScraper = new ApolloScraper(baseUrl);
            System.out.println("RAHVARAAMAT SCRAPER");
            books = rahvaRaamatScraper.scraperahvaRaamatBooks();
            System.out.println("APOLLO SCRAPER");
            books.addAll(apolloScraper.scrapeapolloBooks());
            System.out.println("OPERATION DONE");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(JSON_FILE_PATH), books);
        } catch (IOException _) { }
        return books;
    }
}
