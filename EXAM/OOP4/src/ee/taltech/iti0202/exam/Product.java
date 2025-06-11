package ee.taltech.iti0202.exam;

public class Product {
    private String productName;
    private double productPrice;

    public Product(String productName, double productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "Product " + productName + ". Cost " + productPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return productName.equals(product.productName);
    }

    public String getProductName() {
        return productName;
    }


    public double getProductPrice() {
        return productPrice;
    }

}
