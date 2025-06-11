package ee.taltech.iti0202.mysticorbs.orb;

public class SpaceOrb extends Orb {

    /**
     * @param creator
     */
    public SpaceOrb(String creator) {
        super(creator);
        this.energy = 100;
    }

    /**
     * @param resource
     * @param amount
     */
    @Override
    public void charge(String resource, int amount) {
        // SpaceOrb cant be charged
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "SpaceOrb by " + creator;
    }

    /**
     * Absorb orb
     * @param orb
     * @return
     */
    public boolean absorb(Orb orb) {
        // orb1.absorb(orb2)
        if (orb != null) {
            if (orb.getEnergy() < this.energy) {
                this.energy += orb.getEnergy();
                orb.energy = 0;
                return true;
            }
        }
        return false;
    }
}
