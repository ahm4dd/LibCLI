import java.sql.SQLException;
import java.util.List;

public class BookService {
    private BookDataAO bookDataAO = new BookDataAO();

    public void addBook(String title, String author, String isbn, int availableCopies) throws SQLException {
        bookDataAO.addBook(title,author,isbn,availableCopies);
    }

    public void updateBookTitle(int bookId, String newName) {

    }

    public void updateBookAuthor(int bookId, String newAuthor) {

    }

    public void updateBookIsbn(int bookId, String newIsbn) {

    }

    public void updateBookAvailableCopies(int bookId, int availableCopies) {

    }

    public void deleteBook(int bookInt) throws SQLException {
        bookDataAO.deleteBook(bookInt);
    }

    public Book getBookById(int bookId) {
        return null;
    }

    public List<Book> getAllBooks() {
        return null;
    }

    public List<Book> searchBooks(String keyword) {
        return null;
    }

    public boolean checkIfBookIsAvailable(int bookId) {
        return false;
    }

    public void updateAvailableCopies(int bookId, int newCopies) {

    }
}
