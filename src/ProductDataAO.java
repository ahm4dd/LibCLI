import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDataAO {

    // Add a new product (Book or Magazine)
    public void addProduct(String type, String title, int categoryId, int authorId, int price, int availableCopies, String isbn) throws SQLException {
        String query = "INSERT INTO products (type, title, category_id, author_id, price, available_copies, isbn) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setString(1, type);
        stmt.setString(2, title);
        stmt.setInt(3, categoryId);
        stmt.setInt(4, authorId);
        stmt.setInt(5, price);
        stmt.setInt(6, availableCopies);
        stmt.setString(7, isbn);  // Can be NULL for magazines
        int resultSet = stmt.executeUpdate();
        if (resultSet > 0) {
            System.out.println("Product added successfully!");
        } else {
            System.out.println("Failed to add product.");
        }
    }

    // Update product title by product_id
    public void updateProductTitle(int productId, String newTitle) throws SQLException {
        String query = "UPDATE products SET title = ? WHERE product_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setString(1, newTitle);
        stmt.setInt(2, productId);
        int resultSet = stmt.executeUpdate();
        if (resultSet > 0) {
            System.out.println("Product title updated successfully!");
        } else {
            System.out.println("Product update failed. Product not found.");
        }
    }

    // Update product price by product_id
    public void updateProductPrice(int productId, int newPrice) throws SQLException {
        String query = "UPDATE products SET price = ? WHERE product_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, newPrice);
        stmt.setInt(2, productId);
        int resultSet = stmt.executeUpdate();
        if (resultSet > 0) {
            System.out.println("Product price updated successfully!");
        } else {
            System.out.println("Product update failed. Product not found.");
        }
    }

    // Update available copies for a product
    public void updateProductAvailableCopies(int productId, int newAvailableCopies) throws SQLException {
        String query = "UPDATE products SET available_copies = ? WHERE product_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, newAvailableCopies);
        stmt.setInt(2, productId);
        int resultSet = stmt.executeUpdate();
        if (resultSet > 0) {
            System.out.println("Product available copies updated successfully!");
        } else {
            System.out.println("Product update failed. Product not found.");
        }
    }

    // Update ISBN (only for books)
    public void updateProductISBN(int productId, String newIsbn) throws SQLException {
        String query = "SELECT type FROM products WHERE product_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, productId);
        ResultSet resultSet = stmt.executeQuery();

        if (resultSet.next()) {
            String type = resultSet.getString("type");
            if ("Book".equalsIgnoreCase(type)) {
                String query2 = "UPDATE products SET isbn = ? WHERE product_id = ?";
                stmt = DBconnector.conn.prepareStatement(query2);
                stmt.setString(1, newIsbn);
                stmt.setInt(2, productId);
                int updateResult = stmt.executeUpdate();
                if (updateResult > 0) {
                    System.out.println("Product ISBN updated successfully!");
                } else {
                    System.out.println("Product update failed. Product not found.");
                }
            } else {
                System.out.println("ISBN can only be updated for Books.");
            }
        }
    }

    // Update product category by product_id
    public void updateProductCategory(int productId, int newCategoryId) throws SQLException {
        String query = "UPDATE products SET category_id = ? WHERE product_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, newCategoryId);
        stmt.setInt(2, productId);
        int resultSet = stmt.executeUpdate();
        if (resultSet > 0) {
            System.out.println("Product category updated successfully!");
        } else {
            System.out.println("Product update failed. Product not found.");
        }
    }

    // Update product author by product_id
    public void updateProductAuthor(int productId, int newAuthorId) throws SQLException {
        String query = "UPDATE products SET author_id = ? WHERE product_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, newAuthorId);
        stmt.setInt(2, productId);
        int resultSet = stmt.executeUpdate();
        if (resultSet > 0) {
            System.out.println("Product author updated successfully!");
        } else {
            System.out.println("Product update failed. Product not found.");
        }
    }

    // Delete product by product_id
    public void deleteProductById(int productId) throws SQLException {
        String query = "DELETE FROM products WHERE product_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, productId);
        int resultSet = stmt.executeUpdate();
        if (resultSet > 0) {
            System.out.println("Product deleted successfully!");
        } else {
            System.out.println("Delete failed. Product not found.");
        }
    }

    // Delete all products
    public void deleteAllProducts() throws SQLException {
        String query = "DELETE FROM products";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        int resultSet = stmt.executeUpdate();
        if (resultSet > 0) {
            System.out.println("All products deleted successfully!");
        } else {
            System.out.println("Delete failed. No products found.");
        }
    }

    // Delete all products by type
    public void deleteProductsByType(String type) throws SQLException {
        String query = "DELETE FROM products WHERE type = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setString(1, type);
        int resultSet = stmt.executeUpdate();
        if (resultSet > 0) {
            System.out.println("Products of the type deleted successfully!");
        } else {
            System.out.println("Delete failed. No products found for the type.");
        }
    }


    // Delete all products by category_id
    public void deleteProductsByCategoryId(int categoryId) throws SQLException {
        String query = "DELETE FROM products WHERE category_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, categoryId);
        int resultSet = stmt.executeUpdate();
        if (resultSet > 0) {
            System.out.println("Products in the category deleted successfully!");
        } else {
            System.out.println("Delete failed. No products found for the category.");
        }
    }

    // Delete all products by author_id
    public void deleteProductsByAuthorId(int authorId) throws SQLException {
        String query = "DELETE FROM products WHERE author_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, authorId);
        int resultSet = stmt.executeUpdate();
        if (resultSet > 0) {
            System.out.println("Products by the author deleted successfully!");
        } else {
            System.out.println("Delete failed. No products found for the author.");
        }
    }

    // Retrieve product by product_id
    public Product getProductById(int productId) throws SQLException {
        String query = "SELECT * FROM products WHERE product_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, productId);
        ResultSet resultSet = stmt.executeQuery();
        if (resultSet.next()) {
            return createProductFromResultSet(resultSet);
        }
        return null;
    }

    // Retrieve products by title (partial match)
    public List<Product> getProductsByTitle(String title) throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE title LIKE ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setString(1, "%" + title + "%");
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            products.add(createProductFromResultSet(resultSet));
        }
        return products;
    }

    // Retrieve products by category_id
    public List<Product> getProductsByCategoryId(int categoryId) throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE category_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, categoryId);
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            products.add(createProductFromResultSet(resultSet));
        }
        return products;
    }

    // Retrieve products by author_id
    public List<Product> getProductsByAuthorId(int authorId) throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE author_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, authorId);
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            products.add(createProductFromResultSet(resultSet));
        }
        return products;
    }

    // Retrieve products by price range
    public List<Product> getProductsByPriceRange(int minPrice, int maxPrice) throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE price BETWEEN ? AND ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, minPrice);
        stmt.setInt(2, maxPrice);
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            products.add(createProductFromResultSet(resultSet));
        }
        return products;
    }

    // Retrieve products by available copies
    public List<Product> getProductsByAvailableCopies(int minCopies) throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE available_copies >= ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, minCopies);
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            products.add(createProductFromResultSet(resultSet));
        }
        return products;
    }

    // Retrieve products by ISBN
    public Product getProductsByISBN(String isbn) throws SQLException {
        String query = "SELECT * FROM products WHERE isbn = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setString(1, isbn);
        ResultSet resultSet = stmt.executeQuery();
        if (resultSet.next()) {
            return createProductFromResultSet(resultSet);
        }
        return null;
    }

    // Retrieve all products
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            products.add(createProductFromResultSet(resultSet));
        }
        return products;
    }

    // Retrieve products by type
    public List<Product> getProductsByType(String type) throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE type = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setString(1, type);
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            products.add(createProductFromResultSet(resultSet));
        }
        return products;
    }

    // Helper method to create a Product object from ResultSet
    private Product createProductFromResultSet(ResultSet resultSet) throws SQLException {
        int productId = resultSet.getInt("product_id");
        String type = resultSet.getString("type");
        String title = resultSet.getString("title");
        int categoryId = resultSet.getInt("category_id");
        int authorId = resultSet.getInt("author_id");
        int price = resultSet.getInt("price");
        int availableCopies = resultSet.getInt("available_copies");
        String isbn = resultSet.getString("isbn");

        return new Product(productId, type, title, categoryId, authorId, price, availableCopies, isbn);
    }
}