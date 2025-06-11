package ee.taltech.iti0202.productscatalog.product;

import ee.taltech.iti0202.productscatalog.logger.LogLevel;
import ee.taltech.iti0202.productscatalog.logger.Logger;

import java.util.Map;


public record Product(
        String name,
        String category,
        double price,
        String brand,
        int stock,
        String description,
        Map<ProductAttributes, String> specifications
) {

    /**
     * Validate product whether it is a correct or has mistakes
     * @param name
     * @param category
     * @param price
     * @param brand
     * @param stock
     * @param description
     * @param specifications
     */
    public static void validateProduct(String name,
                                       String category,
                                       double price,
                                       String brand,
                                       int stock,
                                       String description,
                                       Map<ProductAttributes, String> specifications) {

        if (name == null || name.isEmpty()) {
            Logger.log("Product name is required", LogLevel.ERROR);
        }
        if (category == null || category.isEmpty()) {
            Logger.log("Product category is required", LogLevel.ERROR);
        }
        if (price <= 0) {
            Logger.log("Product price must be positive", LogLevel.ERROR);
        }
        if (brand == null || brand.isEmpty()) {
            Logger.log("Product brand is required", LogLevel.ERROR);
        }
        if (stock < 0) {
            Logger.log("Product stock cannot be negative", LogLevel.ERROR);
        }

        if (description == null || description.isEmpty()) {
            Logger.log("Product description is required", LogLevel.ERROR);
        }

        if (specifications == null || specifications.isEmpty()) {
            Logger.log("Product specifications is required", LogLevel.ERROR);
        }
        Logger.log("Validation of product " + name + " was successes", LogLevel.INFO);
    }
}
