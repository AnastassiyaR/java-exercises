package ee.taltech.iti0202.productscatalog.order;

import ee.taltech.iti0202.productscatalog.logger.LogLevel;
import ee.taltech.iti0202.productscatalog.logger.Logger;
import ee.taltech.iti0202.productscatalog.product.Product;

import java.util.List;

public class DeliveryOrder extends Order {

    String address;

    /**
     * Constructor of delivery order
     * @param orderId
     * @param products
     * @param address
     */
    public DeliveryOrder(String orderId, List<Product> products, String address) {
        super(orderId, products);
        if (address == null) {
            Logger.log("Address not found", LogLevel.ERROR);
        }
        this.address = address;
        Logger.log("Delivery order created", LogLevel.INFO);

    }
}
