package ee.taltech.iti0202.person;

public class Person {

    private String idCode;
    private String name;
    private Integer age;
    private Boolean isMale;

    /**
     * Construct a new product with given parameters.
     *
     * @param idCode id code for product
     * @param name name of product
     * @param age age of product
     * @param isMale boolean, true if product is male
     */
    public Person(String idCode, String name, Integer age, Boolean isMale) {
        if (age != null && age <= 0) {
            throw new IllegalArgumentException();
        }
        this.idCode = idCode;
        this.name = name;
        this.age = age;
        this.isMale = isMale;
    }

    /**
     * Return id code of product.
     * @return id code
     */
    public String getIdCode() {
        return idCode;
    }

    /**
     * Return name of product.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Return age of product.
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Return true if product is male.
     * @return boolean
     */
    public Boolean isMale() {
        return isMale;
    }
}
