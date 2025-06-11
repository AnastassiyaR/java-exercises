package booking;

import exceptions.UniException;
import room.Room;
import room.RoomType;
import user.student.Student;
import user.User;

import java.time.LocalDateTime;

public record Booking(User user, Room room, LocalDateTime startTime, LocalDateTime finishTime, int participants) {

    /**
     * Constructor for booking
     * @param user
     * @param room
     * @param startTime
     * @param finishTime
     * @param participants
     */
    public Booking(User user, Room room, LocalDateTime startTime, LocalDateTime finishTime, int participants) {
        this.user = user;
        this.room = room;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.participants = participants;
    }

    /**
     * Validates bookings whether it is correct or not
     * @return true if booking is valid
     * @throws UniException if booking is invalid
     */
    public boolean validateBooking() throws UniException {

        if (user == null || room == null || startTime == null || finishTime == null || participants <= 0) {
            throw new UniException(UniException.Reason.CANNOT_BE_NULL);
        }

        if (user instanceof Student && !(room.getUniversityBelongsTo().equals(((Student) user).getUniversity()))) {
            throw new UniException(UniException.Reason.INVALID_BOOKING);
        }

        if (participants > room.getMaxParticipants()) {
            throw new UniException(UniException.Reason.INVALID_BOOKING);
        }

        if (finishTime.isBefore(startTime) || finishTime.equals(startTime)) {
            throw new UniException(UniException.Reason.INVALID_BOOKING);
        }

        final int MINHOURS = 8;
        final int MAXHOURS = 22;
        LocalDateTime minTime = startTime.toLocalDate().atTime(MINHOURS, 0);
        LocalDateTime maxTime = startTime.toLocalDate().atTime(MAXHOURS, 0);

        if (startTime.isBefore(minTime) || finishTime.isAfter(maxTime)) {
            throw new UniException(UniException.Reason.INVALID_BOOKING);
        }

        if (room.getType() == RoomType.STUDY_ROOM
                && java.time.Duration.between(startTime, finishTime).toHours() > 4) {
            throw new UniException(UniException.Reason.INVALID_BOOKING);
        }

        final int CHECKHOURS = 6;
        long totalBookedHours = user.getTotalBookedHoursForDay(startTime.toLocalDate());
        long newBookingDuration = java.time.Duration.between(startTime, finishTime).toHours();
        if (totalBookedHours + newBookingDuration > CHECKHOURS) {
            throw new UniException(UniException.Reason.INVALID_BOOKING);
        }

        if (room.getType() == RoomType.CLASSROOM && user instanceof Student) {
            throw new UniException(UniException.Reason.INVALID_BOOKING);
        }

        return true;
    }

    @Override
    public String toString() {
        return "Booking{"
                + "user=" + user.getUsername()
                + ", roomNumber=" + room.getNumber()
                + ", start=" + startTime
                + ", end=" + finishTime
                + ", participants=" + participants
                + '}';
    }
}
