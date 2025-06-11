package ee.taltech.iti0202.exam.rentalagency.property;
/**
 * Represents a general rental unit.
 */
public class Property {
    private int id;
    private boolean isRented = false;
    private Integer rentedForDays;
    private String location;
    private float pricePerNight;
    private Integer clientID;

    /**
     * Constructor for Property.
     */
    public Property(String location, float pricePerNight) {
        this.location = location;
        this.pricePerNight = pricePerNight;
    }

    /**
     * Returns the property ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the price per night.
     */
    public float getPricePerNight() {
        return pricePerNight;
    }

    /**
     * Returns the location of the property.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Returns the ID of the client renting the property.
     */
    public Integer getClientID() {
        return clientID;
    }

    /**
     * Returns the number of nights the property is rented for.
     */
    public Integer getRentedForDays() {
        return rentedForDays;
    }

    /**
     * Returns whether the property is currently rented.
     */
    public boolean isRented() {
        return isRented;
    }

    /**
     * Sets the ID of the client renting the property.
     */
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    /**
     * Sets how many nights the property is rented for.
     */
    public void setRentedForDays(Integer rentedForDays) {
        this.rentedForDays = rentedForDays;
    }

    /**
     * Toggles the availability of the property.
     * If rented, resets clientID and rentedForDays.
     */
    public void changeAvailability() {
        if (isRented) {
            isRented = false;
            clientID = null;
            rentedForDays = null;
        } else {
            isRented = true;
        }
    }

    /**
     * Calculates the total price for a given number of nights.
     */
    public float calculatePrice(int nights) {
        return pricePerNight * nights;
    }

    /**
     * Returns a formatted string with property details.
     */
    public String getPropertyDetails() {
        return String.format("Property %d located in %s,\nprice per night: %.1f,\navailable: %b",
                id, location, pricePerNight, !isRented);
    }
}
