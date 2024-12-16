import java.sql.PreparedStatement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
public class TransactionDataAO {
    public  java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
    public void addTransaction(int userId, int bookId,int cost) throws SQLException {
        String query="insert into transactions (user_id, book_id, cost,date) VALUES (?,?,?,?)";
        PreparedStatement stmt=DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,userId);
        stmt.setInt(2,bookId);
        stmt.setInt(3,cost);
        stmt.setDate(4,currentDate);
        int resultSet=stmt.executeUpdate();
        if(resultSet!=0)
            System.out.println("Transaction added successfully!");
        else
            System.out.println("Transaction already exists!");
    }

    public void updateTransactioCost(int transactionId,int cost) throws SQLException{
        String query="update transactions set cost=? where transaction_id=?";
        PreparedStatement stmt=DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,cost);
        stmt.setInt(2,transactionId);
        int resultSet=stmt.executeUpdate();
        if(resultSet!=0)
            System.out.println("Transaction cost updated successfully!");
        else
            System.out.println("Transaction doesn't exist!");
    }

    public void updateTransactionBookId(int transactionId,int bookId)throws SQLException{
        String query="update transactions set book_id=? where transaction_id=?";
        PreparedStatement stmt=DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,bookId);
        stmt.setInt(2,transactionId);
        int resultSet= stmt.executeUpdate();
        if(resultSet!=0)
            System.out.println("Transaction bookID updated successfully! ");
        else
            System.out.println("Transaction doesn't exist!");

    }

    public void updateTransactionUserId(int transactionId,int userId) throws SQLException{
        String query="update transactions set user_id =? where transaction_id=?";
        PreparedStatement stmt=DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,userId);
        stmt.setInt(2,transactionId);
        int resultset= stmt.executeUpdate();
        if(resultset!=0)
            System.out.println("Transaction userID updated successfully! ");
        else
            System.out.println("Transaction doesn't exist!");


    }

    public void deleteTransactionByUserid(int userId) throws SQLException{
        String query="delete from transaction where user_id=?";
        PreparedStatement stmt=DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,userId);
        int resultset=stmt.executeUpdate();
        if(resultset!=0){
            System.out.println("Transaction deleted succsefully!");

        }
        else
            System.out.println("Transaction doesn't exist!");

    }
    public void deleteTransactionByBookid(int bookId)throws SQLException{
        String query="delete from transactions where book_id=?";
        PreparedStatement stmt=DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,bookId);
        int resultset=stmt.executeUpdate();
        if(resultset!=0){
            System.out.println("Transaction deleted succsesfully! ");
        }
        else
            System.out.println("Transaction doesn't exist!");

    }


    public void deleteTransaction(int transactionId) throws SQLException{
        String query="delete from transactions where transaction_id=?";
        PreparedStatement stmt=DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,transactionId);
        int resultset=stmt.executeUpdate();
        if(resultset!=0)
            System.out.println("Transaction deleted successfully!");
        else
            System.out.println("Transaction doesn't exist!");
    }

    public void deleteTransactionByCost(int cost) throws SQLException{
        String query="delete from transactions where cost=?";
        PreparedStatement stmt=DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,cost);
        int resultset=stmt.executeUpdate();
        if(resultset!=0)
            System.out.println("Transaction deleted successfully!");
        else
            System.out.println("Transaction doesn't exist!");
    }


    public  List<Transaction> getTransactionByBookId(int bookId) throws SQLException {
        List<Transaction> transactions=new ArrayList<Transaction>();
        String query="select * from transactions where book_id=?";
        PreparedStatement stmt=DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,bookId);
        ResultSet rs=stmt.executeQuery();
        while(rs.next()){
            int transactionId=rs.getInt("transaction_id");
            int userId=rs.getInt("user_id");
            int cost=rs.getInt("cost");
            Date checkoutDate=rs.getDate("date");
            Transaction transaction=new Transaction(transactionId,userId,bookId,checkoutDate,cost);
            transactions.add(transaction);}
        return transactions;

    }
    public List<Transaction> getTransactionByUserId(int userId) throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String query="select * from transactions where user_id=?";
        PreparedStatement stmt=DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,userId);

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
    public List<Transaction> getTransactionByDate(java.sql.Date currentDate) throws SQLException {
        List<Transaction> transactions=new ArrayList<Transaction>();
        String query="select * from transactions where date=?";
        PreparedStatement stmt=DBconnector.conn.prepareStatement(query);
        stmt.setDate(1,currentDate);
        ResultSet rs=stmt.executeQuery();
        while(rs.next()){
            int transactionId=rs.getInt("transaction_id");
            int userId=rs.getInt("user_id");
            int bookId=rs.getInt("book_id");
            int cost=rs.getInt("cost");
            java.sql.Date transactionDate=rs.getDate("date");
            Transaction transaction=new Transaction(transactionId,userId,bookId,transactionDate,cost);
            transactions.add(transaction);
        }
        return transactions;
    }

    public Transaction getTransactionById(int transactionId) throws SQLException {
        String query="select * from transactions where transaction_id=?";
        PreparedStatement stmt=DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,transactionId);

        ResultSet rs=stmt.executeQuery();
        if(rs.next()){
            int userId=rs.getInt("user_id");
            int bookId=rs.getInt("book_id");
            int cost=rs.getInt("cost");
            java.sql.Date transactionDate=rs.getDate("date");
            Transaction transaction=new Transaction(transactionId,userId,bookId,transactionDate,cost);

            return transaction;

        }


        return null;
    }
    public List<Transaction> getTransactionByCost(int cost) throws SQLException {
        List<Transaction> transactions=new ArrayList<Transaction>();
        String query="select * from transactions where cost=?";
        PreparedStatement stmt=DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,cost);
        ResultSet rs=stmt.executeQuery();
        while (rs.next()) {
            int transactionId=rs.getInt("transaction_id");
            int userId=rs.getInt("user_id");
            int bookId=rs.getInt("book_id");
            java.sql.Date transactionDate=rs.getDate("date");
            Transaction transaction=new Transaction(transactionId,userId,bookId,transactionDate,cost);
            transactions.add(transaction);
        }
        return transactions;
    }

    public List<Transaction> getAllTransactions() throws SQLException {
        List<Transaction> transactions = new ArrayList<Transaction>();
        String query="select * from transactions";
        PreparedStatement stmt=DBconnector.conn.prepareStatement(query);
        ResultSet rs=stmt.executeQuery();
        while(rs.next()){
            int transactionId=rs.getInt("transaction_id");
            int userId=rs.getInt("user_id");
            int bookId=rs.getInt("book_id");
            int cost=rs.getInt("cost");
            java.sql.Date transactionDate=rs.getDate("date");
            Transaction transaction1=new Transaction(transactionId,userId,bookId,transactionDate,cost);
            transactions.add(transaction1);
        }

        return transactions;
    }
    public int getAllSalesBooks(int bookId) throws SQLException {
        String query="select sum(cost) from transactions where book_id=?";
        PreparedStatement stmt=DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,bookId);
        ResultSet rs=stmt.executeQuery();
        int cost=0;
        if(rs.next()){
            cost=rs.getInt("sum(cost)");

        }
        return cost;
    }
    public int getAllRevenue() throws SQLException {
        String query="select sum(cost) from transactions";
        PreparedStatement stmt=DBconnector.conn.prepareStatement(query);
        ResultSet rs= stmt.executeQuery();
        int totalRevenue=0;
        while(rs.next()){
            totalRevenue+=rs.getInt("cost");
        }

        return totalRevenue;
    }


}