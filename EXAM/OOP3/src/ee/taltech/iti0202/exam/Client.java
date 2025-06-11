package ee.taltech.iti0202.exam;
import java.util.*;

public class Client {
    private final String name;
    private final int age;
    private double budget;
    private final List<Cruise> cruiseHistory = new ArrayList<>();

    public Client(String name, int age, double budget) {
        this.name = name;
        this.age = age;
        this.budget = budget;
    }

    public double getBudget() {
        return budget;
    }

    public boolean buyTicket(Cruise cruise) {
        if (cruise.isDeparted()) return false;

        double price = cruise.getTicketPrice();

        if (age < 6) price = 0;
        else if ((age >= 6 && age <= 18) || (age >= 60)) price /= 2;

        if (budget < price) return false;

        if (!cruise.addPassenger(this)) return false;

        budget -= price;
        cruiseHistory.add(cruise);
        return true;
    }

    @Override
    public String toString() {
        return "Client{name='" + name + "', age=" + age + ", budget=" + budget + '}';
    }

    public List<Cruise> getCruiseHistory() {
        return Collections.unmodifiableList(cruiseHistory);
    }
}
