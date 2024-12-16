import java.sql.*;
import java.sql.Date;
import java.util.List;

public class TransactionService {
    private TransactionDataAO tranDAO=new TransactionDataAO();
    private UserService userService =new UserService();
    private BookService bookService=new BookService();
    public void addTransaction(int userId,int bookId)throws SQLException{
        String query = "Select price from books where book_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, bookId);
        ResultSet rs = stmt.executeQuery();
        int price = 0;
        while (rs.next()) {
            price = rs.getInt("price");
        }
        if(userService.getUserById(userId)==null)
            System.out.println("this User ID does not exist! ");
        else if(bookService.getBookById(bookId)==null)
            System.out.println("this Book ID does not exist! ");
        else
            tranDAO.addTransaction(userId,bookId,price);
    }
    public void updateTransactionUserId(int transactionId,int userId) throws SQLException{
        if (getTransactionById(transactionId)==null)
            System.out.println("this transaction does not exist! ");

        else if(userService.getUserById(userId)==null)
            System.out.println("this User ID does not exist! ");
        else
            tranDAO.updateTransactionUserId(transactionId,userId);
    }

    public void updateTransactionBookId(int transactionId,int bookId) throws SQLException{
        if (getTransactionById(transactionId)==null)
            System.out.println("this transaction does not exist! ");
        else if(bookService.getBookById(bookId)==null)
            System.out.println("this Book ID does not exist! ");
        else
            tranDAO.updateTransactionBookId(transactionId,bookId);
    }


    public void updateTransactionCost(int transactionId,int cost) throws SQLException {
        if (getTransactionById(transactionId)==null)
            System.out.println("this transaction ID does not exist! ");
        else if (cost<0)
            System.out.println("Cost cannot be below 0 !");
        else
            tranDAO.updateTransactioCost(transactionId,cost);
    }

    public void deleteTransactionId(int transactionId) throws SQLException {
        if(getTransactionById(transactionId)==null)
            System.out.println("this transaction does not exist! ");
        else
            tranDAO.deleteTransaction(transactionId);
    }
    public void deleteTransactionByBookId(int bookId) throws SQLException{
        if (getTransactionByBookId(bookId)==null)
            System.out.println("this Book ID does not exist! ");
        else
            tranDAO.deleteTransactionByBookid(bookId);

    }
    public void deleteTransactionByUserId(int userid) throws SQLException{
        if (getTransactionByUserId(userid)==null)
            System.out.println("this User ID does not exist! ");
        else
            tranDAO.deleteTransactionByUserid(userid);


    }
    public void deleteTransactionByCost(int cost) throws SQLException{
        if (cost<0)
            System.out.println("cost cannot be below 0 !");
        else
            tranDAO.deleteTransactionByCost(cost);

    }
    public List<Transaction> getAllTransaction() throws SQLException {
        return tranDAO.getAllTransactions();
    }

    public List<Transaction> getTransactionByBookId(int bookId) throws SQLException {
        return tranDAO.getTransactionByBookId(bookId);
    }

    public List<Transaction> getTransactionByUserId(int userId) throws SQLException {
        return tranDAO.getTransactionByUserId(userId);
    }

    public Transaction getTransactionById(int transactionId) throws SQLException
    {
        return tranDAO.getTransactionById(transactionId);
    }
    public Transaction getAllSales(){

        return null;
    }
    public List<Transaction> getTransactionByCost(int cost) throws SQLException {

        return tranDAO.getTransactionByCost(cost);
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
}