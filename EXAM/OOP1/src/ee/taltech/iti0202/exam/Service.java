package ee.taltech.iti0202.exam;

import javax.management.DescriptorRead;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Service {
    private final List<Direction> directions = new ArrayList<>();
    private final List<Bus> busses = new ArrayList<>();
    private final List<Client> clients = new ArrayList<>();
    private final List<Direction> history = new ArrayList<>();

    public Service() { }

    public List<Direction> getDirections() {
        return directions;
    }

    public List<Bus> getBuses() {
        return busses;
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<Direction> getHistory() {
        return history;
    }

    public void addDirection(Direction direction) {
        if (!directions.contains(direction)) {
            directions.add(direction);
        }
    }

    public void addClient(Client client) {
        if (!clients.contains(client)) {
            clients.add(client);
        }
    }

    public void addBusses(Bus bus) {
        if (!busses.contains(bus)) {
            busses.add(bus);
        }
    }

    public List<Direction> findDirections(String startPoint, String endPoint) {
        List<Direction> result = new ArrayList<>();
        for (Direction direction : directions) {
            boolean startMatch = (startPoint == null || direction.getStartPoint().equals(startPoint));
            boolean endMatch = (endPoint == null || direction.getEndPoint().equals(endPoint));
            if (startMatch && endMatch) {
                result.add(direction);
            }
        }
        return result;
    }

    public List<Bus> findMostPopularBuses() {
        if (busses.isEmpty()) {
            return new ArrayList<>();
        }

        int maxPassengers = busses.stream()
                .mapToInt(Bus::getPassengersSize)
                .max()
                .getAsInt();

        return busses.stream()
                .filter(c -> c.getPassengersSize() == maxPassengers)
                .collect(Collectors.toList());
    }

}
