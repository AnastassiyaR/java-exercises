package ee.taltech.iti0202.kittens.http;

public record Response<T>(int httpStatus, T body) {

    private static final int OK_STATUS = 200;

    public boolean isOk() {
        return httpStatus == OK_STATUS;
    }
}
