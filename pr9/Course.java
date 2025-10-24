// декоратор, фабричный метод, наблюдатель, шаблонный метод
package pr9;

import java.util.ArrayList;
import java.util.List;

// Базовый абстрактный класс курса
abstract class Course {                      // !!! шаблонный метод
    protected String title;
    protected String description;
    protected List<Student> enrolledStudents;
    protected List<String> lessons;

    public Course(String title, String description) {
        this.title = title;
        this.description = description;
        this.enrolledStudents = new ArrayList<>();
        this.lessons = new ArrayList<>();
    }

    public void enrollStudent(Student student) {
        if (!enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
            student.enrollInCourse(this);
        }
    }

    public void addLesson(String lesson) {
        lessons.add(lesson);
        notifyStudents(lesson);
    }

    public abstract void displayInfo();                 // !!! шаблонный метод

    protected void notifyStudents(String lesson) {      // !!! наблюдатель
        for (Student student : enrolledStudents) {
            student.update(this, lesson);
        }
    }

    public String getTitle() {
        return title;
    }

    public List<String> getLessons() {
        return lessons;
    }
}

// Конкретные классы курсов
class ProgrammingCourse extends Course {
    public ProgrammingCourse(String title, String description) {
        super(title, description);
    }

    @Override
    public void displayInfo() {
        System.out.println("💻 Программирование: " + title);
        System.out.println("   Описание: " + description);
        System.out.println("   Студентов: " + enrolledStudents.size());
        System.out.println("   Уроков: " + lessons.size());
    }
}

class DesignCourse extends Course {
    public DesignCourse(String title, String description) {
        super(title, description);
    }

    @Override
    public void displayInfo() {
        System.out.println("🎨 Дизайн: " + title);
        System.out.println("   Описание: " + description);
        System.out.println("   Студентов: " + enrolledStudents.size());
        System.out.println("   Уроков: " + lessons.size());
    }
}

class LanguageCourse extends Course {
    public LanguageCourse(String title, String description) {
        super(title, description);
    }

    @Override
    public void displayInfo() {
        System.out.println("🔤 Языковой курс: " + title);
        System.out.println("   Описание: " + description);
        System.out.println("   Студентов: " + enrolledStudents.size());
        System.out.println("   Уроков: " + lessons.size());
    }
}

// Декоратор курсов !!!
abstract class CourseDecorator extends Course {
    protected Course decoratedCourse;

    public CourseDecorator(Course course) {
        super(course.title, course.description);
        this.decoratedCourse = course;
        this.enrolledStudents = course.enrolledStudents;
        this.lessons = course.lessons;
    }

    @Override
    public abstract void displayInfo();
}

// Декоратор с сертификатом
class CertificateCourse extends CourseDecorator {
    public CertificateCourse(Course course) {
        super(course);
    }

    @Override
    public void displayInfo() {
        decoratedCourse.displayInfo();
        System.out.println("   ⭐ Включает сертификат о прохождении");
    }

    @Override
    public void enrollStudent(Student student) {
        super.enrollStudent(student);
        System.out.println("   📝 Студент " + student.getName() + " получит сертификат после завершения курса");
    }
}

// Фабрика курсов !!!
class CourseFactory {
    public static Course createCourse(String type, String title, String description) {
        switch (type.toLowerCase()) {
            case "programming":
                return new ProgrammingCourse(title, description);
            case "design":
                return new DesignCourse(title, description);
            case "language":
                return new LanguageCourse(title, description);
            default:
                throw new IllegalArgumentException("Неизвестный тип курса: " + type);
        }
    }
}