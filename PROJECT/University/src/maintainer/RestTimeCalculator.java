package maintainer;

import room.Room;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class RestTimeCalculator {
    public enum Strategy {
        POPULARITY, FLOOR, REPLACEMENT
    }

    /**
     * Calculates the rest time for a maintainer based on the specified strategy.
     * <p>
     * Supports three strategies:
     * <ul>
     *   <li>POPULARITY — calculates rest time based on room popularity.</li>
     *   <li>FLOOR — calculates rest time based on the floor number of the room.</li>
     *   <li>REPLACEMENT — calculates rest time based on available replacement maintainers with matching skills.</li>
     * </ul>
     * </p>
     *
     * @param maintainer the maintainer who repaired the room
     * @param room the room that was repaired
     * @param allMaintainers list of all maintainers available
     * @param allRooms list of all rooms to consider for popularity
     * @param strategy the strategy to use for calculating rest time
     * @return the calculated rest time (in days)
     */
    public static int calculate(Maintainer maintainer, Room room,
                                List<Maintainer> allMaintainers,
                                List<Room> allRooms,
                                Strategy strategy) {
        switch (strategy) {
            case POPULARITY:
                return calculateByPopularity(room, allRooms);
            case FLOOR:
                return calculateByFloor(room);
            case REPLACEMENT:
                return calculateByReplacement(maintainer, allMaintainers);
            default:
                return 1;
        }
    }

    /**
     * Calculates rest time multiplier based on the popularity of the room.
     * <p>
     * Rooms are sorted by the number of bookings in ascending order (least to most popular).
     * The position of the target room in this sorted list determines the multiplier:
     * 1 + (position_in_list / total_number_of_rooms).
     * The result is rounded up to the nearest integer.
     * </p>
     *
     * @param targetRoom the room for which the popularity factor is calculated
     * @param allRooms the list of all rooms to compare popularity
     * @return rest time multiplier based on room popularity
     */
    public static int calculateByPopularity(Room targetRoom, List<Room> allRooms) {
        List<Room> sortedRooms = new ArrayList<>(allRooms);
        sortedRooms.sort(Comparator.comparingInt(r -> r.getBookings().size()));

        int index = sortedRooms.indexOf(targetRoom);
        if (index == -1) return 1;

        double factor = 1 + ((double) index / allRooms.size());
        return (int) Math.ceil(factor);
    }

    /**
     * Calculates the floor number of a room based on its room number.
     * <p>
     * The method extracts the leading digit of the room number,
     * assuming it represents the floor number.
     * For example, for room number 305, the floor is 3.
     * </p>
     *
     * @param room the Room object whose floor number needs to be determined
     * @return the floor number where the room is located
     */
    public static int calculateByFloor(Room room) {
        int roomNumber = room.getNumber();
        while (roomNumber >= 10) {
            roomNumber /= 10;
        }
        return roomNumber;
    }


    /**
     * Calculates the number of full replacements available for a maintainer based on skills.
     * <p>
     * A full replacement means a group of available maintainers whose combined skills
     * cover all the skills of the given maintainer.
     * The method finds how many times such full replacements can be formed without
     * reusing the same maintainers.
     * </p>
     *
     * @param maintainer the maintainer for whom replacements are calculated
     * @param allMaintainers the list of all maintainers in the system
     * @return the number of full replacement groups available for the maintainer
     */
    public static int calculateByReplacement(Maintainer maintainer, List<Maintainer> allMaintainers) {
        if (!maintainer.isAvailable()) {
            return 0;
        }

        Set<String> requiredSkills = new HashSet<>(maintainer.getItems());
        List<Maintainer> available = new ArrayList<>();

        for (Maintainer m : allMaintainers) {
            if (!m.equals(maintainer) && m.isAvailable()) {
                available.add(m);
            }
        }

        int fullReplacements = 0;

        while (true) {
            Set<String> covered = new HashSet<>();
            List<Maintainer> used = new ArrayList<>();

            for (Maintainer m : available) {
                for (String item : m.getItems()) {
                    if (requiredSkills.contains(item) && !covered.contains(item)) {
                        covered.add(item);
                        used.add(m);
                    }
                    if (covered.containsAll(requiredSkills)) break;
                }
                if (covered.containsAll(requiredSkills)) break;
            }

            if (covered.containsAll(requiredSkills)) {
                fullReplacements++;
                available.removeAll(used);
            } else {
                break;
            }
        }
        return fullReplacements;
    }
}
