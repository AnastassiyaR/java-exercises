package ee.taltech.iti0202.patterns.service;

import ee.taltech.iti0202.patterns.domain.Order;
import ee.taltech.iti0202.patterns.service.strategy.DiscountStrategy;


public class OrderService {

    /**
     * Calculate price
     * @param order
     * @param strategy
     * @return price
     */
    public double calculatePrice(Order order, DiscountStrategy strategy) {
        return order.getProducts().stream()
                .mapToDouble(strategy::calculatePrice)
                .sum();
    }
}
