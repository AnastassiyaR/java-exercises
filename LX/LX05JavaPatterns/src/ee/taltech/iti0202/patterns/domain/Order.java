package ee.taltech.iti0202.patterns.domain;

import ee.taltech.iti0202.patterns.domain.prototype.Product;
import java.util.List;

public class Order {
    private final List<Product> products;

    /**
     * Constructor of an order
     * @param products
     */
    public Order(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
