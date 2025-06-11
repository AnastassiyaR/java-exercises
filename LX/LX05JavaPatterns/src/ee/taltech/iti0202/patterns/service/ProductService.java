package ee.taltech.iti0202.patterns.service;

import ee.taltech.iti0202.patterns.domain.prototype.Product;


public class ProductService {

    /**
     * Clone product
     * @param product
     * @param newName
     * @return clone of product
     */
    public Product cloneProduct(Product product, String newName) {
        Product clone = product.clone();
        clone.setName(newName);
        return clone;
    }
}
