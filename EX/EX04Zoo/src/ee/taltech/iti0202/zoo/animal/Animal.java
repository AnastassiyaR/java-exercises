package ee.taltech.iti0202.zoo.animal;

import ee.taltech.iti0202.zoo.exceptions.ZooException;

public class Animal {

    /**
     * Enum representing the type of animal.
     */
    public enum AnimalType {
        MAMMAL, BIRD, FISH, REPTILE, AMPHIBIAN
    }


    private String name;
    private String sound;
    public int daysBeforeHungry;
    public int countDay = 0;
    public AnimalType type;
    public boolean hungry;

    /**
     * Constructor for creating an Animal object.
     *
     * @param name Name of the animal
     * @param sound Sound the animal makes
     * @param daysBeforeHungry Number of days until the animal becomes hungry
     * @param type Type of the animal
     */
    public Animal(String name, String sound, int daysBeforeHungry, Animal.AnimalType type) {
        this.name = name;
        this.sound = sound;
        this.daysBeforeHungry = daysBeforeHungry;
        this.type = type;
        this.hungry = false;
    }

    /**
     * Returns the name of the animal.
     *
     * @return Name of the animal
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the sound the animal makes. If the animal is hungry, it returns an empty string.
     *
     * @return Sound of the animal or empty string if hungry
     */
    public String getSound() {
        return isHungry() ? "" : sound;
    }

    /**
     * Returns the number of days before the animal becomes hungry.
     *
     * @return Days before the animal becomes hungry
     * @throws ZooException If daysBeforeHungry is negative
     */
    public int getDaysBeforeHungry() throws ZooException {
        if (daysBeforeHungry < 0) {
            throw new ZooException(ZooException.Reason.DAYS_UNTIL_HUNGRY_IS_NEGATIVE);
        }
        return daysBeforeHungry;
    }

    /**
     * Returns the type of the animal.
     *
     * @return Type of the animal
     */
    public AnimalType getType() {
        return type;
    }

    /**
     * Checks if the animal is hungry.
     *
     * @return True if the animal is hungry, false otherwise
     */
    public boolean isHungry() {
        this.hungry = (daysBeforeHungry - countDay <= 0);
        return this.hungry;
    }

    /**
     * Feeds the animal. Resets the hunger counter if the animal is hungry.
     *
     * @throws ZooException If the animal is not hungry
     */
    public void feed() throws ZooException {
        daysBeforeHungry = getDaysBeforeHungry();

        if (daysBeforeHungry - countDay <= 0 && isHungry()) {
            countDay = 0;
            this.hungry = false;
        } else {
            throw new ZooException(ZooException.Reason.ANIMAL_IS_NOT_HUNGRY);
        }
    }

    /**
     * Advances to the next day. Increments the day counter.
     */
    public void nextDay() {
        countDay++;
    }
}
