import java.sql.*;
import java.util.List;

public class TransactionService {
    private TransactionDataAO transactionDataAO=new TransactionDataAO();

    public void addTransaction(int userId, int bookId,int cost, Date checkoutDate) throws SQLException {
        transactionDataAO.addTransaction(userId,bookId,cost, checkoutDate);
    }
    public void updateTransactionCost(int transactionId,int newCost) throws SQLException {
        transactionDataAO.updateTransactionCost(transactionId,newCost);
    }
    public void updateTransactionDate(int transactionId,Date newDate) throws SQLException {
        transactionDataAO.updateTransactionDate(transactionId,newDate);
    }
    public void updateTransactionBookId(int transactionId,int newBookId) throws SQLException {
        transactionDataAO.updateTransactionBookId(transactionId,newBookId);
    }
    public void updateTransactionUserId(int transactionId,int newUserId) throws SQLException {
        transactionDataAO.updateTransactionUserId(transactionId,newUserId);
    }
    public void deleteTransactionByUserId(int userId) throws SQLException{
        transactionDataAO.deleteTransactionByUserId(userId);
    }
    public void deleteTransactionByBookId(int bookId) throws SQLException{
        transactionDataAO.deleteTransactionByBookId(bookId);
    }
    public void deleteTransactionById(int transactionId) throws SQLException{
        transactionDataAO.deleteTransactionById(transactionId);
    }

    public List<Transaction> getTransactionByUserId(int userId) throws SQLException {
        return transactionDataAO.getTransactionByUserId(userId);
    }

    public Transaction getTransactionById(int transactionId) throws SQLException {
        return transactionDataAO.getTransactionById(transactionId);
    }

    public List<Transaction> getTransactionByBookId(int bookId) throws SQLException {
        return transactionDataAO.getTransactionByBookId(bookId);
    }

    public List<Transaction> getAllTransactions() throws SQLException {
        return transactionDataAO.getAllTransactions();
    }
    public int getAllSalesByBookId(int bookId) throws SQLException {
        return transactionDataAO.getAllSalesByBookId(bookId);
    }
    public int getAllRevenue() throws SQLException {
        return transactionDataAO.getAllRevenue();
    }

    public boolean checkIfTransactionExists(int transactionId) throws SQLException {
        if(transactionDataAO.getTransactionById(transactionId) != null)
            return true;
        else
            return false;
    }
}