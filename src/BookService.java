import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class BookService {
    private BookDataAO bookDataAO = new BookDataAO();

    public void addBook(String title, int authorId, int categoryId,String isbn, int availableCopies) throws SQLException {
        if (availableCopies < 0)
            System.out.println("Copies cannot be below 0.");
        else if (searchBooksByIsbn(isbn) != null)
            System.out.println("ISBN already exists");
        // Needs more logic to check if author exists and if the category id is right.
        else
            bookDataAO.addBook(title,authorId,categoryId,isbn,availableCopies);
    }

    public void updateBookTitle(int bookId, String newName) throws SQLException {
        if (!checkIfBookIsAvailable(bookId))
            System.out.println("Book doesn't exist");
        else
            bookDataAO.updateBookTitle(bookId, newName);
    }

    public void updateBookAuthor(int bookId, int newAuthorId) throws SQLException {
        if (!checkIfBookIsAvailable(bookId))
            System.out.println("Book doesn't exist");
        else
            bookDataAO.updateBookAuthor(bookId, newAuthorId);
    }

    public void updateBookIsbn(int bookId, String newIsbn) throws SQLException {
        if (!checkIfBookIsAvailable(bookId))
            System.out.println("Book doesn't exist");
        else if (searchBooksByIsbn(newIsbn) != null)
            System.out.println("The new ISBN already exists");
        else
            bookDataAO.updateBookIsbn(bookId, newIsbn);
    }

    public void updateBookCategory(int bookId, int newCategoryId) throws SQLException{
        if (!checkIfBookIsAvailable(bookId))
            System.out.println("Book doesn't exist");
        else
            bookDataAO.updateBookCategory(bookId, newCategoryId);
    }

    public void deleteBook(int bookId) throws SQLException {
        if (!checkIfBookIsAvailable(bookId))
            System.out.println("Book doesn't exist");
        else
            bookDataAO.deleteBook(bookId);
    }

    public Author getBookAuthor(int bookId) throws SQLException {
        if (!checkIfBookIsAvailable(bookId)) {
            System.out.println("Book doesn't exist");
            return null;
        }
        else
            return bookDataAO.getBookAuthor(bookId);
    }
    public Book getBookById(int bookId) throws SQLException {
        if (!checkIfBookIsAvailable(bookId)) {
            System.out.println("Book doesn't exist");
            return null;
        }
        else
            return bookDataAO.getBookById(bookId);
    }

    public List<Book> getAllBooks() throws SQLException {
        return bookDataAO.getAllBooks();
    }

    public List<Book> searchBooks(String keyword) throws SQLException {
        return bookDataAO.searchBooks(keyword);
    }

    public Book searchBooksByIsbn(String isbn) throws SQLException {
        if (bookDataAO.searchBooksByIsbn(isbn) == null) {
            System.out.println("Book doesn't exist");
            return null;
        }
        else
            return bookDataAO.searchBooksByIsbn(isbn);
    }

    public List<Book> searchBookByCategory(int categoryId) throws SQLException{
        // Needs more logic to check category I guess, maybe not, who knows
        return bookDataAO.searchBooksByCategory(categoryId);
    }

    public boolean checkIfBookIsAvailable(int bookId) throws SQLException {
        return bookDataAO.checkIfBookIsAvailable(bookId);
    }

    public void updateAvailableCopies(int bookId, int newCopies) throws SQLException {
        bookDataAO.updateAvailableCopies(bookId, newCopies);
    }
}
