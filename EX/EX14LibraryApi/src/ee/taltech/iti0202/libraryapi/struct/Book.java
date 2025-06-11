package ee.taltech.iti0202.libraryapi.struct;

import ee.taltech.iti0202.libraryapi.exception.ApiException;

public class Book {
    private int id;
    private String author;
    private String country;
    private String language;
    private int pages;
    private String title;
    private int year;

    /**
     * Constructor of book
     * @param author
     * @param country
     * @param language
     * @param pages
     * @param title
     * @param year
     */
    public Book(String author, String country, String language, Integer pages, String title, Integer year) {
        this.author = author;
        this.country = country;
        this.language = language;
        this.pages = pages;
        this.title = title;
        this.year = year;
    }

    /**
     * Get id
     * @return id
     */
    public int getId() {
        if (id < 0) {
            throw new ApiException("");
        }
        return id;
    }

    /**
     * Set id
     * @param id
     */
    public void setId(int id) {
        if (id < 0) {
            throw new ApiException("");
        }
        this.id = id;
    }

    /**
     * Get author
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Set author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Get country
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Get language
     * @return language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Set language
     * @param language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Get pages
     * @return pages
     */
    public int getPages() {
        return pages;
    }

    /**
     * Set pages
     * @param pages
     */
    public void setPages(int pages) {
        this.pages = pages;
    }

    /**
     * Get title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get year
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * Set year
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }


    @Override
    public String toString() {
        return String.format("%1$s - %2$s (%3$d)", author, title, year);
    }

    /**
     * Builder
     * @return builders
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String author;
        private String country;
        private String language;
        private int pages;
        private String title;
        private int year;
        private static final int MAX_LENGHT = 255;

        /**
         * Set Builder author
         * @param author
         * @return author
         */
        public Builder setAuthor(String author) {
            if (author == null || author.length() < 3 || author.length() > MAX_LENGHT) {
                throw new ApiException("");
            }
            this.author = author;
            return this;
        }

        /**
         * Set Builder country
         * @param country
         * @return country
         */
        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        /**
         * Set Builder language
         * @param language
         * @return language
         */
        public Builder setLanguage(String language) {
            this.language = language;
            return this;
        }

        /**
         * Set Builder pages
         * @param pages
         * @return pages
         */
        public Builder setPages(int pages) {
            this.pages = pages;
            return this;
        }

        /**
         * Set Builder title
         * @param title
         * @return title
         */
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * Set Builder Year
         * @param year
         * @return year
         */
        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        /**
         * build
         * @return new Book
         */
        public Book build() {
            return new Book(author, country, language, pages, title, year);
        }
    }
}
