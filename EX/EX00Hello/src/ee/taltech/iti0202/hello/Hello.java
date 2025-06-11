package ee.taltech.iti0202.hello;

/**
 * Simple class to test whether you get feedback.
 */
public class Hello {
    /**
     * Returns a greeting for the given product.
     * If name is empty string, then returns "Hello!".
     * Otherwise returns "Hello, [name]!"
     *
     * @param name Name of the product.
     * @return Greeting
     */
    public static String getGreeting(String name) {
        if (name == null || name.isEmpty()) {
            return "Hello!";
        } else {
            return "Hello, " + name + "!";
        }
    }

    /**
     * The main method which is executed when the program is executed.
     * @param args Arguments from command line
     */
    public static void main(String[] args) {
        System.out.println(getGreeting("Java"));
    }
}
