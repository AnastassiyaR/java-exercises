package ee.taltech.iti0202.singleton.store;

import ee.taltech.iti0202.singleton.components.Component;
import ee.taltech.iti0202.singleton.database.Database;
import ee.taltech.iti0202.singleton.exceptions.OutOfStockException;
import ee.taltech.iti0202.singleton.exceptions.ProductNotFoundException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Store.
 */
public class Store {


    private String name;
    private BigDecimal profitMargin;

    /**
     * Instantiates a new Store.
     *
     * @param name         the name
     * @param profitMargin the profit margin
     */
    public Store(String name, BigDecimal profitMargin) {
        if (profitMargin.compareTo(BigDecimal.ONE) < 0) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.profitMargin = profitMargin;
    }

    /**
     * Purchase component component.
     *
     * @param id     the id
     * @param amount the amount
     * @return the component
     * @throws OutOfStockException      the out of stock exception
     * @throws ProductNotFoundException the product not found exception
     */
    public Component purchaseComponent(int id, int amount) throws OutOfStockException,
            ProductNotFoundException {
        Database.getInstance().decreaseComponentStock(id, amount);
        return Database.getInstance().getComponents().get(id);
    }

    /**
     * Gets available components.
     *
     * @return the available components
     */
    public List<Component> getAvailableComponents() {
        return Database.getInstance().getComponents().values().stream()
                .filter(c -> c.getAmount() > 0)
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Gets components sorted by amount.
     *
     * @return the components sorted by amount
     */
    public List<Component> getComponentsSortedByAmount() {
        return Database.getInstance().getComponents().values().stream()
                .sorted(Comparator.comparingInt(Component::getAmount))
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Gets components sorted by name.
     *
     * @return the components sorted by name
     */
    public List<Component> getComponentsSortedByName() {
        return Database.getInstance().getComponents().values().stream()
                .sorted(Comparator.comparing(Component::getName))
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Gets components sorted by price.
     *
     * @return the components sorted by price
     */
    public List<Component> getComponentsSortedByPrice() {
        return Database.getInstance().getComponents().values().stream()
                .sorted(Comparator.comparing(Component::getPrice))
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Filter by type list.
     *
     * @param type the type
     * @return the list
     */
    public List<Component> filterByType(Component.Type type) {
        return Database.getInstance().getComponents().values().stream()
                .filter(c -> c.getType() == type)
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Gets inventory value.
     *
     * @return the inventory value
     */
    public BigDecimal getInventoryValue() {
        BigDecimal result = BigDecimal.ZERO;

        for (Component component : Database.getInstance().getComponents().values()) {
            result = result.add(component.getPrice()
                    .multiply(profitMargin)
                    .multiply(BigDecimal.valueOf(component.getAmount())))
                    .setScale(2, RoundingMode.HALF_UP);
        }
        return result;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets profit margin.
     *
     * @return the profit margin
     */
    public BigDecimal getProfitMargin() {
        return profitMargin;
    }

    /**
     * Sets profit margin.
     *
     * @param profitMargin the profit margin
     */
    public void setProfitMargin(BigDecimal profitMargin) {
        if (profitMargin.compareTo(BigDecimal.ONE) < 0) {
            throw new IllegalArgumentException();
        }
        this.profitMargin = profitMargin;
    }
}
