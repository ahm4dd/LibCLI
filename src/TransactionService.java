import java.sql.*;
import java.util.List;

public class TransactionService {
    private TransactionDataAO tranDAO=new TransactionDataAO();
    private UserService userService =new UserService();
    private BookService bookService=new BookService();
    public void addTransaction(int userId,int product_id)throws SQLException{
        String query = "Select type from products where product_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, product_id);
        ResultSet rs = stmt.executeQuery();
        String type = "";
        while (rs.next()) {
            type = rs.getString("type");
        }
        if(userService.getUserById(userId)==null)
            System.out.println("this User ID does not exist! ");
        if(!isProductAvailable(product_id))
            System.out.println("this product ID does not exist! ");
        else if(type.equalsIgnoreCase("Book")) {
            String query1 = "Select price from books where product_id = ?";
            PreparedStatement stmt1 = DBconnector.conn.prepareStatement(query1);
            stmt1.setInt(1, product_id);
            ResultSet rs1 = stmt1.executeQuery();
            int price = 0;
            while (rs1.next()) {
                price = rs1.getInt("price");
            }
            tranDAO.addTransaction(userId, product_id, price);
            this.updateAvailableBooksAfterCheckout(product_id);
        }
        else if(type.equalsIgnoreCase("Magazine")){
            String query1 = "Select price from magazines where product_id = ?";
            PreparedStatement stmt1 = DBconnector.conn.prepareStatement(query1);
            stmt1.setInt(1, product_id);
            ResultSet rs1 = stmt1.executeQuery();
            int price = 0;
            while (rs1.next()) {
                price = rs1.getInt("price");
            }
            tranDAO.addTransaction(userId, product_id, price);
            this.updateAvailableMagazinesAfterCheckout(product_id);
        }
    }
    public void updateTransactionUserId(int transactionId,int userId) throws SQLException{
        if (tranDAO.getTransactionById(transactionId)==null)
            System.out.println("this transaction does not exist! ");

        else if(userService.getUserById(userId)==null)
            System.out.println("this User ID does not exist! ");
        else
            tranDAO.updateTransactionUserId(transactionId,userId);
    }

    public void updateTransactionProductId(int transactionId,int product_id) throws SQLException{
        if (tranDAO.getTransactionById(transactionId)==null)
            System.out.println("this transaction does not exist! ");
        if(!isProductAvailable(product_id))
            System.out.println("this product ID does not exist! ");
        else
            tranDAO.updateTransactionProductId(transactionId,product_id);
    }

    public void updateTransactionCost(int transactionId,int cost) throws SQLException {
        if (tranDAO.getTransactionById(transactionId)==null)
            System.out.println("this transaction ID does not exist! ");
        else if (cost<0)
            System.out.println("Cost cannot be below 0 !");
        else
            tranDAO.updateTransactionCost(transactionId,cost);
    }

    public void deleteTransactionId(int transactionId) throws SQLException {
        if(tranDAO.getTransactionById(transactionId)==null)
            System.out.println("this transaction does not exist! ");
        else
            tranDAO.deleteTransaction(transactionId);
    }
    public void deleteTransactionByProductId(int product_id) throws SQLException{
        if(!isProductAvailable(product_id))
            System.out.println("this product ID does not exist! ");
        else
            tranDAO.deleteTransactionByProductId(product_id);

    }
    public void deleteTransactionByUserId(int userid) throws SQLException{
        if (getTransactionByUserId(userid)==null)
            System.out.println("this User ID does not exist! ");
        else
            tranDAO.deleteTransactionByUserid(userid);
    }
    public List<Transaction> getAllTransaction() throws SQLException {
        return tranDAO.getAllTransactions();
    }

    public List<Transaction> getTransactionByProductId(int product_id) throws SQLException {
        return tranDAO.getTransactionByProductId(product_id);
    }

    public List<Transaction> getTransactionByUserId(int userId) throws SQLException {
        return tranDAO.getTransactionByUserId(userId);
    }

    public Transaction getTransactionById(int transactionId) throws SQLException
    {
        return tranDAO.getTransactionById(transactionId);
    }

    public boolean isProductAvailable(int product_id) throws SQLException {
        String query = "Select * from products where product_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, product_id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            String type = rs.getString("type");
            return true;
        }
        return false;
    }

    public void updateAvailableBooksAfterCheckout(int product_id) throws SQLException {
        BookService bookService = new BookService();
        Book book = bookService.getBookById(product_id);
        bookService.updateAvailableCopies(product_id, book.getAvailableBooks()-1);
    }

    public void updateAvailableMagazinesAfterCheckout(int product_id) throws SQLException {
        MagazineService magazineService = new MagazineService();
        Magazine magazine = magazineService.getMagazineById(product_id);
        magazineService.updateAvailableCopies(product_id, magazine.getAvailableCopies()-1);
    }

    public int getAllSalesProduct(int product_id) throws SQLException {
        return tranDAO.getProfitForProduct(product_id);
    }

    public int getAllRevenue() throws SQLException {
        return tranDAO.getAllRevenue();
    }
}