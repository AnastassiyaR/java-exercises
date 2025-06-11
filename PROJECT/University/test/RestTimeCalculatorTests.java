import booking.Booking;
import maintainer.Maintainer;
import org.junit.jupiter.api.Test;
import room.Room;
import room.RoomType;
import maintainer.RestTimeCalculator;
import university.University;
import user.student.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class RestTimeCalculatorTests {



    @Test
    public void testPopularityStrategy_MultipleRooms() {
        University university = new University("TechUni");
        Student student1 = new Student("testUser", "pass", "TechUni", 3.0);
        Student student2 = new Student("testUser", "pass", "TechUni", 3.0);
        Student student3 = new Student("testUser", "pass", "TechUni", 3.0);

        Room room1 = new Room(101, RoomType.STUDY_ROOM, 10, university);
        Room room2 = new Room(102, RoomType.STUDY_ROOM, 10, university);
        Room room3 = new Room(103, RoomType.STUDY_ROOM, 10, university);

        room1.getBookings().add(
                new Booking(
                        student1,
                        room1,
                        LocalDateTime.of(2025, 4, 15, 10, 0),
                        LocalDateTime.of(2025, 4, 15, 12, 0),
                        9
                )
        );
        room2.getBookings().add(
                new Booking(
                        student1,
                        room1,
                        LocalDateTime.of(2025, 5, 15, 10, 0),
                        LocalDateTime.of(2025, 5, 15, 12, 0),
                        9
                )
        );
        room2.getBookings().add(
                new Booking(
                        student2,
                        room2,
                        LocalDateTime.of(2025, 6, 15, 10, 0),
                        LocalDateTime.of(2025, 6, 15, 12, 0),
                        9
                )
        );
        room2.getBookings().add(
                new Booking(
                        student2,
                        room2,
                        LocalDateTime.of(2025, 7, 15, 10, 0),
                        LocalDateTime.of(2025, 7, 15, 12, 0),
                        9
                )
        );
        room3.getBookings().add(
                new Booking(
                        student3,
                        room3,
                        LocalDateTime.of(2025, 8, 15, 10, 0),
                        LocalDateTime.of(2025, 8, 15, 12, 0),
                        9
                )
        );
        room3.getBookings().add(
                new Booking(
                        student3,
                        room3,
                        LocalDateTime.of(2025, 9, 15, 10, 0),
                        LocalDateTime.of(2025, 9, 15, 12, 0),
                9
                )
        );

        List<Room> allRooms = List.of(room1, room2, room3);

        assertEquals(1, RestTimeCalculator.calculateByPopularity(room1, allRooms));
        assertEquals(2, RestTimeCalculator.calculateByPopularity(room3, allRooms));
        assertEquals(2, RestTimeCalculator.calculateByPopularity(room2, allRooms));
    }

    @Test
    public void testFloorStrategy_VariousFloors() {
        University university = new University("TestUni");
        Room room1 = new Room(101, RoomType.STUDY_ROOM, 10, university);
        Room room2 = new Room(205, RoomType.STUDY_ROOM, 10, university);
        Room room3 = new Room(310, RoomType.STUDY_ROOM, 10, university);
        Room room4 = new Room(402, RoomType.STUDY_ROOM, 10, university);

        assertEquals(1, RestTimeCalculator.calculateByFloor(room1));
        assertEquals(2, RestTimeCalculator.calculateByFloor(room2));
        assertEquals(3, RestTimeCalculator.calculateByFloor(room3));
        assertEquals(4, RestTimeCalculator.calculateByFloor(room4));
    }

    @Test
    public void testReplacementStrategy_BasicScenario() {
        Maintainer toomas = new Maintainer(Set.of("tables"), 0, "TestUni");
        Maintainer mait = new Maintainer(Set.of("tables", "boards"), 0, "TestUni");
        Maintainer ulvi = new Maintainer(Set.of("tables", "boards", "screens"), 0, "TestUni");

        List<Maintainer> allMaintainers = List.of(toomas, mait, ulvi);

        assertEquals(2, RestTimeCalculator.calculateByReplacement(toomas, allMaintainers));
        assertEquals(1, RestTimeCalculator.calculateByReplacement(mait, allMaintainers));
        assertEquals(0, RestTimeCalculator.calculateByReplacement(ulvi, allMaintainers));
    }

    @Test
    public void testReplacementStrategy_WithVacations() {
        Maintainer toomas = new Maintainer(Set.of("tables"), 2, "TestUni");
        Maintainer mait = new Maintainer(Set.of("tables", "boards"), 0, "TestUni");
        Maintainer ulvi = new Maintainer(Set.of("tables", "boards", "screens"), 0, "TestUni");

        toomas.assignVacation(2);

        List<Maintainer> allMaintainers = List.of(toomas, mait, ulvi);

        assertEquals(0, RestTimeCalculator.calculateByReplacement(ulvi, allMaintainers));
        assertEquals(1, RestTimeCalculator.calculateByReplacement(mait, allMaintainers));
        assertEquals(0, RestTimeCalculator.calculateByReplacement(toomas, allMaintainers));
    }

    @Test
    public void testReplacementStrategy_ExtendedSkills() {
        Maintainer toomas = new Maintainer(Set.of("tables", "screens"), 0, "TestUni");
        Maintainer mait = new Maintainer(Set.of("tables", "boards"), 0, "TestUni");
        Maintainer ulvi = new Maintainer(Set.of("tables", "boards", "screens"), 0, "TestUni");

        List<Maintainer> allMaintainers = List.of(toomas, mait, ulvi);

        assertEquals(1, RestTimeCalculator.calculateByReplacement(ulvi, allMaintainers));
    }

    @Test
    public void testCalculate_AllStrategies() {
        University uni = new University("TestUni");
        Student student = new Student("testUser", "pass", "TechUni", 3.0);
        Maintainer maintainer = new Maintainer(Set.of("projector"), 0, "TestUni");
        Room room = new Room(305, RoomType.STUDY_ROOM, 10, uni);
        room.getBookings().add(
                new Booking(
                        student,
                        room,
                        LocalDateTime.of(2025, 9, 15, 10, 0),
                        LocalDateTime.of(2025, 9, 15, 12, 0),
                        9
                )
        );

        List<Room> allRooms = List.of(room);
        List<Maintainer> allMaintainers = List.of(maintainer);

        assertEquals(1, RestTimeCalculator.calculate(
                maintainer, room, allMaintainers, allRooms,
                RestTimeCalculator.Strategy.POPULARITY
        ));

        assertEquals(3, RestTimeCalculator.calculate(
                maintainer, room, allMaintainers, allRooms,
                RestTimeCalculator.Strategy.FLOOR
        ));

        assertEquals(0, RestTimeCalculator.calculate(
                maintainer, room, allMaintainers, allRooms,
                RestTimeCalculator.Strategy.REPLACEMENT
        ));
    }
}
