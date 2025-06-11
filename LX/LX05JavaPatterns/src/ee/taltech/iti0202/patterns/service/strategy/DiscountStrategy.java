package ee.taltech.iti0202.patterns.service.strategy;

import ee.taltech.iti0202.patterns.domain.prototype.Product;

public interface DiscountStrategy {

    /**
     * Calculate price
     * @param product
     * @return
     */
    double calculatePrice(Product product);
}
