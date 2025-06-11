package ee.taltech.iti0202.tk.happymeal;

public class HappyMeal {

    public static String drink;
    public static String side;
    public static String toy;
    public String burger;
    public static boolean eaten;

    public HappyMeal(String drink, String side, String toy) {
        this.burger = "Cheeseburger";
        this.drink = drink;
        this.side = side;
        this.toy = toy;
        this.eaten = false;
    }

    public static String eat() {
        if (!(eaten)) {
            eaten = true;
            return toy;
        }
        return null;
    }

    public String getMealDetails() {
        StringBuilder status = new StringBuilder();
        status.append(this.burger).append(" ").append(this.side).append(" ")
                .append(this.drink).append(" ").append(this.toy).append("\n");
        return status.toString();
    }

}
