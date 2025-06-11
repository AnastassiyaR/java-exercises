package ee.taltech.iti0202.singleton.database;

import ee.taltech.iti0202.singleton.components.Component;
import ee.taltech.iti0202.singleton.exceptions.OutOfStockException;
import ee.taltech.iti0202.singleton.exceptions.ProductAlreadyExistsException;
import ee.taltech.iti0202.singleton.exceptions.ProductNotFoundException;

import java.util.HashMap;
import java.util.Map;

public final class Database {

    private Map<Integer, Component> components = new HashMap<>();
    private static Database database;

    private Database() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    /**
     * Save component.
     *
     * @param component the component
     * @throws ProductAlreadyExistsException the product already exists exception
     */
    public void saveComponent(Component component) throws ProductAlreadyExistsException {
        if (components.containsKey(component.getId())) {
            throw new ProductAlreadyExistsException();
        }
        components.put(component.getId(), component);
    }

    /**
     * Delete component.
     *
     * @param id the id
     * @throws ProductNotFoundException the product not found exception
     */
    public void deleteComponent(int id) throws ProductNotFoundException {
        if (!components.containsKey(id)) {
            throw new ProductNotFoundException();
        }
        components.remove(id);
    }

    /**
     * Increase component stock.
     *
     * @param id     the id
     * @param amount the amount
     * @throws ProductNotFoundException the product not found exception
     */
    public void increaseComponentStock(int id, int amount) throws ProductNotFoundException {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }
        if (!components.containsKey(id)) {
            throw new ProductNotFoundException();
        }

        components.get(id)
                .setAmount(components.get(id).getAmount() + amount);
    }

    /**
     * Decrease component stock.
     *
     * @param id     the id
     * @param amount the amount
     * @throws OutOfStockException      the out of stock exception
     * @throws ProductNotFoundException the product not found exception
     */
    public void decreaseComponentStock(int id, int amount) throws OutOfStockException, ProductNotFoundException {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }
        if (!components.containsKey(id)) {
            throw new ProductNotFoundException();
        }

        if (components.get(id).getAmount() < amount) {
            throw new OutOfStockException();
        }

        components.get(id)
                .setAmount(components.get(id).getAmount() - amount);
    }

    /**
     * Gets components.
     *
     * @return the components
     */
    public Map<Integer, Component> getComponents() {
        return components;
    }

    /**
     * Reset entire database.
     */
    public void resetEntireDatabase() {
        components.clear();
        Component.resetId();
    }
}
