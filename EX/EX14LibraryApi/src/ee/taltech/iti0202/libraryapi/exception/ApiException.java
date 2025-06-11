package ee.taltech.iti0202.libraryapi.exception;

public class ApiException extends RuntimeException {

    /**
     * Api exception
     * @param message
     */
    public ApiException(String message) {
        super(message);
    }
}
