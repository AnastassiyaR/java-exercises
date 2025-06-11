package user.teacher;

import booking.Booking;
import exceptions.UniException;
import java.util.ArrayList;
import java.util.List;

public class TeacherBuilder {
    private String username;
    private String password;
    private List<String> universities = new ArrayList<>();
    private final int num = 6;
    private int dailyLimit = num;
    private List<Booking> bookings = new ArrayList<>();

    /**
     * Student Builder with username
     * @param username
     * @return
     */
    public TeacherBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     * Student Builder with password
     * @param password
     * @return
     */
    public TeacherBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * Student Builder with universities he teaches
     * @param universities
     * @return
     */
    public TeacherBuilder withUniversities(List<String> universities) {
        this.universities = new ArrayList<>(universities);
        return this;
    }

    /**
     * Teacher build
     * @return build
     * @throws UniException
     */
    public Teacher build() throws UniException {
        if (username == null || password == null || universities.isEmpty()) {
            throw new UniException(UniException.Reason.ALL_PARAMETERS_MUST_BE_SET);
        }
        Teacher teacher = new Teacher(username, password, universities);
        teacher.setDailyLimit(dailyLimit);
        teacher.setBookings(bookings);
        return teacher;
    }
}
