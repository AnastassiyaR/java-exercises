package ee.taltech.iti0202.hashcode;

import java.util.HashMap;
import java.util.Map;

class HashCodeMain {
    private static final int AGE_20 = 20;
    private static final int AGE_25 = 25;
    private static final int AGE_35 = 35;

    private static final int VALUE_123 = 123;
    private static final int VALUE_345 = 345;
    private static final int VALUE_3 = 3;
    private static final int VALUE_6 = 6;
    private static final int VALUE_7 = 7;
    private static final int VALUE_8 = 8;

    public static void main(String[] args) {

        Map<Person, Integer> numbers = new HashMap<>();
        Person person1 = new Person("Ago", "von", "Luberg", AGE_20);
        Person person2 = new Person("Ago", "von", "Luberg", AGE_20);
        Person person3 = new Person("Ago", "", "Luberg", AGE_20);
        Person person4 = new Person("Ago", "blah", "Luberg", AGE_20);
        Person person5 = new Person("Ago", "von", "Luberg", AGE_25);
        Person person6 = new Person("Ago", "von", "Luberg", AGE_35);

        numbers.put(person1, VALUE_123);
        numbers.put(person2, VALUE_345);
        numbers.put(person3, VALUE_3);
        numbers.put(person4, VALUE_6);
        numbers.put(person5, VALUE_7);
        numbers.put(person6, VALUE_8);

        System.out.println(person1.equals(person3));  // true
        System.out.println(person1.equals(person4));  // false
        System.out.println(person1.equals(person5));  // true
        System.out.println(person1.equals(person6));  // false
        System.out.println(numbers);

        // Output should be:
        // {Person{firstName='Ago', lastName='Luberg', middleName='von', age=35}=8,
        //  Person{firstName='Ago', lastName='Luberg', middleName='von', age=20}=7,
        //  Person{firstName='Ago', lastName='Luberg', middleName='blah', age=20}=6}

        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());
    }
}
