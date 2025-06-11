package room;

import exceptions.UniException;
import university.University;


public class RoomBuilder {
    private int number;
    private RoomType type;
    private int maxParticipants;
    private University universityBelongsTo;

    /**
     * Set room number
     * @param number The room number
     * @return The builder instance
     */
    public RoomBuilder withNumber(int number) {
        this.number = number;
        return this;
    }

    /**
     * Set the type of the room
     * @param type The room type
     * @return The builder instance
     */
    public RoomBuilder withType(RoomType type) {
        this.type = type;
        return this;
    }

    /**
     * Set the maximum number of participants
     * @param maxParticipants The maximum number of participants
     * @return The builder instance
     */
    public RoomBuilder withMaxParticipants(int maxParticipants) throws UniException {
        if (maxParticipants <= 0) {
            throw new UniException(UniException.Reason.NEGATIVE_NUMBER);
        }
        this.maxParticipants = maxParticipants;
        return this;
    }

    /**
     * Set the university the room belongs to
     * @param universityBelongsTo The university name
     * @return The builder instance
     */
    public RoomBuilder withUniversity(University universityBelongsTo) {
        this.universityBelongsTo = universityBelongsTo;
        return this;
    }

    /**
     * Builds the Room object
     * @return A new Room object
     * @throws UniException if required parameters are missing
     */
    public Room build() throws UniException {
        if (universityBelongsTo == null || type == null || maxParticipants <= 0) {
            throw new UniException(UniException.Reason.INVALID_FORMAT);
        }
        return new Room(number, type, maxParticipants, universityBelongsTo);
    }
}
