package ee.taltech.iti0202.exam;

import java.util.ArrayList;
import java.util.List;

public class Bus {

    private Direction direction;
    private int capacity;
    private boolean departed = false;
    private List<Client> passengers = new ArrayList<>();

    public Bus(Direction direction, int capacity) {
        this.direction = direction;
        this.capacity = capacity;
    }

    public int getPassengersSize() {
        return passengers.size();
    }

    public Direction getDirection() {
        return direction;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isDeparted() {
        return departed;
    }

    public void depart() {
        departed = true;
    }

    public boolean addPassenger(Client client) {
        if (departed) return false;
        if (passengers.size() >= capacity) return false;
        passengers.add(client);
        return true;
    }
}
