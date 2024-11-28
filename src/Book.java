public class Book {
    private int bookId;
    private String title;
    private String author;
    private String isbn;
    private int availableBooks;

    Book(int bookId, String title, String author, String isbn, int availableBooks){
        this.bookId = bookId;
        this.title = title;
        this.author = author;;
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
    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
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
