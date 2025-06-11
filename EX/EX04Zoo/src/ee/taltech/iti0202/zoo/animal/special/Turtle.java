package ee.taltech.iti0202.zoo.animal.special;

import ee.taltech.iti0202.zoo.animal.Animal;

/**
 * Represents a Turtle, a specific type of animal in the zoo.
 * Turtles are amphibians and have unique characteristics.
 */
public class Turtle extends Animal {

    /**
     * Constructor for creating a Turtle object.
     *
     * @param name              The name of the turtle
     * @param sound             The sound the turtle makes (unused, as turtles are silent in this implementation)
     * @param daysBeforeHungry  The number of days until the turtle becomes hungry
     * @param type              The type of the animal (should be AMPHIBIAN for turtles)
     */
    public Turtle(String name, String sound, int daysBeforeHungry, AnimalType type) {
        super(name, "", daysBeforeHungry, AnimalType.AMPHIBIAN);
    }
}
