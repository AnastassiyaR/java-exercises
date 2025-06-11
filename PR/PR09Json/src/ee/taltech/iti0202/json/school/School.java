package ee.taltech.iti0202.json.school;

import ee.taltech.iti0202.json.student.Student;

import java.util.ArrayList;
import java.util.List;

public class School {

    private final List<Student> students = new ArrayList<>();
    private final String name;

    /**
     * Constructor
     * @param name
     */
    public School(String name) {
        this.name = name;
    }

    /**
     * Add student
     * @param student
     */
    public void addStudent(Student student) {
        this.students.add(student);
    }

    /**
     * Remove student
     * @param student
     */
    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    /**
     * Get students
     * @return students
     */
    public List<Student> getStudents() {
        return this.students;
    }

    /**
     * Get name
     * @return name
     */
    public String getName() {
        return this.name;
    }
}
