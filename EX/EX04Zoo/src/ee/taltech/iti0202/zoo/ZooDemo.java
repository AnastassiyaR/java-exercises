package ee.taltech.iti0202.zoo;

import ee.taltech.iti0202.zoo.exceptions.ZooException;
import ee.taltech.iti0202.zoo.animal.Animal;
import ee.taltech.iti0202.zoo.animal.special.Lamb;
import ee.taltech.iti0202.zoo.animal.special.Monkey;
import ee.taltech.iti0202.zoo.animal.special.Turtle;
import ee.taltech.iti0202.zoo.caretaker.Caretaker;
import ee.taltech.iti0202.zoo.zoo.Zoo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ZooDemoTests {
    private Zoo zoo;
    private Animal lamb;
    private Animal monkey;
    private Animal turtle;
    private Caretaker caretaker;

    @BeforeEach
    void setUp() {
        zoo = new Zoo();
        lamb = new Lamb("Dolly", "Mää", 3, Animal.AnimalType.MAMMAL);
        monkey = new Monkey("George", "Ooh", 2, Animal.AnimalType.MAMMAL);
        turtle = new Turtle("Leo", "", 5, Animal.AnimalType.AMPHIBIAN);
        caretaker = new Caretaker("John", List.of(Animal.AnimalType.MAMMAL, Animal.AnimalType.AMPHIBIAN));
    }

    @Test
    void testAddAnimalSuccessfully() throws ZooException {
        zoo.addAnimal(lamb);
        assertTrue(zoo.getAnimals().contains(lamb));
    }

    @Test
    void testGetDaysBeforeHungryNegative() {
        Animal animal = new Animal("Lion", "Roar", -1, Animal.AnimalType.MAMMAL);
        Exception exception = assertThrows(ZooException.class, animal::getDaysBeforeHungry);
        assertEquals(ZooException.Reason.DAYS_UNTIL_HUNGRY_IS_NEGATIVE, ((ZooException) exception).getReason());
    }

    @Test
    void testAddNullAnimalThrowsException() {
        assertThrows(ZooException.class, () -> zoo.addAnimal(null));
    }

    @Test
    void testAddCaretakerSuccessfully() throws ZooException {
        zoo.addCaretaker(caretaker);
        assertTrue(zoo.getCaretakers().contains(caretaker));
    }

    @Test
    void testFeedWhenNotHungry() {
        Animal animal = new Animal("Tiger", "Growl", 5, Animal.AnimalType.MAMMAL);

        assertFalse(animal.isHungry());

        Exception exception = assertThrows(ZooException.class, animal::feed);
        assertEquals(ZooException.Reason.ANIMAL_IS_NOT_HUNGRY, ((ZooException) exception).getReason());
    }

    @Test
    void testAddNullCaretakerThrowsException() {
        assertThrows(ZooException.class, () -> zoo.addCaretaker(null));
    }

    @Test
    void testAnimalHunger() {
        assertFalse(lamb.isHungry());
        assertFalse(monkey.isHungry());
    }

    @Test
    void testLambAlwaysSaysMaa() {
        assertEquals("Mää", lamb.getSound());
    }

    @Test
    void testTurtleMakesNoSound() {
        assertEquals("", turtle.getSound());
    }

    @Test
    void testMonkeyMakesRandomSoundsWhenNotHungry() {
        String sound = monkey.getSound();
        assertTrue(sound.equals("ääh") || sound.equals("uuh"));
    }

    @Test
    void testMonkeySaysBananaWhenHungry() {
        monkey.nextDay();
        monkey.nextDay();
        assertTrue(monkey.isHungry());
        assertEquals("BANANA", monkey.getSound());
    }

    @Test
    void testCaretakerFeedsHungryAnimal() throws ZooException {
        zoo.addAnimal(monkey);
        zoo.addCaretaker(caretaker);
        monkey.nextDay();
        monkey.nextDay();
        assertTrue(monkey.isHungry());
        caretaker.feedAnimal(monkey);
        assertFalse(monkey.isHungry());
    }

    @Test
    void testZooFindsBestCaretaker() throws ZooException {
        Caretaker anotherCaretaker = new Caretaker("Anna", List.of(Animal.AnimalType.MAMMAL));
        zoo.addCaretaker(caretaker);
        zoo.addCaretaker(anotherCaretaker);
        assertEquals(caretaker, zoo.theBestCaretaker().orElse(null));
    }

    @Test
    void testCaretakerGetNameAndGetCanFeed() {
        Caretaker caretaker = new Caretaker("John", List.of(Animal.AnimalType.MAMMAL, Animal.AnimalType.BIRD));

        assertEquals("John", caretaker.getName());

        List<Animal.AnimalType> canFeedList = caretaker.getCanFeed();
        assertEquals(2, canFeedList.size()); // Проверяем размер списка
        assertTrue(canFeedList.contains(Animal.AnimalType.MAMMAL)); // Проверяем наличие млекопитающих
        assertTrue(canFeedList.contains(Animal.AnimalType.BIRD)); // Проверяем наличие птиц
    }

    @Test
    void testFeedAnimalWithNullThrowsException() {
        Caretaker caretaker = new Caretaker("John", List.of(Animal.AnimalType.MAMMAL));

        ZooException exception = assertThrows(ZooException.class, () -> caretaker.feedAnimal(null));

        assertEquals(ZooException.Reason.NOT_ANIMAL, exception.getReason());
    }

    @Test
    void testGetName() {
        Caretaker caretaker = new Caretaker("John", List.of(Animal.AnimalType.MAMMAL));

        assertEquals("John", caretaker.getName());
    }

    @Test
    void testGetCanFeed() {
        Caretaker caretaker = new Caretaker("Anna", List.of(Animal.AnimalType.MAMMAL, Animal.AnimalType.BIRD));

        List<Animal.AnimalType> canFeedList = caretaker.getCanFeed();
        assertEquals(2, canFeedList.size());
        assertTrue(canFeedList.contains(Animal.AnimalType.MAMMAL));
        assertTrue(canFeedList.contains(Animal.AnimalType.BIRD));
    }

    @Test
    void testErrorNoTheBestCaretaker() {
        Caretaker caretaker = new Caretaker("John", List.of(Animal.AnimalType.MAMMAL));

        ZooException exception = assertThrows(ZooException.class, () -> zoo.theBestCaretaker().orElse(null));

        assertEquals(ZooException.Reason.NO_CARETAKERS, exception.getReason());
    }

    @Test
    void testGetAnimalStatusWithMultipleAnimals() throws ZooException {
        Zoo zoo = new Zoo();

        Animal lion = new Animal("Lion", "Roar", 3, Animal.AnimalType.MAMMAL);
        Animal parrot = new Animal("Parrot", "Squawk", 2, Animal.AnimalType.BIRD);
        Animal turtle = new Animal("Turtle", "", 5, Animal.AnimalType.REPTILE); // Черепаха не издает звуков

        zoo.addAnimal(lion);
        zoo.addAnimal(parrot);
        zoo.addAnimal(turtle);

        String expectedStatus = """
                Lion (MAMMAL): "Roar"
                Parrot (BIRD): "Squawk"
                Turtle (REPTILE): ""
                """;

        assertEquals(expectedStatus, zoo.getAnimalStatus());
    }

    @Test
    void testGetAnimalStatusWithNoAnimals() {
        Zoo zoo = new Zoo();

        String expectedStatus = "";

        assertEquals(expectedStatus, zoo.getAnimalStatus());
    }

    @Test
    void testGetAnimalStatusWithHungryAnimal() throws ZooException {
        Zoo zoo = new Zoo();

        Animal lion = new Animal("Lion", "Roar", 0, Animal.AnimalType.MAMMAL); // Лев голоден

        zoo.addAnimal(lion);

        String expectedStatus = """
                Lion (MAMMAL): ""
                """;

        assertEquals(expectedStatus, zoo.getAnimalStatus());
    }
}
