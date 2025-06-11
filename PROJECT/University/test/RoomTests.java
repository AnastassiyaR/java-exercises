import exceptions.UniException;
import org.junit.jupiter.api.Test;
import room.Room;
import room.RoomBuilder;
import room.RoomType;
import university.University;
import user.student.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RoomTests {

    private static final int NUMFIVE = 5;
    private static final int ROOM_NUM_101 = 101;
    private static final int NUM_PARTICIPANTS_50 = 50;
    private static final String UNIVERSITY_TALTECH = "TalTech";
    private static final int ROOM_NUM_102 = 102;

    private University createTestUniversity() {
        return new University(UNIVERSITY_TALTECH);
    }

    @Test
    public void testAddBookingNullBookingThrowsException() {
        University uni = createTestUniversity();
        Room room = new Room(ROOM_NUM_101, RoomType.STUDY_ROOM, NUMFIVE, uni);

        UniException exception = assertThrows(UniException.class, () -> {
            room.addBooking(null);
        });
        assertEquals(UniException.Reason.CANNOT_BE_NULL, exception.getReason());
    }

    @Test
    void fixRoom_Success() throws UniException {
        University uni = createTestUniversity();
        Room room = new Room(101, RoomType.STUDY_ROOM, 10, uni);
        String equipmentItem = "projector";
        room.addEquipment(equipmentItem);

        room.tryBreakRoom(new Student("test", "pass", uni.getUniversityName(), 2.0), 15);
        assertTrue(room.isBroken());
        assertNotNull(room.getBrokenEquipment());
        room.fixRoom();
        assertFalse(room.isBroken());
        assertNull(room.getBrokenEquipment());
    }

    @Test
    void fixRoom_WhenNotBroken_ThrowsException() throws UniException {
        University uni = createTestUniversity();
        Room room = new Room(101, RoomType.STUDY_ROOM, 10, uni);
        String equipmentItem = "projector";
        room.addEquipment(equipmentItem);

        assertFalse(room.isBroken());
        UniException exception = assertThrows(UniException.class, () -> room.fixRoom());
        assertEquals(UniException.Reason.NOT_BROKEN, exception.getReason());
    }

    @Test
    void fixRoom_AlreadyFixed_ThrowsException() throws UniException {
        University uni = createTestUniversity();
        Room room = new Room(101, RoomType.STUDY_ROOM, 10, uni);
        String equipmentItem = "projector";
        room.addEquipment(equipmentItem);

        room.tryBreakRoom(new Student("test", "pass", uni.getUniversityName(), 0.2), 15);
        room.fixRoom();

        assertThrows(UniException.class, () -> room.fixRoom());
    }

    @Test
    public void testRoomBuilderSuccess() throws UniException {
        University uni = createTestUniversity();
        Room room = new RoomBuilder()
                .withNumber(ROOM_NUM_102)
                .withType(RoomType.STUDY_ROOM)
                .withMaxParticipants(NUM_PARTICIPANTS_50)
                .withUniversity(uni)
                .build();

        assertNotNull(room);
        assertAll("Room",
                () -> assertEquals(ROOM_NUM_102, room.getNumber()),
                () -> assertEquals(RoomType.STUDY_ROOM, room.getType()),
                () -> assertEquals(NUM_PARTICIPANTS_50, room.getMaxParticipants()),
                () -> assertEquals(uni.getUniversityName(), room.getUniversityBelongsTo())
        );
        assertTrue(uni.getRooms().contains(room));
        System.out.println("Room created: " + room);
    }
}
