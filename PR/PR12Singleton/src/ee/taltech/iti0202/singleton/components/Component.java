package ee.taltech.iti0202.singleton.components;

import java.math.BigDecimal;

public class Component {
    private static int nextId = 0;
    private int id;
    private String name;
    private Type type;
    private BigDecimal price;
    private int amount = 1;
    private String manufacturer;
    private int performancePoints;
    private int powerConsumption;

    public enum Type {
        CPU, GPU, RAM, MOTHERBOARD, HDD, SSD, PSU, KEYBOARD, TOUCHPAD, SCREEN, BATTERY, FAN
    }

    /**
     * Instantiates a new Component.
     *
     * @param name              the name
     * @param type              the type
     * @param price             the price
     * @param manufacturer      the manufacturer
     * @param performancePoints the performance points
     * @param powerConsumption  the power consumption
     */
    public Component(String name, Type type, BigDecimal price, String manufacturer, int performancePoints,
                     int powerConsumption) {
        this.id = nextId++;
        this.name = name;
        this.type = type;
        this.price = price;
        this.manufacturer = manufacturer;
        this.performancePoints = performancePoints;
        this.powerConsumption = powerConsumption;
    }

    /**
     *  reset Id
     */
    public static void resetId() {
        nextId = 0;
    }

    /**
     * Get name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Set id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get type
     * @return type
     */
    public Type getType() {
        return type;
    }

    /**
     * Set type
     * @param type
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Get price
     * @return price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Set price
     * @param price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Get amount
     * @return amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Set amount
     * @param amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Get manufacturer
     * @return manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Set manufacturer
     * @param manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Get performancePoints
     * @return performancePoints
     */
    public int getPerformancePoints() {
        return performancePoints;
    }

    /**
     * Set performancePoints
     * @param performancePoints
     */
    public void setPerformancePoints(int performancePoints) {
        this.performancePoints = performancePoints;
    }

    /**
     * Get PowerConsumption
     * @return powerConsumption
     */
    public int getPowerConsumption() {
        return powerConsumption;
    }

    /**
     * Set PowerConsumption
     * @param powerConsumption
     */
    public void setPowerConsumption(int powerConsumption) {
        this.powerConsumption = powerConsumption;
    }
}
