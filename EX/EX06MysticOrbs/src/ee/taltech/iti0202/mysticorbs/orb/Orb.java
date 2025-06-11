package ee.taltech.iti0202.mysticorbs.orb;

public class Orb {
    protected int energy;
    protected String creator;

    /**
     *
     * @param creator and energy
     */
    public Orb(String creator) {
        this.creator = creator;
        this.energy = 0;
    }

    public String getCreator() {
        return creator;
    }

    public int getEnergy() {
        return energy;
    }

    /**
     * Charge orb
     * @param resource
     * @param amount
     */
    public void charge(String resource, int amount) {
        if (amount < 0) {
            return;
        }
        if (resource != null && !resource.trim().isEmpty() && !resource.equalsIgnoreCase("dust")) {
            energy += (resource.length() * amount);
        }
    }

    /**
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Orb by " + creator;
    }
}
