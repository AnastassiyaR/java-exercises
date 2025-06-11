package ee.taltech.iti0202.patterns.service.adapter;

import ee.taltech.iti0202.patterns.domain.Order;


public class FastDeliveryAdapter extends ShippingRequest {
    private final Order order;

    /**
     * Constructor of FastDeliveryAdapter
     * @param order
     */
    public FastDeliveryAdapter(Order order) {
        this.order = order;
    }

    /**
     * Get sending cost
     * @return cost
     */
    @Override
    public double getSendingCost() {
        return order.getProducts().size() + DEFAULT_SENDING_COST;
    }
}
