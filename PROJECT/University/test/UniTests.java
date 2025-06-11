import booking.Booking;
import exceptions.UniException;
import maintainer.Maintainer;
import maintainer.RestTimeCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import room.Room;
import room.RoomType;
import university.UniversityBuilder;
import user.User;
import user.student.Student;
import user.teacher.Teacher;
import university.University;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UniTests {

    private University university;
    private Student student;
    private Teacher teacher;
    private Room studyRoom;
    private Room classRoom;

    private final int roomNum101 = 101;
    private final int roomNum102 = 102;
    private final int roomNum = 103;
    private final int maxParticipantsNum = 40;
    private final int year = 2024;
    private final int numNum2 = 2;
    private final int numNum5 = 5;
    private final int numNum50 = 50;
    private final int numNum2025 = 2025;
    private final int numNum15 = 15;
    private final int numNum12 = 12;
    private final double numNum3 = 3.0;
    private final int numNum10 = 10;
    private final int numNum13 = 13;
    private final int numNum14 = 14;
    private final int numNum7 = 7;
    private final int numNum9 = 9;
    private final int numNum21 = 21;
    private final int numNum23 = 23;
    private final int numNum30 = 30;
    private final int numNum40 = 40;
    private final double numNum28 = 2.8;
    private final double numNum35 = 3.5;
    private final int numNum8 = 8;
    private final String repairItem = "projector";
    private final String nonExistentItem = "non-existent-item";
    private final String testUniversity = "TestUniversity";

    @Test
    public void testAddEquipmentNullToRoom() throws UniException {
        University university = new University(testUniversity);
        Room room = new Room(roomNum, RoomType.STUDY_ROOM, maxParticipantsNum, university);
        assertThrows(UniException.class, () -> room.addEquipment(null));
    }

    @Test
    public void testAddEquipmentAlreadyContainToRoom() throws UniException {
        University university = new University(testUniversity);
        Room room = new Room(roomNum, RoomType.STUDY_ROOM, maxParticipantsNum, university);
        room.addEquipment("table");
        assertThrows(UniException.class, () -> room.addEquipment("table"));
    }

    @Test
    public void testAddEquipmentToRoom() throws UniException {
        University university = new University(testUniversity);
        Room room = new Room(roomNum, RoomType.STUDY_ROOM, maxParticipantsNum, university);
        room.addEquipment("table");
        assertTrue(room.getEquipmentList().contains("table"));
    }

    @Test
    public void testRemoveNullEquipment() throws UniException {
        University university = new University(testUniversity);
        Room room = new Room(roomNum, RoomType.STUDY_ROOM, maxParticipantsNum, university);
        room.addEquipment("table");
        assertThrows(UniException.class, () -> room.removeEquipment(null));
    }

    @Test
    public void testRemoveNonexistedEquipment() throws UniException {
        University university = new University(testUniversity);
        Room room = new Room(roomNum, RoomType.STUDY_ROOM, maxParticipantsNum, university);
        room.addEquipment("table");
        assertThrows(UniException.class, () -> room.removeEquipment("chair"));
    }

    @Test
    public void testRemoveEquipment() throws UniException {
        University university = new University(testUniversity);
        Room room = new Room(roomNum, RoomType.STUDY_ROOM, maxParticipantsNum, university);

        room.addEquipment("table");
        room.removeEquipment("table");
        assertFalse(room.getEquipmentList().contains("table"));
    }

    @Test
    public void testValidateBookingnullFields() {
        University university = new University(testUniversity);
        Room room = new Room(roomNum, RoomType.STUDY_ROOM, maxParticipantsNum, university);

        Booking nullUserBooking = new Booking(
                null,
                room,
                LocalDateTime.of(year, numNum5, 1, numNum10, 0),
                LocalDateTime.of(year, numNum5, 1, numNum12, 0),
                numNum10
        );
        assertThrows(UniException.class, nullUserBooking::validateBooking);
    }

    @Test
    public void testValidateBookinginvalidParticipants() {
        Booking overCapacity = new Booking(
                student,
                studyRoom,
                LocalDateTime.of(year, numNum5, 1, numNum10, 0),
                LocalDateTime.of(year, numNum5, 1, numNum12, 0), numNum10
        );
        assertThrows(UniException.class, overCapacity::validateBooking);
    }

    @Test
    public void testValidateBookingstartAfterEnd() {
        Booking invalidTime = new Booking(
                student,
                studyRoom,
                LocalDateTime.of(year, numNum5, 1, numNum15, 0),
                LocalDateTime.of(year, numNum5, 1, numNum14, 0),
                numNum10
        );
        assertThrows(UniException.class, invalidTime::validateBooking);
    }

    @Test
    public void testValidateBookingoutsideHours() {
        Booking earlyBooking = new Booking(
                student,
                studyRoom,
                LocalDateTime.of(year, numNum5, 1, numNum7, 0),
                LocalDateTime.of(year, numNum5, 1, numNum9, 0),
                numNum10
        );
        assertThrows(UniException.class, earlyBooking::validateBooking);

        Booking lateBooking = new Booking(
                student,
                studyRoom,
                LocalDateTime.of(year, numNum5, 1, numNum21, 0),
                LocalDateTime.of(year, numNum5, 1, numNum23, 0),
                numNum10
        );
        assertThrows(UniException.class, lateBooking::validateBooking);
    }

    @Test
    public void testValidateBookingexceedsStudyRoomMaxDuration() {
        Booking longBooking = new Booking(
                student,
                studyRoom,
                LocalDateTime.of(year, numNum5, 1, numNum10, 0),
                LocalDateTime.of(year, numNum5, 1, numNum15, numNum30),
                numNum10
        );
        assertThrows(UniException.class, longBooking::validateBooking);
    }

    @Test
    public void testValidateBookingstudentInClassroom() {
        Booking invalidStudentClass = new Booking(
                student,
                classRoom,
                LocalDateTime.of(year, numNum5, 1, numNum10, 0),
                LocalDateTime.of(year, numNum5, 1, numNum12, 0),
                numNum10
        );
        assertThrows(UniException.class, invalidStudentClass::validateBooking);
    }

    @Test
    public void testAddRoomsuccess() throws UniException {
        University university = new University(testUniversity);
        Room room = new Room(roomNum, RoomType.STUDY_ROOM, numNum40, university);
        University uni = new University("TestUniversity");
        uni.addRoom(room);
        assertTrue(uni.getRooms().contains(room));
    }

    @Test
    public void testAddUsernullUserthrowsException() {
        University uni = new University("TestUniversity");
        assertThrows(UniException.class, () -> uni.addUser(null));
    }

    @Test
    public void testAddUserinvalidUserthrowsException() {
        User student1 = new Student("student", "1234", "Taltech", numNum28);
        University university1 = new University("Test");
        assertThrows(UniException.class, () -> university1.addUser(student1));
    }

    @Test
    public void testAddUseralreadyContains() throws UniException {
        University uni = new University("TestUniversity");
        User student = new Student("student", "1234", "TestUniversity", numNum35);
        uni.addUser(student);
        assertThrows(UniException.class, () -> uni.addUser(student));
    }

    @Test
    public void testAddUsersucess() throws UniException {
        University uni = new University("TestUniversity");
        User student = new Student("student", "1234", "TestUniversity", numNum28);
        uni.addUser(student);
        assertTrue(uni.getUsers().contains(student));
    }

    @BeforeEach
    public void setupforTestSearchAvailableRooms() throws UniException {
        university = new University("TechUni");
        List universityList = new ArrayList();
        universityList.add(university);
        teacher = new Teacher("teacher", "TechUni", universityList);

        Room room1 = new Room(roomNum101, RoomType.STUDY_ROOM, numNum5, university);
        Room room2 = new Room(roomNum102, RoomType.CLASSROOM, numNum10, university);
        Room room3 = new Room(roomNum, RoomType.STUDY_ROOM, numNum8, university);

        university.addRoom(room1);
        university.addRoom(room2);
        university.addRoom(room3);
    }

    @Test
    public void testSearchAvailableRoomsinvalidDateFormat() {
        assertThrows(UniException.class,
                () -> university.searchAvailableRooms(RoomType.STUDY_ROOM, "15-04-2025"));
    }

    @Test
    public void testSortUsersByUsagetimeDifference() throws Exception {
        University uni = new University("TestUni");
        Room room = new Room(1, RoomType.STUDY_ROOM, numNum10, uni);

        uni.addRoom(room);
        List unis = new ArrayList();
        unis.add(uni);

        User teacher = new Teacher("teacher", "1234", unis);
        User student = new Student("student", "5678", "TestUni", numNum3);

        uni.addUser(teacher);
        uni.addUser(student);

        Booking b1 = new Booking(teacher, room,
                LocalDateTime.of(numNum2025, 4, numNum10, numNum10, 0),
                LocalDateTime.of(numNum2025, 4, numNum10, numNum12, 0),
                numNum2
        );
        teacher.getBookings().add(b1);

        Booking b2 = new Booking(student, room,
                LocalDateTime.of(numNum2025, 4, numNum10, numNum13, 0),
                LocalDateTime.of(numNum2025, 4, numNum10, numNum14, 0),
                numNum2
        );
        student.getBookings().add(b2);

        var sorted = uni.sortUsersByUsage();
        assertEquals(teacher, sorted.get(0));
        assertEquals(student, sorted.get(1));
    }

    @Test
    public void testRepairRoomItemNotFound() throws UniException {
        University uni = new University("TestUni");
        Room room = new Room(101, RoomType.STUDY_ROOM, 10, uni);
        room.addEquipment(repairItem);
        room.tryBreakRoom(
                new Student("test", "pass", "TestUni", 2.0),
                15
        );
        assertThrows(UniException.class,
                () -> uni.repairRoom(room, nonExistentItem, RestTimeCalculator.Strategy.FLOOR));
    }

    @Test
    public void testRepairRoomNotBroken() throws UniException {
        University uni = new University("TestUni");
        Room room = new Room(101, RoomType.STUDY_ROOM, 10, uni);
        room.addEquipment(repairItem);
        assertThrows(UniException.class, () -> uni.repairRoom(room, repairItem, RestTimeCalculator.Strategy.FLOOR));
    }

    @Test
    public void testRepairRoomNoMaintainer() throws UniException {
        University uni1 = new University("TestUni");
        Room room = new Room(101, RoomType.STUDY_ROOM, 10, uni1);
        room.addEquipment(repairItem);
        room.tryBreakRoom(
                new Student("test", "pass", "TestUni", 2.0),
                15
        );
        assertThrows(UniException.class, () -> uni1.repairRoom(room, repairItem, RestTimeCalculator.Strategy.FLOOR));
    }

    @Test
    public void testRepairRoomNullParameters() {
        University uni = new University("TestUni");
        Room room = new Room(101, RoomType.STUDY_ROOM, 10, uni);

        assertAll(
                () -> assertThrows(UniException.class,
                () -> uni.repairRoom(room, null, RestTimeCalculator.Strategy.FLOOR))
        );
    }

    @Test
    public void testUniversityBuilderSuccess() throws UniException {
        University university1 = new UniversityBuilder()
                .withName("TTU")
                .build();
        assertNotNull(university1);
        assertAll("University", () -> assertEquals("TTU", university1.getUniversityName()));
        System.out.println("University created: " + university);
    }

    @Test
    public void testGetTop3Maintainers() throws UniException {
        University uni = new University("TestUni");

        Maintainer m1 = new Maintainer(Set.of("projector", "chair"), 2, uni.getUniversityName());
        Maintainer m2 = new Maintainer(Set.of("projector"), 2, uni.getUniversityName());
        Maintainer m3 = new Maintainer(Set.of("chair", "table", "computer"), 2, uni.getUniversityName());

        uni.addMaintainer(m1);
        uni.addMaintainer(m2);
        uni.addMaintainer(m3);

        m1.incrementRepairedRoomsCount();
        m1.incrementRepairedRoomsCount();
        m3.incrementRepairedRoomsCount();
        m3.incrementRepairedRoomsCount();
        m3.incrementRepairedRoomsCount();
        m2.incrementRepairedRoomsCount();

        List<Maintainer> top3 = uni.getTop3Maintainers();
        assertEquals(3, top3.size());
        assertEquals(m3, top3.get(0));
        assertEquals(m1, top3.get(1));
        assertEquals(m2, top3.get(2));
    }

    @Test
    public void testGetTopStudentRoom() throws UniException {
        University uni = new University("TestUni");

        Student s1 = new Student("s1", "pass", uni.getUniversityName(), 3.0);
        Student s2 = new Student("s2", "pass", uni.getUniversityName(), 4.0);

        uni.addUser(s1);
        uni.addUser(s2);

        Room room = new Room(101, RoomType.STUDY_ROOM, 10, uni);
        uni.addRoom(room);

        s1.bookRoom(room, LocalDateTime.now(), LocalDateTime.now().plusHours(1), 5);
        s1.bookRoom(room, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(1), 5);
        s2.bookRoom(room, LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(1), 5);

        assertEquals(s1, uni.getTopStudentRoom());
    }

    @Test
    public void testGetTopTeacherRoom() throws UniException {
        University uni = new University("TestUni");

        Teacher t1 = new Teacher("t1", "pass", List.of(uni.getUniversityName()));
        Teacher t2 = new Teacher("t2", "pass", List.of(uni.getUniversityName()));

        uni.addUser(t1);
        uni.addUser(t2);

        Room room = new Room(101, RoomType.CLASSROOM, 30, uni);
        uni.addRoom(room);

        t1.bookRoom(room, LocalDateTime.now(), LocalDateTime.now().plusHours(2), 20);
        t2.bookRoom(room, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(1), 20);
        t2.bookRoom(room, LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(1), 20);

        assertEquals(t2, uni.getTopTeacherRoom());
    }


}
