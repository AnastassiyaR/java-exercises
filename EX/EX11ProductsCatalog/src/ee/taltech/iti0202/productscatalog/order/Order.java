package ee.taltech.iti0202.productscatalog.order;

import ee.taltech.iti0202.productscatalog.product.Product;

import java.util.ArrayList;
import java.util.List;


public abstract class Order {
    private final String orderId;
    private final List<Product> products;

    /**
     * Constuctor of order
     * @param orderId
     * @param products
     */
    public Order(String orderId, List<Product> products) {
        this.orderId = orderId;
        this.products = new ArrayList<>(products);
    }

    public String getOrderId() {
        return orderId;
    }

    public List<Product> getProducts() {
        return products;
    }
}
