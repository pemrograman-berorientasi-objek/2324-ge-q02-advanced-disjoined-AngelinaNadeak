package academic.model;

import java.util.*;

/**
 * @author 12S22009 - Dolok Butarbutar
 * @author 12S22015 - Angelina Nadeak
 */

public class CourseOpening {
    private final String courseCode;
    private final String academicYear;
    private final String semester;
    private final String lecturerInitials;

    public CourseOpening(String courseCode, String academicYear, String semester, String lecturerInitials) {
        this.courseCode = courseCode;
        this.academicYear = academicYear;
        this.semester = semester;
        this.lecturerInitials = lecturerInitials;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public String getSemester() {
        return semester;
    }

    public String getLecturerInitials() {
        return lecturerInitials;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseOpening that = (CourseOpening) o;
        return Objects.equals(courseCode, that.courseCode) &&
                Objects.equals(academicYear, that.academicYear) &&
                Objects.equals(semester, that.semester) &&
                Objects.equals(lecturerInitials, that.lecturerInitials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode, academicYear, semester, lecturerInitials);
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s", courseCode, academicYear, semester, String.join(",", lecturerInitials));
    }
}
