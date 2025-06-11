package webscraper;

import book.Author;
import book.Book;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class RahvaRaamatScraper {

    public static String rahvabaseUrl = "https://www.rahvaraamat.ee/c/ilukirjandus/1/1/29/et#!/activeTab=tab02";

    /**
     * Constructs a RahvaRaamatScraper with a custom base URL.
     * @param rahvabaseUrl The base URL to use for scraping
     */
    public RahvaRaamatScraper(String rahvabaseUrl) {
        this.rahvabaseUrl = rahvabaseUrl;
    }

    /**
     * Fetches the HTML document from the Rahva Raamat base URL.
     * @return The parsed HTML document
     * @throws IOException if there's an error connecting to the URL
     */
    public Document fetchDocument() throws IOException {
        return Jsoup.connect(rahvabaseUrl).get();
    }

    /**
     * Scrapes book information from the Rahva Raamat website.
     * Extracts basic book information from the listing page and then
     * fetches detailed information for each book.
     * @return A list of Book objects containing scraped information
     * @throws IOException if there's an error during scraping
     */
    public List<Book> scraperahvaRaamatBooks() throws IOException {
        List<Book> rahvaRaamatBooks = new ArrayList<>();
        try {
            List<String> topics = new ArrayList<>();

            Document doc = fetchDocument();
            Elements authors = doc.select(".xx-small-text.capitalize.styles_productSubtitle__l3Bht");
            Elements prices = doc.select(".styles_originalProductPrice__wFbcg.styles_hidden__hveZA");
            Elements titles = doc.select(".styles_productTitle__hwkr7.x-small-text.bold a");

            int minSize = Math.min(titles.size(), Math.min(authors.size(), prices.size()));

            for (int i = 0; i < minSize; i++) {
                String author = authors.get(i).text();
                String url = titles.get(i).attr("href");
                String[] details = url.split("/");

                for (int q = 3; q < details.length - 2; q++) {
                    if (!topics.contains(details[q])) {
                        topics.add(details[q]);
                    }
                }

                Book book = getDetailsFromBook(url, author, topics);
                if (book != null) {
                    book.getAuthor().addBook(book);
                    rahvaRaamatBooks.add(book);
                }
            }
        } catch (IOException _) { }

        return rahvaRaamatBooks;
    }

    /**
     * Extracts detailed book information from a book's individual page.
     * Parses both HTML elements and JSON-LD structured data to gather comprehensive
     * book metadata including publisher, page count, publication year, etc.
     * @param fullUrl The URL of the book's page (relative or absolute)
     * @param author The author's full name
     * @param topics List of topics/categories the book belongs to
     * @return A Book object containing the extracted information, or null if extraction fails
     * @throws IOException if there's an error connecting to the book's page
     * @throws RuntimeException if required JSON-LD data is not found on the page
     */
    private Book getDetailsFromBook(String fullUrl, String author, List<String> topics) throws IOException {
        try {
            if (!fullUrl.startsWith("http")) {
                fullUrl = "https://rahvaraamat.ee" + fullUrl;
            }

            Document doc = Jsoup.connect(fullUrl).get();
            System.out.println("DOC " + doc);
            String publisher = null;

            // Extract publisher information from script tags
            Elements scripts = doc.select("script");
            for (Element s : scripts) {
                String html = s.html();
                if (html.contains("serverSideProduct") && html.contains("vendor_name")) {
                    int start = html.indexOf("vendor_name\\\":\\\"");
                    int end = html.indexOf("\\\",\\\"general_email");

                    if (start != -1 && end != -1 && end > start) {
                        start += "vendor_name\\\":\\\"".length();

                        String vendorName = html.substring(start, end);
                        publisher = vendorName;

                    }
                }

            }

            // Extract structured data from JSON-LD
            Element script = doc.select("script[type=application/ld+json]").first();

            String jsonString = script.html()
                    .replaceAll("\\s+", " ");

            ObjectMapper mapper = new ObjectMapper();

            // to deal with data
            JsonNode productData = mapper.readTree(jsonString);

            String title = productData.path("name").asText("Unknown Title");
            String price = productData.path("price").asText("Price not available");
            String pages = null;
            String year = null;

            // Process additional properties from JSON
            JsonNode additionalProps = productData.path("additionalProperty");
            for (JsonNode prop : additionalProps) {
                String name = prop.path("name").asText();
                String value = prop.path("value").asText();
                if (!value.isEmpty()) {
                    if ("Number of pages".equals(name)) {
                        pages = value;
                    } else if ("Published".equals(name)) {
                        year = value;
                    }
                }
            }

            // Create Author object
            Author bookAuthor = null;
            if (author != null && !author.trim().isEmpty()) {
                String[] parts = author.split("\\s+", 2);
                bookAuthor = new Author(
                        parts.length > 0 ? parts[0] : "",
                        parts.length > 1 ? parts[1] : ""
                );
            }

            return new Book.Builder()
                    .setTitle(title)
                    .setTopics(topics)
                    .setAuthor(bookAuthor)
                    .setPages(pages)
                    .setPublisher(publisher)
                    .setPrice(price)
                    .setLanguage("est")
                    .setYear(year)
                    .setUrl(fullUrl)
                    .build();

        } catch (IOException _) { }
        return null;
    }
}
