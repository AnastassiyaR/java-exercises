import booking.Booking;
import exceptions.UniException;
import org.junit.jupiter.api.Test;
import room.Room;
import room.RoomType;
import university.University;
import user.User;
import user.UserBuilder;
import user.student.Student;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;


public class UserTests {
    private final int roomNum101 = 101;
    private final int numNum2 = 2;
    private final int numNum5 = 5;
    private final int numNum2025 = 2025;
    private final int numNum15 = 15;
    private final int numNum12 = 12;
    private final int numNum10 = 10;
    private final int numNum13 = 13;
    private final int numNum16 = 16;
    private final String taltechName = "Taltech";

    @Test
    public void testAddBookingsuccess() throws UniException {
        University university = new University(taltechName);
        User user = new Student("mari", "1234", "Taltech", 4.0);
        Room room = new Room(roomNum101, RoomType.STUDY_ROOM, numNum5, university);
        LocalDateTime start = LocalDateTime.of(numNum2025, 4, numNum15, numNum10, 0);
        LocalDateTime end = LocalDateTime.of(numNum2025, 4, numNum15, numNum12, 0);
        Booking booking = new Booking(user, room, start, end, 3);
        assertTrue(user.addBooking(booking));
        assertEquals(1, user.getBookings().size());
    }

    @Test
    public void testAddBookingduplicateError() throws UniException {
        University university = new University(taltechName);
        User user = new Student("mari", "1234", taltechName, 4.0);
        Room room = new Room(roomNum101, RoomType.STUDY_ROOM, numNum5, university);
        LocalDateTime start = LocalDateTime.of(numNum2025, 4, numNum15, numNum10, 0);
        LocalDateTime end = LocalDateTime.of(numNum2025, 4, numNum15, numNum12, 0);

        Booking booking = new Booking(user, room, start, end, 3);
        assertTrue(user.addBooking(booking));

        Booking duplicateBooking = new Booking(user, room, start, end, 3);
        assertThrows(UniException.class, () -> user.addBooking(duplicateBooking));
    }


    @Test
    public void testBookRoomsuccess() throws UniException {
        University university = new University(taltechName);
        User user = new Student("mari", "1234", taltechName, 4.0);
        Room room = new Room(roomNum101, RoomType.STUDY_ROOM, numNum5, university);
        LocalDateTime start = LocalDateTime.of(numNum2025, 4, numNum15, numNum10, 0);
        LocalDateTime end = LocalDateTime.of(numNum2025, 4, numNum15, numNum12, 0);

        assertTrue(user.bookRoom(room, start, end, 3));
        assertEquals(1, user.getBookings().size());
    }

    @Test
    public void testGetTotalBookedHoursForDay() throws UniException {
        University university = new University(taltechName);
        User user = new Student("mari", "1234", "Taltech", 4.0);
        Room room = new Room(roomNum101, RoomType.STUDY_ROOM, numNum5, university);
        LocalDateTime start = LocalDateTime.of(numNum2025, 4, numNum15, numNum10, 0);
        LocalDateTime end = LocalDateTime.of(numNum2025, 4, numNum15, numNum12, 0);

        Booking booking1 = new Booking(user, room, start, end, 3);
        LocalDateTime start2 = LocalDateTime.of(numNum2025, 4, numNum15, numNum13, 0);
        LocalDateTime end2 = LocalDateTime.of(numNum2025, 4, numNum15, numNum16, 0);
        Booking booking2 = new Booking(user, room, start2, end2, numNum2);
        user.addBooking(booking1);
        user.addBooking(booking2);
        assertEquals(numNum5, user.getTotalBookedHoursForDay(LocalDate.of(numNum2025, 4, numNum15)));
    }

    @Test
    public void testUserBuilderSuccess() throws UniException {
        User user = new UserBuilder()
                .withUsername("nick")
                .withPassword("pass")
                .build();

        assertAll("User",
            () -> assertEquals("nick", user.getUsername()),
            () -> assertEquals("pass", user.getPassword())
        );
    }
}
