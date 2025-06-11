package ee.taltech.iti0202.patterns.service.adapter;

public class FastDeliveryClient {

    /**
     * Send parcel
     * @param shippingRequest
     * @return parcel
     */
    public String sendParcel(ShippingRequest shippingRequest) {
        return "Parcel sent with operating cost of: " + shippingRequest.getSendingCost();
    }
}
