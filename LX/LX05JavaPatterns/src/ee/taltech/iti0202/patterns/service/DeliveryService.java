package ee.taltech.iti0202.patterns.service;

import ee.taltech.iti0202.patterns.domain.Order;
import ee.taltech.iti0202.patterns.service.adapter.FastDeliveryAdapter;
import ee.taltech.iti0202.patterns.service.adapter.FastDeliveryClient;


public class DeliveryService {
    private final FastDeliveryClient fastDeliveryClient;

    /**
     * Constructor of delivery service
     * @param fastDeliveryClient
     */
    public DeliveryService(FastDeliveryClient fastDeliveryClient) {
        this.fastDeliveryClient = fastDeliveryClient;
    }

    /**
     * Send parcel
     * @param order
     * @return
     */
    public String sendParcel(Order order) {
        FastDeliveryAdapter adapter = new FastDeliveryAdapter(order);
        return fastDeliveryClient.sendParcel(adapter);
    }
}
