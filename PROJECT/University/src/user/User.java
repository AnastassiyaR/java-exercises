package user;

import booking.Booking;
import exceptions.UniException;
import room.Room;
import university.University;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private List<Booking> bookings = new ArrayList<>();

    private final int number = 6;
    private int dailyLimit = number;
    private University university;

    protected User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }
    /**
     * Add booking of an user
     * @param booking
     * @return
     * @throws UniException
     */
    public boolean addBooking(Booking booking) throws UniException {
        if (booking.room().addBooking(booking) && !(bookings.contains(booking))) {
            bookings.add(booking);
            return true;
        }
        return false;
    }

    /**
     * Book room
     * @param room
     * @param startTime
     * @param finishTime
     * @param participants
     * @return
     * @throws UniException
     */
    public boolean bookRoom(
            Room room,
            LocalDateTime startTime,
            LocalDateTime finishTime,
            int participants
    ) throws UniException {
        Booking book = new Booking(this, room, startTime, finishTime, participants);
        if (room.addBooking(book) && !(bookings.contains(book))) {
            bookings.add(book);
            System.out.println("The booking for room " + room + " has been added");
            return true;
        }
        System.out.println("The booking for room " + room + " hasn't been added");
        return false;
    }

    public void setDailyLimit(int dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    /**
     * Get total booked hours for day
     * @param date
     * @return
     */
    public long getTotalBookedHoursForDay(LocalDate date) {
        long totalHours = 0;
        for (Booking booking : bookings) {
            if (booking.startTime().toLocalDate().equals(date)) {
                totalHours += java.time
                        .Duration
                        .between(booking.startTime(), booking.finishTime()).toHours();
            }
        }
        return totalHours;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
