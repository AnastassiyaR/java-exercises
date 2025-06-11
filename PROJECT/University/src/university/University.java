package university;

import check.Checker;
import exceptions.UniException;
import maintainer.Maintainer;
import maintainer.RestTimeCalculator;
import org.apache.logging.log4j.util.PropertySource;
import room.Room;
import room.RoomType;
import user.User;
import user.student.Student;
import user.teacher.Teacher;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class University {

    private final String universityName;
    private final List<User> users;
    private final List<Room> rooms;
    private final List<Maintainer> maintainers;

    /**
     * Constructor of university
     * @param universityName
     */
    public University(String universityName) {
        this.universityName = universityName;
        this.users = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.maintainers = new ArrayList<>();
    }

    public String getUniversityName() {
        return universityName;
    }

    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    public List<Room> getRooms() {
        return new ArrayList<>(rooms);
    }

    public List<Maintainer> getMaintainers() {
        return new ArrayList<>(maintainers);
    }

    /**
     * Add room
     * @param room
     * @throws UniException
     */
    public void addRoom(Room room) throws UniException {
        if (!room.getUniversityBelongsTo().equals(this.universityName)) {
            throw new UniException(UniException.Reason.INVALID_UNIVERSITY);
        }
        rooms.add(room);
        System.out.println("Room " + room.getNumber() + " added to " + universityName);
    }

    /**
     * Add user
     * @param user
     * @throws UniException
     */
    public void addUser(User user) throws UniException {
        new Checker<User>().addingCheck(users, user);
        if (user instanceof Student student && !student.belongsToUniversity(universityName)) {
            throw new UniException(UniException.Reason.UNAUTHORIZED);
        }

        users.add(user);
        System.out.println("User " + user.getUsername() + " added to " + universityName);
    }

    /**
     * Add maintainer
     * @param maintainer
     * @throws UniException
     */
    public void addMaintainer(Maintainer maintainer) throws UniException {
        new Checker<Maintainer>().addingCheck(maintainers, maintainer);
        maintainers.add(maintainer);
        System.out.println("Maintainer " + maintainer.name() + " added to " + universityName);
    }

    /**
     * Find maintainer
     * @param item
     * @return
     * @throws UniException
     */
    public Maintainer findMaintainer(String item) throws UniException {
        if (item == null) {
            throw new UniException(UniException.Reason.CANNOT_BE_NULL);
        }

        Maintainer foundMaintainer = null;
        for (Maintainer m : getMaintainers()) {
            if (m.isAvailable() && m.canFix(item)) {
                foundMaintainer = m;
                break;
            }
        }
        for (Maintainer m : getMaintainers()) {
            if (m != foundMaintainer) {
                m.reduceVacationDay();
            }
        }
        return foundMaintainer;
    }

    /**
     * Repair room
     * @param room
     * @param item
     * @throws UniException
     */
    public void repairRoom(Room room, String item, RestTimeCalculator.Strategy strategy) throws UniException {
        if (!room.getEquipmentList().contains(item)) {
            throw new UniException(UniException.Reason.ITEM_IS_NOT_FOUND);
        }

        if (!room.isBroken()) {
            throw new UniException(UniException.Reason.ROOM_IS_NOT_BROKEN);
        }

        Maintainer maintainer = findMaintainer(item);
        if (maintainer == null) {
            throw new UniException(UniException.Reason.NO_MAINTAINER_AVAILABLE);
        }

        int restDays = RestTimeCalculator.calculate(
                maintainer,
                room,
                getMaintainers(),
                getRooms(),
                strategy
        );

        maintainer.assignVacation(restDays);
        maintainer.incrementRepairedRoomsCount();
        room.fixRoom();
        System.out.println("Room " + room.getNumber() + " has been repaired by " + maintainer.name()
                + ". Rest days: " + restDays);
    }


    public List<Maintainer> getTop3Maintainers() {
        return maintainers.stream()
                .sorted((m1, m2) -> {
                    int repairsCompare = Integer.compare(m2.getRepairedRoomsCount(), m1.getRepairedRoomsCount());
                    if (repairsCompare != 0) return repairsCompare;
                    return Integer.compare(m2.getItems().size(), m1.getItems().size());
                })
                .limit(3)
                .collect(Collectors.toList());
    }


    public Student getTopStudentRoom() {
        return users.stream()
                .filter(u -> u instanceof Student)
                .map(u -> (Student) u)
                .max(Comparator.comparingInt(s -> s.getBookings().size()))
                .orElse(null);
    }


    public Teacher getTopTeacherRoom() {
        return users.stream()
                .filter(u -> u instanceof Teacher)
                .map(u -> (Teacher) u)
                .max(Comparator.comparingInt(t -> t.getBookings().size()))
                .orElse(null);
    }

    /**
     * Search available rooms
     * @param type
     * @param date
     * @return
     * @throws UniException
     */
    public List<Room> searchAvailableRooms(RoomType type, String date) throws UniException {
        if (type == null || date == null) {
            throw new UniException(UniException.Reason.CANNOT_BE_NULL);
        }

        LocalDate finalRequestedDate;
        try {
            finalRequestedDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new UniException(UniException.Reason.INVALID_FORMAT);
        }

        return rooms.stream()
                .filter(room -> room.getType() == type
                        && !room.isBroken()
                        && room.getBookings().stream()
                        .noneMatch(booking -> booking.startTime().toLocalDate().equals(finalRequestedDate)))
                .toList();
    }

    /**
     * Sort users by usage
     * @return
     */
    public List<User> sortUsersByUsage() {
        List<User> sorted = new ArrayList<>(users);

        sorted.sort((u1, u2) -> {
            int totalMinutes1 = (int) u1.getBookings().stream()
                    .mapToLong(b -> Duration.between(b.startTime(), b.finishTime()).toMinutes())
                    .sum();
            int totalMinutes2 = (int) u2.getBookings().stream()
                    .mapToLong(b -> Duration.between(b.startTime(), b.finishTime()).toMinutes())
                    .sum();

            if (totalMinutes1 != totalMinutes2) {
                return Integer.compare(totalMinutes2, totalMinutes1);
            }

            return Integer.compare(u2.getBookings().size(), u1.getBookings().size());
        });

        return sorted;
    }
}
