package academic.model;

import java.util.Objects;

/**
 * @author 12S22009 - Dolok Butarbutar
 * @author 12S22015 - Angelina Nadeak
 */

public class Student extends Person {
    public final int year;
    public final String program;

    public Student(String id, String name, int year, String program) {
        super(id, name);
        this.year = year;
        this.program = program;
    }

    public int getYear() {
        return year;
    }

    public String getProgram() {
        return program;
    }

    @Override
    public String toString() {
        return super.toString() + "|" + year + "|" + program;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), year, program);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        if (!super.equals(obj)) return false;
        Student student = (Student) obj;
        return year == student.year &&
                Objects.equals(program, student.program);
    }
}
