package pr8;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Person {
    private List<Book> borrowedBooks;
    private double totalFines;

    public Reader(String name, int readerId) throws LibraryException {
        super(name, readerId);
        this.borrowedBooks = new ArrayList<>();
        this.totalFines = 0.0;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public double getTotalFines() {
        return totalFines;
    }

    public void addFine(double fine) throws LibraryException {
        if (fine < 0) {
            throw new LibraryException("Штраф не может быть отрицательным");
        }
        this.totalFines += fine;
    }

    public void payFine(double amount) throws LibraryException {
        if (amount < 0) {
            throw new LibraryException("Сумма оплаты не может быть отрицательной");
        }
        if (amount > totalFines) {
            throw new LibraryException("Сумма оплаты (" + amount + ") превышает задолженность (" + totalFines + ")");
        }
        totalFines -= amount;
    }

    public void borrowBook(Book book) throws LibraryException {
        if (book == null) {
            throw new LibraryException("Книга не может быть null");
        }
        if (!book.isAvailable()) {
            throw new LibraryException("Книга '" + book.getTitle() + "' уже выдана другому читателю");
        }
        if (borrowedBooks.size() >= 5) {
            throw new LibraryException("Превышен лимит книг (5) для читателя " + getName());
        }

        borrowedBooks.add(book);
        book.setAvailable(false);
        book.setCurrentReader(this);
    }

    public void returnBook(Book book) throws LibraryException {
        if (book == null) {
            throw new LibraryException("Книга не может быть null");
        }
        if (!borrowedBooks.contains(book)) {
            throw new LibraryException("Книга '" + book.getTitle() + "' не была выдана читателю " + getName());
        }

        borrowedBooks.remove(book);
        book.setAvailable(true);
        book.setCurrentReader(null);

        if (book.getDaysOverdue() > 0) {
            double fine = book.calculateFine();
            addFine(fine);
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("Читатель: " + getName() + " (ID: " + getId() +
                "), Книг на руках: " + borrowedBooks.size() +
                ", Общий штраф: " + totalFines + " руб.");
    }

    @Override
    public String toString() {
        return "Читатель: " + getName() + " (ID: " + getId() +
                "), Книг на руках: " + borrowedBooks.size() +
                ", Общий штраф: " + totalFines + " руб.";
    }
}