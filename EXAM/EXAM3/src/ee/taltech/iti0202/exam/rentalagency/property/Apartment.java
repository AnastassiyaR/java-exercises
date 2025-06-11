package ee.taltech.iti0202.exam.rentalagency.property;

/**
 * Represents a rental apartment located on a certain floor.
 */
public class Apartment extends Property {
    private int floorNr;

    /**
     * Constructor for the Apartment.
     */
    public Apartment(String location, float pricePerNight, int floorNr) {
        super(location, pricePerNight);
        this.floorNr = floorNr;
    }

    /**
     * Calculates the total price including a floor-based percentage increase.
     */
    @Override
    public float calculatePrice(int nights) {
        return super.calculatePrice(nights) * ((float) floorNr / 100);
    }

    /**
     * Returns a formatted string with apartment details.
     */
    @Override
    public String getPropertyDetails() {
        return super.getPropertyDetails();
    }
}
