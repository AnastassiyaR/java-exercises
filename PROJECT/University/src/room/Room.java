package room;

import booking.Booking;
import check.Checker;
import exceptions.UniException;
import university.University;
import user.User;
import user.student.Student;
import user.teacher.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Room {

    private final int number;
    private final RoomType type;
    private final int maxParticipants;
    private final University university;
    public final String universityBelongsTo;
    private List<String> equipmentList = List.of();
    private List<Booking> bookings = List.of();

    private boolean isBroken = false;
    private String brokenEquipment;

    /**
     * Construcor for room
     * @param number
     * @param type
     * @param maxParticipants
     * @param university
     */
    public Room(int number, RoomType type, int maxParticipants, University university) {
        this.number = number;
        this.type = type;
        this.maxParticipants = maxParticipants;
        this.university = university;
        this.universityBelongsTo = university.getUniversityName();
        this.equipmentList = new ArrayList<>();
        this.bookings = new ArrayList<>();

        try {
            university.addRoom(this);
        } catch (UniException e) {
            throw new RuntimeException("Failed to add room to university: " + e.getMessage(), e);
        }
    }

    public int getNumber() {
        return number;
    }

    public RoomType getType() {
        return type;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public String getUniversityBelongsTo() {
        return universityBelongsTo;
    }

    public List<String> getEquipmentList() {
        return new ArrayList<>(equipmentList);
    }

    public List<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }

    public boolean isBroken() {
        return isBroken;
    }

    public String getBrokenEquipment() {
        return brokenEquipment;
    }

    /**
     * Fix room
     * @throws UniException
     */
    public void fixRoom() throws UniException {
        if (!isBroken) {
            throw new UniException(UniException.Reason.NOT_BROKEN);
        }
        isBroken = false;
        brokenEquipment = null;
        System.out.println("Room " + number + " has been fixed.");
    }

    /**
     * Add equipment
     * @param equipment
     * @throws UniException
     */
    public void addEquipment(String equipment) throws UniException {
        new Checker<String>().addingCheck(equipmentList, equipment);
        equipmentList.add(equipment);
        System.out.println("The equipment " + equipment + " has been added to room " + number);
    }

    /**
     * Remove equipment
     * @param equipment
     * @throws UniException
     */
    public void removeEquipment(String equipment) throws UniException {
        new Checker<String>().removingCheck(equipmentList, equipment);
        equipmentList.remove(equipment);
        System.out.println("The equipment " + equipment + " has been removed from room " + number);
    }

    /**
     * Add booking
     * @param booking
     * @return
     * @throws UniException
     */
    public boolean addBooking(Booking booking) throws UniException {
        new Checker<Booking>().addingCheck(bookings, booking);
        booking.validateBooking();

        if (isBroken) {
            throw new UniException(UniException.Reason.ROOM_BROKEN);
        }
        for (Booking existing : bookings) {
            if (existing.startTime() == null || existing.finishTime() == null) {
                throw new  UniException(UniException.Reason.CANNOT_BE_NULL);
            }
            boolean overlaps = booking.startTime().isBefore(existing.finishTime())
                    && booking.finishTime().isAfter(existing.startTime());
            if (overlaps) {
                throw new UniException(UniException.Reason.OVERLAP);
            }
        }

        User user = booking.user();
        if (user == null) {
            throw new UniException(UniException.Reason.CANNOT_BE_NULL);
        }
        if (!(user instanceof Student) && !(user instanceof Teacher)) {
            throw new UniException(UniException.Reason.UNAUTHORIZED);
        }
        if (user instanceof Student student && !student.belongsToUniversity(universityBelongsTo)) {
            throw new UniException(UniException.Reason.UNAUTHORIZED);
        }
        if (user instanceof Teacher teacher && !teacher.getUniversities().contains(universityBelongsTo)) {
            throw new UniException(UniException.Reason.UNAUTHORIZED);
        }
        if (type == RoomType.CLASSROOM && !(user instanceof Teacher)) {
            throw new UniException(UniException.Reason.UNAUTHORIZED);
        }
        bookings.add(booking);
        tryBreakRoom(user, booking.participants());
        return true;
    }

    /**
     * Try break room
     * @param user
     * @param participantsCount
     * @throws UniException
     */
    public void tryBreakRoom(User user, int participantsCount) throws UniException {
        double breakChance = 0.0;
        Random random = new Random();

        // min + (max - min) * random.nextDouble(); [min, max)
        double value = 0.0 + (5.0 - 0.0) * random.nextDouble();

        if (user instanceof Student student) {
            double grade = student.getAverageGrade();
            breakChance = 1.0 - (grade / 5.0);
        }

        if (participantsCount > maxParticipants) {
            breakChance = 1.0;
        }

        if (!equipmentList.isEmpty() && value > breakChance) {
            int index = random.nextInt(equipmentList.size());
            brokenEquipment = equipmentList.get(index);
            isBroken = true;
            System.out.println("Room " + number + " broke! Broken equipment: " + brokenEquipment);
        }
    }

    @Override
    public String toString() {
        return "Room{"
                + "number=" + number
                + ", type=" + type
                + ", maxParticipants=" + maxParticipants
                + ", universityBelongsTo='" + universityBelongsTo + '\''
                + ", equipmentList=" + equipmentList
                + ", bookingsCount=" + bookings.size()
                + ", isBroken=" + isBroken
                + ", brokenEquipment=" + brokenEquipment
                + '}';
    }
}
