class Person {  //Базовый класс "Человек"

    private String lastName;    // Фамилия
    private String firstName;   // Имя
    private String middleName;  // Отчество
    private String gender;      // Пол ("М" или "Ж")
    private int age;          // Возраст

    public Person(String lastName, String firstName, String middleName, String gender, int age) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.gender = gender;
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public void printInfo() {
        System.out.println("Фамилия: " + lastName + ", Имя: " + firstName + ", Отчество: " + middleName);
        System.out.println("Пол: " + gender + ", Возраст: " + age);
    }
}

class Patient extends Person {   //Класс "Пациент", наследуется от "Человека"

    private String nationality;
    private int height;
    private int weight;
    private String birthDate;    // В формате "ГГГГ-ММ-ДД"
    private String phone;
    private String address;
    private String hospitalNumber;
    private String department;
    private String medicalCardNumber;
    private String diagnosis;
    private String bloodType;

    public Patient(String lastName, String firstName, String middleName, String gender, int age,
                   String nationality, int height, int weight, String birthDate, String phone,
                   String address, String hospitalNumber, String department, String medicalCardNumber,
                   String diagnosis, String bloodType) {
        super(lastName, firstName, middleName, gender, age);
        this.nationality = nationality;
        this.height = height;
        this.weight = weight;
        this.birthDate = birthDate;
        this.phone = phone;
        this.address = address;
        this.hospitalNumber = hospitalNumber;
        this.department = department;
        this.medicalCardNumber = medicalCardNumber;
        this.diagnosis = diagnosis;
        this.bloodType = bloodType;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    @Override
    public void printInfo() {
        super.printInfo();   //Вызов метода printInfo родителя, чтобы печатать больше данных
        System.out.println("Национальность: " + nationality + ", Рост: " + height + ", Вес: " + weight);
        System.out.println("Дата рождения: " + birthDate + ", Телефон: " + phone);
        System.out.println("Адрес: " + address);
        System.out.println("Номер больницы: " + hospitalNumber + ", Отделение: " + department);
        System.out.println("Номер мед. карты: " + medicalCardNumber + ", Диагноз: " + diagnosis + ", Группа крови: " + bloodType);
    }

    public static void main(String[] args) { //Основной метод программы
        //Создаем массив объектов Patient
        Patient[] patients = new Patient[5];

        patients[0] = new Patient("Иванов", "Петр", "Сергеевич", "М", 35, "Русский", 180, 85, "1988-05-10", "+7-926-123-45-67", "Москва, ул. Ленина, д.1", "1", "Кардиология", "12345", "Гипертония", "O+");
        patients[1] = new Patient("Смирнова", "Анна", "Ивановна", "Ж", 28, "Русская", 165, 60, "1995-11-20", "+7-916-789-01-23", "Санкт-Петербург, Невский пр., д.2", "2", "Терапия", "67890", "ОРВИ", "A+");
        patients[2] = new Patient("Сидоров", "Дмитрий", "Александрович", "М", 42, "Русский", 175, 90, "1981-02-15", "+7-903-456-78-90", "Казань, ул. Баумана, д.3", "3", "Хирургия", "13579", "Аппендицит", "B+");
        patients[3] = new Patient("Петрова", "Елена", "Андреевна", "Ж", 50, "Русская", 160, 70, "1973-08-01", "+7-968-234-56-78", "Екатеринбург, ул. Малышева, д.4", "4", "Эндокринология", "24680", "Диабет", "O-");
        patients[4] = new Patient("Кузнецов", "Алексей", "Михайлович", "М", 60, "Русский", 185, 95, "1963-12-24", "+7-985-567-89-01", "Новосибирск, Красный проспект, д.5", "5", "Неврология", "98765", "Инсульт", "A-");

        System.out.println("--- ВСЕ ПАЦИЕНТЫ ---");
        for (Patient p : patients) {   //Выводим информацию о каждом пациенте
            p.printInfo();
        }

        System.out.println("--- ВЫБОРКА: Пациенты с диагнозом 'Гипертония' ---");
        for (Patient p : patients) {
            if (p.getDiagnosis().equals("Гипертония")) {   //Выводим только тех, кто соответствует условию
                p.printInfo();
            }
        }
    }
}
