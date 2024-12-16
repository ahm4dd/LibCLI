import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Member extends User{
    BookService bookService = new BookService();
    TransactionService transactionService = new TransactionService();
    BookReviewService bookReviewService = new BookReviewService();
    AuthorService authorService = new AuthorService();
    CategoryService categoryService = new CategoryService();

    private UserService userService = new UserService();
    Member(int userId, String username, String password, String email, String userType) {
        super(userId, username, password, email, userType);
    }

    public void accessLibrarySystem() throws SQLException {
        System.out.println("1.Books 2.Transactions 3.Book Reviews 4.Categories 5.User 6.Authors: ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch(option){
            case 1 -> {
                System.out.println("1.Search book by keyword 2.Search book by ISBN 3.Search book by ID 4.Get all books ");
                int option2 = scanner.nextInt();
                switch(option2) {
                    case 1 -> searchBook();
                    case 2 -> searchBookByIsbn();
                    case 3 -> searchBookById();
                    case 4 -> getAllBooks();
                }
            }

            case 2 -> {
                System.out.println("1.Add transaction 2.Get all transactions");
                int option2 = scanner.nextInt();
                switch(option2) {
                    case 1 -> addTransaction();
                    case 2 -> getAllTransactions();
                }
            }

            case 3 -> {
                System.out.println("1.Add book review 2.Get book reviews by book ID 3.Get all book reviews (Yours) 4.Get book review by ID 5.Update review 6.Delete review");
                int option2 = scanner.nextInt();
                switch(option2) {
                    case 1 -> addBookReview();
                    case 2 -> getReviewForBookId();
                    case 3 -> getAllBookReviews();
                    case 4 -> getBookReviewById();
                    case 5 -> updateReview();
                    case 6 -> deleteReview();
                }
            }

        }
    }

    public void searchBook() throws SQLException {
        System.out.println("Enter book title: ");
        Scanner scanner = new Scanner(System.in);
        String title = scanner.next();
        List<Book> book = bookService.searchBooks(title);
        for (Book book1 : book) {
            System.out.println("------------------------------\nBook id: " + book1.getBookId() + "\nTitle: "
                    + book1.getTitle() + "\nAuthor: " + book1.getAuthor() + "\nISBN: " + book1.getIsbn() + "\nAvailable copies: "
                    + book1.getAvailableBooks() + "\nCategory id: " + book1.getCategory() + "\nPrice: " + book1.getPrice());
        }
    }

    public void searchBookByIsbn() throws SQLException {
        System.out.println("Enter book ISBN: ");
        Scanner scanner = new Scanner(System.in);
        String isbn = scanner.next();
        Book book = bookService.searchBooksByIsbn(isbn);
        System.out.println("------------------------------\nBook id: " + book.getBookId() + "\nTitle: "
                + book.getTitle() + "\nAuthor: " + book.getAuthor() + "\nISBN: " + book.getIsbn() + "\nAvailable copies: "
                + book.getAvailableBooks() + "\nCategory id: " + book.getCategory() + "\nPrice: " + book.getPrice());
    }

    public void searchBookById() throws SQLException {
        System.out.println("Enter the book ID: ");
        Scanner scanner = new Scanner(System.in);
        int bookId = scanner.nextInt();
        Book book = bookService.getBookById(bookId);
        System.out.println("------------------------------\nBook id: " + book.getBookId() + "\nTitle: "
                + book.getTitle() + "\nAuthor: " + book.getAuthor() + "\nISBN: " + book.getIsbn() + "\nAvailable copies: "
                + book.getAvailableBooks() + "\nCategory id: " + book.getCategory() + "\nPrice: " + book.getPrice());
    }

    public void getAllBooks() throws SQLException {
        List<Book> books = bookService.getAllBooks();
        for (Book book : books) {
            System.out.println("------------------------------\nBook id: " + book.getBookId() + "\nTitle: "
                    + book.getTitle() + "\nAuthor: " + book.getAuthor() + "\nISBN: " + book.getIsbn() + "\nAvailable copies: "
                    + book.getAvailableBooks() + "\nCategory id: " + book.getCategory() + "\nPrice: " + book.getPrice());
        }
    }

    public void addTransaction() throws SQLException {
        System.out.println("Enter book id: ");
        Scanner scanner = new Scanner(System.in);
        int bookId = scanner.nextInt();
        transactionService.addTransaction(this.getUserId(),bookId);
        System.out.println("Transaction added successfully!");
    }

    public void getAllTransactions() throws SQLException {
        List<Transaction> transactions = transactionService.getAllTransaction();
        for (Transaction transaction : transactions) {
            System.out.println("Transaction id: " + transaction.getTransactionId() + "\nBook id: " + transaction.getBookId()
                    + "\nUser id: " + transaction.getUserId() + "\nCheckout date: " + transaction.getCheckoutDate()
                    + "\nCost: " + transaction.getCost());
        }
    }

    public void addBookReview() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book id: ");
        int bookId = scanner.nextInt();
        System.out.println("Enter rating: ");
        int rating = scanner.nextInt();
        bookReviewService.addBookReview(this.getUserId(),bookId,rating);
        System.out.println("Review added successfully!");
    }

    public void getReviewForBookId() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book id: ");
        int bookId = scanner.nextInt();
        List<BookReview> bookReviews = new ArrayList<BookReview>();
        bookReviews = bookReviewService.getAllBookReviewsForBook(bookId);
        for (BookReview bookReview : bookReviews){
            System.out.println("Review id: "+ bookReview.getReview_id() +"\nUsed id: " + bookReview.getUser_id() + "\nBook id: " + bookReview.getBook_id() + "\nRating: "+ bookReview.getRating());
        }
    }

    public void getBookReviewById() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter review id: ");
        int reviewId = scanner.nextInt();
        BookReview bookReview = bookReviewService.getBookReviewById();
        System.out.println("Review id: "+ bookReview.getReview_id() +"\nUsed id: " + bookReview.getUser_id() + "\nBook id: " + bookReview.getBook_id() + "\nRating: "+ bookReview.getRating());
    }

    public void updateReview() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter review id: ");
        int reviewId = scanner.nextInt();
        System.out.println("Enter rating: ");
        int newRating = scanner.nextInt();
        bookReviewService.updateBookReviewForUser(reviewId,this.getUserId(),newRating);
        System.out.println("Review updated successfully!");
    }

    public void deleteReview() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter review id: ");
        int reviewId = scanner.nextInt();
        bookReviewService.deleteBookReviewForUser(reviewId,this.getUserId());
        System.out.println("Review deleted successfully!");
    }
    public void getAllBookReviews() throws SQLException {
        List<BookReview> bookReviews = new ArrayList<BookReview>();
        bookReviews = bookReviewService.getAllBookReviewsForUser(this.getUserId());
        for (BookReview bookReview : bookReviews){
            System.out.println("Review id: "+ bookReview.getReview_id() +"\nUsed id: " + bookReview.getUser_id() + "\nBook id: " + bookReview.getBook_id() + "\nRating: "+ bookReview.getRating());
        }
    }

    
}
