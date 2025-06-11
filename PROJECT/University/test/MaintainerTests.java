import maintainer.Maintainer;
import maintainer.MaintainerBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import room.Room;
import room.RoomType;
import university.University;
import exceptions.UniException;
import user.student.Student;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MaintainerTests {
    private University university;
    private Maintainer maintainer;
    private Room room;
    private Set<String> skills;

    @BeforeEach
    public void setUp() {
        university = new University("TestUniversity");
        skills = new HashSet<>();
        skills.add("projector");
        skills.add("computer");
        maintainer = new Maintainer(skills, 3, university.getUniversityName());
    }

    @Test
    public void testMaintainerCreation() {
        assertNotNull(maintainer);
        assertEquals(3, maintainer.getDesiredVacationDays());
        assertEquals(0, maintainer.getRemainingRestDay());
        assertTrue(maintainer.isAvailable());
    }

    @Test
    public void testCanFixWithValidItem() {
        assertTrue(maintainer.canFix("projector"));
        assertTrue(maintainer.canFix("computer"));
    }

    @Test
    public void testCanFixWithInvalidItem() {
        assertFalse(maintainer.canFix("chair"));
        assertFalse(maintainer.canFix(null));
    }

    @Test
    public void testAssignVacation() {
        maintainer.assignVacation(maintainer.getDesiredVacationDays());
        assertEquals(3, maintainer.getRemainingRestDay());
        assertFalse(maintainer.isAvailable());
    }

    @Test
    public void testReduceVacationDay() {
        maintainer.assignVacation(maintainer.getDesiredVacationDays());
        maintainer.reduceVacationDay();
        assertEquals(2, maintainer.getRemainingRestDay());
    }

    @Test
    public void testRepair() {
        maintainer.assignVacation(maintainer.getDesiredVacationDays());
        assertEquals(3, maintainer.getRemainingRestDay());
        assertFalse(maintainer.isAvailable());
    }

    @Test
    public void testVacationDaysDecreaseForAllMaintainers() throws UniException {
        Maintainer m1 = new Maintainer(Set.of("projector"), 2, university.getUniversityName());
        Maintainer m2 = new Maintainer(Set.of("computer"), 3, university.getUniversityName());

        university.addMaintainer(m1);
        university.addMaintainer(m2);
        m1.assignVacation(m1.getDesiredVacationDays());
        m2.assignVacation(m2.getDesiredVacationDays());

        university.findMaintainer("projector");
        assertEquals(1, m1.getRemainingRestDay());
        assertEquals(2, m2.getRemainingRestDay());
    }

    @Test
    public void testBreakWhenOverCapacity() throws UniException {
        room = new Room(101, RoomType.STUDY_ROOM, 10, university);
        room.addEquipment("projector");
        Student student = new Student("test", "pass", "TestUniversity", 5.0);
        room.tryBreakRoom(student, 15);
        assertTrue(room.isBroken());
    }

    @Test
    void testMaintainerBuilderSuccess() throws UniException {
            Set<String> items = Set.of("Hammer", "Screwdriver");
            int vacationDays = 15;
            String university = "Tartu University";

            Maintainer maintainer = new MaintainerBuilder()
                    .withItems(items)
                    .withDesiredVacationDays(vacationDays)
                    .withUniversity(university)
                    .build();

            assertNotNull(maintainer);
            assertEquals(items, maintainer.getItems());
            assertEquals(vacationDays, maintainer.getDesiredVacationDays());
            assertEquals(university, maintainer.getUniversity());
        }
}
