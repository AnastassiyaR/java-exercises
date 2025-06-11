package ee.taltech.iti0202.exam;

import java.util.HashMap;
import java.util.Map;

public class Invoice implements Comparable<Invoice> {
    private Client client;
    private Map<Product, Integer> products;

    public Invoice(Client client) {
        this.client = client;
        this.products = new HashMap<>();
    }

    public Client getClient() {
        return client;
    }

    public void addProduct(Product product, int quantity, Client client) {
        if (this.client == client) {
            products.put(product, products.getOrDefault(product, 0) + quantity);
        }
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public double calculateTotal() {
        return products.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getProductPrice() * entry.getValue())
                .sum();
    }

    @Override
    public int compareTo(Invoice other) {
        return Double.compare(other.calculateTotal(), this.calculateTotal());
    }
}
