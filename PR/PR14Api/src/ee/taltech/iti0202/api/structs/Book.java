package ee.taltech.iti0202.api.structs;

import ee.taltech.iti0202.libraryapi.exception.ApiException;

public class Book {

    private Integer id;
    private String author;
    private String country;
    private String language;
    private Integer pages;
    private String title;
    private Integer year;


    public Integer getId() {
        if (id == null || id < 0) {
            throw new ApiException("");
        }
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return String.format("%1$s - %2$s (%3$d)", author, title, year);
    }
}
