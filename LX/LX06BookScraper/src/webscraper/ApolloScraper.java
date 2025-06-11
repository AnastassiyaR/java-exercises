package webscraper;

import book.Author;
import book.Book;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApolloScraper {

    public static String baseUrl = "https://www.apollo.ee/raamatud";
    public static final int MAX_BOOKS = 50;

    /**
     * Constructs an ApolloScraper with a custom base URL.
     * @param baseUrl The base URL to use for scraping
     */
    public ApolloScraper(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * Fetches the HTML document from the base URL.
     * @return The parsed HTML document
     * @throws IOException if there's an error connecting to the URL
     */
    public Document fetchDocument() throws IOException {
        return Jsoup.connect(baseUrl).get();
    }

    /**
     * Scrapes book information from the Apollo.ee website.
     * @return A list of Book objects containing scraped information
     * @throws IOException if there's an error during scraping
     */
    public List<Book> scrapeapolloBooks() throws IOException {
        List<Book> apolloBooks = new ArrayList<>();
        try {
            Document doc = fetchDocument();
            Elements carousels = doc.select(".styles_carousel__G7SXd.styles_products__Tnywp");
            for (Element carousel : carousels) {
                Element link = carousel.selectFirst(".styles_heading__title__Xym7A.styles_align-left__aHTGM a[href]");
                Elements productCards = carousel.select(".styles_product-card__wrapper__E9Tcl");
                for (Element productCard : productCards) {
                    if (apolloBooks.size() < MAX_BOOKS) {
                        Book book = getDetailsFromBook(productCard.attr("href"), link.text());
                        if (book != null) {
                            apolloBooks.add(book);
                        }
                        if (book != null && book.getAuthor() != null) {
                            book.getAuthor().addBook(book);
                        }
                    } else {
                        return apolloBooks;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Fetch error: " + e.getMessage());
        }
        return apolloBooks;
    }

    /**
     * Extracts detailed book information from a book's individual page.
     * @param fullUrl The URL of the book's page (relative or absolute)
     * @param topic The topic/category of the book
     * @return A Book object containing the extracted information, or null if extraction fails
     * @throws IOException if there's an error connecting to the book's page
     */
    private Book getDetailsFromBook(String fullUrl, String topic) throws IOException {
        try {
            if (!fullUrl.startsWith("http")) {
                fullUrl = "https://www.apollo.ee" + fullUrl;
            }

            Document doc = Jsoup.connect(fullUrl).get();

            Element titleElement = doc.select(".styles_heading__title__Xym7A.styles_align-left__aHTGM").first();
            String title = (titleElement != null) ? titleElement.text() : null;

            Author author = null;
            Element authorElement = doc.selectFirst("#meta-author");
            String authorFull = (authorElement == null) ? null : authorElement.text();
            if (authorFull != null) {
                String[] parts = authorFull.split("\\s+", 2);
                 author = new Author(
                        parts.length > 0 ? parts[0] : "",
                        parts.length > 1 ? parts[1] : ""
                );
            }

            Element publisherElement = doc.selectFirst("#meta-publisher");
            String publisher = (publisherElement == null) ? null : publisherElement.text();

            Element yearElement = doc.selectFirst("#meta-published_at");
            String year = (yearElement == null) ? null : yearElement.text();

            Element pagesElement = doc.selectFirst("#meta-pages");
            String pages = (pagesElement == null) ? null : pagesElement.text();

            Element languageElement = doc.selectFirst("#meta-language");
            String language = (languageElement == null) ? null : languageElement.text();

            if ("Eesti".equals(language)) {
                language = "est";
            }

            Elements prices = doc.select(".styles_product-price-value__Ai9c8");
            String price = null;
            if (prices.size() > 1) {
                price = prices.get(1).text().replace(",", ".").replaceAll("[^0-9.]", "").trim();
            } else if (!prices.isEmpty()) {
                price = prices.get(0).text().replace(",", ".").replaceAll("[^0-9.]", "").trim();
            }

//            System.out.println("Author: " + author);
//            System.out.println("Publisher: " + publisher);
//            System.out.println("Price: " + price);
//            System.out.println("Year: " + year);
//            System.out.println("Pages: " + pages);
//            System.out.println("Language: " + language);
//            System.out.println("Topic: " + topic);
//            System.out.println("URL: " + fullUrl);
//            System.out.println("Title: " + title);

            List<String> topics = new ArrayList<>();
            topics.add(topic);

            return new Book.Builder()
                    .setTitle(title)
                    .setTopics(topics)
                    .setAuthor(author)
                    .setPages(pages)
                    .setPrice(price)
                    .setPublisher(publisher)
                    .setLanguage(language)
                    .setYear(year)
                    .setUrl(fullUrl)
                    .build();

        } catch (IOException _) { }

        return null;
    }
}
