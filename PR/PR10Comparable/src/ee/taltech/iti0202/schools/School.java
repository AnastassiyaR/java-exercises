package ee.taltech.iti0202.schools;

import ee.taltech.iti0202.location.Location;
import ee.taltech.iti0202.student.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class School implements Comparable<School> {

    private static final List<School> SCHOOLS = new ArrayList<>();
    private final List<Student> students = new ArrayList<>();
    private final String name;
    private final Location location;
    /**
     * Construct a new school with a name and Location.
     * @param name name of school
     * @param location Location of school
     */
    protected School(String name, Location location) {
        this.name = name;
        this.location = location;
        SCHOOLS.add(this);
    }


    /**
     * Adds student to list of students
     * @param student Student
     */
    public void addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
        }
    }

    /**
     * Returns name of school
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns location of school
     * @return Location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Returns List of students in school
     * @return List of students
     */
    public List<Student> getStudents() {
        return students;
    }

    private int getPriority() {
        return switch (this) {
            case University university -> 3;
            case SecondarySchool secondarySchool -> 2;
            case PrimarySchool primarySchool -> 1;
            default -> 0;
        };
    }

    /**
     * Comparing order:
     *  1. By class
     *  2. By amount of student
     *  3. By country name
     *  4. By city name
     *  5. By school name
     * @param other the object to be compared.
     * @return int -1, 0, or 1
     */
    @Override
    public int compareTo(School other) {
        if (this.getPriority() != other.getPriority()) {
            return -Integer.compare(this.getPriority(), other.getPriority());
//          -Integer because we need deceased list
//            With minus:
//              Tallinn University
//              Tartu Secondary School
//              Mustamäe Primary School
//            Without minus:
//              Mustamäe Primary School
//              Tartu Secondary School
//              Tallinn University
        } else if (students.size() != other.getStudents().size()) {
//            With minus:
//                Tallinn University         (5000)
//                Tartu Secondary School     (2000)
//                Mustamäe Primary School    (300)
//            Without minus:
//                Mustamäe Primary School    (300)
//                Tartu Secondary School     (2000)
//                Tallinn University         (5000)
            return -Integer.compare(students.size(), other.getStudents().size());

        } else if (!location.country().equals(other.location.country())) {
            return location.country().compareTo(other.location.country());

        } else if (!location.city().equals(other.location.city())) {
            return location.city().compareTo(other.location.city());
        }
       return name.compareTo(other.name);
    }

    /**
     * Adds given school to a list containing all Schools.
     * Does not add it to list if it's already added.
     * @param school School
     */
    public static void addSchool(School school) {
        if (!SCHOOLS.contains(school)) {
            SCHOOLS.add(school);
        }
    }

    /**
     * Clears list containing all schools
     */
    public static void clearSchools() {
        SCHOOLS.clear();
    }

    /**
     * Returns sorted List of all schools.
     * @return sorted list of schools
     */
    public static List<School> getSchools() {
        return SCHOOLS.stream().sorted().collect(Collectors.toUnmodifiableList());
    }
}
