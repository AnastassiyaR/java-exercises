package ee.taltech.iti0202.exam;

import java.util.*;

public class Direction implements Comparable<Direction> {
    private final String startPoint;
    private final String endPoint;
    private final double ticketPrice;
    private final List<Bus> busses = new ArrayList<>();

    public Direction(String startPoint, String endPoint, double ticketPrice) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.ticketPrice = ticketPrice;
    }

    public String getStartPoint() { return startPoint; }
    public String getEndPoint() { return endPoint; }
    public double getTicketPrice() { return ticketPrice; }

    public int getPassengerCount() {
        int sum = 0;
        for (Bus bus : busses) {
            sum += bus.getPassengersSize();
        }
        return sum;
    }

    public void addBus(Bus bus) {
        if (!busses.contains(bus)) {
            busses.add(bus);
        }
    }

    public Bus getFreeBus() {
        System.out.println(busses.size());
        return busses.stream().filter(bus -> !bus.isDeparted()).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return "Cruise from " + startPoint + " to " + endPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Direction)) return false;
        Direction direction = (Direction) o;
        return startPoint.equals(direction.startPoint) && endPoint.equals(direction.endPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startPoint, endPoint);
    }

    @Override
    public int compareTo(Direction other) {
        return Integer.compare(other.getPassengerCount(), this.getPassengerCount());
    }
}
