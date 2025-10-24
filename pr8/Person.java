package pr8;

import java.util.Objects;

public abstract class Person {
    private String name;
    private int id;

    public Person(String name, int id) throws LibraryException {
        if (name == null || name.trim().isEmpty()) {
            throw new LibraryException("Имя не может быть пустым");
        }
        if (id <= 0) {
            throw new LibraryException("ID должен быть положительным числом");
        }
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) throws LibraryException {
        if (name == null || name.trim().isEmpty()) {
            throw new LibraryException("Имя не может быть пустым");
        }
        this.name = name;
    }

    public void setId(int id) throws LibraryException {
        if (id <= 0) {
            throw new LibraryException("ID должен быть положительным числом");
        }
        this.id = id;
    }

    public abstract void displayInfo();

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + name + " (ID: " + id + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}