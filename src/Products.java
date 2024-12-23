public class Products {
    private int product_id;
    private String type;

    public Products(int product_id, String type) {
        this.product_id = product_id;
        this.type = type;
    }

    public int getProductId() {
        return product_id;
    }

    public String getType() {
        return type;
    }
}
