import exceptions.UniException;
import org.junit.jupiter.api.Test;
import room.Room;
import room.RoomType;
import university.University;
import user.teacher.Teacher;
import user.teacher.TeacherBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

public class TeacherTests {
    private final int roomNum101 = 101;
    private final int numNum5 = 5;
    private final int numNum2025 = 2025;
    private final int numNum12 = 12;
    private final int numNum10 = 10;
    private final int numNum20 = 20;
    private final String uniName = "TechUni";
    private final String uniName2 = "Tartu";

    @Test
    public void testAddUniversitiessuccess() {
        Teacher teacher = new Teacher("anna", "pass123", null);
        teacher.addUniversities("TechUni");
        assertTrue(teacher.getUniversities().contains("TechUni"));
    }

    @Test
    public void testAddUniversitiesdoesNotAddDuplicate() {
        Teacher teacher = new Teacher("anna", "pass123", List.of("TechUni"));
        teacher.addUniversities("TechUni");
        assertEquals(1, teacher.getUniversities().size());
    }

    @Test
    public void testBookRoomsuccessteacher() throws UniException {
        University university = new University(uniName);
        Teacher teacher = new Teacher("anna", "pass123", List.of("TechUni"));
        Room room = new Room(roomNum101, RoomType.CLASSROOM, numNum10, university);

        LocalDateTime start = LocalDateTime.of(numNum2025, 4, numNum20, numNum10, 0);
        LocalDateTime end = LocalDateTime.of(numNum2025, 4, numNum20, numNum12, 0);

        boolean result = teacher.bookRoom(room, start, end, numNum5);
        assertTrue(result);
        assertEquals(1, teacher.getBookings().size());
    }

    @Test
    public void testBookRoomnouniInTheList() {
        University university = new University(uniName2);
        Teacher teacher = new Teacher("anna", "pass123", List.of("TechUni"));
        Room room = new Room(roomNum101, RoomType.CLASSROOM, numNum10, university);

        LocalDateTime start = LocalDateTime.of(numNum2025, 4, numNum20, numNum10, 0);
        LocalDateTime end = LocalDateTime.of(numNum2025, 4, numNum20, numNum12, 0);

        assertThrows(UniException.class, () -> teacher.bookRoom(room, start, end,  numNum5));
    }

    @Test
    public void testTeacherBuilderSuccess() throws UniException {
        List<String> universities = new ArrayList<>();
        universities.add("TechUni");

        Teacher teacher = new TeacherBuilder()
                .withUsername("yeac")
                .withPassword("12345")
                .withUniversities(universities)
                .build();

        assertAll("Teacher",
                () -> assertEquals("yeac", teacher.getUsername()),
                () -> assertEquals("12345", teacher.getPassword()),
                () -> assertEquals(universities, teacher.getUniversities())
        );
    }
}
