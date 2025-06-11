package user.student;

import booking.Booking;
import check.Checker;
import exceptions.UniException;
import java.util.ArrayList;
import java.util.List;

public class StudentBuilder {
    private String username;
    private String password;
    private String university;
    private double averageGrade;

    private final int number = 6;
    private int dailyLimit = number;
    private List<Booking> bookings = new ArrayList<>();

    /**
     * Student Builder with username
     * @param username
     * @return
     */
    public StudentBuilder withUsername(String username) throws UniException {
        new Checker<String>().nullCheck(username);
        this.username = username;
        return this;
    }

    /**
     * Student Builder with password
     * @param password
     * @return
     */
    public StudentBuilder withPassword(String password) throws UniException {
        new Checker<String>().nullCheck(password);
        this.password = password;
        return this;
    }

    /**
     * Student Builder with university
     * @param university
     * @return
     */
    public StudentBuilder withUniversity(String university) throws UniException {
        new Checker<String>().nullCheck(university);
        this.university = university;
        return this;
    }

    /**
     * Student Builder with average grade
     * @param averageGrade
     * @return
     */
    public StudentBuilder withAverageGrade(double averageGrade) throws UniException {
        new Checker<Double>().nullCheck(averageGrade);
        this.averageGrade = averageGrade;
        return this;
    }

    /**
     * Build student
     * @return build
     * @throws UniException
     */
    public Student build() throws UniException {
        Student student = new Student(username, password, university, averageGrade);
        return student;
    }

}
