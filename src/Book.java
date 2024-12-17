public class Book extends Products{
    private int bookId;
    private String title;
    private int author_id;
    private int category_id;
    private String isbn;
    private int price;
    private int availableBooks;

    Book(int bookId, int product_id,String title, int author_id,int category_id, String isbn, int price,int availableBooks){
        super(product_id,"Book");
        this.bookId = bookId;
        this.title = title;
        this.author_id = author_id;
        this.category_id = category_id;
        this.isbn = isbn;
        this.availableBooks = availableBooks;
        this.price = price;
    }

    public int getBookId(){
        return bookId;
    }

    public void setBookId(int bookId){
        this.bookId = bookId;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public int getAuthor(){
        return author_id;
    }

    public void setAuthor(int author_id){
        this.author_id = author_id;
    }

    public int getCategory(){
        return category_id;
    }

    public void setCategory(int category_id){
        this.category_id = category_id;
    }

    public String getIsbn(){
        return isbn;
    }

    public void setIsbn(String isbn){
        this.isbn = isbn;
    }

    public int getAvailableBooks(){
        return availableBooks;
    }

    public void setAvailableBooks(int availableBooks){
        this.availableBooks = availableBooks;
    }

    public int getPrice(){
        return price;
    }
    public void setPrice(int price){
        this.price = price;
    }
}
