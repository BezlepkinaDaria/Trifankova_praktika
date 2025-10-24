// одиночка
package pr9;

import java.util.ArrayList;
import java.util.List;

public class OnlineSchool {
    private static OnlineSchool instance;               // !!!
    private List<Course> courses;
    private List<Student> students;

    private OnlineSchool() {
        courses = new ArrayList<>();
        students = new ArrayList<>();
    }

    public static OnlineSchool getInstance() {           // !!!
        if (instance == null) {
            instance = new OnlineSchool();
        }
        return instance;
    }

    public void addCourse(Course course) {
        courses.add(course);
        System.out.println("✅ Курс добавлен: " + course.getTitle());
    }

    public void registerStudent(Student student) {
        students.add(student);
        System.out.println("✅ Студент зарегистрирован: " + student.getName());
    }

    public void displayAllCourses() {
        System.out.println("\n=== 📋 ВСЕ КУРСЫ В ШКОЛЕ (" + courses.size() + ") ===");
        for (Course course : courses) {
            course.displayInfo();
            System.out.println();
        }
    }

    public void displayAllStudents() {
        System.out.println("\n=== 👥 ВСЕ СТУДЕНТЫ (" + students.size() + ") ===");
        for (Student student : students) {
            student.displayInfo();
            System.out.println();
        }
    }

    public Course findCourseByTitle(String title) {
        for (Course course : courses) {
            if (course.getTitle().equalsIgnoreCase(title)) {
                return course;
            }
        }
        return null;
    }

    public Student findStudentByName(String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }
}