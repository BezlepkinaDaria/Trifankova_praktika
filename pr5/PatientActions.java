interface PatientActions {
    String getFullName(); // Получать полное имя (ФИО)
    int getAge();        // Получать возраст
    String getPhone();    // Получать номер телефона
    String getAddress();  // Получать адрес
    String getDiagnosis(); // Получать диагноз
    void printInfo();    // Печатать информацию о пациенте
}
import java.util.Arrays;

class Patient implements PatientActions {

    private String lastName;
    private String firstName;
    private String middleName;
    private String gender;
    private int age;
    private String nationality;
    private int height;
    private int weight;
    private String birthDate;
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
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.gender = gender;
        this.age = age;
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

    // Реализация методов интерфейса PatientActions
    @Override
    public String getFullName() {
        return lastName + " " + firstName + " " + middleName;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getDiagnosis() {
        return diagnosis;
    }

    @Override
    public void printInfo() {
        System.out.println("ФИО: " + getFullName());
        System.out.println("Возраст: " + age);
        System.out.println("Телефон: " + phone);
        System.out.println("Адрес: " + address);
        System.out.println("Диагноз: " + diagnosis);
        System.out.println("Национальность: " + nationality + ", Рост: " + height + ", Вес: " + weight);
        System.out.println("Дата рождения: " + birthDate + ", Номер больницы: " + hospitalNumber + ", Отделение: " + department);
        System.out.println("Номер мед. карты: " + medicalCardNumber + ", Группа крови: " + bloodType);
    }

    public static void main(String[] args) {
        // 1. Создание массива объектов Patient (3-5 объектов)
        Patient[] patients = new Patient[5];

        patients[0] = new Patient("Иванов", "Петр", "Сергеевич", "М", 35, "Русский", 180, 85, "1988-05-10", "+7-926-123-45-67", "Москва, ул. Ленина, д.1", "1", "Кардиология", "12345", "Гипертония", "O+");
        patients[1] = new Patient("Смирнова", "Анна", "Ивановна", "Ж", 28, "Русская", 165, 60, "1995-11-20", "+7-916-789-01-23", "Санкт-Петербург, Невский пр., д.2", "2", "Терапия", "67890", "ОРВИ", "A+");
        patients[2] = new Patient("Сидоров", "Дмитрий", "Александрович", "М", 42, "Русский", 175, 90, "1981-02-15", "+7-903-456-78-90", "Казань, ул. Баумана, д.3", "3", "Хирургия", "13579", "Аппендицит", "B+");
        patients[3] = new Patient("Петрова", "Елена", "Андреевна", "Ж", 50, "Русская", 160, 70, "1973-08-01", "+7-968-234-56-78", "Екатеринбург, ул. Малышева, д.4", "4", "Эндокринология", "24680", "Диабет", "O-");
        patients[4] = new Patient("Кузнецов", "Алексей", "Михайлович", "М", 60, "Русский", 185, 95, "1963-12-24", "+7-985-567-89-01", "Новосибирск, Красный проспект, д.5", "5", "Неврология", "98765", "Инсульт", "A-");

        // 2. Вывод информации обо всех объектах через методы интерфейса
        System.out.println("--- Информация о всех пациентах ---");
        for (Patient patient : patients) {
            patient.printInfo();
            System.out.println("---");
        }

        // 3. Сделать выборку по своим свойствам (например, пациенты определенного возраста)
        System.out.println("--- Пациенты старше 40 лет ---");
        for (Patient patient : patients) {
            if (patient.getAge() > 40) {
                patient.printInfo();
                System.out.println("---");
            }
        }
        System.out.println("--- Пациенты с диагнозом ОРВИ ---");
        Arrays.stream(patients)
                .filter(patient -> "ОРВИ".equals(patient.getDiagnosis()))
                .forEach(Patient::printInfo);
    }
}