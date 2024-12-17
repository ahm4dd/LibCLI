import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class Member extends User{
    private UserService userService = new UserService();
    private BookService bookService = new BookService();
    private TransactionService transactionService = new TransactionService();
    private ReviewService reviewService = new ReviewService();
    private AuthorService authorService = new AuthorService();
    private CategoryService categoryService = new CategoryService();
    private MagazineService magazineService = new MagazineService();
    Member(int userId, String username, String password, String email, String userType) {
        super(userId, username, password, email, userType);
    }

    public void accessLibrarySystem() throws SQLException {
        while(true) {
            System.out.println("1.Books 2.Transactions 3.Reviews 4.Categories 5.User 6.Authors 7.Magazines 8.logout: ");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            if (option == 8) {
                break;
            }
            switch (option) {
                case 0 -> exit(0);
                case 1 -> {
                    System.out.println("1.Search book by keyword 2.Search book by ISBN 3.Search book by ID 4.Get all books 5.Get books by Category: ");
                    int option2 = scanner.nextInt();
                    switch (option2) {
                        case 1 -> searchBook();
                        case 2 -> searchBookByIsbn();
                        case 3 -> searchBookById();
                        case 4 -> getAllBooks();
                        case 5 -> getBooksByCategory();
                    }
                }

                case 2 -> {
                    System.out.println("1.Add transaction 2.Get all transactions");
                    int option2 = scanner.nextInt();
                    switch (option2) {
                        case 1 -> addTransaction();
                        case 2 -> getAllTransactions();
                    }
                }

                case 3 -> {
                    System.out.println("1.Add book review 2.Get book reviews by book ID 3.Get all book reviews (Yours) 4.Get book review by ID 5.Update review 6.Delete review");
                    int option2 = scanner.nextInt();
                    switch (option2) {
                        case 1 -> addBookReview();
                        case 2 -> getReviewForBookId();
                        case 3 -> getAllBookReviews();
                        case 4 -> getBookReviewById();
                        case 5 -> updateReview();
                        case 6 -> deleteReview();
                    }
                }

                case 4 -> {
                    System.out.println("1.Get category by Id 2.Search categories 3.Get all categories:");
                    int option2 = scanner.nextInt();
                    switch (option2) {
                        case 1 -> getCategoryById();
                        case 2 -> searchCategory();
                        case 3 -> getAllCategories();
                    }
                }

                case 5 -> {
                    System.out.println("1.Display your info 2.Update your info 3.Delete your account: ");
                    int option2 = scanner.nextInt();
                    switch (option2) {
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

                case 7 -> {
                    System.out.println("1.Search magazines by keyword 2.Search magazines by ISBN 3.Search magazines by ID 4.Get all magazines ");
                    int option2 = scanner.nextInt();
                    switch (option2) {
                        case 1 -> searchMagazine();
                        case 2 -> searchMagazineByCategory();
                        case 3 -> searchMagazineById();
                        case 4 -> getAllMagazines();
                    }
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    private void getAllMagazines() throws SQLException {
        List<Magazine> magazines = magazineService.getAllMagazines();
        for (Magazine magazine : magazines) {
            System.out.println("------------------------------\nMagazine id: " + magazine.getMagazineId() + "\nTitle: "
                    + magazine.getTitle() + "\nAuthor: " + magazine.getAuthor() + "\nAvailable copies: "
                    + magazine.getAvailableCopies() + "\nCategory id: " + magazine.getCategory() + "\nPrice: " + magazine.getPrice()+"\nProduct id: "+magazine.getProductId());
        }
    }
    private void searchMagazineById() throws SQLException {
        System.out.println("Enter magazine id: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        Magazine magazine = magazineService.getMagazineById(id);
        System.out.println("------------------------------\nMagazine id: " + magazine.getMagazineId() + "\nTitle: "
                + magazine.getTitle() + "\nAuthor: " + magazine.getAuthor() + "\nAvailable copies: "
                + magazine.getAvailableCopies() + "\nCategory id: " + magazine.getCategory() + "\nPrice: " + magazine.getPrice()+"\nProduct id: "+magazine.getProductId());
    }
    private void searchMagazineByCategory() throws SQLException {
        int categoryId;
        System.out.println("Enter category id: ");
        Scanner scanner = new Scanner(System.in);
        categoryId = scanner.nextInt();
        List<Magazine> magazines = magazineService.searchMagazinesByCategory(categoryId);
        for (Magazine magazine : magazines) {
            System.out.println("------------------------------\nMagazine id: " + magazine.getMagazineId() + "\nTitle: "
                    + magazine.getTitle() + "\nAuthor: " + magazine.getAuthor() + "\nAvailable copies: "
                    + magazine.getAvailableCopies() + "\nCategory id: " + magazine.getCategory() + "\nPrice: " + magazine.getPrice()+"\nProduct id: "+magazine.getProductId());
        }
    }

    private void searchMagazine() throws SQLException {
        System.out.println("Enter magazine title: ");
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();
        title = scanner.nextLine();
        List<Magazine> magazines = magazineService.searchMagazines(title);
        for (Magazine magazine : magazines) {
            System.out.println("------------------------------\nMagazine id: " + magazine.getMagazineId() + "\nTitle: "
                    + magazine.getTitle() + "\nAuthor: " + magazine.getAuthor() + "\nAvailable copies: "
                    + magazine.getAvailableCopies() + "\nCategory id: " + magazine.getCategory() + "\nPrice: " + magazine.getPrice()+"\nProduct id: "+magazine.getProductId());
        }
    }

    public void searchBook() throws SQLException {
        System.out.println("Enter book title: ");
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();
        title = scanner.nextLine();
        List<Book> book = bookService.searchBooks(title);
        for (Book book1 : book) {
            System.out.println("------------------------------\nBook id: " + book1.getBookId() + "\nTitle: "
                    + book1.getTitle() + "\nAuthor: " + book1.getAuthor() + "\nISBN: " + book1.getIsbn() + "\nAvailable copies: "
                    + book1.getAvailableBooks() + "\nCategory id: " + book1.getCategory() + "\nPrice: " + book1.getPrice()+"\nProduct id: "+book1.getProductId());
        }
    }

    public void searchBookByIsbn() throws SQLException {
        System.out.println("Enter book ISBN: ");
        Scanner scanner = new Scanner(System.in);
        String isbn = scanner.next();
        Book book = bookService.searchBooksByIsbn(isbn);
        System.out.println("------------------------------\nBook id: " + book.getBookId() + "\nTitle: "
                + book.getTitle() + "\nAuthor: " + book.getAuthor() + "\nISBN: " + book.getIsbn() + "\nAvailable copies: "
                + book.getAvailableBooks() + "\nCategory id: " + book.getCategory() + "\nPrice: " + book.getPrice() + "\nProduct id: "+book.getProductId());
    }

    public void searchBookById() throws SQLException {
        System.out.println("Enter the book ID: ");
        Scanner scanner = new Scanner(System.in);
        int bookId = scanner.nextInt();
        Book book = bookService.getBookById(bookId);
        System.out.println("------------------------------\nBook id: " + book.getBookId() + "\nTitle: "
                + book.getTitle() + "\nAuthor: " + book.getAuthor() + "\nISBN: " + book.getIsbn() + "\nAvailable copies: "
                + book.getAvailableBooks() + "\nCategory id: " + book.getCategory() + "\nPrice: " + book.getPrice() + "\nProduct id: "+book.getProductId());
    }

    public void getAllBooks() throws SQLException {
        List<Book> books = bookService.getAllBooks();
        for (Book book : books) {
            System.out.println("------------------------------\nBook id: " + book.getBookId() + "\nTitle: "
                    + book.getTitle() + "\nAuthor: " + book.getAuthor() + "\nISBN: " + book.getIsbn() + "\nAvailable copies: "
                    + book.getAvailableBooks() + "\nCategory id: " + book.getCategory() + "\nPrice: " + book.getPrice()+"\nProduct id: "+book.getProductId());
        }
    }

    public void addTransaction() throws SQLException {
        System.out.println("Enter product id: ");
        Scanner scanner = new Scanner(System.in);
        int productId = scanner.nextInt();
        transactionService.addTransaction(this.getUserId(),productId);
        System.out.println("Transaction added successfully!");
    }

    public void getAllTransactions() throws SQLException {
        List<Transaction> transactions = transactionService.getAllTransaction();
        for (Transaction transaction : transactions) {
            System.out.println("Transaction id: " + transaction.getTransactionId() + "\nProduct id: " + transaction.getProduct_id()
                    + "\nUser id: " + transaction.getUserId() + "\nCheckout date: " + transaction.getCheckoutDate()
                    + "\nCost: " + transaction.getCost());
        }
    }

    public void addBookReview() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product id: ");
        int productId = scanner.nextInt();
        System.out.println("Enter rating: ");
        int rating = scanner.nextInt();
        reviewService.addBookReview(this.getUserId(),productId,rating);
        System.out.println("Review added successfully!");
    }

    public void getReviewForBookId() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book id: ");
        int bookId = scanner.nextInt();
        List<Review> reviews = new ArrayList<Review>();
        reviews = reviewService.getAllBookReviewsForProduct(bookId);
        for (Review review : reviews){
            System.out.println("Review id: "+ review.getReview_id() +"\nUsed id: " + review.getUser_id() + "\nProduct id: " + review.getProduct_id() + "\nRating: "+ review.getRating());
        }
    }

    public void getBookReviewById() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter review id: ");
        int reviewId = scanner.nextInt();
        Review review = reviewService.getBookReviewById(reviewId);
        System.out.println("Review id: "+ review.getReview_id() +"\nUsed id: " + review.getUser_id() + "\nProduct id: " + review.getProduct_id() + "\nRating: "+ review.getRating());
    }

    public void updateReview() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter review id: ");
        int reviewId = scanner.nextInt();
        System.out.println("Enter rating: ");
        int newRating = scanner.nextInt();
        reviewService.updateBookReviewForUser(reviewId,this.getUserId(),newRating);
        System.out.println("Review updated successfully!");
    }

    public void deleteReview() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter review id: ");
        int reviewId = scanner.nextInt();
        reviewService.deleteBookReviewForUser(reviewId,this.getUserId());
        System.out.println("Review deleted successfully!");
    }
    public void getAllBookReviews() throws SQLException {
        List<Review> reviews = new ArrayList<Review>();
        reviews = reviewService.getAllBookReviewsForUser(this.getUserId());
        for (Review review : reviews){
            System.out.println("Review id: "+ review.getReview_id() +"\nUsed id: " + review.getUser_id() + "\nProduct id: " + review.getProduct_id() + "\nRating: "+ review.getRating());
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
                    + book.getAvailableBooks() + "\nCategory id: " + book.getCategory() + "\nPrice: " + book.getPrice() + "\nProduct id: "+book.getProductId());
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
                userService.updateUsername(this.getUserId(),this.getUsername(),this.getPassword(),newUsername);
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
                userService.updateEmail(this.getUserId(),this.getPassword(),this.getEmail(),newEmail);
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
            +"\nBook author: " + book.getAuthor() + "\nBook category:" + book.getCategory() + "\nBook price: " + book.getPrice() + "\nBook available copies: " + book.getAvailableBooks() + "\nProduct id: "+book.getProductId());
        }
    }

    public void getAllAuthors() throws SQLException {
        List<Author> authors = authorService.getAllAuthors();

        for(Author author : authors){
            System.out.println("Author id: " + author.getAuthorId() + "\nAuthor name: " + author.getFirstName() + " " + author.getLastName() + "\nAuthor bio: " + author.getBio() + "\n");
        }
    }
}
