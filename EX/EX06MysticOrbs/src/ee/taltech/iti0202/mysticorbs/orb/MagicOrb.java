package ee.taltech.iti0202.mysticorbs.orb;

public class MagicOrb extends Orb {

    /**
     *
     * @param creator
     */
    public MagicOrb(String creator) {
        super(creator);
    }

    /**
     * Charge magic orb
     * @param resource
     * @param amount
     */
    public void charge(String resource, int amount) {
        if (amount < 0) {
            return;
        }
        if (resource != null && !resource.trim().isEmpty() && !resource.equalsIgnoreCase("dust")) {
            energy += (resource.length() * 2 * amount);
        }
    }

    /**
     *
     * @return String
     */
    @Override
    public String toString() {
        return "MagicOrb by " + creator;
    }
}
