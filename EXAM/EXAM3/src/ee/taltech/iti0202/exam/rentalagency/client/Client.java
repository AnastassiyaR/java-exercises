package ee.taltech.iti0202.exam.rentalagency.client;

import ee.taltech.iti0202.exam.rentalagency.RentalAgency;
import ee.taltech.iti0202.exam.rentalagency.property.Property;

/**
 * Represents a person who can book or cancel rental properties.
 */
public class Client {
    private int id;
    private float money;

    /**
     * Constructor for the Client.
     */
    public Client(float money) {
        this.money = money;
    }

    /**
     * Returns the client ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the amount of money the client has.
     */
    public float getMoney() {
        return money;
    }

    /**
     * Adds money to the client's account.
     */
    public void addMoney(float sum) {
        money += sum;
    }

    /**
     * Attempts to book a property through the agency.
     */
    public boolean bookProperty(RentalAgency agency, Property property, int nights) {
        if (money >= property.calculatePrice(nights)) {
            if (agency.rentOutProperty(property, nights, id)) {
                money -= property.calculatePrice(nights);
                return true;
            }
        }
        return false;
    }

    /**
     * Cancels a previously booked property.
     */
    public boolean cancelBooking(RentalAgency agency, Property property) {
        if (agency.getAllProperties().contains(property) && property.isRented()) {
            return true;
        }
        return false;
    }
}
