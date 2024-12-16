import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Member extends User{
    UserService userService = new UserService();
    BookService bookService = new BookService();
    TransactionService transactionService = new TransactionService();
    BookReviewService bookReviewService = new BookReviewService();
    AuthorService authorService = new AuthorService();
    CategoryService categoryService = new CategoryService();
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

            case 4 -> {
                System.out.println("1.Get category by Id 2.Search categories 3.Get Books by category 4.Get all categories:");
                int option2 = scanner.nextInt();
                switch(option2) {
                    case 1 -> getCategoryById();
                    case 2 -> searchCategory();
                    case 3 -> getBooksByCategory();
                    case 4 -> getAllCategories();
                }
            }

            case 5 -> {
                System.out.println("1.Display your info 2.Update your info 3.Delete your account: ");
                int option2 = scanner.nextInt();
                switch(option2) {
                    case 1 -> displayUserInfo();
                    case 2 -> updateUserInfo();
                    case 3 -> deleteAccount();
                }
            }

            case 6 -> {
                System.out.println("1.Get author by Id 2.Get Books by author 3.Get all authors:");
                int option2 = scanner.nextInt();
                switch (option2) {
                    case 1 -> getAuthorById();
                    case 2 -> getBooksByAuthor();
                    case 3 -> getAllAuthors();
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
        BookReview bookReview = bookReviewService.getBookReviewById(reviewId);
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

    public void getCategoryById() throws SQLException {
        System.out.println("Enter category ID: ");
        Scanner scanner = new Scanner(System.in);
        int categoryId = scanner.nextInt();
        Category category = categoryService.getCategoryById(categoryId);
        System.out.println("Category id: " + category.getCategory_id() + "\nCategory name: " + category.getName()+"\nCategory description: " + category.getDescription());
    }

    public void searchCategory() throws SQLException {
        System.out.println("Enter keyword: ");
        Scanner scanner = new Scanner(System.in);
        String keyword = scanner.next();
        List<Category> category = categoryService.searchCategories(keyword);
        for (Category category1 : category) {
            System.out.println("Category id: "+ category1.getCategory_id() +"\nCategory Name: "+category1.getName()+"\nCategory Description: "+category1.getDescription());
        }
    }

    public void getBooksByCategory() throws SQLException {
        System.out.println("Enter category id: ");
        Scanner scanner = new Scanner(System.in);
        int categoryId = scanner.nextInt();
        List<Book> books = bookService.searchBookByCategory(categoryId);
        for (Book book : books) {
            System.out.println("------------------------------\nBook id: " + book.getBookId() + "\nTitle: "
                    + book.getTitle() + "\nAuthor: " + book.getAuthor() + "\nISBN: " + book.getIsbn() + "\nAvailable copies: "
                    + book.getAvailableBooks() + "\nCategory id: " + book.getCategory() + "\nPrice: " + book.getPrice());
        }
    }

    public void getAllCategories() throws SQLException {
        List<Category> categories = categoryService.getAllCategories();
        for (Category category : categories) {
            System.out.println("Category id: "+ category.getCategory_id() +"\nCategory Name: "+category.getName()+"\nCategory Description: "+category.getDescription());
        }
    }

    public void displayUserInfo() throws SQLException {
        User user = userService.getUserById(this.getUserId());
        System.out.println("User id: " + user.getUserId() + "\nUsername: " + user.getUsername() + "\nEmail: " + user.getEmail()
                + "\nPassword: " + user.getPassword() + "\nUser Type: " + getUserType());
    }

    public void updateUserInfo() throws SQLException {
        System.out.println("1.Username 2.Password 3.Email: ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch(option){
            case 1 -> {
                System.out.println("Enter new username: ");
                String newUsername = scanner.next();
                userService.updateUsername(this.getUserId(),this.getUsername(),newUsername);
                System.out.println("Username updated successfully!");
            }

            case 2 -> {
                System.out.println("Enter new password: ");
                String newPassword = scanner.next();
                userService.updatePassword(this.getUserId(),this.getPassword(),newPassword);
                System.out.println("Password updated successfully!");
            }

            case 3 -> {
                System.out.println("Enter new email: ");
                String newEmail = scanner.next();
                userService.updateEmail(this.getUserId(),this.getEmail(),newEmail);
                System.out.println("Email updated successfully!");
            }
        }
    }

    public void deleteAccount() throws SQLException {
        userService.deleteUser(this.getUserId());
        System.out.println("Account deleted successfully!");
        userService.logout();
    }

    public void getAuthorById() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter author id: ");
        int authorId = scanner.nextInt();
        Author author = authorService.getAuthorById(authorId);
        System.out.println("Author id: " + author.getAuthorId() + "\nAuthor name: " + author.getFirstName() + " " + author.getLastName() + "\nAuthor bio: " + author.getBio() + "\n");
    }

    public void getBooksByAuthor() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter author id: ");
        int authorId = scanner.nextInt();
        List<Book> books = authorService.getBooksByAuthor(authorId);
        for (Book book : books) {
            System.out.println("Book id: "+book.getBookId() + "\nBook Title: " + book.getTitle() + "\n Book ISBN: " + book.getIsbn()
            +"\nBook author: " + book.getAuthor() + "\nBook category:" + book.getCategory() + "\nBook price: " + book.getPrice() + "\nBook available copies: " + book.getAvailableBooks());
        }
    }

    public void getAllAuthors() throws SQLException {
        List<Author> authors = authorService.getAllAuthors();

        for(Author author : authors){
            System.out.println("Author id: " + author.getAuthorId() + "\nAuthor name: " + author.getFirstName() + " " + author.getLastName() + "\nAuthor bio: " + author.getBio() + "\n");
        }
    }
}
