package ee.taltech.iti0202.json.student;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private static int nextId;
    private static int getAndIncrementNextId() {
        return ++nextId;
    }

    private final int id;
    private final String name;
    private final List<Grade> grades = new ArrayList<>();

    /**
     * Constructor
     * @param name
     */
    public Student(String name) {
        this.id = getAndIncrementNextId();
        this.name = name;
    }

    /**
     * Add grade
     * @param grade
     */
    public void addGrade(Grade grade) {
        this.grades.add(grade);
    }

    /**
     * Return name
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Return id
     * @return id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Return grades
     * @return grades
     */
    public List<Grade> getGrades() {
        return this.grades;
    }
}
