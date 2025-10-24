package pr8;

public class Librarian extends Person {
    public Librarian(String name, int employeeId) throws LibraryException {
        super(name, employeeId);
    }

    public void issueBook(Book book, Reader reader) {
        try {
            if (book == null) {
                throw new LibraryException("Книга не может быть null");
            }
            if (reader == null) {
                throw new LibraryException("Читатель не может быть null");
            }

            reader.borrowBook(book);
            System.out.println("Книга '" + book.getTitle() + "' выдана читателю " + reader.getName());
        } catch (LibraryException e) {
            System.err.println("Ошибка при выдаче книги: " + e.getMessage());
        }
    }

    public void acceptReturn(Book book, Reader reader, int daysOverdue) {
        try {
            if (book == null) {
                throw new LibraryException("Книга не может быть null");
            }
            if (reader == null) {
                throw new LibraryException("Читатель не может быть null");
            }
            if (daysOverdue < 0) {
                throw new LibraryException("Количество дней просрочки не может быть отрицательным");
            }

            book.setDaysOverdue(daysOverdue);
            reader.returnBook(book);

            if (daysOverdue > 0) {
                double fine = book.calculateFine();
                System.out.println("Книга возвращена с просрочкой " + daysOverdue + " дней. Штраф: " + fine + " руб.");
            } else {
                System.out.println("Книга возвращена вовремя");
            }
        } catch (LibraryException e) {
            System.err.println("Ошибка при возврате книги: " + e.getMessage());
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("Библиотекарь: " + getName() + " (ID: " + getId() + ")");
    }

    @Override
    public String toString() {
        return "Библиотекарь: " + getName() + " (ID: " + getId() + ")";
    }
}