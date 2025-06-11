package ee.taltech.iti0202.zoo.animal.special;

import ee.taltech.iti0202.zoo.animal.Animal;

import java.util.Random;

/**
 * Represents a Monkey, a specific type of animal in the zoo.
 * Monkeys are mammals and have unique behaviors, such as making random sounds when not hungry.
 */
public class Monkey extends Animal {

    private Random random = new Random(); // Random generator for sound selection

    /**
     * Constructor for creating a Monkey object.
     *
     * @param name              The name of the monkey
     * @param sound             The sound the monkey makes (unused, as monkeys have unique sound logic)
     * @param daysBeforeHungry  The number of days until the monkey becomes hungry
     * @param type              The type of the animal (should be MAMMAL for monkeys)
     */
    public Monkey(String name, String sound, int daysBeforeHungry, AnimalType type) {
        super(name, sound, daysBeforeHungry, AnimalType.MAMMAL);
    }

    /**
     * Overrides the getSound method to provide unique sound behavior for monkeys.
     * If the monkey is hungry, it returns "BANANA". Otherwise, it randomly returns "ääh" or "uuh".
     *
     * @return The sound the monkey makes, depending on its hunger state
     */
    @Override
    public String getSound() {
        return isHungry() ? "BANANA" : (random.nextBoolean() ? "ääh" : "uuh");
    }
}
