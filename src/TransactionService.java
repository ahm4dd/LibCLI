import java.util.List;

public class TransactionService {

    public void checkoutBook(Transaction transaction) {

    }

    public void returnBook(Transaction transaction) {

    }

    public void updateTransaction(Transaction transaction) {

    }

    public void deleteTransaction(Transaction transaction) {

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

    public boolean isBookAvailable(int bookId) {
        return false;
    }

    public void updateAvailableBooksAfterCheckout(int bookId) {

    }

    public void updateAvailableBooksAfterReturn(int bookId) {

    }

    public List<Book> getBorrowedBooksByUserId(int userId) {
        return null;
    }

}
