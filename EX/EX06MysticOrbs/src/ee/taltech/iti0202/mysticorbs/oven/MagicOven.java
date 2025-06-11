package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.MagicOrb;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class MagicOven extends Oven implements Fixable {
    private int timesFixed = 0;
    private int orbsSinceLastFix = 0;
    private final int neededClay = 25;

    /**
     * @param name
     * @param resourceStorage
     */
    public MagicOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
    }

    @Override
    public boolean isBroken() {
        return orbsSinceLastFix >= 5; // MagicOven breaks after 5 orbs
    }

    @Override
    public Optional<Orb> craftOrb() {
        if (isBroken()) {
            return Optional.empty(); // Broken ovens cannot produce orbs
        }
        if (resourceStorage.hasEnoughResource("gold", 1)
                && resourceStorage.hasEnoughResource("dust", 3)) {
            resourceStorage.takeResource("gold", 1);
            resourceStorage.takeResource("dust", 3);
            createdOrbsAmount++;
            orbsSinceLastFix++;

            Orb orb;
            // Every second orb is a MagicOrb
            if (createdOrbsAmount % 2 == 0) {
                orb = new MagicOrb(getName());
            } else {
                orb = new Orb(getName());
            }
            return Optional.of(orb);
        }
        return Optional.empty();
    }

    @Override
    public void fix() throws CannotFixException {
        if (!isBroken()) {
            throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
        }
        if (timesFixed >= 10) {
            throw new CannotFixException(this, CannotFixException.Reason.FIXED_MAXIMUM_TIMES);
        }

        int clayNeeded = neededClay * (timesFixed + 1);
        int freezingPowderNeeded = 100 * (timesFixed + 1);

        if (!resourceStorage.hasEnoughResource("clay", clayNeeded)
                || !resourceStorage.hasEnoughResource("freezing powder", freezingPowderNeeded)) {
            throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
        }

        resourceStorage.takeResource("clay", clayNeeded);
        resourceStorage.takeResource("freezing powder", freezingPowderNeeded);
        timesFixed++;
        orbsSinceLastFix = 0;
        broke = false; // Mark as fixed
    }

    @Override
    public int getTimesFixed() {
        return timesFixed;
    }
}
