package ee.taltech.iti0202.patterns.service.strategy;

import ee.taltech.iti0202.patterns.domain.prototype.Product;


public class PromocodeDiscount implements DiscountStrategy {
    private final double percentage;

    /**
     * Constructor of promocode discount
     * @param percentage
     */
    public PromocodeDiscount(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public double calculatePrice(Product product) {
        return product.getPrice() * (1 - percentage);
    }
}
