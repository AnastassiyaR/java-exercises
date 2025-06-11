package ee.taltech.iti0202.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import ee.taltech.iti0202.json.school.School;
import ee.taltech.iti0202.json.student.Grade;
import ee.taltech.iti0202.json.student.Student;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.OptionalDouble;

public class SchoolDatabase {

    /*** DO NOT CHANGE */
    private final List<School> schools = new ArrayList<>();

    private GsonBuilder builder = new GsonBuilder();
    private Gson gson = builder.create();

    /**
     * DO NOT CHANGE
     * @param school school to add
     */
    public void addSchool(School school) {
        this.schools.add(school);
    }

    /**
     * DO NOT CHANGE
     * @return schools in the db
     */
    public List<School> getSchools() {
        return this.schools;
    }
    /**
     * Get all students in all schools in the database
     * @return all students json, if it's empty, return empty json {}
     */
    public String getAllStudents() {
        List<Student> students = schools.stream()
                .map(School::getStudents)
                .flatMap(List::stream) // flatMap - all students from all schools are combined together in one list.
                .toList();
        if (students.isEmpty()) {
            return "{}";
        }
        return gson.toJson(students);
    }

    /**
     * Get all students in specific school
     * @param school school's students to get
     * @return school's students json, if it's empty, return empty json {}
     */
    public String getAllStudents(School school) {
        if (school.getStudents().isEmpty()) {
            return "{}";
        }
        return gson.toJson(school.getStudents());
    }

    /**
     * Get student by id, check all schools that are in the database
     * @param id student's id
     * @return student class's json, if student is not found, return empty json {}
     */
    public String getStudent(int id) {
        Optional<Student> student = schools.stream()
                .map(School::getStudents)
                .flatMap(List::stream)
                .filter(s -> s.getId() == id)
                .findFirst();
        if (student.isEmpty()) {
            return "{}";
        }
        return gson.toJson(student.get(), Student.class);
    }

    /**
     * Get student's grades by id
     * @param id student's id
     * @return student's name with key "name", and array of grades (Grade class) with key "grades" in json,
     * if student is not found, return empty json {}
     */
    public String getStudentGrades(int id) {
        Optional<Student> students = schools.stream()
                .map(School::getStudents)
                .flatMap(List::stream)
                .filter(s -> s.getId() == id)
                .findFirst();
        if (students.isEmpty()) {
            return "{}";
        }

        JsonObject result = new JsonObject();
        result.addProperty("name", students.get().getName());
        JsonArray grades = new JsonArray();
        for (Grade grade : students.get().getGrades()) {
            JsonObject gradeObj = new JsonObject();
            gradeObj.addProperty("grade", grade.getGrade());
            gradeObj.addProperty("assignment", grade.getAssignment());
            grades.add(gradeObj);
        }
        result.add("grades", grades);
        return gson.toJson(result);
    }

    /**
     * Get student's average grade by id
     * @param id student's id
     * @return student's name with key "name", and average grade with key "averageGrade" in json,
     * if student is not found, return empty json {}
     */
    public String getStudentAverageGrade(int id) {
        Optional<Student> student = schools.stream()
                .map(School::getStudents)
                .flatMap(List::stream)
                .filter(s -> s.getId() == id)
                .findFirst();
        if (student.isEmpty()) {
            return "{}";
        }

        // OptionalDouble is similar to Optional<T>, but specialized for primitive double (avoids boxing overhead)
        OptionalDouble avg = student.get().getGrades().stream()
                .mapToInt(Grade::getGrade)
                .average();
        JsonObject result = new JsonObject();
        result.addProperty("name", student.get().getName());
        result.addProperty("averageGrade", avg.getAsDouble());
        return gson.toJson(result);
    }

    /**
     * Get average grade in each school in the database
     * @return json array of [{"school": "school's name", "averageGrade": averageGrade double}, ...],
     * if no schools are in the db, return empty json {}
     */
    public String getAverageGradeInEachSchool() {
        if (schools.isEmpty()) {
            return "{}";
        }
        JsonArray result = new JsonArray();
        for (School school : schools) {
            JsonObject obj = new JsonObject();
            OptionalDouble avg = school.getStudents().stream()
                    .flatMapToInt(st -> st.getGrades()
                            .stream()
                            .mapToInt(Grade::getGrade))
                    .average();
            obj.addProperty("school", school.getName());
            obj.addProperty("averageGrade", avg.getAsDouble());
            result.add(obj);
        }
        return gson.toJson(result);
    }

    /**
     * Get average grade for each student in each school in the database
     * @return json array of
     * [{"school": "school's name", "grades": [{"student": "student's name","averageGrade": averageGrade}]}, ...],
     * if no schools are in the db, return empty json {}
     */
    public String getAllStudentsInEachSchoolAndTheirAverageGrades() {
        if (schools.isEmpty()) {
            return "{}";
        }
        JsonArray result = new JsonArray();
        for (School school : schools) {
            JsonObject schoolObj = new JsonObject();
            schoolObj.addProperty("school", school.getName());
            JsonArray students = new JsonArray();
            for (Student student : school.getStudents()) {
                JsonObject studentObj = new JsonObject();
                OptionalDouble avg = student.getGrades().stream()
                        .mapToInt(Grade::getGrade)
                        .average();
                studentObj.addProperty("student", student.getName());
                studentObj.addProperty("averageGrade", avg.getAsDouble());
                students.add(studentObj);
            }
            schoolObj.add("grades", students);
            result.add(schoolObj);
        }
        return gson.toJson(result);
    }

    /**
     * Get all student's names in each school
     * @return json array of [{"school": "school's name", "students": ["student1", "student2", ...]}, ...],
     * if no schools are in the db, return empty json {}
     */
    public String getAllStudentsNamesInEachSchool() {
        if (schools.isEmpty()) {
            return "{}";
        }

        JsonArray result = new JsonArray();
        for (School school : schools) {
            JsonObject schoolObj = new JsonObject();
            schoolObj.addProperty("school", school.getName());
            JsonArray students = new JsonArray();
            for (Student student : school.getStudents()) {
                students.add(student.getName());
            }
            schoolObj.add("students", students);
            result.add(schoolObj);
        }

        return gson.toJson(result);
    }

    /**
     * Get average grade and all given grades count from all schools in the database
     * @return json of {"averageGrade": averageGradeDouble, "gradesTotal": gradesTotalInt}
     */
    public String getAverageGradeAndGradesCountGlobally() {
        if (schools.isEmpty()) {
            return "{}";
        }

        int sum = 0;
        int amount = 0;

        for (School school : schools) {
            List<Grade> grades = school.getStudents().stream()
                    .map(Student::getGrades)
                    .flatMap(List::stream)
                    .toList();
            amount += grades.size();
            sum += grades.stream()
                    .mapToInt(Grade::getGrade)
                    .sum();
        }

        JsonObject result = new JsonObject();
        result.addProperty("averageGrade", (double) sum / amount);
        result.addProperty("gradesTotal", amount);
        return gson.toJson(result);
    }
}
