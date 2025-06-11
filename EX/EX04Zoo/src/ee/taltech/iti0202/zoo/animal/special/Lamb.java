package ee.taltech.iti0202.zoo.animal.special;

import ee.taltech.iti0202.zoo.animal.Animal;

/**
 * Represents a Lamb, a specific type of animal in the zoo.
 * Lambs are mammals and have unique characteristics, such as always being non-hungry.
 */
public class Lamb extends Animal {

    /**
     * Constructor for creating a Lamb object.
     *
     * @param name              The name of the lamb
     * @param sound             The sound the lamb makes (default is "Mää")
     * @param daysBeforeHungry  The number of days until the lamb becomes hungry (unused, as lambs are never hungry)
     * @param type              The type of the animal (should be MAMMAL for lambs)
     */
    public Lamb(String name, String sound, int daysBeforeHungry, AnimalType type) {
        super(name, "Mää", daysBeforeHungry, AnimalType.MAMMAL);
    }

    /**
     * Overrides the isHungry method to ensure that a lamb is never hungry.
     *
     * @return Always returns false, as lambs are never hungry
     */
    @Override
    public boolean isHungry() {
        return false;
    }
}
