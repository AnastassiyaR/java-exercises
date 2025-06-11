package ee.taltech.iti0202.builder;

import ee.taltech.iti0202.person.Person;


public class PersonBuilder {

    public String idCode;
    public String name;
    public Boolean isMale;
    public Integer age;

    /**
     * product builder
     * @param idCode
     */
    public PersonBuilder(String idCode) {
        this.idCode = idCode;
    }

    /**
     * with name
     * @param name
     * @return name
     */
    public PersonBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * with age
     * @param age
     * @return age
     */
    public PersonBuilder withAge(Integer age) {
        if (age <= 0) {
            throw new IllegalArgumentException();
        }
        this.age = age;
        return this;
    }

    /**
     * is male
     * @param isMale
     * @return isMale
     */
    public PersonBuilder isMale(boolean isMale) {
        this.isMale = isMale;
        return this;
    }

    /**
     * build
     * @return Person
     */
    public Person build() {
        return new Person(idCode, name, age, isMale);
    }
}



