package ee.taltech.iti0202.exam;

import java.util.*;
import java.util.stream.Collectors;

public class ProductService {
    private Set<Client> clients = new HashSet<>();
    private Set<Product> products = new HashSet<>();
    private Set<Invoice> historyList = new HashSet<>();

    public ProductService() { }

    public Set<Client> getClients() {
        return clients;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void addClient(Client client) {
        if (!clients.contains(client)) {
            clients.add(client);
        }
    }

    public void addProduct(Product product) {
        if (!products.contains(product)) {
            products.add(product);
        }
    }

    public void addHistory(Invoice invoice) {
        if (!historyList.contains(invoice)) {
            historyList.add(invoice);
        }
    }

    public List<Product> findPopularProducts() {
        if (historyList.isEmpty()) {
            return new ArrayList<>();
        }

        Map<Product, Integer> productList = new HashMap<>();
        for (Invoice invoice : historyList) {
            for (Map.Entry<Product, Integer> entry : invoice.getProducts().entrySet()) {
                if (productList.containsKey(entry.getKey())) {
                    productList.put(entry.getKey(), productList.get(entry.getKey()) + entry.getValue());
                } else {
                    productList.put(entry.getKey(), entry.getValue());
                }
            }
        }
        int maximum = 0;
        for (Integer num : productList.values()) {
           if (num > maximum) {
               maximum = num;
           }
        }
        List<Product> popularProducts = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : productList.entrySet()) {
            if (entry.getValue() == maximum) {
                popularProducts.add(entry.getKey());
            }
        }

        return popularProducts;
    }

    public List<Product> findProducts(String name) {
        return products.stream().filter(p -> p.getProductName().equals(name)).collect(Collectors.toList());
    }

    public Set<Invoice> getHistoryList() {
        return historyList;
    }

}
