import java.util.*;

class Book<T> {
    private String title;
    private String author;
    private T year;

    public Book(String title, String author, T year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public T getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book<?> book = (Book<?>) o;
        return Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(year, book.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, year);
    }
}

public class LibraryCatalog {
    private Map<String, Book<Integer>> library = new HashMap<>();

    public void addBook(String isbn, Book<Integer> book) {
        library.put(isbn, book);
    }

    public List<Book<Integer>> findBooksByAuthor(String authorName) {
        List<Book<Integer>> foundBooks = new ArrayList<>();
        for (Book<Integer> book : library.values()) {
            if (book.getAuthor().equalsIgnoreCase(authorName)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public List<Book<Integer>> findAndSortBooksByAuthor(String authorName, Comparator<Book<Integer>> comparator) {
        List<Book<Integer>> foundBooks = findBooksByAuthor(authorName);
        Collections.sort(foundBooks, comparator);
        return foundBooks;
    }

    public static void main(String[] args) {
        LibraryCatalog catalog = new LibraryCatalog();

        catalog.addBook("978-0743273565", new Book<>("The Great Gatsby", "F. Scott Fitzgerald", 1925));
        catalog.addBook("978-0061120084", new Book<>("To Kill a Mockingbird", "Harper Lee", 1960));
        catalog.addBook("978-0393334631", new Book<>("1984", "George Orwell", 1949));
        catalog.addBook("978-0451524935", new Book<>("Brave New World", "Aldous Huxley", 1932));
        catalog.addBook("978-0307593313", new Book<>("The Catcher in the Rye", "J.D. Salinger", 1951));
        catalog.addBook("978-0743273566", new Book<>("Tender Is the Night", "F. Scott Fitzgerald", 1934));

        String searchAuthor = "F. Scott Fitzgerald";
        System.out.println("--- Поиск книг автора: " + searchAuthor + " ---");
        List<Book<Integer>> booksByFitzgerald = catalog.findBooksByAuthor(searchAuthor);

        if (booksByFitzgerald.isEmpty()) {
            System.out.println("Книги автора \"" + searchAuthor + "\" не найдены.");
        } else {
            System.out.println("Найденные книги:");
            for (Book<Integer> book : booksByFitzgerald) {
                System.out.println(book);
            }
        }

        System.out.println("\n--- Поиск и сортировка книг автора: " + searchAuthor + " (по году) ---");

        Comparator<Book<Integer>> yearComparator = Comparator.comparing(Book::getYear);

        List<Book<Integer>> sortedBooksByFitzgerald = catalog.findAndSortBooksByAuthor(searchAuthor, yearComparator);

        if (sortedBooksByFitzgerald.isEmpty()) {
            System.out.println("Книги автора \"" + searchAuthor + "\" не найдены.");
        } else {
            System.out.println("Найденные и отсортированные книги (по году):");
            for (Book<Integer> book : sortedBooksByFitzgerald) {
                System.out.println(book);
            }
        }

        System.out.println("\n--- Поиск и сортировка книг автора: " + searchAuthor + " (по названию) ---");

        Comparator<Book<Integer>> titleComparator = Comparator.comparing(Book::getTitle);

        List<Book<Integer>> sortedBooksByFitzgeraldByTitle = catalog.findAndSortBooksByAuthor(searchAuthor, titleComparator);

        if (sortedBooksByFitzgeraldByTitle.isEmpty()) {
            System.out.println("Книги автора \"" + searchAuthor + "\" не найдены.");
        } else {
            System.out.println("Найденные и отсортированные книги (по названию):");
            for (Book<Integer> book : sortedBooksByFitzgeraldByTitle) {
                System.out.println(book);
            }
        }
    }
}