package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class Oven implements Comparable<Oven> {
    protected String name;
    protected ResourceStorage resourceStorage;
    protected int createdOrbsAmount = 0;
    protected boolean broke = false;
    private final int maxcreatedorbs = 15;

    /**
     *
     * @param name
     * @param resourceStorage
     */
    public Oven(String name, ResourceStorage resourceStorage) {
        this.name = name;
        this.resourceStorage = resourceStorage;
    }

    public String getName() {
        return name;
    }

    public ResourceStorage getResourceStorage() {
        return resourceStorage;
    }

    public int getCreatedOrbsAmount() {
        return createdOrbsAmount;
    }

    public boolean isBroken() {
        return broke;
    }

    /**
     * Craft orb
     * @return Optional
     */
    public Optional<Orb> craftOrb() {
        if (isBroken()) {
            return Optional.empty();
        }
        if (resourceStorage.hasEnoughResource("pearl", 1)
                && resourceStorage.hasEnoughResource("silver", 1)) {
            resourceStorage.takeResource("pearl", 1);
            resourceStorage.takeResource("silver", 1);
            createdOrbsAmount++;
            if (createdOrbsAmount >= maxcreatedorbs) {
                broke = true;
            }
            Orb orb = new Orb(name);
            orb.charge("pearl", 1);
            orb.charge("silver", 1);
            return Optional.of(orb);
        }
        return Optional.empty();
    }

    @Override
    public int compareTo(Oven other) {
        // Broken ovens have lower priority
        if (this.isBroken() && !other.isBroken()) {
            return -1;
        } else if (!this.isBroken() && other.isBroken()) {
            return 1;
        }

        // Get priority for both ovens
        int thisPriority = getOvenPriority(this);
        int otherPriority = getOvenPriority(other);

        // Compare based on priority
        if (thisPriority != otherPriority) {
            return Integer.compare(thisPriority, otherPriority);
        } else {
            // If priorities are the same, compare based on created orbs
            if (this.getCreatedOrbsAmount() != other.getCreatedOrbsAmount()) {
                // Reverse the comparison to ensure that fewer orbs means higher priority
                return Integer.compare(other.getCreatedOrbsAmount(), this.getCreatedOrbsAmount());
            } else {
                // If created orbs are the same, compare lexicographically by name
                return this.getName().compareTo(other.getName());
            }
        }
    }

    private int getOvenPriority(Oven oven) {
        return switch (oven) {
            case SpaceOven spaceOven -> 4;
            case InfinityMagicOven infinityMagicOven -> 3;
            case MagicOven magicOven -> 2;
            case null, default -> 1;
        };
    }
}
