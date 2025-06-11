package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.orb.SpaceOrb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class SpaceOven extends Oven implements Fixable {
    private int timesFixed = 0;
    private int orbsSinceLastFix = 0;
    private final int maxFixed = 25;
    private final int starfragmentAmount = 15;
    private final int maxorbsSinceLastFix = 25;

    private final int liquidsilverAmount = 40;
    private final int staressenceAmount = 10;
    /**
     *
     * @param name
     * @param resourceStorage
     */
    public SpaceOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
    }

    @Override
    public boolean isBroken() {
        return orbsSinceLastFix >= maxFixed && timesFixed < 5;
    }

    @Override
    public Optional<Orb> craftOrb() {
        // If the oven is broken, check if it can produce a standard orb
        if (isBroken()) {
            if (getResourceStorage().hasEnoughResource("pearl", 1)
                    && getResourceStorage().hasEnoughResource("silver", 1)) {
                getResourceStorage().takeResource("pearl", 1);
                getResourceStorage().takeResource("silver", 1);
                createdOrbsAmount++;
                return Optional.of(new Orb(getName())); // Produce a standard orb
            }
            return Optional.empty(); // Cannot produce anything if broken and no resources
        }

        // Normal crafting logic for SpaceOrb
        if (getResourceStorage().hasEnoughResource("meteorite stone", 1)
                && getResourceStorage().hasEnoughResource("star fragment", starfragmentAmount)) {
            getResourceStorage().takeResource("meteorite stone", 1);
            getResourceStorage().takeResource("star fragment", starfragmentAmount);
            createdOrbsAmount++;
            orbsSinceLastFix++;
            if (orbsSinceLastFix >= maxorbsSinceLastFix) {
                broke = true; // Mark as broken after 25 orbs
            }
            return Optional.of(new SpaceOrb(getName())); // Produce a SpaceOrb
        }

        // If not enough resources for SpaceOrb, check for standard orb
        if (getResourceStorage().hasEnoughResource("pearl", 1)
                && getResourceStorage().hasEnoughResource("silver", 1)) {
            getResourceStorage().takeResource("pearl", 1);
            getResourceStorage().takeResource("silver", 1);
            createdOrbsAmount++;
            return Optional.of(new Orb(getName())); // Produce a standard orb
        }

        return Optional.empty(); // Not enough resources to craft any orb
    }

    @Override
    public void fix() throws CannotFixException {
        if (!isBroken()) {
            throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
        }
        if (getTimesFixed() >= 5) {
            throw new CannotFixException(this, CannotFixException.Reason.FIXED_MAXIMUM_TIMES);
        }

        // Check for resources needed for fixing
        if (getResourceStorage().hasEnoughResource("liquid silver", liquidsilverAmount)) {
            getResourceStorage().takeResource("liquid silver", liquidsilverAmount);
        } else if (getResourceStorage().hasEnoughResource("star essence", staressenceAmount)) {
            getResourceStorage().takeResource("star essence", staressenceAmount);
        } else {
            throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
        }
        timesFixed++;
        orbsSinceLastFix = 0;
        broke = false; // Mark as fixed
    }

    @Override
    public int getTimesFixed() {
        return timesFixed;
    }
}
