package ee.taltech.iti0202.tk.cat;


public class Cat {

    String name;
    int age;
    String color;
    Person person;

    /**
     *  Cat constructor
     */
    public Cat(String name) {
        this.name = name;
    }

    /**
     *  Cat constructor 2
     */
    public Cat(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    /**
     *  Get Cat name
     */
    public String getName() {
        return name;
    }

    /**
     *  Get Cat age
     */
    public int getAge() {
        return age;
    }

    /**
     *  Get Cat color
     */
    public String getColor() {
        return color;
    }

    /**
     *  Get Cat status
     */
    public String toString() {
        if (this.color == null) {
            StringBuilder result = new StringBuilder();
            result.append(this.name);
            return result.toString();
        }
        StringBuilder result = new StringBuilder();
        result.append(this.color).append(" ").append(this.name).append(" (").append(this.age).append(")");

        return result.toString();
    }
}
