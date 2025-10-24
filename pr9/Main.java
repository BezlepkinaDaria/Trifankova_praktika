// добавление курсов, регистрацию студентов, уведомления о новых уроках
package pr9;

public class Main {
    public static void main(String[] args) {
        // Получаем экземпляр онлайн-школы
        OnlineSchool school = OnlineSchool.getInstance(); // (Singleton) !!!

        System.out.println("🚀 === СИСТЕМА УПРАВЛЕНИЯ ОНЛАЙН-ШКОЛОЙ ===\n");

        // Создаем курсы через фабрику
        Course javaCourse = CourseFactory.createCourse( // фабрика !!!
                "programming", "Java для начинающих", "Изучение основ Java"
        );

        Course pythonCourse = CourseFactory.createCourse(
                "programming", "Python Advanced", "Продвинутый Python"
        );

        Course webDesignCourse = CourseFactory.createCourse(
                "design", "Веб-дизайн", "Основы дизайна для веба"
        );

        Course englishCourse = CourseFactory.createCourse(
                "language", "Английский B2", "Английский для среднего уровня"
        );

        // Добавляем обычные курсы в школу
        school.addCourse(javaCourse);
        school.addCourse(webDesignCourse);
        school.addCourse(englishCourse);

        // Создаем курс с сертификатом            !!! (Decorator)
        Course pythonWithCertificate = new CertificateCourse(pythonCourse);
        school.addCourse(pythonWithCertificate);

        // Регистрируем студентов
        Student student1 = new Student("Анна Иванова", "anna@mail.com");
        Student student2 = new Student("Петр Сидоров", "petr@mail.com");
        Student student3 = new Student("Мария Козлова", "maria@mail.com");

        school.registerStudent(student1);
        school.registerStudent(student2);
        school.registerStudent(student3);

        // Зачисляем студентов на курсы
        System.out.println("\n=== 🎯 ЗАЧИСЛЕНИЕ СТУДЕНТОВ НА КУРСЫ ===");
        javaCourse.enrollStudent(student1);
        javaCourse.enrollStudent(student2);

        pythonWithCertificate.enrollStudent(student1);
        pythonWithCertificate.enrollStudent(student3);

        webDesignCourse.enrollStudent(student2);
        englishCourse.enrollStudent(student3);

        // Добавляем уроки в курсы (студенты получат уведомления - Observer)
        System.out.println("\n=== 📖 ДОБАВЛЕНИЕ НОВЫХ УРОКОВ ===");
        javaCourse.addLesson("Введение в Java и установка среды");  // !!! наблюдатель
        javaCourse.addLesson("Переменные и типы данных");

        pythonWithCertificate.addLesson("Декораторы в Python");
        pythonWithCertificate.addLesson("Генераторы и итераторы");

        webDesignCourse.addLesson("Основы цветовой теории");
        englishCourse.addLesson("Времена группы Perfect");

        // Показываем всю информацию
        school.displayAllCourses();
        school.displayAllStudents();

        // Демонстрация поиска
        System.out.println("\n=== 🔍 ПОИСК КУРСА ===");
        Course foundCourse = school.findCourseByTitle("Java для начинающих");
        if (foundCourse != null) {
            System.out.println("Найден курс:");
            foundCourse.displayInfo();               // !!! шаблонный метод
        }

        System.out.println("\n=== 🔍 ПОИСК СТУДЕНТА ===");
        Student foundStudent = school.findStudentByName("Анна Иванова");
        if (foundStudent != null) {
            System.out.println("Найден студент:");
            foundStudent.displayInfo();
        }

        System.out.println("\n🎉 === ПРОГРАММА ЗАВЕРШЕНА ===");
    }
}