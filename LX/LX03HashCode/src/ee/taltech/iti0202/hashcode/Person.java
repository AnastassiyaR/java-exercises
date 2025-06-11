package ee.taltech.iti0202.hashcode;

import java.util.Objects;

public class Person {
    private String firstName;
    private String middleName;
    private String lastName;
    private int age;
    private final int dividor = 10; // The divisor used for age normalization

    /**
     * Constructor for creating a Person object.
     * @param firstName the first name of the person
     * @param middleName the middle name of the person
     * @param lastName the last name of the person
     * @param age the age of the person
     */
    public Person(String firstName, String middleName, String lastName, int age) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.age = age;
    }

    /**
     * Normalizes the first name by returning only the first character.
     * @return the first letter of the first name
     */
    private String normalizedFirstName() {
        return String.valueOf(firstName.charAt(0));
    }

    /**
     * Normalizes the middle name. If the middle name is empty, return an empty string.
     * @param otherMiddleName the middle name of another person to compare with
     * @return normalized middle name
     */
    private String normalizedMiddleName(String otherMiddleName) {
        if (middleName.isEmpty() || otherMiddleName.isEmpty()) {
            return "";
        }
        return middleName;
    }

    /**
     * Normalizes the age. Groups ages into ranges of 10 (e.g., 25 and 29 will be considered equal).
     * (As 25 / 10 = 2 and 29 / 10 = 2)
     * @return normalized age
     */
    private int normalizedAge() {
        return age / dividor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // If both objects are the same, return true

        // if (!(o instanceof Person person)) return false; -> This is a pattern matching from newer Java versions.
        // It checks both the type of o and creates a 'person' variable.

        if (!(o instanceof Person)) return false; // If o is not an instance of Person, return false
        Person person = (Person) o;

        // Check if first names match:
        boolean firstMatches = firstName.equals(person.firstName)
                || (firstName.length() == 1 || person.firstName.length() == 1)
                && normalizedFirstName().equals(person.normalizedFirstName());

        // Check if middle names match:
        boolean middleMatches = middleName.equals(person.middleName)
                || middleName.isEmpty()
                || person.middleName.isEmpty();

        // Final check to ensure equality based on all criteria:
        return firstMatches
                && lastName.equals(person.lastName)
                && middleMatches
                && normalizedAge() == person.normalizedAge();
    }

    /**
     * The hashCode method is needed to ensure objects can be stored correctly in collections like HashMap.
     * This method generates a hash code based on normalized first name, last name, middle name, and normalized age.
     * @return the hash code of the object
     */
    @Override
    public int hashCode() {
        String normFirst = normalizedFirstName();
        String normMiddle = ""; // Middle name is treated as empty if not provided
        int normAge = normalizedAge();

        return Objects.hash(normFirst, lastName, normMiddle, normAge);
    }

    @Override
    public String toString() {
        return "Person{firstName='" + firstName + "', lastName='" + lastName
                + "', middleName='" + middleName + "', age=" + age + "}";
    }
}
