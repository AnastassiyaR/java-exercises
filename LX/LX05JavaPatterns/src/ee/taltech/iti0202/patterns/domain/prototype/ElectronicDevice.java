package ee.taltech.iti0202.patterns.domain.prototype;


public class ElectronicDevice implements Product {
    private String name;
    private final double price;

    /**
     * Constructor of Electronic device
     * @param name
     * @param price
     */
    public ElectronicDevice(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Make a clone of product
     * @return clone
     */
    @Override
    public Product clone() {
        return new ElectronicDevice(this.name, this.price);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ElectronicDevice{name='" + name + "', price=" + price + "}";
    }
}
