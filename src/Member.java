import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class Member extends User{
    private UserService userService = new UserService();
    private ProductService productService = new ProductService();
    private TransactionService transactionService = new TransactionService();
    private ReviewService reviewService = new ReviewService();
    private AuthorService authorService = new AuthorService();
    private CategoryService categoryService = new CategoryService();
    Member(int userId, String username, String password, String email, String userType) {
        super(userId, username, password, email, userType);
    }

    public void accessLibrarySystem() throws SQLException {
        while(true) {
            System.out.println("1.Products 2.Transactions 3.Reviews 4.Categories 5.User 6.Authors 7.logout: ");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            if (option == 7) {
                break;
            }
            switch (option) {
                case 0 -> exit(0);
                case 1 -> {
                    System.out.println("1.Books 2.Magazines: ");
                    int option2 = scanner.nextInt();
                    switch (option2) {
                        case 1 -> {
                            System.out.println("1.Search books by title 2.Search books by author 3.Search books by category 4.Get all books 5.Get books by author 6.Get books by category 7.Get books by price range 8.Get books by available copies 9.Get books by ISBN: ");
                            int option3 = scanner.nextInt();
                            switch (option3) {
                                case 1 -> searchProductsByTitle("Book");
                                case 2 -> searchProductsByAuthor("Book");
                                case 3 -> searchProductsByCategory("Book");
                                case 4 -> getAllProducts("Book");
                                case 5 -> getProductByAuthor("Book");
                                case 6 -> getProductByCategory("Book");
                                case 7 -> getProductsByPriceRange("Book");
                                case 8 -> getProductsByAvailableCopies("Book");
                                case 9 -> searchProductsByISBN();
                            }
                        }
                        case 2 -> {
                            System.out.println("1.Search magazines by title 2.Search magazines by author 3.Search magazines by category 4.Get all magazines 5.Get magazines by author 6.Get magazines by category 7.Get magazines by price range 8.Get magazines by available copies: ");
                            int option3 = scanner.nextInt();
                            switch (option3) {
                                case 1 -> searchProductsByTitle("Magazine");
                                case 2 -> searchProductsByAuthor("Magazine");
                                case 3 -> searchProductsByCategory("Magazine");
                                case 4 -> getAllProducts("Magazine");
                                case 5 -> getProductByAuthor("Magazine");
                                case 6 -> getProductByCategory("Magazine");
                                case 7 -> getProductsByPriceRange("Magazine");
                                case 8 -> getProductsByAvailableCopies("Magazine");
                            }
                        }
                    }
                }
                case 2 -> {
                    System.out.println("1.Add transaction 2.Get all transactions: ");
                    int option2 = scanner.nextInt();
                    switch (option2) {
                        case 1 -> addTransaction();
                        case 2 -> getAllTransactions();
                    }
                }

                case 3 -> {
                    System.out.println("1.Add product review 2.Get product reviews by product ID 3.Get all product reviews (Yours) 4.Get product review by ID 5.Update review 6.Delete review: ");
                    int option2 = scanner.nextInt();
                    switch (option2) {
                        case 1 -> addReview();
                        case 2 -> getReviewForProductId();
                        case 3 -> getAllReviews();
                        case 4 -> getReviewById();
                        case 5 -> updateReview();
                        case 6 -> deleteReview();
                    }
                }

                case 4 -> {
                    System.out.println("1.Get category by Id 2.Search categories 3.Get all categories: ");
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
                    System.out.println("1.Get author by Id 2.Get Products by author 3.Get all authors: ");
                    int option2 = scanner.nextInt();
                    switch (option2) {
                        case 1 -> getAuthorById();
                        case 2 -> getProductsByAuthor();
                        case 3 -> getAllAuthors();
                    }
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    private void getProductsByPriceRange(String type) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter min price: ");
        int minPrice = scanner.nextInt();
        System.out.println("Enter max price: ");
        int maxPrice = scanner.nextInt();
        List<Product> products = productService.searchProductsByPriceRange(minPrice, maxPrice);
        for (Product product : products) {
            if (product.getType().equals(type)) {
                System.out.println("Product id: " + product.getProductId() + "\nTitle: " + product.getTitle() + "\nAuthor: " + product.getAuthorId()
                        + "\nISBN: " + product.getIsbn() + "\nCategory: " + product.getCategoryId() + "\n: "
                        + "\nPrice: " + product.getPrice() + "\nAvailable Copies: " + product.getAvailableCopies() + "\nType: " + type);
            }
        }
    }

    private void getProductsByAvailableCopies(String type) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter available copies: ");
        int availableCopies = scanner.nextInt();
        List<Product> products = productService.searchProductsByAvailableCopies(availableCopies);
        for (Product product : products) {
            if (product.getType().equals(type)) {
                System.out.println("Product id: " + product.getProductId() + "\nTitle: " + product.getTitle() + "\nAuthor: " + product.getAuthorId()
                        + "\nISBN: " + product.getIsbn() + "\nCategory: " + product.getCategoryId() + "\n: "
                        + "\nPrice: " + product.getPrice() + "\nAvailable Copies: " + product.getAvailableCopies() + "\nType: " + type);
            }
        }
    }

    private void getAllProducts(String type) throws SQLException {
        List<Product> products = productService.getAllProducts();
        for (Product product : products) {
            if (product.getType().equals(type)) {
                System.out.println("Product id: " + product.getProductId() + "\nTitle: " + product.getTitle() + "\nAuthor: " + product.getAuthorId()
                        + "\nISBN: " + product.getIsbn() + "\nCategory: " + product.getCategoryId() + "\n: "
                        + "\nPrice: " + product.getPrice() + "\nAvailable Copies: " + product.getAvailableCopies() + "\nType: " + type);
            }
        }
    }

    private void searchProductsByTitle(String type) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter title: ");
        String title = scanner.nextLine();
        List<Product> products = productService.searchProductsByTitle(title);
        for (Product product : products) {
            if (product.getType().equals(type)) {
                System.out.println("Product id: " + product.getProductId() + "\nTitle: " + product.getTitle() + "\nAuthor: " + product.getAuthorId()
                        + "\nISBN: " + product.getIsbn() + "\nCategory: " + product.getCategoryId() + "\n: "
                        + "\nPrice: " + product.getPrice() + "\nAvailable Copies: " + product.getAvailableCopies() + "\nType: " + type);
            }
        }
    }

    private void getProductByAuthor(String type) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter author id: ");
        int authorId = scanner.nextInt();
        List<Product> products = productService.searchProductsByAuthor(authorId);
        for (Product product : products) {
            if (product.getType().equals(type)) {
                System.out.println("Product id: " + product.getProductId() + "\nTitle: " + product.getTitle() + "\nAuthor: " + product.getAuthorId()
                        + "\nISBN: " + product.getIsbn() + "\nCategory: " + product.getCategoryId() + "\n: "
                        + "\nPrice: " + product.getPrice() + "\nAvailable Copies: " + product.getAvailableCopies() + "\nType: " + type);
            }
        }
    }

    private void getProductByCategory(String type) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter category id: ");
        int categoryId = scanner.nextInt();
        List<Product> products = productService.searchProductsByCategory(categoryId);
        for (Product product : products) {
            if (product.getType().equals(type)) {
                System.out.println("Product id: " + product.getProductId() + "\nTitle: " + product.getTitle() + "\nAuthor: " + product.getAuthorId()
                        + "\nISBN: " + product.getIsbn() + "\nCategory: " + product.getCategoryId() + "\n: "
                        + "\nPrice: " + product.getPrice() + "\nAvailable Copies: " + product.getAvailableCopies() + "\nType: " + type);
            }
        }
    }

    private void searchProductsByAuthor(String type) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter author id: ");
        int authorId = scanner.nextInt();
        List<Product> products = productService.searchProductsByAuthor(authorId);
        for (Product product : products) {
            if (product.getType().equals(type)) {
                System.out.println("Product id: " + product.getProductId() + "\nTitle: " + product.getTitle() + "\nAuthor: " + product.getAuthorId()
                        + "\nISBN: " + product.getIsbn() + "\nCategory: " + product.getCategoryId() + "\n: "
                        + "\nPrice: " + product.getPrice() + "\nAvailable Copies: " + product.getAvailableCopies() + "\nType: " + type);
            }
        }
    }

    private void searchProductsByCategory(String type) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter category id: ");
        int categoryId = scanner.nextInt();
        List<Product> products = productService.searchProductsByCategory(categoryId);
        for (Product product : products) {
            if (product.getType().equals(type)) {
                System.out.println("Product id: " + product.getProductId() + "\nTitle: " + product.getTitle() + "\nAuthor: " + product.getAuthorId()
                        + "\nISBN: " + product.getIsbn() + "\nCategory: " + product.getCategoryId() + "\n: "
                        + "\nPrice: " + product.getPrice() + "\nAvailable Copies: " + product.getAvailableCopies() + "\nType: " + type);
            }
        }
    }

    private void searchProductsByISBN() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ISBN: ");
        String isbn = scanner.nextLine();
        Product product = productService.getProductByISBN(isbn);
        System.out.println("Product id: " + product.getProductId() + "\nTitle: " + product.getTitle() + "\nAuthor: " + product.getAuthorId()
                + "\nISBN: " + product.getIsbn() + "\nCategory: " + product.getCategoryId() + "\n: "
                + "\nPrice: " + product.getPrice() + "\nAvailable Copies: " + product.getAvailableCopies() + "\nType: " + product.getType());

    }

    private void addTransaction() throws SQLException {
        System.out.println("Enter product id: ");
        Scanner scanner = new Scanner(System.in);
        int productId = scanner.nextInt();
        transactionService.addTransaction(this.getUserId(),productId);
        System.out.println("Transaction added successfully!");
    }

    private void getAllTransactions() throws SQLException {
        List<Transaction> transactions = transactionService.getTransactionByUserId(this.getUserId());
        for (Transaction transaction : transactions) {
            System.out.println("Transaction id: " + transaction.getTransactionId() + "\nProduct id: " + transaction.getProduct_id()
                    + "\nUser id: " + transaction.getUserId() + "\nCheckout date: " + transaction.getCheckoutDate()
                    + "\nCost: " + transaction.getCost());
        }
    }

    private void addReview() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product id: ");
        int productId = scanner.nextInt();
        System.out.println("Enter rating: ");
        int rating = scanner.nextInt();
        reviewService.addReview(this.getUserId(),productId,rating);
        System.out.println("Review added successfully!");
    }

    private void getReviewForProductId() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book id: ");
        int bookId = scanner.nextInt();
        List<Review> reviews = new ArrayList<Review>();
        reviews = reviewService.getAllReviewsForProduct(bookId);
        for (Review review : reviews){
            System.out.println("Review id: "+ review.getReview_id() +"\nUsed id: " + review.getUser_id() + "\nProduct id: " + review.getProduct_id() + "\nRating: "+ review.getRating());
        }
    }

    private void getReviewById() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter review id: ");
        int reviewId = scanner.nextInt();
        Review review = reviewService.getReviewById(reviewId);
        System.out.println("Review id: "+ review.getReview_id() +"\nUsed id: " + review.getUser_id() + "\nProduct id: " + review.getProduct_id() + "\nRating: "+ review.getRating());
    }

    private void updateReview() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter review id: ");
        int reviewId = scanner.nextInt();
        System.out.println("Enter rating: ");
        int newRating = scanner.nextInt();
        reviewService.updateReviewForUser(reviewId,this.getUserId(),newRating);
        System.out.println("Review updated successfully!");
    }

    private void deleteReview() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter review id: ");
        int reviewId = scanner.nextInt();
        reviewService.deleteReviewForUser(reviewId,this.getUserId());
        System.out.println("Review deleted successfully!");
    }
    private void getAllReviews() throws SQLException {
        List<Review> reviews = new ArrayList<Review>();
        reviews = reviewService.getAllReviewsForUser(this.getUserId());
        for (Review review : reviews){
            System.out.println("Review id: "+ review.getReview_id() +"\nUsed id: " + review.getUser_id() + "\nProduct id: " + review.getProduct_id() + "\nRating: "+ review.getRating());
        }
    }

    private void getCategoryById() throws SQLException {
        System.out.println("Enter category ID: ");
        Scanner scanner = new Scanner(System.in);
        int categoryId = scanner.nextInt();
        Category category = categoryService.getCategoryById(categoryId);
        System.out.println("Category id: " + category.getCategory_id() + "\nCategory name: " + category.getName()+"\nCategory description: " + category.getDescription());
    }

    private void searchCategory() throws SQLException {
        System.out.println("Enter keyword: ");
        Scanner scanner = new Scanner(System.in);
        String keyword = scanner.next();
        List<Category> category = categoryService.searchCategoriesByName(keyword);
        for (Category category1 : category) {
            System.out.println("Category id: "+ category1.getCategory_id() +"\nCategory Name: "+category1.getName()+"\nCategory Description: "+category1.getDescription());
        }
    }

    private void getAllCategories() throws SQLException {
        List<Category> categories = categoryService.getAllCategories();
        for (Category category : categories) {
            System.out.println("Category id: "+ category.getCategory_id() +"\nCategory Name: "+category.getName()+"\nCategory Description: "+category.getDescription());
        }
    }

    private void displayUserInfo() throws SQLException {
        User user = userService.getUserById(this.getUserId());
        System.out.println("User id: " + user.getUserId() + "\nUsername: " + user.getUsername() + "\nEmail: " + user.getEmail()
                + "\nPassword: " + user.getPassword() + "\nUser Type: " + getUserType());
    }

    private void updateUserInfo() throws SQLException {
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

    private void deleteAccount() throws SQLException {
        userService.deleteUser(this.getUserId());
        System.out.println("Account deleted successfully!");
        userService.logout();
    }

    private void getAuthorById() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter author id: ");
        int authorId = scanner.nextInt();
        Author author = authorService.getAuthorById(authorId);
        System.out.println("Author id: " + author.getAuthorId() + "\nAuthor name: " + author.getFirstName() + " " + author.getLastName() + "\nAuthor bio: " + author.getBio() + "\n");
    }

    private void getProductsByAuthor() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter author id: ");
        int authorId = scanner.nextInt();
        List<Product> products = productService.searchProductsByAuthor(authorId);
        for (Product product : products) {
            System.out.println("Product id: " + product.getProductId() + "\nTitle: " + product.getTitle() + "\nAuthor: " + product.getAuthorId()
                    + "\nISBN: " + product.getIsbn() + "\nCategory: " + product.getCategoryId() + "\n: "
                    + "\nPrice: " + product.getPrice() + "\nAvailable Copies: " + product.getAvailableCopies() + "\nType: " + product.getType());
        }
    }

    private void getAllAuthors() throws SQLException {
        List<Author> authors = authorService.getAllAuthors();

        for(Author author : authors){
            System.out.println("Author id: " + author.getAuthorId() + "\nAuthor name: " + author.getFirstName() + " " + author.getLastName() + "\nAuthor bio: " + author.getBio() + "\n");
        }
    }
}
