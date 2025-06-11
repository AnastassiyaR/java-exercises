package ee.taltech.iti0202.mysticorbs.storage;

import java.util.HashMap;
import java.util.Map;

public class ResourceStorage {
    private Map<String, Integer> resources = new HashMap<>();

    /**
     * Get resources
     */
    public ResourceStorage() {
        this.resources = resources;
    }

    /**
     * @return true if storage empty
     */
    public boolean isEmpty() {
        for (int amount : resources.values()) {
            if (amount != 0 || amount < 0) {
                return false;
            }
        }
        return true; // All resources are zero
    }

    /**
     * Add resources
     * @param resource
     * @param amount
     */
    public void addResource(String resource, int amount) {
        if (resource == null || resource.trim().isEmpty() || amount <= 0) {
            return;
        }
        String key = resource.toLowerCase();
        resources.put(key, resources.getOrDefault(key, 0) + amount);
    }

    /**
     * Get resource amount
     * @param resource
     * @return
     */
    public int getResourceAmount(String resource) {
        return resources.getOrDefault(resource.toLowerCase(), 0);
    }

    /**
     * Check if there is enough resources
     * @param resource
     * @param amount
     * @return
     */
    public boolean hasEnoughResource(String resource, int amount) {
        return getResourceAmount(resource) >= amount && amount > 0;
    }

    /**
     * Take resources
     * @param resource
     * @param amount
     * @return
     */
    public boolean takeResource(String resource, int amount) {
        String key = resource.toLowerCase();
        if (!hasEnoughResource(key, amount)) {
            return false;
        }
        resources.put(key, resources.get(key) - amount);
        return true;
    }
}
