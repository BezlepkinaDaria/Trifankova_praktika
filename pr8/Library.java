package pr8;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private String name;
    private List<Book> books;
    private List<Reader> readers;
    private List<Librarian> librarians;

    public Library(String name) throws LibraryException {
        if (name == null || name.trim().isEmpty()) {
            throw new LibraryException("Название библиотеки не может быть пустым");
        }
        this.name = name;
        this.books = new ArrayList<>();
        this.readers = new ArrayList<>();
        this.librarians = new ArrayList<>();
    }

    public void addBook(Book book) throws LibraryException {
        if (book == null) {
            throw new LibraryException("Книга не может быть null");
        }
        if (books.contains(book)) {
            throw new LibraryException("Книга '" + book.getTitle() + "' уже существует в библиотеке");
        }
        books.add(book);
    }

    public void addReader(Reader reader) throws LibraryException {
        if (reader == null) {
            throw new LibraryException("Читатель не может быть null");
        }
        if (readers.contains(reader)) {
            throw new LibraryException("Читатель '" + reader.getName() + "' уже зарегистрирован");
        }
        readers.add(reader);
    }

    public void addLibrarian(Librarian librarian) throws LibraryException {
        if (librarian == null) {
            throw new LibraryException("Библиотекарь не может быть null");
        }
        if (librarians.contains(librarian)) {
            throw new LibraryException("Библиотекарь '" + librarian.getName() + "' уже работает в библиотеке");
        }
        librarians.add(librarian);
    }

    public List<Book> findBooksByAuthor(String author) throws LibraryException {
        if (author == null || author.trim().isEmpty()) {
            throw new LibraryException("Имя автора не может быть пустым");
        }

        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }

        if (result.isEmpty()) {
            throw new BookNotFoundException("Книги автора '" + author + "' не найдены");
        }
        return result;
    }

    public List<Book> findBooksByTitle(String title) throws LibraryException {
        if (title == null || title.trim().isEmpty()) {
            throw new LibraryException("Название книги не может быть пустым");
        }

        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                result.add(book);
            }
        }

        if (result.isEmpty()) {
            throw new BookNotFoundException("Книги с названием '" + title + "' не найдены");
        }
        return result;
    }

    public Book findBookByExactTitle(String title) throws LibraryException {
        if (title == null || title.trim().isEmpty()) {
            throw new LibraryException("Название книги не может быть пустым");
        }

        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        throw new BookNotFoundException("Книга с точным названием '" + title + "' не найдена");
    }

    public void displayAllBooks() {
        System.out.println("=== ВСЕ КНИГИ В БИБЛИОТЕКЕ ===");
        if (books.isEmpty()) {
            System.out.println("В библиотеке нет книг");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void displayAllReaders() {
        System.out.println("=== ВСЕ ЧИТАТЕЛИ ===");
        if (readers.isEmpty()) {
            System.out.println("В библиотеке нет читателей");
            return;
        }
        for (Reader reader : readers) {
            reader.displayInfo();
        }
    }

    public void displayAllLibrarians() {
        System.out.println("=== ВСЕ БИБЛИОТЕКАРИ ===");
        if (librarians.isEmpty()) {
            System.out.println("В библиотеке нет библиотекарей");
            return;
        }
        for (Librarian librarian : librarians) {
            librarian.displayInfo();
        }
    }

    @Override
    public String toString() {
        return "Библиотека: " + name +
                ", Книг: " + books.size() +
                ", Читателей: " + readers.size() +
                ", Библиотекарей: " + librarians.size();
    }
}