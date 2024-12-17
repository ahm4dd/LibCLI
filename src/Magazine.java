public class Magazine extends Products {
    private int magazineId;
    private String title;
    private int author_id;
    private int category_id;
    private int price;
    private int availableCopies;

    public Magazine(int magazineId, int product_id,String title, int author_id,int category_id, int price,int availableCopies){
        super(product_id,"Magazine");
        this.magazineId = magazineId;
        this.title = title;
        this.author_id = author_id;
        this.category_id = category_id;
        this.price = price;
        this.availableCopies = availableCopies;
    }

    public int getMagazineId() {
        return magazineId;
    }

    public void setMagazineId(int magazineId) {
        this.magazineId = magazineId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthor() {
        return author_id;
    }

    public void setAuthor(int author_id) {
        this.author_id = author_id;
    }

    public int getCategory() {
        return category_id;
    }

    public void setCategory(int category_id) {
        this.category_id = category_id;
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
}
