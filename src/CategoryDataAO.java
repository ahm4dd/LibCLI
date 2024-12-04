import java.sql.*;
import java.util.List;
import java.util.ArrayList;
public class CategoryDataAO {

    public void addCategory(String name,String description) throws SQLException {
        String query = "insert into categories (name, description) VALUES (?, ?)";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setString(1, name);
        stmt.setString(2, description);
        int resultSet = stmt.executeUpdate();
    }

    public void deleteCategory(int categoryId) throws SQLException {
        String query = "delete from categories where category_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, categoryId);
        int resultSet = stmt.executeUpdate();
    }

    public void updateCategoryName(int categoryId, String newName) throws SQLException {
        String query = "update categories set name = ? where category_id = " + categoryId;
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setString(1, newName);
        int resultSet = stmt.executeUpdate();
    }

    public void updateCategoryDescription(int categoryId, String newDescription) throws SQLException {
        String query = "update categories set description = ? where category_id = " + categoryId;
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setString(1, newDescription);
        int resultSet = stmt.executeUpdate();
    }

    public Category getCategory(int categoryId) throws SQLException {
        String query = "select * from categories where category_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, categoryId);
        ResultSet resultSet = stmt.executeQuery();
        Category category = new Category();
        if(resultSet.next()) {
            category.setCategory_id(resultSet.getInt("category_id"));
            category.setName(resultSet.getString("name"));
            category.setDescription(resultSet.getString("description"));
        }
        return category;
    }

    public List<Category> getAllCategories() throws SQLException {
        List<Category> categories = new ArrayList<Category>();
        String query = "select * from categories";
        Statement stmt = DBconnector.conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        while(resultSet.next()) {
            int categoryId = resultSet.getInt("category_id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            Category category = new Category(categoryId, name, description);
            categories.add(category);
        }
        return categories;
    }

    public List<Category> searchCategories(String keyword) throws SQLException {
        List<Category> categories = new ArrayList<Category>();
        String query = "select * from categories where name like \"%"+keyword+"%\"";
        Statement stmt = DBconnector.conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        while(resultSet.next()) {
            int categoryId = resultSet.getInt("category_id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            Category category = new Category(categoryId, name, description);
            categories.add(category);
        }
        return categories;
    }
}
