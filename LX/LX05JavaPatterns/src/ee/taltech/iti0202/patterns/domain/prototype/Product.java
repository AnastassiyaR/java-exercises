package ee.taltech.iti0202.patterns.domain.prototype;


public interface Product {

    /**
     * Make a clone
     * @return
     */
    Product clone();

    /**
     * Set a new name
     * @param name
     */
    void setName(String name);

    /**
     * Get name of product
     * @return name
     */
    String getName();

    /**
     * Get price
     * @return
     */
    double getPrice();
}
