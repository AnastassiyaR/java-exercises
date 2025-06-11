//package ee.taltech.iti0202.patterns;
//
//import ee.taltech.iti0202.patterns.domain.Order;
//import ee.taltech.iti0202.patterns.domain.prototype.ElectronicDevice;
//import ee.taltech.iti0202.patterns.domain.prototype.Product;
//import ee.taltech.iti0202.patterns.service.DeliveryService;
//import ee.taltech.iti0202.patterns.service.OrderService;
//import ee.taltech.iti0202.patterns.service.ProductService;
//import ee.taltech.iti0202.patterns.service.adapter.FastDeliveryClient;
//import ee.taltech.iti0202.patterns.service.strategy.DiscountStrategy;
//import ee.taltech.iti0202.patterns.service.strategy.PromocodeDiscount;
//
//import java.util.List;
//
//public class DemoApp {
//
//    /**
//     * Main method to demonstrate the use of Prototype, Strategy, and Adapter patterns.
//     *
//     * @param args command line arguments
//     */
//    public static void main(String[] args) {
//        // Setup
//        ProductService productService = new ProductService();
//        OrderService orderService = new OrderService();
//
//        // 1. Prototype demo
//        Product original = new ElectronicDevice("Laptop1", 2000.0);
//        Product copy = productService.cloneProduct(original, "Laptop2");
//        System.out.println("Original: " + original);
//        System.out.println("Copy: " + copy);
//
//
//        // 2. Strategy demo
//        Order order = new Order(List.of(original, copy));
//        DiscountStrategy strategy = new PromocodeDiscount(0.1);
//        double finalPrice = orderService.calculatePrice(order, strategy);
//        System.out.println("Final price with discount: " + finalPrice); // 3600.0
//
//        // 3. Adapter demo
//        FastDeliveryClient fastClient = new FastDeliveryClient();
//        DeliveryService deliveryService = new DeliveryService(fastClient);
//        String shippingResponse = deliveryService.sendParcel(order);
//        System.out.println("Shipping response: " + shippingResponse); // Parcel sent with operating cost of: 5
//    }
//}
