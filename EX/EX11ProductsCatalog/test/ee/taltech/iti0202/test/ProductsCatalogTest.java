package ee.taltech.iti0202.test;

import ee.taltech.iti0202.productscatalog.catalog.CatalogFacade;
import ee.taltech.iti0202.productscatalog.order.Order;
import ee.taltech.iti0202.productscatalog.order.OrderFactory;
import ee.taltech.iti0202.productscatalog.order.OrderType;
import ee.taltech.iti0202.productscatalog.order.PickupOrder;
import ee.taltech.iti0202.productscatalog.order.DeliveryOrder;
import ee.taltech.iti0202.productscatalog.product.Product;
import ee.taltech.iti0202.productscatalog.product.ProductBuilder;
import ee.taltech.iti0202.productscatalog.product.ProductAttributes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ProductTest {

    @Test
    void testValidProductCreation() {
        Map<ProductAttributes, String> specifications = new HashMap<>();
        specifications.put(ProductAttributes.COLOR, "Black");
        specifications.put(ProductAttributes.MATERIAL, "Wood");
        specifications.put(ProductAttributes.SIZE, "Medium");

        Product product = new ProductBuilder()
                .withName("Table")
                .withCategory("Wood stuff")
                .withPrice(20.00)
                .withBrand("Linux")
                .withStock(100)
                .withDescription("Nice table")
                .withSpecifications(specifications)
                .build();

        assertEquals("Table", product.name());
        assertEquals("Wood stuff", product.category());
        assertEquals(20.00, product.price());
        assertEquals("Linux", product.brand());
        assertEquals(100, product.stock());
        assertEquals("Nice table", product.description());
        assertEquals(specifications, product.specifications());
    }


    @Test
    void testInValidNameProductCreation() {
        Map<ProductAttributes, String> specifications = new HashMap<>();
        specifications.put(ProductAttributes.COLOR, "Black");
        specifications.put(ProductAttributes.MATERIAL, "Wood");
        specifications.put(ProductAttributes.SIZE, "Medium");


        assertThrows(IllegalArgumentException.class, () -> {
            new ProductBuilder()
                    .withName(null)
                    .withCategory("Wood stuff")
                    .withPrice(0.00)
                    .withBrand("Linux")
                    .withStock(100)
                    .withDescription("Nice table")
                    .withSpecifications(specifications)
                    .build();
        });

    }

    @Test
    void testInValidCategoryProductCreation() {
        Map<ProductAttributes, String> specifications = new HashMap<>();
        specifications.put(ProductAttributes.COLOR, "Black");
        specifications.put(ProductAttributes.MATERIAL, "Wood");
        specifications.put(ProductAttributes.SIZE, "Medium");


        assertThrows(IllegalArgumentException.class, () -> {
            new ProductBuilder()
                    .withName("Table")
                    .withCategory(null)
                    .withPrice(0.00)
                    .withBrand("Linux")
                    .withStock(100)
                    .withDescription("Nice table")
                    .withSpecifications(specifications)
                    .build();
        });

    }

    @Test
    void testInValidPriceProductCreation() {
        Map<ProductAttributes, String> specifications = new HashMap<>();
        specifications.put(ProductAttributes.COLOR, "Black");
        specifications.put(ProductAttributes.MATERIAL, "Wood");
        specifications.put(ProductAttributes.SIZE, "Medium");


        assertThrows(IllegalArgumentException.class, () -> {
            new ProductBuilder()
                    .withName("Table")
                    .withCategory("Wood stuff")
                    .withPrice(0.00)
                    .withBrand("Linux")
                    .withStock(100)
                    .withDescription("Nice table")
                    .withSpecifications(specifications)
                    .build();
        });

    }

    @Test
    void testInValidBrandProductCreation() {
        Map<ProductAttributes, String> specifications = new HashMap<>();
        specifications.put(ProductAttributes.COLOR, "Black");
        specifications.put(ProductAttributes.MATERIAL, "Wood");
        specifications.put(ProductAttributes.SIZE, "Medium");


        assertThrows(IllegalArgumentException.class, () -> {
            new ProductBuilder()
                    .withName("Table")
                    .withCategory("Wood stuff")
                    .withPrice(10.00)
                    .withBrand(null)
                    .withStock(100)
                    .withDescription("Nice table")
                    .withSpecifications(specifications)
                    .build();
        });

    }

    @Test
    void testInValidStockProductCreation() {
        Map<ProductAttributes, String> specifications = new HashMap<>();
        specifications.put(ProductAttributes.COLOR, "Black");
        specifications.put(ProductAttributes.MATERIAL, "Wood");
        specifications.put(ProductAttributes.SIZE, "Medium");


        assertThrows(IllegalArgumentException.class, () -> {
            new ProductBuilder()
                    .withName("Table")
                    .withCategory("Wood stuff")
                    .withPrice(10.00)
                    .withBrand("Linux")
                    .withStock(-10)
                    .withDescription("Nice table")
                    .withSpecifications(specifications)
                    .build();
        });

    }

    @Test
    void testInValidDescriptionProductCreation() {
        Map<ProductAttributes, String> specifications = new HashMap<>();
        specifications.put(ProductAttributes.COLOR, "Black");
        specifications.put(ProductAttributes.MATERIAL, "Wood");
        specifications.put(ProductAttributes.SIZE, "Medium");


        assertThrows(IllegalArgumentException.class, () -> {
            new ProductBuilder()
                    .withName("Table")
                    .withCategory("Wood stuff")
                    .withPrice(0.00)
                    .withBrand("Linux")
                    .withStock(100)
                    .withDescription("")
                    .withSpecifications(specifications)
                    .build();
        });

    }

    @Test
    void testInValidSpecificationsProductCreation() {
        Map<ProductAttributes, String> specifications = new HashMap<>();


        assertThrows(IllegalArgumentException.class, () -> {
            new ProductBuilder()
                    .withName("Table")
                    .withCategory("Wood stuff")
                    .withPrice(10.00)
                    .withBrand("Linux")
                    .withStock(100)
                    .withDescription("Nice table")
                    .withSpecifications(specifications)
                    .build();
        });

    }
}

class OrderTest {

    Product product;
    List<Product> products = new ArrayList<>();

    @BeforeEach
    void testValidProductCreation() {
        Map<ProductAttributes, String> specifications = new HashMap<>();
        specifications.put(ProductAttributes.COLOR, "Black");
        specifications.put(ProductAttributes.MATERIAL, "Wood");
        specifications.put(ProductAttributes.SIZE, "Medium");

        product = new ProductBuilder()
                .withName("Table")
                .withCategory("Wood stuff")
                .withPrice(20.00)
                .withBrand("Linux")
                .withStock(100)
                .withDescription("Nice table")
                .withSpecifications(specifications)
                .build();

        products.add(product);
        OrderFactory.resetCounter();

    }

    @Test
    void testEmptyProductsOrderCreation() {
        List<Product> emptyProducts = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            Order order = OrderFactory.createOrder(OrderType.DELIVERY, emptyProducts, "Masrva");
        });
    }

    @Test
    void testDeliveryOrderCreation() {
        Order order = OrderFactory.createOrder(OrderType.DELIVERY, products, "Tallinn");

        assertInstanceOf(DeliveryOrder.class, order);
        assertEquals("ORD-1", order.getOrderId());
        assertEquals(1, order.getProducts().size());
        assertEquals(1, OrderFactory.getCounter());

    }

    @Test
    void testInValidAddressInDeliveryOrderCreation() {
        assertThrows(IllegalArgumentException.class, () -> {
            Order order = OrderFactory.createOrder(OrderType.DELIVERY, products, null);
        });
    }

    @Test
    void testDeliveryOrderWithInvalidAddressCreation() {
        assertThrows(IllegalArgumentException.class, () -> {
            Order order = OrderFactory.createOrder(OrderType.DELIVERY, products, null);
        });
    }

    @Test
    void testPickUpOrderCreation() {
        Order order = OrderFactory.createOrder(OrderType.PICKUP, products, "");

        assertInstanceOf(PickupOrder.class, order);
        assertEquals("ORD-1", order.getOrderId());
        assertEquals(1, order.getProducts().size());
    }

    @Test
    void testInValidTypeOrderCreation() {
        assertThrows(IllegalArgumentException.class, () -> {
            Order order = OrderFactory.createOrder(null, products, "Wood");
        });
    }
}

class CatalogTest {

    private CatalogFacade catalog;
    private String jsonPath = "C:\\Users\\anast\\IdeaProjects\\iti0202-2025\\"
            + "EX\\EX11ProductsCatalog\\src\\ee\\taltech\\iti0202\\productscatalog\\"
            + "products.json";

    @BeforeEach
    void setUp() {
        catalog = new CatalogFacade(jsonPath);
        catalog.clearCatalog();
    }

    @Test
    void testEmptyCatalog() {
        assertEquals("No products found", catalog.allProducts());
    }

    @Test
    void testAddProduct() {
        Map<ProductAttributes, String> specifications = new HashMap<>();
        specifications.put(ProductAttributes.COLOR, "Black");
        specifications.put(ProductAttributes.MATERIAL, "Wood");
        specifications.put(ProductAttributes.SIZE, "Medium");

        Product product = new ProductBuilder()
                .withName("Test Product")
                .withCategory("Test Category")
                .withPrice(99.99)
                .withBrand("Test Brand")
                .withStock(10)
                .withDescription("Test Description")
                .withSpecifications(specifications)
                .build();

        catalog.addProduct(product);

        assertTrue(CatalogFacade.getCatalog().contains(product));
    }

    @Test
    void testRemoveProduct() throws IOException {
        Map<ProductAttributes, String> specifications = new HashMap<>();
        specifications.put(ProductAttributes.COLOR, "Black");
        specifications.put(ProductAttributes.MATERIAL, "Wood");
        specifications.put(ProductAttributes.SIZE, "Medium");

        Product product = new ProductBuilder()
                .withName("Test Product")
                .withCategory("Test Category")
                .withPrice(99.99)
                .withBrand("Test Brand")
                .withStock(10)
                .withDescription("Test Description")
                .withSpecifications(specifications)
                .build();

        catalog.addProduct(product);
        catalog.removeProduct(product);
        assertFalse(CatalogFacade.getCatalog().contains(product));
    }

    @Test
    void testAllProductsCatalog() {
        Map<ProductAttributes, String> specifications = new HashMap<>();
        specifications.put(ProductAttributes.COLOR, "Black");
        specifications.put(ProductAttributes.MATERIAL, "Wood");
        specifications.put(ProductAttributes.SIZE, "Medium");

        Product product = new ProductBuilder()
                .withName("Test Product")
                .withCategory("Test Category")
                .withPrice(99.99)
                .withBrand("Test Brand")
                .withStock(10)
                .withDescription("Test Description")
                .withSpecifications(specifications)
                .build();

        catalog.addProduct(product);

        String expectedOutput = "Product: Test Product\nCategory: Test Category\nPrice: 99.99\n-------------------";
        String result = catalog.allProducts();
        assertTrue(result.contains(expectedOutput));
    }

    @Test
    void testChangeProductSuccess() {
        Map<ProductAttributes, String> specifications = new HashMap<>();
        specifications.put(ProductAttributes.COLOR, "Black");

        Product oldProduct = new ProductBuilder()
                .withName("Test Product")
                .withCategory("Test Category")
                .withPrice(99.99)
                .withBrand("Test Brand")
                .withStock(10)
                .withDescription("Test Description")
                .withSpecifications(specifications)
                .build();

        Product newProduct = new ProductBuilder()
                .withName("Updated Product")
                .withCategory("Updated Category")
                .withPrice(149.99)
                .withBrand("Updated Brand")
                .withStock(20)
                .withDescription("Updated Description")
                .withSpecifications(specifications)
                .build();

        catalog.addProduct(oldProduct);
        catalog.changeProduct(oldProduct, newProduct);

        assertTrue(CatalogFacade.getCatalog().contains(newProduct));
        assertFalse(CatalogFacade.getCatalog().contains(oldProduct));
    }

    @Test
    void testChangeProductWhenOldProductIsNull() {
        Product oldProduct = null;
        Map<ProductAttributes, String> specifications = new HashMap<>();
        specifications.put(ProductAttributes.COLOR, "Black");

        Product newProduct = new ProductBuilder()
                .withName("Test Product")
                .withCategory("Test Category")
                .withPrice(99.99)
                .withBrand("Test Brand")
                .withStock(10)
                .withDescription("Test Description")
                .withSpecifications(specifications)
                .build();

        assertThrows(IllegalArgumentException.class, () -> catalog.changeProduct(oldProduct, newProduct));
    }

    @Test
    void testChangeProductWhenNewProductHasSameNameAsOld() {
        Map<ProductAttributes, String> specifications = new HashMap<>();
        specifications.put(ProductAttributes.COLOR, "Black");

        Product oldProduct = new ProductBuilder()
                .withName("Test Product")
                .withCategory("Test Category")
                .withPrice(99.99)
                .withBrand("Test Brand")
                .withStock(10)
                .withDescription("Test Description")
                .withSpecifications(specifications)
                .build();

        Product newProduct = new ProductBuilder()
                .withName("Test Product")
                .withCategory("Test Category")
                .withPrice(99.99)
                .withBrand("Test Brand")
                .withStock(10)
                .withDescription("Test Description")
                .withSpecifications(specifications)
                .build();

        catalog.addProduct(oldProduct);
        assertThrows(IllegalArgumentException.class, () -> catalog.changeProduct(oldProduct, newProduct));
    }

    @Test
    void testChangeProductWhenOldProductNotFound() {
        Map<ProductAttributes, String> specifications = new HashMap<>();
        specifications.put(ProductAttributes.COLOR, "Black");

        Product oldProduct = new ProductBuilder()
                .withName("Non-Existent Product")
                .withCategory("Test Category")
                .withPrice(99.99)
                .withBrand("Test Brand")
                .withStock(10)
                .withDescription("Test Description")
                .withSpecifications(specifications)
                .build();

        Product newProduct = new ProductBuilder()
                .withName("New Product")
                .withCategory("New Category")
                .withPrice(149.99)
                .withBrand("New Brand")
                .withStock(20)
                .withDescription("New Description")
                .withSpecifications(specifications)
                .build();

        assertThrows(IllegalArgumentException.class, () -> catalog.changeProduct(oldProduct, newProduct));
    }

    @Test
    void testFilterByCategory() {
        Map<ProductAttributes, String> specifications = new HashMap<>();
        specifications.put(ProductAttributes.COLOR, "Black");

        Product product = new ProductBuilder()
                .withName("Product 1")
                .withCategory("Electronics")
                .withPrice(99.99)
                .withBrand("Brand A")
                .withStock(10)
                .withDescription("Description 1")
                .withSpecifications(specifications)
                .build();

        catalog.addProduct(product);
        List<Product> electronics = catalog.filterByCategory("Electronics");
        assertEquals(1, electronics.size());
        assertTrue(electronics.contains(product));
    }

    @Test
    void testFilterByPriceRange() {
        Map<ProductAttributes, String> specifications = new HashMap<>();
        specifications.put(ProductAttributes.COLOR, "Black");

        Product product = new ProductBuilder()
                .withName("Product 1")
                .withCategory("Electronics")
                .withPrice(99.99)
                .withBrand("Brand A")
                .withStock(10)
                .withDescription("Description 1")
                .withSpecifications(specifications)
                .build();

        catalog.addProduct(product);
        List<Product> priceRange = catalog.filterByPriceRange(10.00, 100);
        assertEquals(1, priceRange.size());
        assertTrue(priceRange.contains(product));
    }

    @Test
    void testFilterByBrand() {
        Map<ProductAttributes, String> specifications = new HashMap<>();
        specifications.put(ProductAttributes.COLOR, "Black");

        Product product = new ProductBuilder()
                .withName("Product 1")
                .withCategory("Electronics")
                .withPrice(99.99)
                .withBrand("Brand")
                .withStock(10)
                .withDescription("Description 1")
                .withSpecifications(specifications)
                .build();

        catalog.addProduct(product);
        List<Product> byBrand = catalog.filterByBrand("Brand");
        assertEquals(1, byBrand.size());
        assertTrue(byBrand.contains(product));
    }

    @Test
    void testSortByPriceAscending() {
        Map<ProductAttributes, String> specifications = new HashMap<>();
        specifications.put(ProductAttributes.COLOR, "Black");

        Product product1 = new ProductBuilder()
                .withName("Product 1")
                .withCategory("Electronics")
                .withPrice(9.99)
                .withBrand("Brand")
                .withStock(10)
                .withDescription("Description 1")
                .withSpecifications(specifications)
                .build();

        Product product2 = new ProductBuilder()
                .withName("Product 1")
                .withCategory("Electronics")
                .withPrice(99.99)
                .withBrand("Brand")
                .withStock(10)
                .withDescription("Description 1")
                .withSpecifications(specifications)
                .build();

        catalog.addProduct(product1);
        catalog.addProduct(product2);

        List<Product> sortedAscending = catalog.sortByPrice(true);
        assertEquals(2, sortedAscending.size());
        assertTrue(sortedAscending.get(0).price() < sortedAscending.get(1).price());
    }

    @Test
    void testSortByPriceDescending() {
        Map<ProductAttributes, String> specifications = new HashMap<>();
        specifications.put(ProductAttributes.COLOR, "Black");

        Product product1 = new ProductBuilder()
                .withName("Product 1")
                .withCategory("Electronics")
                .withPrice(9.99)
                .withBrand("Brand")
                .withStock(10)
                .withDescription("Description 1")
                .withSpecifications(specifications)
                .build();

        Product product2 = new ProductBuilder()
                .withName("Product 1")
                .withCategory("Electronics")
                .withPrice(99.99)
                .withBrand("Brand")
                .withStock(10)
                .withDescription("Description 1")
                .withSpecifications(specifications)
                .build();

        catalog.addProduct(product1);
        catalog.addProduct(product2);

        List<Product> sortedDescending = catalog.sortByPrice(false);
        assertEquals(2, sortedDescending.size());
        assertTrue(sortedDescending.get(0).price() > sortedDescending.get(1).price());
    }

    @Test
    void testSortByNameA_Z() {
        Map<ProductAttributes, String> specifications = new HashMap<>();
        specifications.put(ProductAttributes.COLOR, "Black");

        Product product1 = new ProductBuilder()
                .withName("Pro")
                .withCategory("Electronics")
                .withPrice(9.99)
                .withBrand("Brand")
                .withStock(10)
                .withDescription("Description 1")
                .withSpecifications(specifications)
                .build();

        Product product2 = new ProductBuilder()
                .withName("Product")
                .withCategory("Electronics")
                .withPrice(99.99)
                .withBrand("Brand")
                .withStock(10)
                .withDescription("Description 1")
                .withSpecifications(specifications)
                .build();

        catalog.addProduct(product1);
        catalog.addProduct(product2);

        List<Product> sortedAscending = catalog.sortByName(true);
        assertEquals(2, sortedAscending.size());
        assertTrue(sortedAscending.get(0).name().compareTo(sortedAscending.get(1).name()) < 0);
    }

    @Test
    void testSortByNameZ_A() {
        Map<ProductAttributes, String> specifications = new HashMap<>();
        specifications.put(ProductAttributes.COLOR, "Black");

        Product product1 = new ProductBuilder()
                .withName("Pro")
                .withCategory("Electronics")
                .withPrice(9.99)
                .withBrand("Brand")
                .withStock(10)
                .withDescription("Description 1")
                .withSpecifications(specifications)
                .build();

        Product product2 = new ProductBuilder()
                .withName("Product")
                .withCategory("Electronics")
                .withPrice(99.99)
                .withBrand("Brand")
                .withStock(10)
                .withDescription("Description 1")
                .withSpecifications(specifications)
                .build();

        catalog.addProduct(product1);
        catalog.addProduct(product2);

        List<Product> sortedDescending = catalog.sortByName(false);
        assertEquals(2, sortedDescending.size());
        assertTrue(sortedDescending.get(0).name().compareTo(sortedDescending.get(1).name()) > 0);
    }
}
