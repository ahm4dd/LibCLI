public class Product {
    private int productId;
    private String type; // "Book" or "Magazine"
    private String title;
    private int categoryId;
    private int authorId;
    private int price;
    private int availableCopies;
    private String isbn; // Null for magazines, not applicable

    // Constructor
    public Product(int productId, String type, String title, int categoryId, int authorId, int price, int availableCopies, String isbn) {
        this.productId = productId;
        this.type = type;
        this.title = title;
        this.categoryId = categoryId;
        this.authorId = authorId;
        this.price = price;
        this.availableCopies = availableCopies;
        this.isbn = isbn;
    }

    // Getters and Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
