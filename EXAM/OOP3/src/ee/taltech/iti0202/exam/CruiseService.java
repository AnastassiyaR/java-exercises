package ee.taltech.iti0202.exam;

import java.util.*;
import java.util.stream.Collectors;

public class CruiseService {
    private final List<Cruise> cruises = new ArrayList<>();
    private final List<Client> clients = new ArrayList<>();
    private final List<Cruise> history = new ArrayList<>();

    public void addCruise(Cruise cruise) {
        cruises.add(cruise);
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public List<Cruise> findCruises(String startPoint, String endPoint) {
        List<Cruise> result = new ArrayList<>();
        for (Cruise cruise : cruises) {
            boolean startMatch = (startPoint == null || cruise.getStartPoint().equals(startPoint));
            boolean endMatch = (endPoint == null || cruise.getEndPoint().equals(endPoint));
            if (startMatch && endMatch) {
                result.add(cruise);
            }
        }
        return result;
    }

    public List<Cruise> findMostPopularCruises() {
        if (cruises.isEmpty()) {
            return new ArrayList<>();
        }

        int maxPassengers = cruises.stream()
                .mapToInt(Cruise::getPassengerCount)
                .max()
                .getAsInt();

        return cruises.stream()
                .filter(c -> c.getPassengerCount() == maxPassengers)
                .collect(Collectors.toList());
    }

    public void depart(Cruise cruise) {
        cruise.depart();
        history.add(cruise);
    }

    public List<Cruise> getCruises() {
        return Collections.unmodifiableList(cruises);
    }

    public List<Client> getClients() {
        return Collections.unmodifiableList(clients);
    }

    public List<Cruise> getHistory() {
        return Collections.unmodifiableList(history);
    }
}
