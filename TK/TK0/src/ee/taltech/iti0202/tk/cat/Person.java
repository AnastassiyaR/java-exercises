package ee.taltech.iti0202.tk.cat;


import java.util.ArrayList;
import java.util.List;

public class Person {

    public List<Cat> ownedCats = new ArrayList<>();

    /**
     *  Add Person cat
     */
    public boolean addCat(Cat cat) {
        if (!(ownedCats.contains(cat)) && cat.person == null) {
            ownedCats.add(cat);
            cat.person = this;
            return true;
        }
        return false;
    }

    /**
     *  Get Person cats
     */
    public List<Cat> getCats() {
        return ownedCats;
    }

    /**
     *  Sell Person cat
     */
    public boolean sellCat(Person sellTo, Cat cat) {
        if (sellTo == this) {
            return false;
        }
        if (!(this.ownedCats.contains(cat))) {
            return false;
        }
        if (cat.person != this) {
            return false;
        }
        this.ownedCats.remove(cat);
        sellTo.ownedCats.add(cat);
        cat.person = sellTo;
        return true;
    }
}
