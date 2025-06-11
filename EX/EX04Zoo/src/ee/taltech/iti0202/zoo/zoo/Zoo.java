package ee.taltech.iti0202.zoo.zoo;

import ee.taltech.iti0202.zoo.exceptions.ZooException;
import ee.taltech.iti0202.zoo.animal.Animal;
import ee.taltech.iti0202.zoo.caretaker.Caretaker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Zoo {

    public List<Animal> animals = new ArrayList<>(); // List of animals in the zoo
    public List<Caretaker> caretakers = new ArrayList<>(); // List of caretakers in the zoo
    public List<Animal> hungryAnimals = new ArrayList<>(); // List of currently hungry animals

    /**
     * Returns the list of animals in the zoo.
     *
     * @return List of animals
     */
    public List<Animal> getAnimals() {
        return animals;
    }

    /**
     * Returns the list of caretakers in the zoo.
     *
     * @return List of caretakers
     */
    public List<Caretaker> getCaretakers() {
        return caretakers;
    }

    /**
     * Adds an animal to the zoo.
     *
     * @param animal The animal to add
     * @throws ZooException If the provided object is not an animal or is null
     */
    public void addAnimal(Animal animal) throws ZooException {
        if (!(animal instanceof Animal) || animal == null) {
            throw new ZooException(ZooException.Reason.NOT_ANIMAL);
        }
        if (!animals.contains(animal)) {
            animals.add(animal);
        }
    }

    /**
     * Adds a caretaker to the zoo.
     *
     * @param caretaker The caretaker to add
     * @throws ZooException If the provided object is not a caretaker or is null
     */
    public void addCaretaker(Caretaker caretaker) throws ZooException {
        if (!(caretaker instanceof Caretaker) || caretaker == null) {
            throw new ZooException(ZooException.Reason.NOT_CARETAKER);
        }
        if (!caretakers.contains(caretaker)) {
            caretakers.add(caretaker);
        }
    }

    /**
     * Checks if the zoo contains a specific animal.
     *
     * @param animal The animal to check
     * @return True if the animal is in the zoo, false otherwise
     */
    public boolean haveAnimal(Animal animal) {
        return animals.contains(animal);
    }

    /**
     * Checks if a specific caretaker is in the zoo's list of caretakers.
     *
     * @param caretaker The caretaker to check
     * @return True if the caretaker is in the zoo, false otherwise
     */
    public boolean haveCaretaker(Caretaker caretaker) {
        return caretakers.contains(caretaker);
    }

    /**
     * Feeds an animal in the zoo. If the animal is not in the zoo, increments its day counter.
     *
     * @param animal The animal to feed
     * @param caretaker The caretaker responsible for feeding
     * @throws ZooException If the animal or caretaker is not in the zoo, or if the animal cannot be fed
     */
    public void feedAnimal(Animal animal, Caretaker caretaker) throws ZooException {
        if (!haveAnimal(animal) || !haveCaretaker(caretaker)) {
            throw new ZooException(ZooException.Reason.NOT_IN_ZOO);
        } else if (!(animal instanceof Animal) || animal == null) {
            throw new ZooException(ZooException.Reason.NOT_ANIMAL);
        } else if (!(caretaker instanceof Caretaker) || caretaker == null) {
            throw new ZooException(ZooException.Reason.NOT_CARETAKER);
        } else {
            if (getHungryAnimals().contains(animal)) {
                caretaker.feedAnimal(animal);
            }
        }
    }

    /**
     * Generates a status report for all animals in the zoo.
     *
     * @return A string containing the name, type, and sound of each animal
     */
    public String getAnimalStatus() {
        StringBuilder status = new StringBuilder();
        for (Animal animal : animals) {
            status.append(animal.getName())
                    .append(" (")
                    .append(animal.getType())
                    .append("): \"")
                    .append(animal.getSound())
                    .append("\"\n");
        }
        return status.toString();
    }

    /**
     * Returns a list of currently hungry animals in the zoo.
     *
     * @return List of hungry animals
     */
    public List<Animal> getHungryAnimals() {
        for (Animal animal : animals) {
            if (animal.isHungry()) {
                hungryAnimals.add(animal);
            }
        }
        return hungryAnimals;
    }

    /**
     * Determines the best caretaker in the zoo based on their ability to feed the most hungry animals.
     *
     * @return An Optional containing the best caretaker, or empty if no caretakers are available
     * @throws ZooException If there are no caretakers in the zoo
     */
    public Optional<Caretaker> theBestCaretaker() throws ZooException {
        if (caretakers.isEmpty()) {
            throw new ZooException(ZooException.Reason.NO_CARETAKERS);
        }

        return caretakers.stream()
                .max(Comparator.comparingInt(caretaker ->
                        (int) getHungryAnimals().stream()
                                .filter(animal -> caretaker.canFeed.contains(animal.type))
                                .count()));
    }

    /**
     * Advances the zoo to the next day, incrementing the day counter for all animals.
     */
    public void nextDay() {
        for (Animal animal : animals) {
            animal.nextDay();
        }
    }
}
