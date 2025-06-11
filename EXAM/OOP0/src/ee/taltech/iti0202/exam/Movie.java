package ee.taltech.iti0202.exam;

public class Movie {
    private String title;
    private int duration;
    private String genre;
    private int year;
    private double ticketPrice;
    private int viewCount = 0;

    public Movie(String title, int duration, String genre, int year, double ticketPrice) {
        this.title = title;
        this.duration = duration;
        this.genre = genre;
        this.year = year;
        this.ticketPrice = ticketPrice;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double newPrice) {
        this.ticketPrice = newPrice;
    }

    public void incrementViewCount() {
        viewCount++;
    }

    public int getViewCount() {
        return viewCount;
    }
}
