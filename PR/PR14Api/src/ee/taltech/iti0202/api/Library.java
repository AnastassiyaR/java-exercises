package ee.taltech.iti0202.api;

import ee.taltech.iti0202.api.api.Api;
import ee.taltech.iti0202.api.structs.Book;

public class Library {

    private Api api = new Api("https://cs.taltech.ee/services/library/");

    public Book[] getAll() {
        return api.get("/library", Book[].class);
    }

    /**
     * Get by id
     * @param id
     * @return book(s)
     */
    public Book getById(int id) {
        return api.get(String.format("/library/%1$d", id), Book.class);
    }

    /**
     * Get page
     * @param pageSize
     * @param pageNo
     * @return book(s)
     */
    public Book[] getPage(int pageSize, int pageNo) {
        return api.get(String.format("/library?pageSize=%1$d&pageNo=%2$d", pageSize, pageNo), Book[].class);
    }

    /**
     * Get by author
     * @param author
     * @return book(s)
     */
    public Book[] getByAuthor(String author) {
        author = author.replaceAll(" ", "%20");
        return api.get(String.format("/library?author=%1$s", author), Book[].class);
    }

    /**
     * Get by country
     * @param country
     * @return book(s)
     */
    public Book[] getByCountry(String country) {
        country = country.replaceAll(" ", "%20");
        return api.get(String.format("/library?country=%1$s", country), Book[].class);
    }

    /**
     * Get by title
     * @param title
     * @return book(s)
     */
    public Book[] getByTitle(String title) {
        title = title.replaceAll(" ", "%20");
        return api.get(String.format("/library?title=%1$s", title), Book[].class);
    }

    /**
     * Get by country and year
     * @param country
     * @param year
     * @return book(s)
     */
    public Book[] getByCountryAndYear(String country, int year) {
        country = country.replaceAll(" ", "%20");
        return api.get(String.format("/library?country=%1$s&year=%2$d", country, year), Book[].class);
    }

    /**
     * Get all sorted by author
     * @return sorted book(s)
     */
    public Book[] getAllSortedByAuthor() {
        return api.get("/library?sortBy=author", Book[].class);
    }

    /**
     * Get all sorted by pages
     * @return sorted book(s)
     */
    public Book[] getAllSortedByPages() {
        return api.get("/library?sortBy=pages", Book[].class);
    }

    /**
     * Get by author and sorted by year
     * @param author
     * @return sorted book(s)
     */
    Book[] getByAuthorAndSortedByYear(String author) {
        author = author.replaceAll(" ", "%20");
        return api.get(String.format("/library?author=%1$s&sortBy=year", author), Book[].class);
    }

    public static void main(String[] args) {
        var lib = new Library();
        var res = lib.getByAuthor("Chinua Achebe");

        for (Book b : res) {
            System.out.println(b);
        }

    }
}
