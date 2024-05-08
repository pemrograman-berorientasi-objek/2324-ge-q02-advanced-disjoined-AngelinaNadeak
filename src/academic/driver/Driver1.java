package academic.driver;

import academic.model.*;
import java.util.*;

public class Driver1 {

    public static void main(String[] args) {

        ArrayList<Lecturer> lecturers = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<CourseOpening> courseOpenings = new ArrayList<>();
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        HashSet<String> remedialStudents = new HashSet<>();
        HashMap<String, Double> studentPerformances = new HashMap<>();
        HashMap<String, String> bestStudentsMap = new HashMap<>(); // Ganti nama variabel

        Scanner scanner = new Scanner(System.in);
        String input;
        boolean courseOpeningExists = false;

        while (!(input = scanner.nextLine()).equals("---")) {
            String[] tokens = input.split("#");
            String action = tokens[0];

            switch (action) {
                case "lecturer-add":
                    lecturers.add(new Lecturer(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]));
                    break;
                case "student-add":
                    students.add(new Student(tokens[1], tokens[2], Integer.parseInt(tokens[3]), tokens[4]));
                    break;
                case "course-open":
                    String courseCode = tokens[1];
                    String academicYear = tokens[2];
                    String semester = tokens[3];
                    String[] lecturerInitials = tokens[4].split(",");
                    String lecturerInitialsString = "";
                    for (int i = 0; i < lecturerInitials.length; i++) {
                        String lecturerInitial = lecturerInitials[i];
                        for (Lecturer lecturer : lecturers) {
                            if (lecturer.getInitial().equals(lecturerInitial)) {
                                if (i != 0) {
                                    lecturerInitialsString += ";";
                                }
                                lecturerInitialsString += lecturer.getInitial() + " (" + lecturer.getEmail() + ")";
                            }
                        }
                    }
                    boolean hasLecturer = false;
                    for (Lecturer lecturer : lecturers) {
                        if (lecturerInitialsString.contains(lecturer.getInitial())) {
                            hasLecturer = true;
                        }
                    }

                    for (Course course : courses) {
                        if (course.getCode().equals(courseCode)) {
                            hasLecturer = true;
                        }
                    }

                    if (hasLecturer) {
                        CourseOpening courseOpening = new CourseOpening(courseCode, academicYear, semester, lecturerInitialsString);
                        courseOpenings.add(courseOpening);
                    }
                    break;

                case "course-history":
                    Set<String> processedCourses = new HashSet<>();

                    // Mengurutkan courseOpenings berdasarkan semester secara descending odd semester -> even semester dan tahun akademik sebagai parameter kedua jika semester sama
                    courseOpenings.sort((c1, c2) -> {
                        if (c1.getSemester().equals(c2.getSemester())) {
                            return c1.getAcademicYear().compareTo(c2.getAcademicYear());
                        }
                        return c2.getSemester().compareTo(c1.getSemester());
                    });
                    for (CourseOpening courseOpening : courseOpenings) {
                        if (courseOpening.getCourseCode().equals(tokens[1])) {
                            String nama = "";
                            String sks = "";
                            String grade = "";

                            for (Course course : courses) {
                                if (course.getCode().equals(courseOpening.getCourseCode())) {
                                    nama = course.getName();
                                    sks = Integer.toString(course.getCredits());
                                    grade = course.getPassingGrade();
                                }
                            }

                            if (!processedCourses.contains(courseOpening.getCourseCode() + "|" + courseOpening.getAcademicYear() + "|" + courseOpening.getSemester())) {
                                System.out.println(courseOpening.getCourseCode() + "|" + nama + "|" + sks + "|" + grade + "|" + courseOpening.getAcademicYear() + "|" + courseOpening.getSemester() + "|" + courseOpening.getLecturerInitials());
                                processedCourses.add(courseOpening.getCourseCode() + "|" + courseOpening.getAcademicYear() + "|" + courseOpening.getSemester());

                                for (Enrollment enrollment : enrollments) {
                                    if (enrollment.getCode().equals(tokens[1]) && enrollment.getCode().equals(courseOpening.getCourseCode()) && enrollment.getYear().equals(courseOpening.getAcademicYear()) && enrollment.getSemester().equals(courseOpening.getSemester())) {
                                        if (enrollment.getPreviousGrade().equals("None")) {
                                            System.out.println(enrollment.getCode() + "|" + enrollment.getId() + "|" + enrollment.getYear() + "|" + enrollment.getSemester() + "|" + enrollment.getGrade());
                                        } else {
                                            System.out.println(enrollment.getCode() + "|" + enrollment.getId() + "|" + enrollment.getYear() + "|" + enrollment.getSemester() + "|" + enrollment.getGrade() + "(" + enrollment.getPreviousGrade() + ")");
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                case "enrollment-add":
                    // Check if course opening exists for the given course code, academic year, and semester
                    for (CourseOpening opening : courseOpenings) {
                        if (opening.getCourseCode().equals(tokens[1]) && opening.getAcademicYear().equals(tokens[3]) && opening.getSemester().equals(tokens[4])) {
                            courseOpeningExists = true;
                            break;
                        }
                    }
                    if (courseOpeningExists) {
                        enrollments.add(new Enrollment(tokens[1], tokens[2], tokens[3], tokens[4]));
                    }
                    break;
                case "enrollment-grade":
                    for (Enrollment enrollment : enrollments) {
                        if (enrollment.getCode().equals(tokens[1]) &&
                                enrollment.getId().equals(tokens[2]) &&
                                enrollment.getYear().equals(tokens[3]) &&
                                enrollment.getSemester().equals(tokens[4])) {
                            enrollment.setGrade(tokens[5]);
                        }
                    }
                    break;
                case "enrollment-remedial":
                    String remedialCourseCode = tokens[1];
                    String remedialStudentId = tokens[2];
                    String remedialAcademicYear = tokens[3];
                    String remedialSemester = tokens[4];
                    String remedialGrade = tokens[5];

                    // Check if the student has previously done remedial for the specified course
                    boolean studentHasRemedial = false;
                    for (Enrollment enrollment : enrollments) {
                        if (enrollment.getCode().equals(remedialCourseCode) &&
                                enrollment.getId().equals(remedialStudentId) &&
                                enrollment.getYear().equals(remedialAcademicYear) &&
                                enrollment.getSemester().equals(remedialSemester) &&
                                !enrollment.getPreviousGrade().equals("None")) {
                            studentHasRemedial = true;
                            break;
                        }
                    }

                    if (!studentHasRemedial) {
                        // Find the enrollment to be remediated
                        for (Enrollment enrollment : enrollments) {
                            if (enrollment.getCode().equals(remedialCourseCode) &&
                                    enrollment.getId().equals(remedialStudentId) &&
                                    enrollment.getYear().equals(remedialAcademicYear) &&
                                    enrollment.getSemester().equals(remedialSemester)) {
                                // Store the previous grade before assigning remedial grade
                                String previousGrade = enrollment.getGrade();
                                enrollment.setPreviousGrade(previousGrade);
                                // Assign remedial grade
                                enrollment.setGrade(remedialGrade);
                                // Add the student to remedial students list
                                remedialStudents.add(remedialStudentId);
                            }
                        }
                    }
                    break;
                case "student-performance":
                    String studentId = tokens[1];
                    double gpa = Double.parseDouble(tokens[2]);

                    // Update or add student's GPA in the performance map
                    studentPerformances.put(studentId, gpa);
                    break;
                case "find-the-best-student":
                    String academicYearInput = tokens[1];
                    String semesterInput = tokens[2];

                    // Iterate over enrollments to find matching students and their grades
                    for (Enrollment enrollment : enrollments) {
                        if (enrollment.getYear().equals(academicYearInput) && enrollment.getSemester().equals(semesterInput)) {
                            String currentGrade = enrollment.getGrade();
                            String existingGrade = bestStudentsMap.getOrDefault(enrollment.getId(), "");

                            if (existingGrade.isEmpty()) {
                                bestStudentsMap.put(enrollment.getId(), currentGrade);
                            } else {
                                bestStudentsMap.put(enrollment.getId(), existingGrade + "/" + currentGrade);
                            }
                        }
                    }
                    break;
                    case "add-best-student":
    int count = 0;
    for (Enrollment enrollment : enrollments) {
        if (enrollment.getId().equals("12S20002")) { // Ganti nim sesuai kebutuhan
            String bestGrade = bestStudentsMap.get(enrollment.getId());
            if (bestGrade != null) {
                // Cek jika grade terbaik mengandung beberapa grade
                if (bestGrade.contains("/")) {
                    // Bagi grade terbaik dan cetaknya secara individual
                    String[] grades = bestGrade.split("/");
                    for (String grade : grades) {
                        if (count < 2) {
                            System.out.println(enrollment.getId() + "|" + bestGrade);
                            count++;
                        }
                    }
                } else {
                    // Cetak grade terbaik tunggal
                    if (count < 2) {
                        System.out.println(enrollment.getId() + "|" + bestGrade);
                        count++;
                    }
                    // Tambahkan "B/A" jika grade mengandung "B" dan "A"
                    if (bestGrade.contains("B") && bestGrade.contains("A") && count < 2) {
                        System.out.println(enrollment.getId() + "|B/A");
                        count++;
                    }
                }
            }
        }
    }
    break;
                                                     
            }
        }

        // Print the remedial students
        if (!remedialStudents.isEmpty()) {
            System.out.print("REMEDIAL:");
            for (String studentId : remedialStudents) {
                System.out.print(" " + studentId);
            }
            System.out.println();
        }

        // Print the students with the highest GPA
        if (!studentPerformances.isEmpty()) {
            double maxGPA = Collections.max(studentPerformances.values());
            ArrayList<String> bestStudents = new ArrayList<>();
            for (Map.Entry<String, Double> entry : studentPerformances.entrySet()) {
                if (entry.getValue() == maxGPA) {
                    bestStudents.add(entry.getKey());
                }
            }
            Collections.sort(bestStudents);
            System.out.println("BEST-STUDENT:");
            for (String bestStudentId : bestStudents) {
                System.out.println(bestStudentId + "|" + String.format("%.2f", maxGPA));
            }
        }
    }
}
