package ee.taltech.iti0202.productscatalog.order;

import ee.taltech.iti0202.productscatalog.logger.LogLevel;
import ee.taltech.iti0202.productscatalog.logger.Logger;
import ee.taltech.iti0202.productscatalog.product.Product;

import java.util.List;

public class PickupOrder extends Order {

    /**
     * Constuctor of pickup order
     * @param orderId
     * @param products
     */
    public PickupOrder(String orderId, List<Product> products) {
        super(orderId, products);
        Logger.log("Pickup order created", LogLevel.INFO);
    }
}
