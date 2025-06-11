package ee.taltech.iti0202.zoo.caretaker;

import ee.taltech.iti0202.zoo.exceptions.ZooException;
import ee.taltech.iti0202.zoo.animal.Animal;

import java.util.List;

public class Caretaker {

    private String name; // Name of the caretaker
    public List<Animal.AnimalType> canFeed; // List of animal types the caretaker can feed

    /**
     * Constructor for creating a Caretaker object.
     *
     * @param name    The name of the caretaker
     * @param canFeed List of animal types the caretaker can feed
     */
    public Caretaker(String name, List<Animal.AnimalType> canFeed) {
        this.name = name;
        this.canFeed = canFeed;
    }

    /**
     * Returns the name of the caretaker.
     *
     * @return Name of the caretaker
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the list of animal types the caretaker can feed.
     *
     * @return List of animal types the caretaker can feed
     */
    public List<Animal.AnimalType> getCanFeed() {
        return canFeed;
    }

    /**
     * Feeds a specific animal if the caretaker is capable of feeding its type.
     *
     * @param animal The animal to feed
     * @throws ZooException If the provided object is not an animal or is null
     */
    public void feedAnimal(Animal animal) throws ZooException {
        if (!(animal instanceof Animal) || animal == null) {
            throw new ZooException(ZooException.Reason.NOT_ANIMAL);
        }

        if (canFeed.contains(animal.getType())) {
            animal.feed();
        }
    }
}
