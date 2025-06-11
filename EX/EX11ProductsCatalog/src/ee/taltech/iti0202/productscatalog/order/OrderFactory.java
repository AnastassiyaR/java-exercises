package ee.taltech.iti0202.productscatalog.order;

import ee.taltech.iti0202.productscatalog.logger.LogLevel;
import ee.taltech.iti0202.productscatalog.logger.Logger;
import ee.taltech.iti0202.productscatalog.product.Product;
import java.util.List;


public class OrderFactory {
    private static int counter = 0;

    /**
     * Create a order
     * @param type
     * @param products
     * @param address
     * @return
     */
    public static Order createOrder(OrderType type, List<Product> products, String address) {
        String orderId = "ORD-" + (++counter);

        validateParameters(type, orderId, products);

        return switch (type) {
            case DELIVERY -> new DeliveryOrder(orderId, products, address);
            case PICKUP -> new PickupOrder(orderId, products);
        };
    }

    /**
     * Validate parameters of order
     * @param type
     * @param orderId
     * @param products
     */
    private static void validateParameters(OrderType type, String orderId, List<Product> products) {
        if (type == null) {
            Logger.log("Type is missing", LogLevel.ERROR);
        }
        if (orderId == null || orderId.isEmpty()) {
            Logger.log("The orderId is missing", LogLevel.ERROR);
        }
        if (products == null || products.isEmpty()) {
            Logger.log("No products are provided", LogLevel.ERROR);
        }
    }

    /**
     * Resets the order counter (for testing purposes)
     */
    public static void resetCounter() {
        counter = 0;
        Logger.log("The counter was restart", LogLevel.INFO);
    }

    public static int getCounter() {
        return counter;
    }
}
