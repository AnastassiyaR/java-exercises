package ee.taltech.iti0202.productscatalog.catalog;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.taltech.iti0202.productscatalog.json.Json;
import ee.taltech.iti0202.productscatalog.logger.LogLevel;
import ee.taltech.iti0202.productscatalog.logger.Logger;
import ee.taltech.iti0202.productscatalog.product.Product;
import ee.taltech.iti0202.productscatalog.product.ProductAttributes;

import java.util.Comparator;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CatalogFacade {
    static List<Product> catalog;
    private final String jsonFilePath;

    /**
     * Constructor of catalog facade
     * @param jsonFilePath
     */
    public CatalogFacade(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
        this.catalog = Json.loadCases(jsonFilePath);
    }

    public static List<Product> getCatalog() {
        return catalog;
    }

    /**
     * Get all products
     * @return a table of products
     */
    public String allProducts() {
        if (catalog.isEmpty()) return "No products found";

        return catalog.stream()
                .map(product -> {
                    String name = product.name();
                    String category = product.category();
                    double price = product.price();
                    String brand = product.brand();
                    int stock = product.stock();
                    String description = product.description();
                    Map<ProductAttributes, String> specifications = product.specifications();

                    Product.validateProduct(name, category, price, brand, stock, description, specifications);

                    return String.format(
                            "Product: %s\nCategory: %s\nPrice: %.2f\n-------------------",
                            name, category, price, brand, stock, description, specifications);
                })
                .collect(Collectors.joining("\n"));
    }

    /**
     * Add product
     * @param product
     */
    public void addProduct(Product product) {
        catalog.add(product);
        Logger.log("Product added to memory: " + product.name(), LogLevel.INFO);
        saveCatalogToJson();
    }

    /**
     * Remove product
     * @param product
     */
    public void removeProduct(Product product) {
        Logger.log("Attempting to remove product: " + product.name(), LogLevel.INFO);

        if (catalog.contains(product)) {
            catalog.remove(product);  // Удаление продукта из каталога
            Logger.log("Product removed from memory: " + product.name(), LogLevel.INFO);
            saveCatalogToJson();  // Сохраняем изменения в файл JSON
        } else {
            Logger.log("Product not found in catalog: " + product.name(), LogLevel.DEBUG);
        }
    }

    /**
     * Change product
     * @param oldProduct
     * @param newProduct
     */
    public void changeProduct(Product oldProduct, Product newProduct) {
        if (oldProduct == null || newProduct == null) {
            Logger.log("Product not found", LogLevel.ERROR);
        }

        if (oldProduct.equals(newProduct)) {
            Logger.log("Product already exists", LogLevel.ERROR);
        }

        boolean found = false;
       for (Product product : catalog) {
           if (product.equals(oldProduct)) {
               removeProduct(product);
               addProduct(newProduct);
               found = true;
           }
       }
       if (!found) {
           Logger.log("Product not found", LogLevel.ERROR);
       }
       Logger.log("Changed product: " + oldProduct.name() + " -> " + newProduct.name(), LogLevel.INFO);
    }

    /**
     * Filter by category
     * @param category
     * @return sorted category based on category
     */
    public List<Product> filterByCategory(String category) {
        return catalog.stream()
                .filter(product -> product.category().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    /**
     * Filter by price range
     * @param min
     * @param max
     * @return sorted category based on price range
     */
    public List<Product> filterByPriceRange(double min, double max) {
        return catalog.stream()
                .filter(product -> product.price() >= min && product.price() <= max)
                .collect(Collectors.toList());
    }

    /**
     * Filter by brand
     * @param brand
     * @return sorted category based on brand
     */
    public List<Product> filterByBrand(String brand) {
        return catalog.stream()
                .filter(product -> product.brand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    /**
     * Ascending or descending sort by price
     * @param ascending
     * @return if true, ascending sorted category based on price. If false - descending one
     */
    public List<Product> sortByPrice(boolean ascending) {
        return catalog.stream()
                .sorted((p1, p2) -> {
                    int result = Double.compare(p1.price(), p2.price());
                    return ascending ? result : -result;
                })
                .collect(Collectors.toList());
    }

    /**
     * Ascending/descending sort by price
     * @param ascending
     * @return if true, ascending sorted category based on names. If false - descending one
     */
    public List<Product> sortByName(boolean ascending) {
        return catalog.stream()
                .sorted(ascending
                        ? Comparator.comparing(Product::name)
                        : Comparator.comparing(Product::name).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Save catalog to json after adding/removing/changing product
     */
    private void saveCatalogToJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(jsonFilePath), catalog);
            Logger.log("Catalog successfully saved to JSON", LogLevel.INFO);
        } catch (IOException e) {
            Logger.log("Failed to save catalog to JSON: " + e.getMessage(), LogLevel.ERROR);
        }
    }

    /**
     * Clear catalog (for testing)
     */
    public void clearCatalog() {
        catalog.clear();
        Logger.log("Catalog cleared", LogLevel.INFO);
        saveCatalogToJson();
    }
}
