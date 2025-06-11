package ee.taltech.iti0202.patterns.service.strategy;

import ee.taltech.iti0202.patterns.domain.prototype.Product;


public class VIPDiscount implements DiscountStrategy {
    private static final double VIP_DISCOUNT_PERCENTAGE = 0.2;

    @Override
    public double calculatePrice(Product product) {
        return product.getPrice() * (1 - VIP_DISCOUNT_PERCENTAGE);
    }
}
