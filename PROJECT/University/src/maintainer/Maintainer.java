package maintainer;

import java.util.HashSet;
import java.util.Set;

public class Maintainer {
    private final Set<String> items;
    private final int desiredVacationDays;
    private final String university;
    private int remainingRestDay = 0;
    private int repairedRoomsCount = 0;

    /**
     * Constructor for Maintainer
     * @param items
     * @param desiredVacationDays
     * @param university
     */
    public Maintainer(Set<String> items, int desiredVacationDays, String university) {
        this.items = new HashSet<>(items);
        this.desiredVacationDays = desiredVacationDays;
        this.university = university;
        this.repairedRoomsCount = repairedRoomsCount;
    }

    /**
     * Increment repaired rooms count
     */
    public void incrementRepairedRoomsCount() {
        repairedRoomsCount++;
    }

    /**
     * Get desired vacation days
     * @return desired vacation days
     */
    public int getDesiredVacationDays() {
        return desiredVacationDays;
    }

    /**
     * Get name
     * @return name
     */
    public String name() {
        return "Maintainer@" + hashCode();
    }

    /**
     * Control if maintainer can fix an item
     * @param item
     * @return boolean
     */
    public boolean canFix(String item) {
        return items.contains(item);
    }

    /**
     * Reduce vacation day
     */
    public void reduceVacationDay() {
        if (remainingRestDay > 0) {
            remainingRestDay--;
        }
    }

    /**
     * Assign vacation
     */
    public void assignVacation(int restDays) {
        this.remainingRestDay = restDays;
    }

    /**
     * Check if a maintainer is available
     * @return boolean
     */
    public boolean isAvailable() {
        return remainingRestDay == 0;
    }

    /**
     * Get items
     * @return items
     */
    public Set<String> getItems() {
        return new HashSet<>(items);
    }

    /**
     * Get university maintaner works
     * @return university
     */
    public String getUniversity() {
        return university;
    }

    /**
     * Get remaining rest day
     * @return remaining rest day
     */
    public int getRemainingRestDay() {
        return remainingRestDay;
    }

    /**
     * Get repaired rooms count
     * @return repairedRoomsCount
     */
    public int getRepairedRoomsCount() {
        return repairedRoomsCount;
    }

    /**
     * Set repaired rooms count
     * @return repairedRoomsCount
     */
    public void setRepairedRoomsCount(int repairedRoomsCount) {
        this.repairedRoomsCount = repairedRoomsCount;
    }
}
