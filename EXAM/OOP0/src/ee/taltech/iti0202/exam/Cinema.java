package ee.taltech.iti0202.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cinema {
    private List<Movie> movies;
    private List<Client> clients;

    /**
     * Constructor cinema
     */
    public Cinema() {
        this.movies = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    /**
     * Add movie
     * @param movie
     */
    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    /**
     * Add client
     * @param client
     */
    public void addClient(Client client) {
        clients.add(client);
    }

    /**
     * Search movies by title
     * @param title
     * @return
     */
    public List<Movie> searchMoviesByTitle(String title) {
        return movies.stream()
                .filter(m -> m.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Get the most popular movies
     * @return
     */
    public List<Movie> getMostPopularMovies() {
        if (movies.isEmpty()) return new ArrayList<>();

        int maxViews = movies.stream()
                .mapToInt(Movie::getViewCount)
                .max()
                .getAsInt();

        return movies.stream()
                .filter(m -> m.getViewCount() == maxViews)
                .collect(Collectors.toList());
    }

    /**
     * Watch movie
     * @param client
     * @param movie
     * @return
     */
    public boolean watchMovie(Client client, Movie movie) {
        if (!clients.contains(client) || !movies.contains(movie)) {
            return false;
        }

        if (client.canAffordTicket(movie.getTicketPrice())) {
            client.purchaseTicket(movie.getTicketPrice());
            movie.incrementViewCount();
            return true;
        }
        return false;
    }

    /**
     * Apply discount
     * @param movie
     * @param discountPercentage
     */
    public void applyDiscount(Movie movie, double discountPercentage) {
        if (movies.contains(movie)) {
            double newPrice = movie.getTicketPrice() * (1 - discountPercentage / 100);
            movie.setTicketPrice(newPrice);
        }
    }

    public List<Movie> getMovies() {
        return new ArrayList<>(movies);
    }

    public List<Client> getClients() {
        return new ArrayList<>(clients);
    }
}
