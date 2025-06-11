package ee.taltech.iti0202.exam;

import org.junit.Test;


import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class CinemaTest {

    // Kinol on kliendid
    @Test
    public void cinemaHasClients() {
        Cinema cinema = new Cinema();
        Client client = new Client("Mari", 25, 50.0);
        cinema.addClient(client);

        List<Client> clients = cinema.getClients();
        assertEquals(1, clients.size());
        assertEquals("Mari", clients.get(0).getName());
    }

    // Kinol on filmid, mida külastajatele näidatakse
    @Test
    public void cinemaHasMovies() {
        Cinema cinema = new Cinema();
        Movie movie = new Movie("Titanic", 195, "Drama", 1997, 9.99);
        cinema.addMovie(movie);

        List<Movie> movies = cinema.getMovies();
        assertEquals(1, movies.size());
        assertEquals("Titanic", movies.get(0).getTitle());
    }

    // Kinost saab otsida filme nime järgi
    @Test
    public void canSearchMoviesByTitle() {
        Cinema cinema = new Cinema();
        cinema.addMovie(new Movie("Kevade", 85, "Drama", 1969, 5.50));
        cinema.addMovie(new Movie("Suvi", 89, "Drama", 1976, 5.50));

        List<Movie> results = cinema.searchMoviesByTitle("evad");
        assertEquals(1, results.size());
        assertEquals("Kevade", results.get(0).getTitle());
    }

    // Kinost saab pärida, mis film(id) parasjagu kõige populaarsem(ad) on
    @Test
    public void canGetMostPopularMovies() {
        Cinema cinema = new Cinema();
        Movie movie1 = new Movie("Herkus Kunts", 83, "Comedy", 1969, 4.50);
        Movie movie2 = new Movie("Viimne reliikvia", 92, "Adventure", 1969, 5.00);

        cinema.addMovie(movie1);
        cinema.addMovie(movie2);

        assertEquals(2, cinema.getMostPopularMovies().size());

        Client client = new Client("Jüri", 30, 100.0);
        cinema.addClient(client);
        cinema.watchMovie(client, movie1);

        List<Movie> popular = cinema.getMostPopularMovies();
        assertEquals(1, popular.size());
        assertEquals("Herkus Kunts", popular.get(0).getTitle());
    }

    // Kliendil on nimi, vanus, eelarve
    @Test
    public void clientHasNameAgeBudget() {
        Client client = new Client("Peeter", 19, 35.50);
        assertEquals("Peeter", client.getName());
        assertEquals(19, client.getAge());
        assertEquals(35.50, client.getBudget(), 0.001);
    }

    // Klient saab filme vaatamas käia ainult siis, kui tal on piisavalt raha
    @Test
    public void clientCanWatchOnlyWithEnoughMoney() {
        Cinema cinema = new Cinema();
        Movie movie = new Movie("Malev", 114, "War", 2005, 8.00);
        Client richClient = new Client("Rich", 40, 100.0);
        Client poorClient = new Client("Poor", 40, 5.0);

        cinema.addMovie(movie);
        cinema.addClient(richClient);
        cinema.addClient(poorClient);

        assertTrue(cinema.watchMovie(richClient, movie));
        assertFalse(cinema.watchMovie(poorClient, movie));
        assertEquals(92.0, richClient.getBudget(), 0.001);
        assertEquals(5.0, poorClient.getBudget(), 0.001);
    }

    // Filmil on nimi, pikkus, žanr, režissöör ja piletihind
    @Test
    public void movieHasAllRequiredAttributes() {
        Movie movie = new Movie("Nukitsamees", 81, "Children", 1981, 4.00);
        assertEquals("Nukitsamees", movie.getTitle());
        assertEquals(81, movie.getDuration());
        assertEquals("Children", movie.getGenre());
        assertEquals(4.00, movie.getTicketPrice(), 0.001);
    }

    // Kinol on lubatud piletihinna osas soodustusi teha
    @Test
    public void cinemaCanApplyDiscounts() {
        Movie movie = new Movie("Kevade", 85, "Drama", 1969, 10.00);
        Cinema cinema = new Cinema();
        cinema.addMovie(movie);

        cinema.applyDiscount(movie, 20);
        assertEquals(8.00, movie.getTicketPrice(), 0.001);
        cinema.applyDiscount(movie, 50);
        assertEquals(4.00, movie.getTicketPrice(), 0.001);
    }
}
