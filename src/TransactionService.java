import java.sql.*;
import java.util.Date;
import java.util.List;

public class TransactionService {

    public void checkoutBook(int userId, int bookId, Date checkoutDate) {

    }

    public void returnBook(int userId, int bookId, Date returnDate) {

    }

    public void updateTransaction(int transactionId) {

    }

    public void deleteTransaction(int transactionId) {

    }

    public List<Transaction> getTransactionByBookId(int bookId) {
        return null;
    }

    public List<Transaction> getTransactionByUserId(int userId) {
        return null;
    }

    public Transaction getTransactionById(int transactionId)
    {
        return null;
    }

    public boolean isBookAvailable(int bookId) throws SQLException {
        BookService bookService = new BookService();
        return bookService.checkIfBookIsAvailable(bookId);
    }

    public void updateAvailableBooksAfterCheckout(int bookId) throws SQLException {
        BookService bookService = new BookService();
        Book book = bookService.getBookById(bookId);
        bookService.updateAvailableCopies(bookId, book.getAvailableBooks()-1);
    }

    public void updateAvailableBooksAfterReturn(int bookId) {

    }

    public List<Book> getBorrowedBooksByUserId(int userId) {
        return null;
    }

}
