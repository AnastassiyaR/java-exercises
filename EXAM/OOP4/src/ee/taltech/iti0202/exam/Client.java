package ee.taltech.iti0202.exam;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private double budget;
    private List<Product> clientProduct;

    public Client(String name, double budget) {
        this.name = name;
        this.budget = budget;
        this.clientProduct = new ArrayList<>();
    }

    public double getBudget() {
        return budget;
    }

    public boolean buyProduct(Invoice invoice) {
        double price = invoice.calculateTotal();
        if (budget < price) return false;

        budget -= price;
        clientProduct.addAll(invoice.getProducts().keySet());
        return true;
    }

    @Override
    public String toString() {
        return "Client{name='" + name + "', budget=" + budget + '}';
    }

    public List<Product> getClientProduct() {
        return clientProduct;
    }

}
