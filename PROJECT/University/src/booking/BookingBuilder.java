package booking;

import check.Checker;
import exceptions.UniException;
import room.Room;
import user.User;
import java.time.LocalDateTime;

public class BookingBuilder {
    private User user;
    private Room room;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private int participants;

    /**
     * Booking Builder with user
     * @param user
     * @return
     */
    public BookingBuilder withUser(User user) throws UniException {
        new Checker<User>().nullCheck(user);
        this.user = user;
        return this;
    }
    /**
     * Booking Builder with room
     * @param room
     * @return
     */
    public BookingBuilder withRoom(Room room) throws UniException {
        new Checker<Room>().nullCheck(room);
        this.room = room;
        return this;
    }

    /**
     * Booking Builder with start time
     * @param startTime
     * @return
     */
    public BookingBuilder withStartTime(LocalDateTime startTime) throws UniException {
        new Checker<LocalDateTime>().nullCheck(startTime);
        this.startTime = startTime;
        return this;
    }

    /**
     * Booking Builder with finish time
     * @param finishTime
     * @return
     */
    public BookingBuilder withFinishTime(LocalDateTime finishTime) throws UniException {
        new Checker<LocalDateTime>().nullCheck(finishTime);
        this.finishTime = finishTime;
        return this;
    }

    /**
     * Booking Builder with participants
     * @param participants
     * @return
     */
    public BookingBuilder withParticipants(int participants) throws UniException {
        new Checker<Integer>().nullCheck(participants);
        this.participants = participants;
        return this;
    }

    /**
     * StringBuilder build
     * @return build
     * @throws UniException
     */
    public Booking build() throws UniException {
        Booking booking = new Booking(user, room, startTime, finishTime, participants);
        return booking;
    }
}
