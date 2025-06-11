package exceptions;

public class UniException extends Exception {

    public enum Reason {
        ALL_PARAMETERS_MUST_BE_SET,
        INVALID_TIME,
        INVALID_BOOKING,
        CANNOT_BE_NULL,
        ALREADY_CONTAINS,
        DO_NOT_CONTAIN,
        INVALID_FORMAT,
        UNAUTHORIZED,
        INVALID_UNIVERSITY,
        ROOM_BROKEN,
        NO_MAINTAINER_AVAILABLE,
        NOT_BROKEN,
        OVERLAP,
        ITEM_IS_NOT_FOUND,
        ROOM_IS_NOT_BROKEN,
        NEGATIVE_NUMBER
        }

    private Reason reason;

    /**
     * Constructor
     * @param reason
     */
    public UniException(Reason reason) {
        this.reason = reason;
    }

    public Reason getReason() {
        return reason;
    }
}
