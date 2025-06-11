package ee.taltech.iti0202.productscatalog.logger;

public class Logger {

    /**
     * Logs a message with a specified log level
     * @param message
     * @param level
     */
    public static void log(String message, LogLevel level) {
        if (level == LogLevel.ERROR) {
            throw new IllegalArgumentException(message);
        }
        System.out.println("[" + level + "] " + message);
    }
}
