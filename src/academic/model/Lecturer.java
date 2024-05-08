package academic.model;

import java.util.Objects;

/**
 * @author 12S22009 - Dolok Butarbutar
 * @author 12S22015 - Angelina Nadeak
 */

public class Lecturer {
    private String id;
    private final String name;
    private final String initial;
    private final String email;
    private final String StudyProgram;

    //id tidak boleh kosong
    public Lecturer(String id, String name, String initial, String email, String StudyProgram) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID tidak boleh kosong");
        }
        this.id = id;
        this.name = name;
        this.initial = initial;
        this.email = email;
        this.StudyProgram = StudyProgram;
    }
    
    @Override
    public String toString() {
        return id + "|" + name + "|" + initial + "|" + email + "|" + StudyProgram;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Lecturer lecturer = (Lecturer) obj;
        return Objects.equals(id, lecturer.id);
    }

    
    //getter id
    public String getId() {
        return id;
    }
    //getter initial
    public String getInitial() {
        return initial;
    }
    //getter email
    public String getEmail() {
        return email;
    }//
}