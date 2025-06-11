import exceptions.UniException;
import org.junit.jupiter.api.Test;
import room.Room;
import room.RoomType;
import university.University;
import user.student.Student;
import user.student.StudentBuilder;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;


public class StudentTests {
    private final int roomNum101 = 101;
    private final int number5 = 5;
    private final int number2025 = 2025;
    private final int number12 = 12;
    private final int number10 = 10;
    private final double number45 = 4.5;
    private final int number16 = 16;
    private final String universityName = "TechUni";

    @Test
    public void testStudentBookStudyRoomsuccess() throws UniException {
        University university = new University(universityName);
        Student student = new Student("karl", "pass", "TechUni", number45);
        Room room = new Room(roomNum101, RoomType.STUDY_ROOM, number10, university);

        LocalDateTime start = LocalDateTime.of(number2025, 4, number16, number10, 0);
        LocalDateTime end = LocalDateTime.of(number2025, 4, number16, number12, 0);

        boolean result = student.bookStudyRoom(room, start, end,  number5);
        assertTrue(result);
        assertEquals(1, student.getBookings().size());
    }

    @Test
    public void testStudentBuilderSuccess() throws UniException {
        Student student = new StudentBuilder()
                .withUsername("karl")
                .withPassword("123")
                .withUniversity("Tal")
                .withAverageGrade(4)
                .build();

        assertAll("Student",
            () -> assertEquals("karl", student.getUsername()),
                () -> assertEquals("123", student.getPassword()),
                () -> assertEquals(4, student.getAverageGrade())
        );
    }
}
