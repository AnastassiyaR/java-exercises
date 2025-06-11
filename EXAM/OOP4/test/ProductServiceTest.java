import ee.taltech.iti0202.exam.Client;
import ee.taltech.iti0202.exam.Invoice;
import ee.taltech.iti0202.exam.Product;
import ee.taltech.iti0202.exam.ProductService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    // Kliendil on nimi ja eelarve. Peavad olema toString meetodis kajastatud - 5p
    @Test
    public void testClientToString() {
        Client client = new Client("John", 25);
        String expected = "Client{name='John', budget=25.0}";
        assertEquals(expected, client.toString());
    }

    // Kliendil on ostuajalugu - 5p
    @Test
    public void testClientPurchaseHistory() {
        Client client = new Client("John", 25);
        assertEquals(0, client.getClientProduct().size());
    }

    // Süsteemis on implementeeritud arve/ostukorvi klass.
    // Ostukorv koosneb omanikust ning toodetest, mis sinna kuuluvad - 10p

    @Test
    public void invoiceHasClientsAndProducts() {
        Client client = new Client("John", 25);
        Invoice invoice = new Invoice(client);
        assertEquals(0, invoice.getProducts().size());
        assertEquals(client, invoice.getClient());
    }

    // Arvel kajastuvad tooted koos kogustega. Arvelt on võimalik saada lõppsumma - 10p
    @Test
    public void testCartTotalCalculation() {
        Client client = new Client("John", 25);
        Invoice invoice = new Invoice(client);
        Product product = new Product("Apple", 15.0);
        Product product2 = new Product("Banana", 10.0);
        invoice.addProduct(product, 2, client);
        invoice.addProduct(product2, 3, client);
        assertEquals(60.0, invoice.calculateTotal(), 0.001);
    }

    // Klient saab osta meilt ainult siis, kui tal on ostukorvi tasumiseks piisavalt raha - 15p
    @Test
    public void testClientPurchaseFailsWhenInsufficientFunds() {
        Client client = new Client("John", 25);
        Invoice invoice = new Invoice(client);
        Product product = new Product("Toy", 100);
        invoice.addProduct(product, 2, client);
        boolean result = client.buyProduct(invoice);
        assertFalse(result);
    }

    @Test
    public void testClientPurchaseSuccess() {
        Client client = new Client("John", 50000);
        Invoice invoice = new Invoice(client);
        Product product = new Product("Toy", 100);
        invoice.addProduct(product, 2, client);
        boolean result = client.buyProduct(invoice);
        assertTrue(result);
    }

    // Kliendi eelarve väheneb, kui ostukorv kinnitatakse (ehk ost viiakse läbi) - 20p
    @Test
    public void testClientBudgetReducedAfterPurchaseSuccess() {
        Client client = new Client("John", 50);
        Invoice invoice = new Invoice(client);
        Product product = new Product("Toy", 20);
        invoice.addProduct(product, 2, client);
        client.buyProduct(invoice);
        assertEquals(10, client.getBudget(), 0.001);
    }

    // Tootel on nimi ja hind. Peavad olema toString meetodis kajastatud - 5p
    @Test
    public void testProductToString() {
        Product product = new Product("Toy", 100);
        String expected = "Product Toy. Cost 100.0";
        assertEquals(expected, product.toString());
    }

    // Tootel on equals meetod implementeeritud. Tooted on võrdsed kui nimi on sama. - 10p
    @Test
    public void testProductEquals() {
        Product p1 = new Product("Bear", 100.0);
        Product p2 = new Product("Toy", 100.0);
        Product p3 = new Product("Bear", 100.0);
        assertEquals(p1, p3);
        assertNotEquals(p1, p2);
    }

    // Süsteemil hoiab järge klientidest ja toodetest - 5p
    @Test
    public void testSystemHasClientsAndProducts() {
        ProductService productService = new ProductService();
        productService.addProduct(new Product("Toy", 100.0));
        productService.addProduct(new Product("Bear", 100.0));
        productService.addClient(new Client("Someone", 100.0));
        assertEquals(2, productService.getProducts().size());
        assertEquals(1, productService.getClients().size());

    }

    // Süsteem ei hoiusta duplikaat tooteid - 10p
    @Test
    public void testSystemHasNoDublicates() {
        ProductService productService1 = new ProductService();
        Product product = new Product("Toy", 100.0);
        productService1.addProduct(product);
        productService1.addProduct(product);
        assertEquals(1, productService1.getProducts().size());

        Client client = new Client("John", 50);
        productService1.addClient(client);
        productService1.addClient(client);
        assertEquals(1, productService1.getClients().size());
    }

    // Süsteemil on ostuajalugu - 5p
    @Test
    public void testSystemHasHistoryList() {
        ProductService productService1 = new ProductService();
        Product product = new Product("Toy", 100.0);
        Client client = new Client("John", 5000000);
        Invoice invoice = new Invoice(client);
        invoice.addProduct(product, 2, client);
        client.buyProduct(invoice);
        productService1.addHistory(invoice);
        assertEquals(1, productService1.getHistoryList().size());
    }

    // Süsteemil on meetod, millega leida toode nime järgi - 20p
    @Test
    public void testFindProductByName() {
        ProductService productService1 = new ProductService();
        Product product = new Product("Toy", 100.0);
        Product product2 = new Product("Bear", 100.0);
        productService1.addProduct(product);
        productService1.addProduct(product2);
        assertEquals(1, productService1.findProducts("Bear").size());
        assertTrue(productService1.findProducts("Bear").contains(product2));
    }

    // Süsteemil on meetod, et leida kõige populaarseim toode - 40p
    @Test
    public void testFindTheMostPopularProduct() {
        Client client = new Client("John", 5000);
        Invoice i1 = new Invoice(client);

        Client client1 = new Client("Mary", 5000);
        Invoice i2 = new Invoice(client1);

        Client client2 = new Client("Mary", 5000);
        Invoice i3 = new Invoice(client2);

        Product p1 = new Product("Toy", 10);
        Product p2 = new Product("Bear", 10);
        i1.addProduct(p1, 1, client);
        i2.addProduct(p2, 2, client1);
        i3.addProduct(p2, 3, client2);

        ProductService productService = new ProductService();
        productService.addHistory(i1);
        productService.addHistory(i2);
        productService.addHistory(i3);

        assertTrue(productService.findPopularProducts().contains(p2));
    }

    @Test
    public void testFindTheMostPopularProductIfEmpty() {
        Client client = new Client("John", 5000);
        Invoice i1 = new Invoice(client);
        ProductService productService = new ProductService();
        assertEquals(List.of(), productService.findPopularProducts());
    }

    // Ostukorvide võrdlemiseks on implementeeritud Comparable liides ning sellega kaasnev compareTo meetod - 40p
    @Test
    public void testProductsCompareTo() {
        Client client = new Client("John", 5000);
        Invoice i1 = new Invoice(client);

        Client client1 = new Client("Mary", 5000);
        Invoice i2 = new Invoice(client1);

        Client client2 = new Client("Mary", 5000);
        Invoice i3 = new Invoice(client2);

        Product p1 = new Product("Toy", 10);
        Product p2 = new Product("Bear", 10);
        i1.addProduct(p1, 1, client);
        i2.addProduct(p2, 2, client);
        i3.addProduct(p2, 2, client);

        assertFalse(i2.compareTo(i1) < 0);
        assertFalse(i1.compareTo(i2) > 0);
        assertEquals(0, i3.compareTo(i2));
    }
}

