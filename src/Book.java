public class Book {
    private int bookId;
    private String title;
    private int author_id;
    private String isbn;
    private int availableBooks;

    Book(int bookId, String title, int author_id, String isbn, int availableBooks){
        this.bookId = bookId;
        this.title = title;
        this.author_id = author_id;
        this.isbn = isbn;
        this.availableBooks = availableBooks;
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
}
