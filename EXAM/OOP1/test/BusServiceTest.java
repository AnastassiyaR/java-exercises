import ee.taltech.iti0202.exam.Bus;
import ee.taltech.iti0202.exam.Client;
import ee.taltech.iti0202.exam.Direction;
import ee.taltech.iti0202.exam.Service;
import org.testng.annotations.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BusServiceTest {

    // Kliendil on nimi, vanus, eelarve (Peab olema kajastatud toString meetodis) - 5p
    @Test
    public void testClientToString() {
        Client client = new Client("John", 25, 500);
        String expected = "Client{name='John', age=25, budget=500.0}";
        assertEquals(expected, client.toString());
    }

    // Klient saab osta bussipileti ainult siis, kui tal on selleks piisavalt raha ning bussis on vabu istekohti - 20p
    @Test
    public void testClientBuyTicketEnoughMoneyAndCapacity() {
        Direction direction = new Direction("Tallinn", "Helsinki", 2);
        Client client = new Client("John", 25, 500);
        Bus bus = new Bus(direction, 30);
        direction.addBus(bus);
        boolean bought = client.buyTicket(direction);
        assertTrue(bought);
    }

    // Eelarve väheneb, kui pilet osta - 10p
    @Test
    public void testClientBuyTicketLowCost() {
        Direction direction = new Direction("Tallinn", "Helsinki", 2);
        Client client = new Client("John", 25, 500);
        Bus bus = new Bus(direction, 30);
        direction.addBus(bus);
        client.buyTicket(direction);
        assertEquals(498, client.getBudget());
    }

    // Marsruudil on algpunkti, sihtpunkt, piletihind ning bussid, mis seda marsruuti järgivad. (Alg- ja sihtpunkt peavad olema kajastatud toString meetodis) - 5p
    @Test
    public void testDirectionToString() {
        Direction direction = new Direction("Tallinn", "Helsinki", 2);
        String expected = "Cruise from Tallinn to Helsinki";
        assertEquals(expected, direction.toString());
    }

    // Marsruudil on realiseeritud equals meetod - marsruudid on võrdsed, kui alg- ja sihtpunkt on omavahel samad. - 5p
    @Test
    public void testCruiseEquals() {
        Direction c1 = new Direction("Tallinn", "Helsinki", 100);
        Direction c2 = new Direction("Tallinn", "Helsinki", 200);
        Direction c3 = new Direction("Tallinn", "Stockholm", 100);
        assertEquals(c1, c2);
        assertNotEquals(c1, c3);
    }

    // Marsruudil saab kokku arvutada reisijate arvu - 20p
    @Test
    public void getPassangersInDirection() {
        Direction direction = new Direction("Tallinn", "Helsinki", 100);
        Client c1 = new Client("A", 30, 200);
        Client c2 = new Client("John", 30, 200);
        Bus bus = new Bus(direction, 30);
        direction.addBus(bus);
        bus.addPassenger(c1);
        bus.addPassenger(c2);
        assertEquals(2, direction.getPassengerCount());
    }

    // Piletihind sõltub kliendi vanusest - 10p
    @Test
    public void getClientCostForTicket() {
        // -6
        Direction direction = new Direction("Tallinn", "Helsinki", 100);
        Client c1 = new Client("A", 4, 200);
        assertEquals(0, c1.getTicketPriceForDirection(direction));

        // 7-18
        Client c3 = new Client("A", 14, 200);
        assertEquals(50, c3.getTicketPriceForDirection(direction));

        // 60+
        Client c2 = new Client("A", 74, 200);
        assertEquals(0, c2.getTicketPriceForDirection(direction));

        // 19-58
        Client c4 = new Client("A", 54, 200);
        assertEquals(100, c4.getTicketPriceForDirection(direction));
    }

    // Bussil on marsruut, istekohad - 5p
    @Test
    public void getBusToString() {
        Direction direction = new Direction("Tallinn", "Helsinki", 100);
        Bus bus = new Bus(direction, 30);
        assertEquals(direction, bus.getDirection());
        assertEquals(30, bus.getCapacity());
    }

    // Kui buss on sõitma hakanud, siis enam reisijaid peale ei võeta - 10p
    @Test
    public void busStartDriving() {
        Direction direction = new Direction("Tallinn", "Helsinki", 100);
        Bus bus = new Bus(direction, 30);
        Client c1 = new Client("A", 54, 200);
        direction.addBus(bus);
        bus.depart();
        bus.addPassenger(c1);
        assertFalse(c1.buyTicket(direction));
    }

    // Teenusel on marsruudid, bussid, kliendid - 5p
    @Test
    public void getServiceHasDirectionBusAndClient() {
        Service service = new Service();
        Direction direction = new Direction("Tallinn", "Helsinki", 100);
        Bus bus = new Bus(direction, 30);
        Client c1 = new Client("A", 50, 200);

        service.addClient(c1);
        assertEquals(1, service.getClients().size());

        service.addDirection(direction);
        assertEquals(1, service.getDirections().size());

        service.addBusses(bus);
        assertEquals(1, service.getBuses().size());
    }

    // Teenusel on sõiduajalugu - 5p
    @Test
    public void getServiceHasHistoryList() {
        Service service = new Service();
        Direction direction = new Direction("Tallinn", "Helsinki", 100);
        service.getHistory().add(direction);
        assertEquals(1, service.getHistory().size());
    }

    // Teenusel on meetod, millega otsida marsruute alg- ja/või lõpppunkti järgi - 20p
    @Test
    public void testServiceFindCruise() {
        Service service = new Service();
        Direction c1 = new Direction("Tallinn", "Helsinki", 10);
        Direction c2 = new Direction("Tallinn", "Stockholm", 10);
        Direction c3 = new Direction("Helsinki", "Stockholm", 10);

        service.addDirection(c1);
        service.addDirection(c2);
        service.addDirection(c3);

        List<Direction> result = service.findDirections("Tallinn", null);
        assertTrue(result.contains(c1));
        assertTrue(result.contains(c2));
        assertFalse(result.contains(c3));

        result = service.findDirections(null, "Stockholm");
        assertTrue(result.contains(c2));
        assertTrue(result.contains(c3));
        assertFalse(result.contains(c1));

        result = service.findDirections("Tallinn", "Stockholm");
        assertEquals(1, result.size());
        assertTrue(result.contains(c2));
    }

    // Teenusel on meetod, millega leida kõige populaarseim(ad) bussiliin(id) - 40p
    @Test
    public void testServiceFindMostPopularCruise() {
        Service service = new Service();
        Direction d1 = new Direction("Tallinn", "Helsinki", 10);
        Bus bus1 = new Bus(d1, 50);
        Bus bus2 = new Bus(d1, 30);

        Client c1 = new Client("A", 50, 200);
        Client c2 = new Client("John", 30, 200);
        d1.addBus(bus1);
        d1.addBus(bus2);
        c1.buyTicket(d1);
        c2.buyTicket(d1);
        service.addBusses(bus1);
        service.addBusses(bus2);

        List<Bus> popular = service.findMostPopularBuses();
        assertEquals(1, popular.size());
        assertTrue(popular.contains(bus1));
    }

    // Marsruute peaks saama ka ükshaaval võrrelda, kasutades Comparable liidest - 40p
    @Test
    public void testCruiseCompareTo() {
        Direction c1 = new Direction("Tallinn", "Helsinki", 10);
        Direction c2 = new Direction("Tallinn", "Helsinki", 10);

        Bus bus = new Bus(c1, 50);
        Bus bus2 = new Bus(c2, 50);

        Client client1 = new Client("Alice", 30, 1000);
        Client client2 = new Client("Bob", 30, 1000);

        c1.addBus(bus);
        c2.addBus(bus);

        client1.buyTicket(c1);
        client2.buyTicket(c1);

        assertFalse(c2.compareTo(c1) < 0);
        assertFalse(c1.compareTo(c2) > 0);
        assertEquals(0, c1.compareTo(c1));
    }
}
