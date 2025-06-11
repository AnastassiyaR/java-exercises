package ee.taltech.iti0202.tk.happymeal;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private boolean orderMeal = false;
    private List<String> toys = new ArrayList<>();
    private HappyMeal currentMeal;

    public boolean order(String side, String drink, String toy) {
        if (side != null && drink != null && toy != null) {
            currentMeal = new HappyMeal(drink, side, toy);
            orderMeal = true;
            return true;
        }
        return false;
    }
    public List<String> getToys() {
        return toys;
    }

    public boolean eatHappyMeal() {
        if (orderMeal && !(currentMeal == null)) {
            String toy = currentMeal.eat();
            if (toy != null) {
                toys.add(toy);
                return true;
            }
        }
        return false;
    }
}