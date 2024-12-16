import javax.xml.transform.sax.SAXSource;
import java.util.List;
import java.sql.*;
import java.util.Scanner;

public class Admin extends User {
  private UserService userService = new UserService();
  private BookService bookService = new BookService();
  private TransactionService transactionService = new TransactionService();
  private AuthorService authorService = new AuthorService();
  private CategoryService categoryService = new CategoryService();

  Admin(int userId, String username, String password, String email, String userType) {
    super(userId, username, password, email, userType);
  }

  public void accessLibrarySystem() throws SQLException {
    System.out.println(
            "1.Add book 2.Remove book 3.Manage user 4.Update book 5.View all users\n" +
                    "6.Search book 7.Search user 8.Search transaction 9.Add transaction 10.Modify transaction\n" +
                    "11.View all books 12.View all transactions 13.Add author 14.Manage Author 15.Add category\n" +
                    "16.View all categories 17.Manage category 18.Search category 19.View all authors 20.Search author 21.Get books by author: ");
    {
      Scanner scanner = new Scanner(System.in);
      int option = scanner.nextInt();
      switch (option) {
        case 1 -> addBook();
        case 2 -> removeBook();
        case 3 -> manageMember();
        case 4 -> updateBook();
        case 5 -> viewAllUsers();
        case 6 -> searchBook();
        case 7 -> searchUser();
        case 8 -> searchTransaction();
        case 9 -> addTransaction();
        case 10 -> modifyTransaction();
        case 11 -> viewAllBooks();
        case 12 -> viewAllTransactions();
        case 13 -> addAuthor();
        case 14 -> manageAuthor();
        case 15 -> addCategory();
        case 16 -> viewAllCategories();
        case 17 -> manageCategory();
        case 18 -> searchCategory();
        case 19 -> viewAllAuthors();
        case 20 -> searchAuthor();
        case 21 -> getBooksByAuthor();
      }
    }
  }

  public void addBook() throws SQLException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter title: ");
    String title = scanner.nextLine();
    System.out.println("Enter author id: ");
    int authorId = scanner.nextInt();
    System.out.println("Enter category id: ");
    int categoryId = scanner.nextInt();
    System.out.println("Enter ISBN: ");
    String isbn = scanner.next();
    System.out.println("Enter price: ");
    int price = scanner.nextInt();
    System.out.println("Enter available copies: ");
    int availableCopies = scanner.nextInt();
    bookService.addBook(title, authorId, categoryId, isbn, price,availableCopies);
  }

  public void removeBook() throws SQLException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter book id: ");
    int bookId = scanner.nextInt();
    bookService.deleteBook(bookId);
  }

  public void manageMember() throws SQLException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter user id: ");
    int userId = scanner.nextInt();

    User user = userService.getUserById(userId);
    if (user == null) {
      System.out.println("couldn't find the user");
    } else if (user.getUserType().equalsIgnoreCase("Admin")) {
      System.out.println("Cannot display an admin user!");
    } else {
      System.out.println("User id: " + user.getUserId() + "\nUsername: " + user.getUsername() + "\nPassword: "
              + user.getPassword() + "\nEmail: " + user.getEmail());

      System.out.println("Enter what you want to change 1.Username 2.Password 3.Email: ");
      int option = scanner.nextInt();
      switch (option) {
        case 1 -> {
          System.out.println("Enter new username: ");
          String newUsername = scanner.next();
          userService.updateUsername(user.getUserId(), user.getUsername(), newUsername);
        }
        case 2 -> {
          System.out.println("Enter new password: ");
          String newPassword = scanner.next();
          userService.updatePassword(user.getUserId(), user.getPassword(), newPassword);
        }
        case 3 -> {
          System.out.println("Enter new email: ");
          String newEmail = scanner.next();
          userService.updateEmail(user.getUserId(), user.getEmail(), newEmail);
        }
        default -> System.out.println("Invalid choice!");
      }
    }
  }

  public void updateBook() throws SQLException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter book id: ");
    int bookId = scanner.nextInt();
    Book book = bookService.getBookById(bookId);
    if (book == null) {
      System.out.println("Couldn't find the book provided!");
    } else {
      System.out.println("Book id: " + book.getBookId() + "\nTitle: " + book.getTitle() + "\nAuthor id: "
              + book.getAuthor() + "ISBN: " + book.getIsbn() + "\nAvailable copies: " + book.getAvailableBooks() + "\nCategory id: " + book.getCategory());
      System.out.println("\n1.Enter what you want to change 1.Title 2.Author 3.ISBN 4.Available copies 5.Category id 6.Price: ");
      int option = scanner.nextInt();

      switch (option) {
        case 1 -> {
          System.out.println("Enter new title: ");
          String newTitle = scanner.next();
          bookService.updateBookTitle(book.getBookId(), newTitle);
        }

        case 2 -> {
          System.out.println("Enter new author ID: ");
          int newAuthorId = scanner.nextInt();
          bookService.updateBookAuthor(book.getBookId(), newAuthorId);
        }

        case 3 -> {
          System.out.println("Enter new ISBN: ");
          String newIsbn = scanner.next();
          bookService.updateBookIsbn(book.getBookId(), newIsbn);
        }

        case 4 -> {
          System.out.println("Enter new available copies: ");
          int newAvailableCopies = scanner.nextInt();
          bookService.updateAvailableCopies(book.getBookId(), newAvailableCopies);
        }

        case 5 -> {
          System.out.println("Enter new category id: ");
          int newCategoryId = scanner.nextInt();
          bookService.updateBookCategory(book.getBookId(), newCategoryId);
        }

        case 6 -> {
          System.out.println("Enter new price: ");
          int newPrice = scanner.nextInt();
          bookService.updateBookPrice(book.getBookId(), newPrice);
        }
      }
    }
  }

  public void viewAllUsers() throws SQLException {
    List<User> users = userService.getAllUsers();
    for (User user : users) {
      if (user.getUserType().equalsIgnoreCase("Member"))
        System.out.println("------------------------------\nUser Id: " + user.getUserId() + "\nUsername: "
                + user.getUsername() + "\nPassword: " + user.getPassword() + "\nEmail: " + user.getEmail());
      else if (user.getUserType().equalsIgnoreCase("Admin"))
        System.out.println("------------------------------\nUser Id: " + user.getUserId() + "\nUsername: "
                + user.getUsername() + "\nEmail: " + user.getEmail());
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

  public void searchUser() throws SQLException {
    System.out.println("Enter search by 1.UserID 2.Username 3.Email: ");
    Scanner scanner = new Scanner(System.in);
    int option = scanner.nextInt();
    switch (option) {
      case 1 -> {
        System.out.println("Enter user id: ");
        int userId = scanner.nextInt();
        User user = userService.getUserById(userId);
        if (user.getUserType().equalsIgnoreCase("Member")) {
          System.out.println("User id: " + user.getUserId() + "\nUsername: " + user.getUsername() + "\nPassword: "
                  + user.getPassword() + "\nEmail: " + user.getEmail());
        } else {
          System.out.println(
                  "User id: " + user.getUserId() + "\nUsername: " + user.getUsername() + "\nEmail: " + user.getEmail());
        }
      }

      case 2 -> {
        System.out.println("Enter username: ");
        String username = scanner.next();
        User user = userService.getUserByUsername(username);
        if (user.getUserType().equalsIgnoreCase("Member")) {
          System.out.println("User id: " + user.getUserId() + "\nUsername: " + user.getUsername() + "\nPassword: "
                  + user.getPassword() + "\nEmail: " + user.getEmail());
        } else {
          System.out.println(
                  "User id: " + user.getUserId() + "\nUsername: " + user.getUsername() + "\nEmail: " + user.getEmail());
        }
      }

      case 3 -> {
        System.out.println("Enter email: ");
        String email = scanner.next();
        User user = userService.getUserByEmail(email);
        if (user.getUserType().equalsIgnoreCase("Member")) {
          System.out.println("User id: " + user.getUserId() + "\nUsername: " + user.getUsername() + "\nPassword: "
                  + user.getPassword() + "\nEmail: " + user.getEmail());
        } else {
          System.out.println(
                  "User id: " + user.getUserId() + "\nUsername: " + user.getUsername() + "\nEmail: " + user.getEmail());
        }
      }
    }
  }

  public void searchTransaction() throws SQLException {
    System.out.println("Enter search by 1.TransactionID 2.UserID 3.BookID: ");
    Scanner scanner = new Scanner(System.in);
    int option = scanner.nextInt();
    switch (option) {
      case 1 -> {
        System.out.println("Enter transaction id: ");
        int transactionId = scanner.nextInt();
        Transaction transaction = transactionService.getTransactionById(transactionId);
        System.out.println("Transaction id: " + transaction.getTransactionId() + "\nBook id: " + transaction.getBookId()
                + "\nUser id: " + transaction.getUserId() + "\nCheckout date: " + transaction.getCheckoutDate()
                + "\nCost: " + transaction.getCost());
      }
      case 2 -> {
        System.out.println("Enter user id: ");
        int userId = scanner.nextInt();
        List<Transaction> transactions = transactionService.getTransactionByUserId(userId);
        for (Transaction transaction : transactions) {
          System.out.println("Transaction id: " + transaction.getTransactionId() + "\nBook id: " + transaction.getBookId()
                  + "\nUser id: " + transaction.getUserId() + "\nCheckout date: " + transaction.getCheckoutDate()
                  + "\nCost: " + transaction.getCost());
        }
      }

      case 3 -> {
        System.out.println("Enter book id: ");
        int bookId = scanner.nextInt();
        List<Transaction> transactions = transactionService.getTransactionByBookId(bookId);
        for (Transaction transaction : transactions) {
          System.out.println("Transaction id: " + transaction.getTransactionId() + "\nBook id: " + transaction.getBookId()
                  + "\nUser id: " + transaction.getUserId() + "\nCheckout date: " + transaction.getCheckoutDate()
                  + "\nCost: " + transaction.getCost());
        }
      }
    }
  }

  public void addTransaction() throws SQLException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter book id: ");
    int bookId = scanner.nextInt();
    System.out.println("Enter user id: ");
    int userId = scanner.nextInt();
    System.out.println("Enter checkout date: ");
    Date checkoutDate = Date.valueOf(scanner.next());
    transactionService.addTransaction(userId, bookId);
  }

  public void modifyTransaction() throws SQLException {
    System.out.println("Enter what you want to modify 1.Remove transaction 2.Update transaction: ");
    Scanner scanner = new Scanner(System.in);
    int option = scanner.nextInt();
    switch (option) {
      case 1 -> {
        System.out.println("Enter remove by 1.TransactionID 2.UserID 3.BookID: ");
        int option2 = scanner.nextInt();
        switch (option2) {
          case 1 -> {
            System.out.println("Enter transaction id: ");
            int transactionId = scanner.nextInt();
            transactionService.deleteTransactionId(transactionId);
          }
          case 2 -> {
            System.out.println("Enter user id: ");
            int userId = scanner.nextInt();
            transactionService.deleteTransactionByUserId(userId);
          }
          case 3 -> {
            System.out.println("Enter book id: ");
            int bookId = scanner.nextInt();
            transactionService.deleteTransactionByBookId(bookId);
          }
        }
      }
      case 2 -> {
        System.out.println("Enter modify 1.UserID 2.BookID 3.Cost: ");
        int option2 = scanner.nextInt();
        switch (option2) {
          case 1 -> {
            System.out.println("Enter transaction ID: ");
            int transactionId = scanner.nextInt();
            System.out.println("Enter user id: ");
            int userId = scanner.nextInt();
            transactionService.updateTransactionUserId(transactionId, userId);
          }
          case 2 -> {
            System.out.println("Enter transaction ID: ");
            int transactionId = scanner.nextInt();
            System.out.println("Enter book id: ");
            int bookId = scanner.nextInt();
            transactionService.updateTransactionBookId(transactionId, bookId);
          }
          case 3 -> {
            System.out.println("Enter transaction ID: ");
            int transactionId = scanner.nextInt();
            System.out.println("Enter cost: ");
            int cost = scanner.nextInt();
            transactionService.updateTransactionCost(transactionId, cost);
          }
        }
      }
    }
  }


  public void viewAllBooks() throws SQLException {
    List<Book> books = bookService.getAllBooks();
    for (Book book : books) {
      System.out.println("Book id: " + book.getBookId() + "\nTitle: " + book.getTitle() + "\nAuthor id: "+ book.getAuthor() + "\nISBN: " + book.getIsbn() + "\nAvailable copies: " + book.getAvailableBooks() + "\nCategory id: " + book.getCategory() + "\nPrice: " + book.getPrice());
    }
  }

  public void viewAllTransactions() throws SQLException {
    List<Transaction> transactions = transactionService.getAllTransaction();
    for (Transaction transaction : transactions) {
      System.out.println("Transaction id: " + transaction.getTransactionId() + "\nBook id: " + transaction.getBookId()
              + "\nUser id: " + transaction.getUserId() + "\nCheckout date: " + transaction.getCheckoutDate()
              + "\nCost: " + transaction.getCost());
    }
  }

  public void addAuthor() throws SQLException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter author first name: ");
    String firstName = scanner.next();
    System.out.println("Enter author last name: ");
    String lastName = scanner.next();
    System.out.println("Enter author bio:");
    String bio = scanner.next();
    authorService.addAuthor(firstName, lastName, bio);
  }

  public void manageAuthor() throws SQLException {
    System.out.println("Enter what you want to modify 1.Remove author 2.Update author: ");
    Scanner scanner = new Scanner(System.in);
    int option = scanner.nextInt();
    switch (option) {
      case 1 -> {
        System.out.println("Enter remove by 1.AuthorID ");
        int option2 = scanner.nextInt();
        switch (option2) {
          case 1 -> {
            System.out.println("Enter author id: ");
            int authorId = scanner.nextInt();
            authorService.deleteAuthor(authorId);
          }
        }
      }
      case 2 -> {
        System.out.println("Enter modify 1.FirstName 2.LastName 3.Bio: ");
        int option2 = scanner.nextInt();
        switch (option2) {
          case 1 -> {
            System.out.println("Enter author ID: ");
            int authorId = scanner.nextInt();
            System.out.println("Enter first name: ");
            String firstName = scanner.next();
            authorService.updateFirstName(authorId, firstName);
          }
          case 2 -> {
            System.out.println("Enter author ID: ");
            int authorId = scanner.nextInt();
            System.out.println("Enter last name: ");
            String lastName = scanner.next();
            authorService.updateLastName(authorId, lastName);
          }
          case 3 -> {
            System.out.println("Enter author ID: ");
            int authorId = scanner.nextInt();
            System.out.println("Enter bio: ");
            String bio = scanner.next();
            authorService.updateBio(authorId, bio);
          }
        }
      }
    }
  }

  public void viewAllAuthors() throws SQLException {
    List<Author> authors = authorService.getAllAuthors();
    for (Author author : authors) {
      System.out.println("Author id: " + author.getAuthorId() + "\nAuthor name: " + author.getFirstName() + " " + author.getLastName() + "\nAuthor bio: " + author.getBio() + "\n");
    }
  }

  public void addCategory() throws SQLException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter category name: ");
    String categoryName = scanner.next();
    System.out.println("Enter category description: ");
    String description = scanner.next();
    categoryService.addCategory(categoryName, description);
  }

  public void manageCategory() throws SQLException {
    System.out.println("Enter what you want to modify 1.Remove category 2.Update category: ");
    Scanner scanner = new Scanner(System.in);
    int option = scanner.nextInt();
    switch (option) {
      case 1 -> {
        System.out.println("Enter remove by 1.CategoryID ");
        int option2 = scanner.nextInt();
        switch (option2) {
          case 1 -> {
            System.out.println("Enter category id: ");
            int categoryId = scanner.nextInt();
            categoryService.deleteCategory(categoryId);
          }
        }
      }
      case 2 -> {
        System.out.println("Enter modify 1.CategoryName 2.Description: ");
        int option2 = scanner.nextInt();
        switch(option2){
          case 1 -> {
            System.out.println("Enter category ID: ");
            int categoryId = scanner.nextInt();
            System.out.println("Enter category name: ");
            String categoryName = scanner.next();
            categoryService.updateCategoryName(categoryId, categoryName);
          }
          case 2 -> {
            System.out.println("Enter category ID: ");
            int categoryId = scanner.nextInt();
            System.out.println("Enter description: ");
            String description = scanner.next();
            categoryService.updateCategoryDescription(categoryId, description);
          }
        }
      }
    }
  }

  public void viewAllCategories() throws SQLException{
    List<Category> categories = categoryService.getAllCategories();
    for (Category category : categories) {
      System.out.println("Category id: " + category.getCategory_id() + "\nCategory name: " + category.getName() + "\nCategory description: " + category.getDescription() + "\n");
    }
  }

  public void searchCategory() throws SQLException{
    System.out.println("Enter keyword: ");
    Scanner scanner = new Scanner(System.in);
    String keyword = scanner.next();
    List<Category> categories = categoryService.searchCategories(keyword);
    for (Category category : categories) {
      System.out.println("Category id: " + category.getCategory_id() + "\nCategory name: " + category.getName() + "\nCategory description: " + category.getDescription() + "\n");
    }
  }

  public void searchAuthor() throws SQLException {
    System.out.println("Enter author ID: ");
    Scanner scanner = new Scanner(System.in);
    int authorId = scanner.nextInt();
    Author author = authorService.getAuthorById(authorId);
    System.out.println("Author id: " + author.getAuthorId() + "\nAuthor name: " + author.getFirstName() + " " + author.getLastName() + "\nAuthor bio: " + author.getBio() + "\n");
  }

  public void getBooksByAuthor() throws SQLException{
    System.out.println("Enter the author ID: ");
    Scanner scanner = new Scanner(System.in);
    int authorId = scanner.nextInt();
    List<Book> books = authorService.getBooksByAuthor(authorId);
    for (Book book1 : books) {
      System.out.println("------------------------------\nBook id: " + book1.getBookId() + "\nTitle: "
              + book1.getTitle() + "\nAuthor: " + book1.getAuthor() + "\nISBN: " + book1.getIsbn() + "\nAvailable copies: "
              + book1.getAvailableBooks() + "\nCategory id: " + book1.getCategory() + "\nPrice: " + book1.getPrice());
    }
  }

}