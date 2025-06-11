package ee.taltech.iti0202.lotr;

public class Person {

    private String name;
    private String race;
    private Ring ring;

    public Person(String race, String name, Ring ring) {
        this.race = race;
        this.name = name;
        this.ring = ring;
    }

    public Person(String race, String name) {
        this(race, name, null);
    }

    public String getName() {
        return name;
    }
    public String getRace() {
        return race;
    }
    public Ring getRing() {
        return ring;
    }

    public void setRing(Ring ring) {
        this.ring = ring;
    }

    public String isSauron() {
        if (name.equals("Sauron")) {
            if (ring == null || ring.getType() != Ring.Type.THE_ONE) {
                return "No, but he's claiming to be";
            }

            return switch (ring.getMaterial()) {
                case GOLD -> "Affirmative";
                case SILVER, BRONZE, PLASTIC, DIAMOND -> "No, the ring is fake!";
            };
        } else if (ring != null && ring.getType() == Ring.Type.THE_ONE && ring.getMaterial() == Ring.Material.GOLD) {
            return "No, he just stole the ring";
        }
        return "No";
    }
}
