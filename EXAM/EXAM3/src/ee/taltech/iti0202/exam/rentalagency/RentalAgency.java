package ee.taltech.iti0202.exam.rentalagency;

import ee.taltech.iti0202.exam.rentalagency.property.Property;

import java.util.ArrayList;
import java.util.List;

/**
 * RentalAgency manages properties and handles bookings.
 */
public class RentalAgency {
    private List<Property> allProperties = new ArrayList<>();

    /**
     * Returns all properties belonging to the agency.
     */
    public List<Property> getAllProperties() {
        return allProperties;
    }

    /**
     * Adds a property to the agency if not already added.
     */
    public void addProperty(Property property) {
        if (!allProperties.contains(property)) {
            allProperties.add(property);
        }
    }

    /**
     * Removes a property from the agency.
     */
    public boolean removeProperty(Property property) {
        if (allProperties.contains(property)) {
            allProperties.remove(property);
            return true;
        }
        return false;
    }

    /**
     * Returns a list of all available (not rented) properties.
     */
    public List<Property> showAvailableProperties() {
        List<Property> properties = new ArrayList<>();
        for (Property property : allProperties) {
            if (!property.isRented()) {
                properties.add(property);
            }
        }
        return properties;
    }

    /**
     * Rents out a property if it is available and not currently rented.
     */
    public boolean rentOutProperty(Property property, int nights, int clientID) {
        if (this.allProperties.contains(property) && !property.isRented()) {
            return true;
        }
        return false;
    }

    /**
     * Cancels a rental and returns half the rental cost if valid.
     */
    public float returnProperty(Property property, int clientID) {
        if (allProperties.contains(property) && property.isRented() && property.getClientID() == clientID) {
            float refund = property.calculatePrice(property.getRentedForDays()) / 2;
            property.changeAvailability();
            return refund;
        }
        return 0;
    }
}
