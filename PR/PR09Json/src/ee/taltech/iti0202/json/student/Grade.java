package ee.taltech.iti0202.json.student;

public class Grade {
    private int grade;
    private String assignment;

    /**
     * Constructor
     * @param grade
     * @param assignment
     */
    public Grade(int grade, String assignment) {
        this.grade = grade;
        this.assignment = assignment;
    }

    /**
     * Get grade
     * @return grade
     */
    public int getGrade() {
        return grade;
    }

    /**
     * Get assignment
     * @return assignment
     */
    public String getAssignment() {
        return assignment;
    }

}
