// наблюдатель
package pr9;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String email;
    private List<Course> enrolledCourses;

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
        this.enrolledCourses = new ArrayList<>();
    }

    public void enrollInCourse(Course course) {
        enrolledCourses.add(course);
    }

    // Метод для уведомлений
    public void update(Course course, String lesson) {          // !!! наблюдатель
        System.out.println("📧 Уведомление для " + name + ": Новый урок в курсе '" +
                course.getTitle() + "' - \"" + lesson + "\"");
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void displayInfo() {
        System.out.println("🎓 Студент: " + name + " (" + email + ")");
        System.out.println("   Зачислен на курсы: " + enrolledCourses.size());
        for (Course course : enrolledCourses) {
            System.out.println("   📚 " + course.getTitle());
        }
    }
}