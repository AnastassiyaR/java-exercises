package ee.taltech.iti0202.zoo.exceptions;

/**
 * Special exception for Zoo program.
 *
 * This exception is used to handle specific error scenarios in the zoo management system.
 * You MUST NOT change anything here!
 * Use this exception in your implementation.
 */
public class ZooException extends Exception {

    /**
     * Different reasons for exception.
     *
     * Each reason corresponds to a specific error scenario in the zoo management system.
     * Another option would be to have separate classes for each reason.
     * E.g. NegativePriceException etc.
     */
    public enum Reason {
        ANIMAL_IS_NOT_HUNGRY,
        DAYS_UNTIL_HUNGRY_IS_NEGATIVE,
        NOT_ANIMAL,
        NOT_CARETAKER,
        NOT_IN_ZOO,
        NO_CARETAKERS
    }

    private Reason reason;

    /**
     * Constructor with the reason.
     *
     * @param reason The reason for exception.
     */
    public ZooException(Reason reason) {
        this.reason = reason;
    }

    /**
     * Returns the reason of the exception.
     *
     * @return The reason.
     */
    public Reason getReason() {
        return reason;
    }
}
