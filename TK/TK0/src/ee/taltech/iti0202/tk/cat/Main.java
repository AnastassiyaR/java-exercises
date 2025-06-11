package ee.taltech.iti0202.tk.cat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.Collections.max;

public class Main {
    public static void main(String[] args) {
        Cat cat = new Cat("Mati");
        System.out.println(cat);

        Cat muri = new Cat("Muri", 3, "White");
        System.out.println(muri);

        Person malle = new Person();
        Person kalle = new Person();
        System.out.println(malle.addCat(cat)); // true
        System.out.println(malle.addCat(cat)); // false

        System.out.println(malle.sellCat(kalle, cat)); // true
        System.out.println(malle.getCats()); // []
        System.out.println(kalle.getCats()); // [Mati]
        String str = "xxbreadjambreadyy";
        System.out.println(str.lastIndexOf("bread"));
        System.out.println(str.substring(str.indexOf("bread") + 5, str.lastIndexOf("bread")));

        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(4);
        nums.add(100);
        int max = max(nums);
        nums.remove((Integer) max);
        System.out.println(nums);

        HashMap<String, String> result = new HashMap<>();

        result.put("bread", "butter");
        for (String key : result.keySet()) {
            System.out.println(key + ": " + result.get(key));
        }
    }
}
