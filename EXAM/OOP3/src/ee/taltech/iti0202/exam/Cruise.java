package ee.taltech.iti0202.exam;

import java.util.*;

public class Cruise implements Comparable<Cruise> {
    private final String startPoint;
    private final String endPoint;
    private final int capacity;
    private final double ticketPrice;
    private final List<Client> passengers = new ArrayList<>();
    private boolean departed = false;

    public Cruise(String startPoint, String endPoint, int capacity, double ticketPrice) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.capacity = capacity;
        this.ticketPrice = ticketPrice;
    }

    public String getStartPoint() { return startPoint; }
    public String getEndPoint() { return endPoint; }
    public int getCapacity() { return capacity; }
    public double getTicketPrice() { return ticketPrice; }
    public boolean isDeparted() { return departed; }
    public void depart() {
        departed = true;
    }
    public List<Client> getPassengers() { return passengers; }
    public int getPassengerCount() { return passengers.size(); }

    public boolean addPassenger(Client client) {
        if (departed) return false;
        if (passengers.size() >= capacity) return false;
        passengers.add(client);
        return true;
    }

    @Override
    public String toString() {
        return "Cruise from " + startPoint + " to " + endPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cruise)) return false;
        Cruise cruise = (Cruise) o;
        return startPoint.equals(cruise.startPoint) && endPoint.equals(cruise.endPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startPoint, endPoint);
    }

    @Override
    public int compareTo(Cruise other) {
        return Integer.compare(other.getPassengerCount(), this.getPassengerCount());
    }
}
