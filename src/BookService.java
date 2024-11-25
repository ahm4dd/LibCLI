import java.sql.SQLException;
import java.util.List;

public class BookService {
    private BookDataAO bookDataAO = new BookDataAO();

    public void addBook(String title, String author, String isbn, int availableCopies) throws SQLException {
        bookDataAO.addBook(title,author,isbn,availableCopies);
    }

    public void updateBookTitle(int bookId, String newName) throws SQLException {
        bookDataAO.updateBookTitle(bookId, newName);
    }

    public void updateBookAuthor(int bookId, String newAuthor) throws SQLException {
        bookDataAO.updateBookAuthor(bookId, newAuthor);
    }

    public void updateBookIsbn(int bookId, String newIsbn) throws SQLException {
        bookDataAO.updateBookIsbn(bookId, newIsbn);
    }

    public void deleteBook(int bookInt) throws SQLException {
        bookDataAO.deleteBook(bookInt);
    }

    public Book getBookById(int bookId) throws SQLException {
        return bookDataAO.getBookById(bookId);
    }

    public List<Book> getAllBooks() throws SQLException {
        return bookDataAO.getAllBooks();
    }

    public List<Book> searchBooks(String keyword) throws SQLException {
        return bookDataAO.searchBooks(keyword);
    }

    public Book searchBooksByIsbn(String isbn) throws SQLException {
        return bookDataAO.searchBooksByIsbn(isbn);
    }

    public boolean checkIfBookIsAvailable(int bookId) throws SQLException {
        return bookDataAO.checkIfBookIsAvailable(bookId);
    }

    public void updateAvailableCopies(int bookId, int newCopies) throws SQLException {
        bookDataAO.updateAvailableCopies(bookId, newCopies);
    }
}
