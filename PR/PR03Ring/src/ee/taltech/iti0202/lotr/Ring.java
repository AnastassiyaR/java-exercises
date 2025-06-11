package ee.taltech.iti0202.lotr;

public class Ring {

    // no class then write enum
    public enum Type {
        THE_ONE, GOLDEN, NENYA, OTHER
    }

    public enum Material {
        //        GOLD("Kuld"),
        GOLD,
        SILVER,
        BRONZE,
        PLASTIC,
        DIAMOND;

//        String translation;
        // Et kasutada need asjad
//        Material(String translation) {
//            this.translation = translation;
//        }
    }

    private Type type;
    private Material material;

    public Ring(Type type, Material material) {
        this.type = type;
        this.material = material;
    }

    public Type getType() {
        return type;
    }

    public Material getMaterial() {
        return material;
    }

//    public static void main(String[] args) {
//        Material material = Material.GOLD;
//        System.out.println(material.translation);
//    }
}
