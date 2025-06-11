package ee.taltech.iti0202.exam;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CruiseTest {

    // Kruiisil on algpunkti, sihtpunkt, mahtuvus, piletihind. Peab olema toString meetodis kajastatud alg- ja sihtpunkt - 5p
    @Test
    public void testCruiseToString() {
        Cruise cruise = new Cruise("Tallinn", "Helsinki", 100, 50);
        String expected = "Cruise from Tallinn to Helsinki";
        assertEquals(expected, cruise.toString());
    }

    // Kruiisil on realiseeritud equals meetod. Kruiisid on võrdsed, kui alg- ja sihtpunkt on samad. - 10p
    @Test
    public void testCruiseEquals() {
        Cruise c1 = new Cruise("Tallinn", "Helsinki", 100, 50);
        Cruise c2 = new Cruise("Tallinn", "Helsinki", 200, 60);
        Cruise c3 = new Cruise("Tallinn", "Stockholm", 100, 50);
        assertEquals(c1, c2);
        assertNotEquals(c1, c3);
    }

    // Kliendil on nimi, vanus, eelarve, kõik kolm peavad toString meetodis kajastatud olema - 5p
    @Test
    public void testClientToString() {
        Client client = new Client("John", 25, 500);
        String expected = "Client{name='John', age=25, budget=500.0}";
        assertEquals(expected, client.toString());
    }

    // Klient saab minna kruiisile ainult siis, kui tal on selleks piisavalt raha ning laeval on piisavalt ruumi - 20p
    @Test
    public void testClientBuyTicketEnoughMoneyAndCapacity() {
        Cruise cruise = new Cruise("Tallinn", "Helsinki", 2, 100);
        Client client = new Client("John", 30, 150);
        boolean bought = client.buyTicket(cruise);
        assertTrue(bought);
        assertEquals(50.0, client.getBudget(), 0.001);
        assertTrue(cruise.getPassengers().contains(client));
    }

    // Kui kruiis on juba merele läinud, siis uusi reisijaid ei võeta enam peale - 10p
    @Test
    public void testClientBuyTicketAfterDeparture() {
        Cruise cruise = new Cruise("Tallinn", "Helsinki", 2, 100);
        Client client = new Client("John", 30, 200);
        cruise.depart();
        boolean bought = client.buyTicket(cruise);
        assertFalse(bought);
    }

    // Eelarve väheneb, kui pilet osta - 10p
    @Test
    public void testClientBudgetDecreases() {
        Cruise cruise = new Cruise("Tallinn", "Helsinki", 2, 100);
        Client client = new Client("John", 30, 150);
        client.buyTicket(cruise);
        assertEquals(50, client.getBudget(), 0.001);
    }

    // Kliendile kehtib soodustus -50%, kui ta vanus on 6-18 või 60+ aastat vana. Alla 6 aastased lapsed sõidavad tasuta - 15p
    @Test
    public void testClientDiscounts() {
        Cruise cruise = new Cruise("Tallinn", "Helsinki", 10, 100);

        Client child = new Client("Kid", 5, 1000);
        assertTrue(child.buyTicket(cruise));
        assertEquals(1000, child.getBudget(), 0.001); // tasuta

        Client teenager = new Client("Teen", 10, 1000);
        assertTrue(teenager.buyTicket(cruise));
        assertEquals(950, teenager.getBudget(), 0.001); // 50% soodustus

        Client senior = new Client("Senior", 65, 1000);
        assertTrue(senior.buyTicket(cruise));
        assertEquals(950, senior.getBudget(), 0.001); // 50% soodustus

        Client adult = new Client("Adult", 30, 1000);
        assertTrue(adult.buyTicket(cruise));
        assertEquals(900, adult.getBudget(), 0.001); // täishind
    }

    // Kliendil on ajalugu kruiisidest, kus ta käinud on - 10p
    @Test
    public void testClientCruiseHistory() {
        Cruise cruise = new Cruise("Tallinn", "Helsinki", 2, 100);
        Client client = new Client("John", 30, 200);
        client.buyTicket(cruise);
        assertTrue(client.getCruiseHistory().contains(cruise));
    }

    // Teenusel on kruiisid, kliendid - 5p
    @Test
    public void testServiceHasCruisesAndClients() {
        CruiseService service = new CruiseService();
        Cruise cruise = new Cruise("Tallinn", "Helsinki", 10, 100);
        Client client = new Client("Alice", 30, 1000);

        service.addCruise(cruise);
        service.addClient(client);

        assertTrue(service.getCruises().contains(cruise));
        assertTrue(service.getClients().contains(client));
    }

    // Teenusel on ajalugu kruiisidest - 10p
    @Test
    public void testServiceHistory() {
        CruiseService service = new CruiseService();
        Cruise cruise = new Cruise("Tallinn", "Helsinki", 10, 100);
        Client client = new Client("Alice", 30, 1000);

        service.addCruise(cruise);
        service.addClient(client);

        client.buyTicket(cruise);
        service.depart(cruise);

        List<Cruise> history = service.getHistory();
        assertTrue(history.contains(cruise));
    }

    // Teenusel on meetod, millega otsida kruiise alg- ja/või sihtpunkti järgi - 20p
    @Test
    public void testServiceFindCruise() {
        CruiseService service = new CruiseService();
        Cruise c1 = new Cruise("Tallinn", "Helsinki", 10, 100);
        Cruise c2 = new Cruise("Tallinn", "Stockholm", 10, 150);
        Cruise c3 = new Cruise("Helsinki", "Stockholm", 10, 200);

        service.addCruise(c1);
        service.addCruise(c2);
        service.addCruise(c3);

        List<Cruise> result = service.findCruises("Tallinn", null);
        assertTrue(result.contains(c1));
        assertTrue(result.contains(c2));
        assertFalse(result.contains(c3));

        result = service.findCruises(null, "Stockholm");
        assertTrue(result.contains(c2));
        assertTrue(result.contains(c3));
        assertFalse(result.contains(c1));

        result = service.findCruises("Tallinn", "Stockholm");
        assertEquals(1, result.size());
        assertTrue(result.contains(c2));
    }

    // Teenusel on meetod, millega leida kõige populaarseim(ad) kruiis(id) - 40p
    @Test
    public void testServiceFindMostPopularCruise() {
        CruiseService service = new CruiseService();
        Cruise c1 = new Cruise("Tallinn", "Helsinki", 10, 100);
        Cruise c2 = new Cruise("Tallinn", "Stockholm", 10, 150);

        Client client1 = new Client("Alice", 30, 1000);
        Client client2 = new Client("Bob", 30, 1000);

        service.addCruise(c1);
        service.addCruise(c2);
        service.addClient(client1);
        service.addClient(client2);

        client1.buyTicket(c1);
        client2.buyTicket(c1);
        client2.buyTicket(c2);

        List<Cruise> popular = service.findMostPopularCruises();
        assertEquals(1, popular.size());
        assertTrue(popular.contains(c1));
    }

    // Kruiise saab omavahel võrrelda populaarsuse järgi, kasutades Comparable liidest ja compareTo meetodit. - 40p
    @Test
    public void testCruiseCompareTo() {
        Cruise c1 = new Cruise("Tallinn", "Helsinki", 10, 100);
        Cruise c2 = new Cruise("Tallinn", "Helsinki", 10, 100);

        Client client1 = new Client("Alice", 30, 1000);
        Client client2 = new Client("Bob", 30, 1000);

        c1.addPassenger(client1);
        c2.addPassenger(client1);
        c2.addPassenger(client2);

        assertTrue(c2.compareTo(c1) < 0);  // c2 on populaarsem
        assertTrue(c1.compareTo(c2) > 0);
        assertEquals(0, c1.compareTo(c1));
    }
}