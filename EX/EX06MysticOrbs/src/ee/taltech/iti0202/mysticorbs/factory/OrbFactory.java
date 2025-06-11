package ee.taltech.iti0202.mysticorbs.factory;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.oven.Fixable;
import ee.taltech.iti0202.mysticorbs.oven.Oven;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

public class OrbFactory {
    private ResourceStorage resourceStorage;
    private List<Oven> ovens = new ArrayList<>();
    private List<Orb> producedOrbs = new ArrayList<>();

    /**
     *
     * @param resourceStorage
     */
    public OrbFactory(ResourceStorage resourceStorage) {
        this.resourceStorage = resourceStorage;
    }

    /**
     * Add oven
     * @param oven
     */
    public void addOven(Oven oven) {
        if (!ovens.contains(oven) && oven != null) {
            ovens.add(oven);
        }
    }

    public List<Oven> getOvens() {
        return ovens;
    }

    /**
     * Get and clear producted orbs list
     * @return result
     */
    public List<Orb> getAndClearProducedOrbsList() {
        List<Orb> result = new ArrayList<>(producedOrbs);
        producedOrbs.clear();
        return result;
    }

    /**
     * Produce orbs
     * @return
     */
    public int produceOrbs() {
        int count = 0;
        for (Oven oven : ovens) {
            if (oven.isBroken() && oven instanceof Fixable) {
                try {
                    Fixable fixableOven = (Fixable) oven;
                    fixableOven.fix();
                } catch (CannotFixException e) {
                    continue;
                }
            }

            Optional<Orb> orb = oven.craftOrb();
            if (orb.isPresent()) {
                producedOrbs.add(orb.get());
                count++;
            }
        }
        return count;
    }

    /**
     * Produce orbs
     * @param cycles
     * @return
     */
    public int produceOrbs(int cycles) {
        int total = 0;
        for (int i = 0; i < cycles; i++) {
            total += produceOrbs();
        }
        return total;
    }

    /**
     * Get ovens that cant be fixed
     * @return ovensThatCannotBeFixed
     */
    public List<Oven> getOvensThatCannotBeFixed() {
        List<Oven> ovensThatCannotBeFixed = new ArrayList<>();
        for (Oven oven : ovens) {
            if (oven.isBroken() && (!(oven instanceof Fixable) || ((Fixable) oven).getTimesFixed() >= 10)) {
                ovensThatCannotBeFixed.add(oven);
            }
        }
        return ovensThatCannotBeFixed;

    }

    /**
     * Get rid of ovens that cant be fixed
     */
    public void getRidOfOvensThatCannotBeFixed() {
        ovens.removeAll(getOvensThatCannotBeFixed());
    }

    /**
     * Optimize ovens order
     */
    public void optimizeOvensOrder() {
        ovens.sort((o1, o2) -> o2.compareTo(o1));
    }
}
