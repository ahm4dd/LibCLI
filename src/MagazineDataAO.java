import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MagazineDataAO {

    public void addMagazine(String title, int authorId, int categoryId,int price, int availableCopies) throws SQLException {
        String productCreationQuery = "insert into products (type) VALUES (\"Magazine\")";
        Statement creationStmt = DBconnector.conn.createStatement();
        creationStmt.executeUpdate(productCreationQuery);
        ResultSet productResultSet = creationStmt.executeQuery("select product_id from products order by product_id desc limit 1");
        productResultSet.next();
        int productId = productResultSet.getInt(1);
        String query = "insert into magazines (title,author_id,category_id,available_copies,price,product_id) VALUES (?,?,?,?,?,?)";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setString(1, title);
        stmt.setInt(2, authorId);
        stmt.setInt(3, categoryId);
        stmt.setInt(4, availableCopies);
        stmt.setInt(5, price);
        stmt.setInt(6, productId);
        int resultSet = stmt.executeUpdate();
        if (resultSet != 0)
            System.out.println("Added book successfully!");

    }

    public void updateMagazineTitle(int magazineId, String title) throws SQLException {
        String query = "Update magazines set title = ? where magazine_id = " + magazineId;
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setString(1, title);
        int resultSet = stmt.executeUpdate();
        if (resultSet != 0)
            System.out.println("Magazine title updated successfully!");
    }

    public void updateMagazinePrice(int magazineId, int price) throws SQLException {
        String query = "Update magazines set price = ? where magazine_id = " + magazineId;
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, price);
        int resultSet = stmt.executeUpdate();
        if (resultSet != 0)
            System.out.println("Magazine price updated successfully!");
    }

    public void updateMagazineAuthor(int magazineId, int authorId) throws SQLException {
        String query = "Update magazines set author_id = ? where magazine_id = " + magazineId;
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, authorId);
        int resultSet = stmt.executeUpdate();
        if (resultSet != 0)
            System.out.println("Magazine author updated successfully!");
    }

    public void updateMagazineCategory(int magazineId, int categoryId) throws SQLException {
        String query = "Update magazines set category_id = ? where magazine_id = " + magazineId;
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, categoryId);
        int resultSet = stmt.executeUpdate();
        if (resultSet != 0)
            System.out.println("Magazine category updated successfully!");
    }

    public void updateAvailableCopies(int magazineId, int newCopies) throws SQLException {
        String query = "update magazines set available_copies = ? where magazine_id = " + magazineId;
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, newCopies);
        int resultSet = stmt.executeUpdate();
        if (resultSet != 0)
            System.out.println("Copies updated successfully!");
    }

    public void deleteMagazine(int magazineId) throws SQLException {
        String query = "delete from magazines where magazine_id = " + magazineId;
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        int resultSet = stmt.executeUpdate();
        if (resultSet != 0)
            System.out.println("Magazine deleted successfully!");
    }

    public void deleteAllMagazines() throws SQLException {
        String query = "delete from magazines";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        int resultSet = stmt.executeUpdate();
        if (resultSet != 0)
            System.out.println("All magazines deleted successfully!");
    }

    public Magazine getMagazineById(int magazineId) throws SQLException {
        String query = "select * from magazines where magazine_id = " + magazineId;
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        ResultSet resultSet = stmt.executeQuery();
        resultSet.next();
        int productId = resultSet.getInt("product_id");
        String title = resultSet.getString("title");
        int authorId = resultSet.getInt("author_id");
        int categoryId = resultSet.getInt("category_id");
        int price = resultSet.getInt("price");
        int availableCopies = resultSet.getInt("available_copies");
        return new Magazine(magazineId, productId, title, authorId, categoryId, price, availableCopies);
    }

    public List<Magazine> getAllMagazines() throws SQLException {
        List<Magazine> magazines = new ArrayList<Magazine>();
        String query = "select * from magazines";
        Statement stmt = DBconnector.conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        while (resultSet.next()) {
            int magazineId = resultSet.getInt("magazine_id");
            int productId = resultSet.getInt("product_id");
            String title = resultSet.getString("title");
            int authorId = resultSet.getInt("author_id");
            int categoryId = resultSet.getInt("category_id");
            int price = resultSet.getInt("price");
            int availableCopies = resultSet.getInt("available_copies");
            Magazine magazine = new Magazine(magazineId, productId, title, authorId, categoryId, price, availableCopies);
            magazines.add(magazine);
        }
        return magazines;
    }

    public List<Magazine> searchMagazines(String keyword) throws SQLException {
        List<Magazine> magazines = new ArrayList<Magazine>();
        String query = "select * from magazines where title like \"%" + keyword + "%\"";
        Statement stmt = DBconnector.conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        while (resultSet.next()) {
            int magazineId = resultSet.getInt("magazine_id");
            int productId = resultSet.getInt("product_id");
            String title = resultSet.getString("title");
            int authorId = resultSet.getInt("author_id");
            int categoryId = resultSet.getInt("category_id");
            int price = resultSet.getInt("price");
            int availableCopies = resultSet.getInt("available_copies");
            Magazine magazine = new Magazine(magazineId, productId, title, authorId, categoryId, price, availableCopies);
            magazines.add(magazine);
        }
        return magazines;
    }

    public Author getMagazineAuthor(int magazineId) throws SQLException {
        String query = "Select author.first_name, author.last_name, author.bio from magazines INNER JOIN author ON magazines.author_id = author.author_id AND magazines.magazine_id = "
            + magazineId;
        Statement stmt = DBconnector.conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        while (resultSet.next()) {
            int authorId = resultSet.getInt("magazines.author_id");
            String firstName = resultSet.getString("author.first_name");
            String lastName = resultSet.getString("author.last_name");
            String bio = resultSet.getString("author.bio");
            Author author = new Author(authorId,firstName, lastName, bio);
            return author;
        }
        return null;
    }

    public List<Magazine> searchMagazinesByCategory(int categoryId) throws SQLException {
        List<Magazine> magazines = new ArrayList<Magazine>();
        String query = "select * from magazines where category_id = " + categoryId;
        Statement stmt = DBconnector.conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        while (resultSet.next()) {
            int magazineId = resultSet.getInt("magazine_id");
            int productId = resultSet.getInt("product_id");
            String title = resultSet.getString("title");
            int authorId = resultSet.getInt("author_id");
            int price = resultSet.getInt("price");
            int availableCopies = resultSet.getInt("available_copies");
            Magazine magazine = new Magazine(magazineId, productId, title, authorId, categoryId, price, availableCopies);
            magazines.add(magazine);
        }
        return magazines;
    }

    public boolean checkIfMagazineExists(int magazineId) throws SQLException {
        String query = "select * from magazines where magazine_id = " + magazineId;
        Statement stmt = DBconnector.conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        return resultSet.next();
    }
}
