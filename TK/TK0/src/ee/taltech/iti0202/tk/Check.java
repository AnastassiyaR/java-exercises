package ee.taltech.iti0202.tk;

import java.util.HashMap;

import static ee.taltech.iti0202.tk.Exam.getSandwich;
import static ee.taltech.iti0202.tk.Exam.topping;

public class Check {

    public static void main(String[] args) {
        HashMap<String, String> a = new HashMap<>();
        a.put("ice cream", "world");
        a.put("three", "3");
        System.out.println(topping(a));

        System.out.println(getSandwich("xxbreadjambreadnnnbreadyy"));
    }
}
