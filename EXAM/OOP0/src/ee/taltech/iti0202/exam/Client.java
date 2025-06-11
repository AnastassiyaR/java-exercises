package ee.taltech.iti0202.exam;

public class Client {
    private String name;
    private int age;
    private double budget;

    public Client(String name, int age, double budget) {
        this.name = name;
        this.age = age;
        this.budget = budget;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getBudget() {
        return budget;
    }

    /**
     * Can afford ticket
     * @param price
     * @return
     */
    public boolean canAffordTicket(double price) {
        return budget >= price;
    }

    /**
     * Purchase ticket
     * @param price
     */
    public void purchaseTicket(double price) {
        if (canAffordTicket(price)) {
            budget -= price;
        }
    }
}
