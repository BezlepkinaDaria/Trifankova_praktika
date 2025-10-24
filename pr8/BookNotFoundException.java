package pr8;

public class BookNotFoundException extends LibraryException {
    public BookNotFoundException(String message) {
        super(message);
    }
}