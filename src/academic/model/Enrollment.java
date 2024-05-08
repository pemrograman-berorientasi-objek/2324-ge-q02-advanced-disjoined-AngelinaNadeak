package academic.model;

import java.util.Objects;

/**
 * @author 12S22009 - Dolok Butarbutar
 * @author 12S22015 - Angelina Nadeak
 */

public class Enrollment implements GradeHistory {
    public final String courseCode;
    public final String studentID;
    public final String academicYear;
    public final String semester;
    public String grade;
    public int credits;

    private String previousGrade = "None"; // Initialize previousGrade to "None"

    public Enrollment(String courseCode, String studentID, String academicYear, String semester) {
        if (courseCode == null || courseCode.isEmpty() || studentID == null || studentID.isEmpty()) {
            throw new IllegalArgumentException("Course code and student ID cannot be null or empty");
        }
        this.courseCode = courseCode;
        this.studentID = studentID;
        this.academicYear = academicYear;
        this.semester = semester;
        this.grade = "None";
    }

    // Constructor with credits
    public Enrollment(String courseCode, String studentID, String academicYear, String semester, int credits) {
        this(courseCode, studentID, academicYear, semester);
        this.credits = credits;
    }

    public String getSemester() {
        return semester;
    }

    public String getYear() {
        return academicYear;
    }


    public String getCode() {
        return courseCode;
    }

    public String getId() {
        return studentID;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String newGrade) {
        this.grade = newGrade;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return courseCode + "|" + studentID + "|" + academicYear + "|" + semester + "|" + grade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode, studentID);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Enrollment that = (Enrollment) obj;
        return Objects.equals(courseCode, that.courseCode) &&
                Objects.equals(studentID, that.studentID);
    }

    public int getCredit() {
        return credits;
    }

    @Override
    public String getPreviousGrade() {
        return previousGrade;
    }

    @Override
    public void setPreviousGrade(String previousGrade) {
        this.previousGrade = previousGrade;
    }

    public int compareTo(Enrollment enrollment2) {
        return 0;
    }
}
