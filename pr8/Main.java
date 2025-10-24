// учет выдачи и возврата книг (reader,librarian), штрафы за просрочку(book,reader), поиск книги по автору или названию(library)
package pr8;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Library library = new Library("Центральная городская библиотека");

            // Создание библиотекарей
            Librarian librarian1 = new Librarian("Анна Иванова", 1);
            Librarian librarian2 = new Librarian("Петр Сидоров", 2);
            library.addLibrarian(librarian1);
            library.addLibrarian(librarian2);

            // Создание читателей
            Reader reader1 = new Reader("Мария Петрова", 101);
            Reader reader2 = new Reader("Иван Козлов", 102);
            Reader reader3 = new Reader("Елена Смирнова", 103);
            library.addReader(reader1);
            library.addReader(reader2);
            library.addReader(reader3);

            // Создание книг
            Book book1 = new Book("Преступление и наказание", "Федор Достоевский", 1866);
            Book book2 = new Book("Война и мир", "Лев Толстой", 1869);
            Book book3 = new Book("Мастер и Маргарита", "Михаил Булгаков", 1967);
            Book book4 = new Book("Евгений Онегин", "Александр Пушкин", 1833);
            Book book5 = new Book("Отцы и дети", "Иван Тургенев", 1862);
            Book book6 = new Book("Анна Каренина", "Лев Толстой", 1877);

            library.addBook(book1);
            library.addBook(book2);
            library.addBook(book3);
            library.addBook(book4);
            library.addBook(book5);
            library.addBook(book6);

            System.out.println(library);
            System.out.println();

            library.displayAllLibrarians();
            System.out.println();

            library.displayAllReaders();
            System.out.println();

            library.displayAllBooks();
            System.out.println();

            // Демонстрация работы с исключениями
            System.out.println("=== ДЕМОНСТРАЦИЯ ОБРАБОТКИ ИСКЛЮЧЕНИЙ ===");

            // Попытка создать невалидную книгу
            try {
                Book invalidBook = new Book("", "Автор", 2020);
            } catch (LibraryException e) {
                System.err.println("Ошибка создания книги: " + e.getMessage());
            }

            // Попытка создать читателя с отрицательным ID
            try {
                Reader invalidReader = new Reader("Василий", -1);
            } catch (LibraryException e) {
                System.err.println("Ошибка создания читателя: " + e.getMessage());
            }

            // Попытка найти несуществующего автора
            try {
                List<Book> unknownAuthorBooks = library.findBooksByAuthor("Неизвестный Автор");
            } catch (LibraryException e) {
                System.err.println("Ошибка поиска: " + e.getMessage());
            }

            // Попытка найти несуществующую книгу
            try {
                List<Book> unknownBooks = library.findBooksByTitle("Несуществующая Книга");
            } catch (LibraryException e) {
                System.err.println("Ошибка поиска: " + e.getMessage());
            }

            // Выдача книг
            librarian1.issueBook(book1, reader1);
            librarian1.issueBook(book2, reader1);
            librarian2.issueBook(book3, reader2);

            // Попытка выдать уже выданную книгу
            librarian1.issueBook(book1, reader3);
            System.out.println();

            System.out.println("После выдачи книг:");
            library.displayAllBooks();
            System.out.println();

            // Поиск книг по автору с обработкой исключений
            try {
                System.out.println("=== ПОИСК КНИГ ПО АВТОРУ 'Лев Толстой' ===");
                List<Book> tolstoyBooks = library.findBooksByAuthor("Лев Толстой");
                for (Book book : tolstoyBooks) {
                    System.out.println(book);
                }
            } catch (LibraryException e) {
                System.err.println("Ошибка поиска: " + e.getMessage());
            }
            System.out.println();

            // Поиск книг по названию с обработкой исключений
            try {
                System.out.println("=== ПОИСК КНИГ ПО НАЗВАНИЮ 'Мастер и Маргарита' ===");
                List<Book> masterBooks = library.findBooksByTitle("Мастер и Маргарита");
                for (Book book : masterBooks) {
                    System.out.println(book);
                }
            } catch (LibraryException e) {
                System.err.println("Ошибка поиска: " + e.getMessage());
            }
            System.out.println();

            // Возврат книг
            librarian1.acceptReturn(book1, reader1, 0);
            librarian2.acceptReturn(book3, reader2, 5);
            System.out.println();

            // Попытка возврата книги, которая не была выдана
            librarian1.acceptReturn(book4, reader1, 0);
            System.out.println();

            System.out.println("После возврата книг:");
            library.displayAllBooks();
            System.out.println();

            System.out.println("=== ИНФОРМАЦИЯ О ЧИТАТЕЛЯХ ===");
            library.displayAllReaders();
            System.out.println();

            // Оплата штрафа с обработкой исключений
            try {
                reader2.payFine(25.0);
                System.out.println("После оплаты штрафа:");
                reader2.displayInfo();
            } catch (LibraryException e) {
                System.err.println("Ошибка оплаты штрафа: " + e.getMessage());
            }

            // Попытка оплаты неверной суммы
            try {
                reader2.payFine(-10.0);
            } catch (LibraryException e) {
                System.err.println("Ошибка оплаты: " + e.getMessage());
            }

            System.out.println("\n=== ДЕМОНСТРАЦИЯ ПОЛИМОРФИЗМА ===");
            List<Person> people = new ArrayList<>();
            people.add(reader1);
            people.add(reader2);
            people.add(librarian1);
            people.add(librarian2);

            for (Person person : people) {
                person.displayInfo();
            }

        } catch (LibraryException e) {
            System.err.println("Критическая ошибка при инициализации библиотеки: " + e.getMessage());
            e.printStackTrace();
        }
    }
}