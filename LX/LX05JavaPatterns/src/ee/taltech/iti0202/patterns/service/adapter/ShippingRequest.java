package ee.taltech.iti0202.patterns.service.adapter;


public abstract class ShippingRequest {
    public static final double DEFAULT_SENDING_COST = 3;

    /**
     * Get sending cost
     * @return cost
     */
    public double getSendingCost() {
        return DEFAULT_SENDING_COST;
    }
}
