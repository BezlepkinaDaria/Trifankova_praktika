package pr8;

import java.util.Objects;

public class Book {
    private String title;
    private String author;
    private int year;
    private boolean isAvailable;
    private Reader currentReader;
    private int daysOverdue;

    public Book(String title, String author, int year) throws LibraryException {
        if (title == null || title.trim().isEmpty()) {
            throw new LibraryException("Название книги не может быть пустым");
        }
        if (author == null || author.trim().isEmpty()) {
            throw new LibraryException("Автор не может быть пустым");
        }
        if (year < 0 || year > java.time.Year.now().getValue()) {
            throw new LibraryException("Некорректный год издания: " + year);
        }

        this.title = title;
        this.author = author;
        this.year = year;
        this.isAvailable = true;
        this.daysOverdue = 0;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Reader getCurrentReader() {
        return currentReader;
    }

    public void setCurrentReader(Reader currentReader) {
        this.currentReader = currentReader;
    }

    public int getDaysOverdue() {
        return daysOverdue;
    }

    public void setDaysOverdue(int daysOverdue) throws LibraryException {
        if (daysOverdue < 0) {
            throw new LibraryException("Количество дней просрочки не может быть отрицательным");
        }
        this.daysOverdue = daysOverdue;
    }

    public double calculateFine() {
        double finePerDay = 10.0;
        return daysOverdue * finePerDay;
    }

    @Override
    public String toString() {
        String status = isAvailable ? "Доступна" : "Выдана: " + (currentReader != null ? currentReader.getName() : "неизвестно");
        String overdue = daysOverdue > 0 ? ", Просрочено дней: " + daysOverdue + ", Штраф: " + calculateFine() + " руб." : "";
        return title + " (" + author + ", " + year + ") - " + status + overdue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return year == book.year && Objects.equals(title, book.title) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, year);
    }
}