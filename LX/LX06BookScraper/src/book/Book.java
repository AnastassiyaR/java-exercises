package book;

import java.util.List;

public final class Book {
    private String language;
    private List<String> topics;
    private String title;
    private String publisher;
    private Author author;
    private String url;
    private String year;
    private String pages;
    private String price;

    // for JSON
    public Book() { }

    private Book(Builder builder) {
        this.language = builder.language;
        this.topics = builder.topics;
        this.title = builder.title;
        this.publisher = builder.publisher;
        this.author = builder.author;
        this.url = builder.url;
        this.year = builder.year;
        this.pages = builder.pages;
        this.price = builder.price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{"
                + "title='" + title + '\''
                + ", author=" + (author != null ? author.firstName() + " " + author.lastName() : "")
                + ", language='" + language + '\''
                + ", topics=" + topics
                + ", publisher='" + publisher + '\''
                + ", url='" + url + '\''
                + ", year='" + year + '\''
                + ", pages='" + pages + '\''
                + ", price='" + price + '\''
                + '}';
    }

    public static class Builder {
        private String language;
        private List<String> topics;
        private String title;
        private String publisher;
        private Author author;
        private String url;
        private String year;
        private String pages;
        private String price;

        public Builder setLanguage(String language) {
            this.language = language;
            return this;
        }

        public Builder setTopics(List<String> topics) {
            this.topics = topics;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setPublisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public Builder setAuthor(Author author) {
            this.author = author;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setYear(String year) {
            this.year = year;
            return this;
        }

        public Builder setPages(String pages) {
            this.pages = pages;
            return this;
        }

        public Builder setPrice(String price) {
            this.price = price;
            return this;
        }

        public Book build() {
            return new Book(this);
        }

    }
}
