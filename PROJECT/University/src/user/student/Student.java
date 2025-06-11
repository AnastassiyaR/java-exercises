package user.student;

import booking.Booking;
import exceptions.UniException;
import room.Room;
import user.User;

import java.time.LocalDateTime;

public class Student extends User {

    private String university;
    private double averageGrade;

    /**
     * Constructor
     * @param username
     * @param password
     * @param university
     * @param averageGrade
     */
    public Student(String username, String password, String university, double averageGrade) {
        super(username, password);
        this.university = university;
        this.averageGrade = averageGrade;
    }

    public String getUniversity() {
        return university;
    }

    /**
     * Get average grade if it has range between 1 and 10
     * @return
     * @throws UniException
     */
    public double getAverageGrade() throws UniException {
        if (averageGrade < 0.0 || averageGrade > 5.0) {
            throw new UniException(UniException.Reason.INVALID_FORMAT);
        }
        return averageGrade;
    }

    /**
     * Tudeng saab broneerida ainult õpitoa.
     *
     * @param room ruum
     * @param startTime algusaeg
     * @param finishTime lõpuaeg
     * @return true, kui broneering õnnestus
     */
    public boolean bookStudyRoom(
            Room room,
            LocalDateTime startTime,
            LocalDateTime finishTime,
            int participants
    ) throws UniException {
        Booking book = new Booking(this, room, startTime, finishTime, participants);
        return this.addBooking(book);
    }

    /**
     * Check if the student belongs to a certain university
     * @param university
     * @return
     */
    public boolean belongsToUniversity(String university) {
        return this.university.equals(university);
    }
}
