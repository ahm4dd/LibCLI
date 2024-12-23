import java.sql.*;
import java.util.List;

public class ProductService {

    private ProductDataAO productDataAO;

    public ProductService() {
        this.productDataAO = new ProductDataAO();
    }

    // Add a new product (Book or Magazine)
    public void addProduct(String type, String title, int categoryId, int authorId, int price, int availableCopies, String isbn) throws SQLException {
        if (type == null || title == null || categoryId <= 0 || authorId <= 0 || price < 0 || availableCopies < 0) {
            System.out.println("Invalid product data.");
            return;
        }

        if (type.equals("Book") && (isbn == null || isbn.isEmpty())) {
            System.out.println("ISBN is required for books.");
            return;
        }

        if (type.equals("Magazine") && isbn != null && !isbn.isEmpty()) {
            System.out.println("ISBN should not be provided for magazines.");
            return;
        }

        // Proceed to add product
        productDataAO.addProduct(type, title, categoryId, authorId, price, availableCopies, isbn);
    }

    // Update a product title
    public void updateProductTitle(int productId, String newTitle) throws SQLException {
        if (productId <= 0 || newTitle == null || newTitle.isEmpty()) {
            System.out.println("Invalid product ID or title.");
            return;
        }

        productDataAO.updateProductTitle(productId, newTitle);
    }

    // Delete a product
    public void deleteProductById(int productId) throws SQLException {
        if (productId <= 0) {
            System.out.println("Invalid product ID.");
            return;
        }

        productDataAO.deleteProductById(productId);
    }

    // Delete all products by category ID
    public void deleteProductsByCategoryId(int categoryId) throws SQLException {
        if (categoryId <= 0) {
            System.out.println("Invalid category ID.");
            return;
        }

        productDataAO.deleteProductsByCategoryId(categoryId);
    }

    // Delete all products by author ID
    public void deleteProductsByAuthorId(int authorId) throws SQLException {
        if (authorId <= 0) {
            System.out.println("Invalid author ID.");
            return;
        }

        productDataAO.deleteProductsByAuthorId(authorId);
    }

    // Delete all products
    public void deleteAllProducts() throws SQLException {
        productDataAO.deleteAllProducts();
    }

    // Delete all products by type
    public void deleteProductsByType(String type) throws SQLException {
        if (type == null || type.isEmpty()) {
            System.out.println("Type cannot be empty.");
            return;
        }

        productDataAO.deleteProductsByType(type);
    }

    // Search products by type (Book/Magazine)
    public List<Product> searchProductsByType(String type) throws SQLException {
        if (type == null || type.isEmpty()) {
            System.out.println("Type cannot be empty.");
            return null;
        }

        return productDataAO.getProductsByType(type);
    }

    // Search products by category ID
    public List<Product> searchProductsByCategory(int categoryId) throws SQLException {
        if (categoryId <= 0) {
            System.out.println("Invalid category ID.");
            return null;
        }

        return productDataAO.getProductsByCategoryId(categoryId);
    }

    // Search products by author ID
    public List<Product> searchProductsByAuthor(int authorId) throws SQLException {
        if (authorId <= 0) {
            System.out.println("Invalid author ID.");
            return null;
        }
        return productDataAO.getProductsByAuthorId(authorId);
    }

    // Search products by title (partial match)
    public List<Product> searchProductsByTitle(String title) throws SQLException {
        if (title == null || title.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return null;
        }

        return productDataAO.getProductsByTitle(title);
    }

    // Search products by price range
    public List<Product> searchProductsByPriceRange(int minPrice, int maxPrice) throws SQLException {
        if (minPrice < 0 || maxPrice < 0 || minPrice > maxPrice) {
            System.out.println("Invalid price range.");
            return null;
        }

        return productDataAO.getProductsByPriceRange(minPrice, maxPrice);
    }

    // Search products by available copies
    public List<Product> searchProductsByAvailableCopies(int minCopies) throws SQLException {
        if (minCopies < 0) {
            System.out.println("Invalid number of copies.");
            return null;
        }

        return productDataAO.getProductsByAvailableCopies(minCopies);
    }

    // Get product by ID
    public Product getProductById(int productId) throws SQLException {
        if (productId <= 0) {
            System.out.println("Invalid product ID.");
            return null;
        }

        return productDataAO.getProductById(productId);
    }

    // Get product by ISBN
    public Product getProductByISBN(String isbn) throws SQLException {
        if (isbn == null || isbn.isEmpty()) {
            System.out.println("Invalid ISBN.");
            return null;
        }
        return productDataAO.getProductsByISBN(isbn);
    }

    // Get all products
    public List<Product> getAllProducts() throws SQLException {
        return productDataAO.getAllProducts();
    }

    // Check if a product is available (enough copies in stock)
    public boolean isProductAvailable(int productId, int quantity) throws SQLException {
        if (productId <= 0 || quantity <= 0) {
            System.out.println("Invalid product ID or quantity.");
            return false;
        }

        Product product = productDataAO.getProductById(productId);
        return product != null && product.getAvailableCopies() >= quantity;
    }

    // Update stock after transaction (reduce available copies)
    public void updateProductStock(int productId, int quantity) throws SQLException {
        if (productId <= 0 || quantity <= 0) {
            System.out.println("Invalid product ID or quantity.");
            return;
        }

        Product product = productDataAO.getProductById(productId);
        if (product != null && product.getAvailableCopies() >= quantity) {
            int newAvailableCopies = product.getAvailableCopies() - quantity;
            productDataAO.updateProductAvailableCopies(productId, newAvailableCopies);
        } else {
            System.out.println("Not enough copies available.");
        }
    }

    // Update product's category
    public void updateProductCategory(int productId, int categoryId) throws SQLException {
        if (categoryId <= 0) {
            System.out.println("Invalid category ID.");
            return;
        }

        productDataAO.updateProductCategory(productId, categoryId);
    }

    // Update product's author
    public void updateProductAuthor(int productId, int authorId) throws SQLException {
        if (authorId <= 0) {
            System.out.println("Invalid author ID.");
            return;
        }

        productDataAO.updateProductAuthor(productId, authorId);
    }

    // Update product's price
    public void updateProductPrice(int productId, int price) throws SQLException {
        if (price < 0) {
            System.out.println("Invalid price.");
            return;
        }

        productDataAO.updateProductPrice(productId, price);
    }

    // Update product's available copies
    public void updateProductAvailableCopies(int productId, int availableCopies) throws SQLException {
        if (availableCopies < 0) {
            System.out.println("Invalid number of available copies.");
            return;
        }

        productDataAO.updateProductAvailableCopies(productId, availableCopies);
    }

    // Update product's ISBN
    public void updateProductIsbn(int productId, String isbn) throws SQLException {
        if (isbn == null || isbn.isEmpty()) {
            System.out.println("Invalid ISBN.");
            return;
        }
        productDataAO.updateProductISBN(productId, isbn);
    }
}
