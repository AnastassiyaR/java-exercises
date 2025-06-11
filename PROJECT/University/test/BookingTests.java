import booking.Booking;
import booking.BookingBuilder;
import exceptions.UniException;
import org.junit.jupiter.api.Test;
import room.Room;
import room.RoomType;
import university.University;
import user.User;
import user.student.Student;
import user.teacher.Teacher;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;


public class BookingTests {

    private Room studyRoom;
    final int roomNum101 = 101;
    final int roomNum102 = 102;
    final int numNum2 = 2;
    final int numNum5 = 5;
    final int numNum2025 = 2025;
    final int numNum15 = 15;
    final int numNum12 = 12;
    final int numNum10 = 10;
    final int numNum13 = 13;
    final int numNum7 = 7;
    final int numNum9 = 9;
    final int numNum30 = 30;
    final int numNum20 = 20;
    final double numNum35 = 3.5;
    final int numNum8 = 8;
    final int numNum11 = 11;

    private static final int ROOM_NUM_101 = 101;
    private static final int NUM_PARTICIPANTS_50 = 50;
    private static final String UNIVERSITY_TALTECH = "TalTech";

    @Test
    public void testValidateBookingstudentFromDifferentUniversity() {
        University university = new University("TechUni");
        Student student = new Student("karl", "pass123", "Tallinna Ülikool", 4.0);
        Room room = new Room(roomNum101, RoomType.STUDY_ROOM, numNum5, university);

        LocalDateTime start = LocalDateTime.of(numNum2025, 4, numNum20, numNum10, 0);
        LocalDateTime end = LocalDateTime.of(numNum2025, 4, numNum20, numNum12, 0);

        Booking booking = new Booking(student, room, start, end, 3);

        UniException e = assertThrows(UniException.class, booking::validateBooking);
        assertEquals(UniException.Reason.INVALID_BOOKING, e.getReason());
    }
    @Test
    public void testValidateBookingsuccess() throws UniException {
        University university = new University("TechUni");
        Student student = new Student("mari", "1234", "TechUni", 4.0);
        Room room = new Room(roomNum101, RoomType.STUDY_ROOM, numNum5, university);
        LocalDateTime start = LocalDateTime.of(numNum2025, 4, numNum15, numNum10, 0);
        LocalDateTime end = LocalDateTime.of(numNum2025, 4, numNum15, numNum12, 0);
        Booking booking = new Booking(student, room, start, end, 3);
        assertTrue(booking.validateBooking());
    }

    @Test
    public void testValidateBookingnull() {
        Booking booking = new Booking(null, studyRoom, null, null, numNum2);
        assertThrows(UniException.class, booking::validateBooking);
    }

    @Test
    public void testValidateBookingtooManyParticipants() {
        University university = new University("TechUni");
        Student student = new Student("mari", "1234", "TechUni", 4.0);
        Room room = new Room(roomNum101, RoomType.STUDY_ROOM, numNum5, university);
        LocalDateTime start = LocalDateTime.of(numNum2025, 4, numNum15, numNum10, 0);
        LocalDateTime end = LocalDateTime.of(numNum2025, 4, numNum15, numNum12, 0);
        Booking booking = new Booking(student, room, start, end, numNum10);
        assertThrows(UniException.class, booking::validateBooking);
    }

    @Test
    public void testValidateBookinginvalidTime() {
        University university = new University("TechUni");
        Student student = new Student("mari", "1234", "TechUni", 4.0);
        Room room = new Room(roomNum101, RoomType.STUDY_ROOM, numNum5, university);
        LocalDateTime start = LocalDateTime.of(numNum2025, 4, numNum15, numNum13, 0);
        LocalDateTime end = LocalDateTime.of(numNum2025, 4, numNum15, numNum11, 0);
        Booking booking = new Booking(student, room, start, end, numNum2);
        assertThrows(UniException.class, booking::validateBooking);
    }

    @Test
    public void testValidateBookingoutsideAllowedHours() {
        University university = new University("TechUni");
        Student student = new Student("mari", "1234", "TechUni", 4.0);
        Room room = new Room(roomNum101, RoomType.STUDY_ROOM, numNum5, university);
        LocalDateTime start = LocalDateTime.of(numNum2025, 4, numNum15, numNum7, 0);
        LocalDateTime end = LocalDateTime.of(numNum2025, 4, numNum15, numNum9, 0);
        Booking booking = new Booking(student, room, start, end, numNum2);
        assertThrows(UniException.class, booking::validateBooking);
    }

    @Test
    public void testValidateBookingstudyRoomTooLong() {
        University university = new University("TechUni");
        Student student = new Student("mari", "1234", "TechUni", 4.0);
        Room room = new Room(roomNum101, RoomType.STUDY_ROOM, numNum5, university);
        LocalDateTime start = LocalDateTime.of(numNum2025, 4, numNum15, numNum10, 0);
        LocalDateTime end = LocalDateTime.of(numNum2025, 4, numNum15, numNum15, numNum30);
        Booking booking = new Booking(student, room, start, end, numNum2);
        assertThrows(UniException.class, booking::validateBooking);
    }

    @Test
    public void testValidateBookingstudentBookingClassroom() {
        University university = new University("TechUni");
        Student student = new Student("mari", "1234", "TechUni", 4.0);
        Room room = new Room(roomNum102, RoomType.CLASSROOM, numNum10, university);
        LocalDateTime start = LocalDateTime.of(numNum2025, 4, numNum15, numNum10, 0);
        LocalDateTime end = LocalDateTime.of(numNum2025, 4, numNum15, numNum12, 0);
        Booking booking = new Booking(student, room, start, end, 3);
        assertThrows(UniException.class, booking::validateBooking);
    }

    @Test
    public void testValidateBookingwrongUniversity() {
        University university = new University("TechUni");
        Room room = new Room(roomNum101, RoomType.STUDY_ROOM, numNum5, university);
        Student outsider = new Student("john", "abc", "TTÜ", numNum35);

        LocalDateTime start = LocalDateTime.of(numNum2025, 4, numNum15, numNum10, 0);
        LocalDateTime end = LocalDateTime.of(numNum2025, 4, numNum15, numNum12, 0);
        Booking booking = new Booking(outsider, room, start, end, 3);
        assertThrows(UniException.class, booking::validateBooking);
    }

    @Test
    public void testToStringreturnsCorrectFormat() {
        University university = new University("TechUni");
        Student student = new Student("mari", "1234", "TechUni", 4.0);
        Room room = new Room(roomNum101, RoomType.STUDY_ROOM, numNum5, university);
        LocalDateTime start = LocalDateTime.of(numNum2025, 4, numNum15, numNum10, 0);
        LocalDateTime end = LocalDateTime.of(numNum2025, 4, numNum15, numNum12, 0);
        Booking booking = new Booking(student, room, start, end, 3);

        String expected = "Booking{user=mari, "
                + "roomNumber=101, "
                + "start=2025-04-15T10:00, "
                + "end=2025-04-15T12:00, "
                + "participants=3}";
        assertEquals(expected, booking.toString());
    }

    @Test
    public void testBookingBuilderSuccess() throws UniException {
        University university = new University(UNIVERSITY_TALTECH);
        User user3 = new Teacher("User", "qwer", List.of(UNIVERSITY_TALTECH));
        Room room3 = new Room(ROOM_NUM_101, RoomType.CLASSROOM, NUM_PARTICIPANTS_50, university);
        LocalDateTime start = LocalDateTime.of(2024, 12, 12, 12, 12);
        LocalDateTime end = start.plusHours(1);
        int participants = 3;

        Booking booking = new BookingBuilder()
                .withUser(user3)
                .withRoom(room3)
                .withStartTime(start)
                .withFinishTime(end)
                .withParticipants(participants)
                .build();

        assertAll("Booking",
                () -> assertEquals(user3, booking.user()),
                () -> assertEquals(room3, booking.room()),
                () -> assertEquals(start, booking.startTime()),
                () -> assertEquals(end, booking.finishTime()),
                () -> assertEquals(participants, booking.participants())
        );

        System.out.println("Booking created: " + booking);
    }
}
