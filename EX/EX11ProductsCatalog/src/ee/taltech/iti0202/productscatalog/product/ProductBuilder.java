package ee.taltech.iti0202.productscatalog.product;

import ee.taltech.iti0202.productscatalog.logger.LogLevel;
import ee.taltech.iti0202.productscatalog.logger.Logger;

import java.util.Map;

public class ProductBuilder {

    public String name;
    public String category;
    public double price;
    public String brand;
    public int stock;
    public String description;
    public Map<ProductAttributes, String> specifications;


    /**
     * Get name
     * @param name
     * @return
     */
    public ProductBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get category
     * @param category
     * @return
     */
    public ProductBuilder withCategory(String category) {
        this.category = category;
        return this;
    }

    /**
     * Get price
     * @param price
     * @return
     */
    public ProductBuilder withPrice(double price) {
        this.price = price;
        return this;
    }

    /**
     * Get brand
     * @param brand
     * @return
     */
    public ProductBuilder withBrand(String brand) {
        this.brand = brand;
        return this;
    }

    /**
     * Get stock
     * @param stock
     * @return
     */
    public ProductBuilder withStock(int stock) {
        this.stock = stock;
        return this;
    }

    /**
     * Get description
     * @param description
     * @return
     */
    public ProductBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get specifications
     * @param specifications
     * @return
     */
    public ProductBuilder withSpecifications(Map<ProductAttributes, String> specifications) {
        this.specifications = specifications;
        return this;
    }

    /**
     * Build product if it is validated
     * @return product
     */
    public Product build() {
        Product.validateProduct(name, category, price, brand, stock, description, specifications);
        Logger.log("Product created successfully: " + name, LogLevel.INFO);
        return new Product(name, category, price, brand, stock, description, specifications);
    }
}
