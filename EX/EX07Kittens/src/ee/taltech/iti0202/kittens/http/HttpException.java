package ee.taltech.iti0202.kittens.http;

public class HttpException extends RuntimeException {
    private final int code;

    /**
     *
     * @param code
     */
    public HttpException(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
