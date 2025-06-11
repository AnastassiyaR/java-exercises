package ee.taltech.iti0202.exam;
import java.util.*;

public class Client {
    private final String name;
    private final int age;
    private double budget;

    public Client(String name, int age, double budget) {
        this.name = name;
        this.age = age;
        this.budget = budget;
    }

    public double getBudget() {
        return budget;
    }

    public boolean buyTicket(Direction direction) {
        if (this.getBudget() < direction.getTicketPrice()) {
            return false;
        }

        if (budget < this.getTicketPriceForDirection(direction)) return false;

        if (direction.getFreeBus() == null) return false;
        if (!direction.getFreeBus().addPassenger(this)) return false;

        budget -= this.getTicketPriceForDirection(direction);
        return true;
    }

    public double getTicketPriceForDirection(Direction direction) {
        double price = direction.getTicketPrice();

        if (age <= 6 || age >= 60) price = 0;
        else if ((age >= 7 && age <= 18)) {
            price /= 2;
        }

        return price;
    }

    @Override
    public String toString() {
        return "Client{name='" + name + "', age=" + age + ", budget=" + budget + '}';
    }

}
