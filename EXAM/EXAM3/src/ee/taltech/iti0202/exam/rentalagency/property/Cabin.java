package ee.taltech.iti0202.exam.rentalagency.property;

/**
 * Represents a rental cabin which may have a fireplace.
 */
public class Cabin extends Property {
    private boolean hasFirePlace;

    /**
     * Constructor for the Cabin
     */
    public Cabin(String location, float pricePerNight, boolean hasFirePlace) {
        super(location, pricePerNight);
        this.hasFirePlace = hasFirePlace;
    }

    /**
     * Calculates the total price for the given number of nights.
     * Adds 10 if the cabin has a fireplace.
     */
    @Override
    public float calculatePrice(int nights) {
        float sum = super.calculatePrice(nights);
        if (hasFirePlace) {
            return sum + 10;
        }
        return sum;
    }

    /**
     * Returns a formatted string with cabin details.
     */
    @Override
    public String getPropertyDetails() {
        return super.getPropertyDetails();
    }
}
