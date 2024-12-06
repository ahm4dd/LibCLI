import java.sql.PreparedStatement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
public class TransactionDataAO {

    public void addTransaction(int userId, int bookId,int cost, Date checkoutDate) throws SQLException {
        String query="insert into transactions (user_id, book_id, cost, date) VALUES (?,?,?,?)";
        PreparedStatement stmt=DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,userId);
        stmt.setInt(2,bookId);
        stmt.setInt(3,cost);
        stmt.setDate(4,checkoutDate);
        int resultSet=stmt.executeUpdate();
        if(resultSet!=0)
            System.out.println("Transaction added successfully!");
    }


    public void updateTransactionCost(int transactionId,int newCost) throws SQLException {
        String query="update transactions set cost=? where transaction_id=?";
        PreparedStatement stmt=DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,newCost);
        stmt.setInt(2,transactionId);
        int resultset=stmt.executeUpdate();
        if(resultset!=0)
            System.out.println("Transaction cost updated successfully!");
    }

    public void updateTransactionDate(int transactionId,Date newDate) throws SQLException {
        String query="update transactions set date=? where transaction_id=?";
        PreparedStatement stmt=DBconnector.conn.prepareStatement(query);
        stmt.setDate(1,newDate);
        stmt.setInt(2,transactionId);
        int resultset=stmt.executeUpdate();
        if(resultset!=0)
            System.out.println("Transaction date updated successfully!");
    }

    public void updateTransactionBookId(int transactionId,int newBookId) throws SQLException {
        String query="update transactions set book_id=? where transaction_id=?";
        PreparedStatement stmt=DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,newBookId);
        stmt.setInt(2,transactionId);
        int resultset=stmt.executeUpdate();
        if(resultset!=0)
            System.out.println("Transaction book id updated successfully!");
    }

    public void updateTransactionUserId(int transactionId,int newUserId) throws SQLException {
        String query="update transactions set user_id=? where transaction_id=?";
        PreparedStatement stmt=DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,newUserId);
        stmt.setInt(2,transactionId);
        int resultset=stmt.executeUpdate();
        if(resultset!=0)
            System.out.println("Transaction user id updated successfully!");
    }

    public void deleteTransactionByUserId(int userId) throws SQLException{
        String query="delete from transactions where user_id=?";
        PreparedStatement stmt=DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,userId);
        int resultset=stmt.executeUpdate();
        if(resultset!=0)
            System.out.println("Transaction deleted successfully!");
    }

    public void deleteTransactionByBookId(int bookId) throws SQLException{
        String query="delete from transactions where book_id=?";
        PreparedStatement stmt=DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,bookId);
        int resultset=stmt.executeUpdate();
        if(resultset!=0)
            System.out.println("Transaction deleted successfully!");
    }

    public void deleteTransactionById(int transactionId) throws SQLException{
        String query="delete from transactions where transaction_id=?";
        PreparedStatement stmt=DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,transactionId);
        int resultset=stmt.executeUpdate();
        if(resultset!=0)
            System.out.println("Transaction deleted successfully!");
    }

    public List<Transaction> getTransactionByUserId(int userId) throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String query="select * from transactions where user_id=?";
        PreparedStatement stmt=DBconnector.conn.prepareStatement(query);

        ResultSet rs=stmt.executeQuery();
        while(rs.next()){
            int transactionID=rs.getInt("transaction_id");
            int bookId=rs.getInt("book_id");
            int cost=rs.getInt("cost");
            Date checkoutDate=rs.getDate("date");
            Transaction transaction=new Transaction(transactionID,userId,bookId,checkoutDate,cost);
            transactions.add(transaction);

        }
        return transactions;
    }

    public Transaction getTransactionById(int transactionId) throws SQLException {
        String query="select * from transactions where transaction_id=?";
        PreparedStatement stmt=DBconnector.conn.prepareStatement(query);

        ResultSet rs=stmt.executeQuery();
        while(rs.next()){
            int userId=rs.getInt("user_id");
            int bookId=rs.getInt("book_id");
            int cost=rs.getInt("cost");
            Date checkoutDate=rs.getDate("date");
            Transaction transaction=new Transaction(transactionId,userId,bookId,checkoutDate,cost);

            return transaction;
        }
        return null;
    }

    public List<Transaction> getAllTransactions() throws SQLException {
        List<Transaction> transaction=new ArrayList<Transaction>();
        String query="select * from transactions";
        PreparedStatement stmt=DBconnector.conn.prepareStatement(query);
        ResultSet rs=stmt.executeQuery();
        while(rs.next()){
            int transactionId=rs.getInt("transaction_id");
            int userId=rs.getInt("user_id");
            int bookId=rs.getInt("book_id");
            int cost=rs.getInt("cost");
            Date checkoutDate=rs.getDate("date");
            Transaction transaction1=new Transaction(transactionId,userId,bookId,checkoutDate,cost);
            transaction.add(transaction1);
        }

        return transaction;
    }
    public int getAllSalesByBookId(int bookId) throws SQLException {
        String query="select sum(cost) as sumCost from transactions where book_id=?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,bookId);
        ResultSet rs=stmt.executeQuery();
        int cost=0;
        if(rs.next()){
            cost=rs.getInt("sumCost");
        }
        return cost;
    }
    public int getAllRevenue() throws SQLException {
        String query="select sum(cost) as sumCost from transactions";
        PreparedStatement stmt=DBconnector.conn.prepareStatement(query);
        ResultSet rs= stmt.executeQuery();
        int totalRevenue=0;
        while(rs.next()){
            totalRevenue+=rs.getInt("sumCost");
        }
        return totalRevenue;
    }
}