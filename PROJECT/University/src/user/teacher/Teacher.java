package user.teacher;

import exceptions.UniException;
import room.Room;
import user.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Teacher extends User {

    private List<String> universities = new ArrayList<>();

    /**
     * Constructor
     * @param username
     * @param password
     * @param universities
     */
    public Teacher(String username, String password, List<String> universities) {
        super(username, password);
        if (universities != null) {
            this.universities = universities;
        }
    }

    public List<String> getUniversities() {
        return new ArrayList<>(universities);
    }
    /**
     * Lisa ülikool, kus õpetaja töötab.
     *
     * @param university ülikooli nimi
     */
    public void addUniversities(String university) {
        if (university != null && !universities.contains(university)) {
            this.universities.add(university);
        }
    }

    /**
     * Õppejõud saab broneerida kõiki ruume, kui ruum kuulub tema ülikoolide hulka.
     *
     * @param room ruum
     * @param startTime algusaeg
     * @param finishTime lõpuaeg
     * @return true, kui broneering õnnestus
     * @throws UniException broneering ebaõnnestus
     */
    @Override
    public boolean bookRoom(
            Room room,
            LocalDateTime startTime,
            LocalDateTime finishTime,
            int participants
    ) throws UniException {

        if (!universities.contains(room.universityBelongsTo)) {
            throw new UniException(UniException.Reason.UNAUTHORIZED);
        }
        return super.bookRoom(room, startTime, finishTime, participants);
    }
}
