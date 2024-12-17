import java.sql.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import static java.lang.System.exit;

public class Admin extends User {
  private UserService userService = new UserService();
  private BookService bookService = new BookService();
  private BookReviewService bookReviewService = new BookReviewService();
  private TransactionService transactionService = new TransactionService();
  private AuthorService authorService = new AuthorService();
  private CategoryService categoryService = new CategoryService();

  Admin(int userId, String username, String password, String email, String userType) {
    super(userId, username, password, email, userType);
  }

  public void accessLibrarySystem() throws SQLException {
    while (true) {
      System.out.println(
              "1.Book Menu 2.Transaction Menu 3.Member Menu 4.Author Menu 5.Book Review Menu 6.Category Menu 7.Logout: ");
      {
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        if(option == 7){
            break;
        }
        switch (option) {
          case 0 -> exit(0);
          case 1 -> bookMenu();
          case 2 -> transactionMenu();
          case 3 -> memberMenu();
          case 4 -> authorMenu();
          case 5 -> bookReviewMenu();
          case 6 -> categoryMenu();
          default -> System.out.println("Invalid option!");
        }
      }
    }
  }

  public void bookMenu() throws SQLException {
    System.out.println("choose one\n 1.Add Book 2.Update Book Title 3.Update Book Author 4.Update Book ISBN 5.Book Available Copies 6.Update Book Category 7.Update Book Price" +
            " 8.Delete Book 9.Delete All Books 10.Search Books By Keyword 11.Search Books By ISBN 12.Search Books By Category 13.Check If Book Is Available 14.Update Available Copies 15.Get All Books");
    Scanner scanner = new Scanner(System.in);
    int option = scanner.nextInt();
    if (option == 1) {
      System.out.println("Enter title: ");
      String title = scanner.next();
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
      bookService.addBook(title, authorId, categoryId, isbn, price, availableCopies);
    }
    if (option == 2) {
      System.out.println("Enter book id: ");
      int bookId = scanner.nextInt();
      System.out.println("Enter new title: ");
      String newTitle = scanner.next();
      bookService.updateBookTitle(bookId, newTitle);

    }
    if (option == 3) {
      System.out.println("Enter book id: ");
      int bookId = scanner.nextInt();
      System.out.println("Enter new author id: ");
      int newAuthorId = scanner.nextInt();
      bookService.updateBookAuthor(bookId, newAuthorId);
    }
    if (option == 4) {
      System.out.println("Enter book id: ");
      int bookId = scanner.nextInt();
      System.out.println("Enter new ISBN: ");
      String newIsbn = scanner.next();
      bookService.updateBookIsbn(bookId, newIsbn);
    }
    if (option == 5) {
      System.out.println("Enter book id: ");
      int bookId = scanner.nextInt();
      System.out.println();
      System.out.println("Enter new available copies: ");
      int newAvailableCopies = scanner.nextInt();
      bookService.updateAvailableCopies(bookId, newAvailableCopies);
    }
    if (option == 6) {
      System.out.println("Enter book id: ");
      int bookId = scanner.nextInt();
      System.out.println("Enter new category id: ");
      int newCategoryId = scanner.nextInt();
      bookService.updateBookCategory(bookId, newCategoryId);
    }
    if (option == 7) {
      System.out.println("Enter book id: ");
      int bookId = scanner.nextInt();
      System.out.println("Enter new price: ");
      int newPrice = scanner.nextInt();
      bookService.updateBookPrice(bookId, newPrice);
    }
    if (option == 8) {
      System.out.println("Enter book id: ");
      int bookId = scanner.nextInt();
      bookService.deleteBook(bookId);
    }
    if (option == 9) {
      System.out.println("Are you sure! (Y/N)");
      String answer = scanner.next();
      if (answer.equalsIgnoreCase("Y"))
          bookService.deleteAllBooks();
    }
    if (option == 10) {
      System.out.println("Enter keyword to search: ");
      String keyword = scanner.next();
      List<Book> books = bookService.searchBooks(keyword);
      for (Book book : books) {
        System.out.println("Book id: " + book.getBookId() + "\nBook Title: " + book.getTitle() + "\nBook Author: " + book.getAuthor() + "\nBook ISBN: " + book.getIsbn() + "\nBook category:" + book.getCategory() + "\nBook price: " + book.getPrice() + "\nBook available copies: " + book.getAvailableBooks());
      }
    }
    if (option == 11) {
      System.out.println("Enter isbn to search: ");
      String isbn = scanner.next();
      Book book = bookService.searchBooksByIsbn(isbn);
      System.out.println("Book id: " + book.getBookId() + "\nBook Title: " + book.getTitle() + "\nBook Author: " + book.getAuthor() + "\nBook ISBN: " + book.getIsbn() + "\nBook category:" + book.getCategory() + "\nBook price: " + book.getPrice() + "\nBook available copies: " + book.getAvailableBooks());
    }
    if (option == 12) {
      System.out.println("Enter category to search: ");
      int categoryId = scanner.nextInt();
      List<Book> books = bookService.searchBookByCategory(categoryId);
      for (Book book : books) {
        System.out.println("Book id: " + book.getBookId() + "\nBook Title: " + book.getTitle() + "\nBook Author: " + book.getAuthor() + "\nBook ISBN: " + book.getIsbn() + "\nBook category:" + book.getCategory() + "\nBook price: " + book.getPrice() + "\nBook available copies: " + book.getAvailableBooks());
      }
    }
    if (option == 13) {
      System.out.println("Enter book id to check: ");
      int bookId = scanner.nextInt();
      bookService.checkIfBookIsAvailable(bookId);
    }
    if (option == 14) {
      System.out.println("Enter book id to search: ");
      int bookId = scanner.nextInt();
      System.out.println("Enter new available copies: ");
      int newAvailableCopies = scanner.nextInt();
      bookService.updateAvailableCopies(bookId, newAvailableCopies);
    }
    if(option == 15){
      List<Book> books = bookService.getAllBooks();
      for (Book book : books) {
        System.out.println("Book id: " + book.getBookId() + "\nBook Title: " + book.getTitle() + "\nBook Author: " + book.getAuthor() + "\nBook ISBN: " + book.getIsbn() + "\nBook category:" + book.getCategory() + "\nBook price: " + book.getPrice() + "\nBook available copies: " + book.getAvailableBooks());
      }
    }
    else
      System.out.println("Invalid option!");
  }

  public void transactionMenu() throws SQLException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("1.Add Transaction 2.Update Transaction Cost 3.Update Transaction Book Id 4.Update Transaction User Id 5.Get Transaction By Id 6.Get Transaction By User Id" +
            "7.Get Transaction By Book Id 8.Get All Transactions 9.Get All Profit By Book 10.Get All Revenue 11.Delete Transaction By User Id 12.Delete Transaction By Book Id 13.Delete Transaction By Id");
    int option = scanner.nextInt();
    if (option == 1) {
      System.out.println("enter bookId: ");
      int bookId = scanner.nextInt();

      transactionService.addTransaction(this.getUserId(), bookId);
    }
    if (option == 2) {
      System.out.println("transactionId: ");
      int transactionId = scanner.nextInt();
      System.out.println("enter cost: ");
      int cost = scanner.nextInt();
      transactionService.updateTransactionCost(transactionId, cost);
    }
    if (option == 3) {
      System.out.println("transactionId: ");
      int transactionId = scanner.nextInt();
      System.out.println("enter bookId: ");
      int bookId = scanner.nextInt();
      transactionService.updateTransactionBookId(transactionId, bookId);
    }
    if (option == 4) {
      System.out.println("transactionId: ");
      int transactionId = scanner.nextInt();
      System.out.println("enter userId: ");
      int userId = scanner.nextInt();
      transactionService.updateTransactionUserId(transactionId, userId);
    }
    if (option == 5) {
      System.out.println("transactionId: ");
      int transactionId = scanner.nextInt();
      Transaction transaction = transactionService.getTransactionById(transactionId);
      System.out.println("Transaction id: "+transaction.getTransactionId()+"\nTransaction Book Id: " + transaction.getBookId() + "\nTransaction User Id: " + transaction.getUserId() + "\nTransaction cost: " + transaction.getCost() + "\nTransaction checkout date: " + transaction.getCheckoutDate());
    }
    if (option == 6) {
      System.out.println("UserId: ");
      int userId = scanner.nextInt();
      List<Transaction> transactions = transactionService.getTransactionByUserId(userId);
      for(Transaction transaction : transactions){
        System.out.println("Transaction id: "+transaction.getTransactionId()+"\nTransaction Book Id: " + transaction.getBookId() + "\nTransaction User Id: " + transaction.getUserId() + "\nTransaction cost: " + transaction.getCost() + "\nTransaction checkout date: " + transaction.getCheckoutDate());
      }
    }
    if (option == 7) {
      System.out.println("bookId: ");
      int bookId = scanner.nextInt();
      List<Transaction> transactions = transactionService.getTransactionByBookId(bookId);
      for(Transaction transaction : transactions){
        System.out.println("Transaction id: "+transaction.getTransactionId()+"\nTransaction Book Id: " + transaction.getBookId() + "\nTransaction User Id: " + transaction.getUserId() + "\nTransaction cost: " + transaction.getCost() + "\nTransaction checkout date: " + transaction.getCheckoutDate());
      }
    }
    if (option == 8) {
      List<Transaction> transactions = transactionService.getAllTransaction();
      for(Transaction transaction : transactions){
        System.out.println("Transaction id: "+transaction.getTransactionId()+"\nTransaction Book Id: " + transaction.getBookId() + "\nTransaction User Id: " + transaction.getUserId() + "\nTransaction cost: " + transaction.getCost() + "\nTransaction checkout date: " + transaction.getCheckoutDate());
      }
    }
    if (option == 9) {
      System.out.println("enter bookId: ");
      int bookId = scanner.nextInt();
      int profit = transactionService.getAllSalesBooks(bookId);
      System.out.println("Money generated by this book: " + profit);

    }
    if (option == 10) {
      int revenue = transactionService.getAllRevenue();
      System.out.println("Total revenue: " + revenue);
    }
    if (option == 11) {
      System.out.println("enter userId: ");
      int userId = scanner.nextInt();
      System.out.println("are you sure? (Y/N)");
      String answer = scanner.next();
      if (answer.equalsIgnoreCase("Y"))
        transactionService.deleteTransactionByUserId(userId);
      else
        return;
    }
    if (option == 12) {
      System.out.println("enter bookId: ");
      int bookId = scanner.nextInt();
      System.out.println("are you sure? (Y/N)");
      String answer = scanner.next();
      if (answer.equalsIgnoreCase("Y"))
        transactionService.deleteTransactionByBookId(bookId);
      else
        return;

    }
    if (option == 13) {
      System.out.println("enter transactionId: ");
      int transactionId = scanner.nextInt();
      System.out.println("are you sure? (Y/N)");
      String answer = scanner.next();
      if (answer.equalsIgnoreCase("Y"))
        transactionService.deleteTransactionId(transactionId);


    } else
      System.out.println("Invalid option!");
  }

  public void memberMenu() throws SQLException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("1.Add Member 2.Update Member 3.Remove Member 4.Search Member");
    int option = scanner.nextInt();
    if (option == 1) {
      System.out.println("Enter username: ");
      String username = scanner.next();
      System.out.println("Enter password: ");
      String password = scanner.next();
      System.out.println("Enter email: ");
      String email = scanner.next();
      userService.registerUser(username, password, email, "Member");
    }
    if (option == 2) {
      System.out.println("Enter what you want to update 1.Username 2.Password 3.Email: ");
      int option2 = scanner.nextInt();
      switch (option2) {
        case 1 -> {
          System.out.println("Enter user id: ");
          int userId = scanner.nextInt();
          System.out.println("Enter old username: ");
          String oldUsername = scanner.next();
          System.out.println("Enter password: ");
          String password = scanner.next();
          System.out.println("enter new username: ");
          String newUsername = scanner.next();
          userService.updateUsername(userId, oldUsername,password, newUsername);
        }
        case 2 -> {
          System.out.println("Enter user id: ");
          int userId = scanner.nextInt();
          System.out.println("Enter old password: ");
          String oldPassword = scanner.next();
          System.out.println("Enter new password: ");
          String newPassword = scanner.next();
          userService.updatePassword(userId, oldPassword, newPassword);
        }
        case 3 -> {
          System.out.println("Enter user id: ");
          int userId = scanner.nextInt();
          System.out.println("Enter old email: ");
          String oldEmail = scanner.next();
          System.out.println("Enter password: ");
          String password = scanner.next();
          System.out.println("enter new email: ");
          String newEmail = scanner.next();
          userService.updateEmail(userId,password,oldEmail,newEmail);
        }
        default -> System.out.println("invalid option!");
      }
    }
    if (option == 3) {
      System.out.println("Enter user id: ");
      int userId = scanner.nextInt();
      System.out.println("Are you sure? (Y/N)");
      String answer = scanner.next();
      if (answer.equalsIgnoreCase("Y"))
        userService.deleteUser(userId);
      else
        return;
    }
    if (option == 4) {
      System.out.println("Enter how do you want to find user 1.Username 2.Email 3.All users 4.ID");
      int option2 = scanner.nextInt();
      switch (option2) {
        case 1 -> {
          System.out.println("Enter username: ");
          String username = scanner.next();
          User user = userService.getUserByUsername(username);
          System.out.println("User id: " + user.getUserId() + "\nUser username: " + user.getUsername() + "\nUser password: " + user.getPassword() + "\nUser email: " + user.getEmail() +"\nUser role: " + user.getUserType());
        }
        case 2 -> {
          System.out.println("Enter email: ");
          String email = scanner.next();
          User user = userService.getUserByEmail(email);
          System.out.println("User id: " + user.getUserId() + "\nUser username: " + user.getUsername() + "\nUser password: " + user.getPassword() + "\nUser email: " + user.getEmail() +"\nUser role: " + user.getUserType());
        }
        case 3 -> {
          List<User> users = userService.getAllUsers();
          for(User user : users){
            System.out.println("User id: " + user.getUserId() + "\nUser username: " + user.getUsername() + "\nUser password: " + user.getPassword() + "\nUser email: " + user.getEmail() +"\nUser role: " + user.getUserType());
          }
        }
        case 4 -> {
          System.out.println("Enter user id: ");
          int userId = scanner.nextInt();
          User user = userService.getUserById(userId);
          System.out.println("User id: " + user.getUserId() + "\nUser username: " + user.getUsername() + "\nUser password: " + user.getPassword() + "\nUser email: " + user.getEmail() +"\nUser role: " + user.getUserType());
        }
        default -> System.out.println("Invalid option!");
      }

    } else
      System.out.println("Invalid option!");


  }

  public void authorMenu() throws SQLException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("1.Add Author 2.Update Author 3.Remove Author 4.Search Author");
    int option = scanner.nextInt();
    if (option == 1) {
      System.out.println("Enter first name: ");
      String firstName = scanner.next();
      System.out.println("Enter second name: ");
      String secondName = scanner.next();
      System.out.println("enter bio: ");
      String bio = scanner.nextLine();
      bio = scanner.nextLine();
      authorService.addAuthor(firstName, secondName, bio);
    }
    if (option == 2) {
      System.out.println("Enter what do you want to update from author 1.FirstName 2.LastName 3.Bio: ");
      int option2 = scanner.nextInt();
      switch (option2) {
        case 1 -> {
          System.out.println("Enter author id: ");
          int authorId = scanner.nextInt();
          System.out.println("Enter first name: ");
          String firstName = scanner.next();
          authorService.updateFirstName(authorId, firstName);
        }
        case 2 -> {
          System.out.println("Enter author id: ");
          int authorId = scanner.nextInt();
          System.out.println("Enter last name: ");
          String lastName = scanner.next();
          authorService.updateLastName(authorId, lastName);
        }
        case 3 -> {
          System.out.println("Enter author id: ");
          int authorId = scanner.nextInt();
          System.out.println("Enter bio: ");
          String bio = scanner.nextLine();
          bio = scanner.nextLine();
          authorService.updateBio(authorId, bio);
        }
        default -> System.out.println("Invalid option!");
      }
    }
    if (option == 3) {
      System.out.println("Enter author id: ");
      int authorId = scanner.nextInt();
      System.out.println("Are you sure? (Y/N)");
      String answer = scanner.next();
      if (answer.equalsIgnoreCase("Y"))
        authorService.deleteAuthor(authorId);
      else
        return;
    }
    if (option == 4) {
      System.out.println("Enter how do you want to find author 1.ID 2.All Authors");
      int option2 = scanner.nextInt();
      switch (option2) {
        case 1 -> {
          System.out.println("Enter author ID: ");
          int authorId = scanner.nextInt();
          Author author = authorService.getAuthorById(authorId);
          System.out.println("Author id:" + author.getAuthorId() +"\nAuthor full name: " + author.getFirstName()+ " " + author.getLastName() + "\nAuthor bio: "+author.getBio());
        }
        case 2 -> {
          List<Author> authors = authorService.getAllAuthors();
          for(Author author : authors){
            System.out.println("Author id:" + author.getAuthorId() +"\nAuthor full name: " + author.getFirstName()+ " " + author.getLastName() + "\nAuthor bio: "+author.getBio());
          }
        }

        default -> System.out.println("Invalid option!");
      }
    }
  }

  public void bookReviewMenu() throws SQLException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("1.Add Book Rating 2.Update Book Rating 3.Remove Book Rating 4.Search Book Rating");
    int option = scanner.nextInt();
    if (option == 1) {
      System.out.println("Enter book id: ");
      int bookId = scanner.nextInt();
      System.out.println("Enter rating: ");
      int rating = scanner.nextInt();
      bookReviewService.addBookReview(this.getUserId(), bookId, rating);
    }
    if (option == 2) {
      System.out.println("Enter what you want to update 1.Book 2.Rating:");
      int option2 = scanner.nextInt();
      switch (option2) {
        case 1 -> {
          System.out.println("Enter Review Id: ");
          int reviewId = scanner.nextInt();
          System.out.println("Enter new book id: ");
          int newBookId = scanner.nextInt();
          bookReviewService.updateBookReviewBookId(reviewId,newBookId);
        }
        case 2 -> {
          System.out.println("Enter review id: ");
          int reviewId = scanner.nextInt();
          System.out.println("Enter rating: ");
          int newRating = scanner.nextInt();
          bookReviewService.updateBookReviewForUser(reviewId,this.getUserId(),newRating);
        }
        default -> System.out.println("Invalid option!");
      }
    }
    if (option == 3) {
      System.out.println("How you want to delete the review of the books 1.ID 2.User 3.Book 4.All By User");
      int option2 = scanner.nextInt();
      switch (option2) {
        case 1 -> {
          System.out.println("Enter book id: ");
          int bookId = scanner.nextInt();
          System.out.println("Are sure? (Y/N)");
          String answer = scanner.next();
          if (answer.equalsIgnoreCase("Y"))
            bookReviewService.deleteBookReview(bookId);
        }
        case 2 -> {
          System.out.println("Enter review ID: ");
          int reviewId = scanner.nextInt();
          System.out.println("Enter user id: ");
          int userId = scanner.nextInt();
          System.out.println("Are sure? (Y/N)");
          String answer = scanner.next();
          if (answer.equalsIgnoreCase("Y"))
            bookReviewService.deleteBookReviewForUser(reviewId,userId);
        }
        case 3 -> {
          System.out.println("Enter book id: ");
          int bookId = scanner.nextInt();
          System.out.println("Are sure? (Y/N)");
          String answer = scanner.next();
          if (answer.equalsIgnoreCase("Y"))
            bookReviewService.deleteAllBookReviewsForBook(bookId);
        }
        case 4 -> {
          System.out.println("enter user id: ");
          int userId = scanner.nextInt();
          System.out.println("Are sure? (Y/N)");
          String answer = scanner.next();
          if (answer.equalsIgnoreCase("Y"))
            bookReviewService.deleteAllBookReviewsForUser(userId);
        }
        default -> System.out.println("Invalid option!");
      }
    }
    if (option == 4) {
      System.out.println("How do want to search for book Review 1.ID 2.Book 3.User");
      int op2=scanner.nextInt();
      switch (op2){
        case 1->{
          System.out.println("Enter book review ID: ");
          int reviewId = scanner.nextInt();
          BookReview bookReview = bookReviewService.getBookReviewById(reviewId);
          System.out.println("Review id: " + bookReview.getReview_id() +"\nReview book id: " + bookReview.getBook_id() + "\nReview user id: "+bookReview.getUser_id() + "\nRating: "+ bookReview.getRating());
        }
        case 2->{
          System.out.println("Enter book id: ");
          int bookId = scanner.nextInt();
          List<BookReview> bookReviews = bookReviewService.getAllBookReviewsForBook(bookId);
          for(BookReview bookReview : bookReviews){
            System.out.println("Review id: " + bookReview.getReview_id() +"\nReview book id: " + bookReview.getBook_id() + "\nReview user id: "+bookReview.getUser_id() + "\nRating: "+ bookReview.getRating());
          }
        }
        case 3->{
          System.out.println("Enter user id: ");
          int userId = scanner.nextInt();
          List<BookReview> bookReviews = bookReviewService.getAllBookReviewsForUser(userId);
          for(BookReview bookReview : bookReviews){
            System.out.println("Review id: " + bookReview.getReview_id() +"\nReview book id: " + bookReview.getBook_id() + "\nReview user id: "+bookReview.getUser_id() + "\nRating: "+ bookReview.getRating());
          }
        }
        default->System.out.println("Invalid option!");
      }
    }
  }

  public void categoryMenu() throws SQLException {
    System.out.println("1.Add Category 2.Update Category 3.Remove Category 4.Search Category");
    Scanner scanner = new Scanner(System.in);
    int option = scanner.nextInt();
    if (option == 1) {
      System.out.println("Enter name: ");
      String name = scanner.next();
      System.out.println("Enter description: ");
      String description = scanner.nextLine();
      description = scanner.nextLine();
      categoryService.addCategory(name, description);
    }
    if (option == 2) {
      System.out.println("Enter which you want to update the category 1.Name 2.Description");
      int option2 = scanner.nextInt();
      switch (option2) {
        case 1 -> {
          System.out.println("Enter category id: ");
          int categoryId = scanner.nextInt();
          System.out.println("Enter new name: ");
          String newName = scanner.next();
          categoryService.updateCategoryName(categoryId, newName);
        }
        case 2 -> {
          System.out.println("Enter category id: ");
          int categoryId = scanner.nextInt();
          System.out.println("Enter new description: ");
          String newDescription = scanner.nextLine();
          newDescription =scanner.nextLine();
          categoryService.updateCategoryDescription(categoryId, newDescription);
        }
        default -> System.out.println("Invalid option!");
      }
    }
    if (option == 3) {
      System.out.println("Enter category id: ");
      int categoryId = scanner.nextInt();
      System.out.println("Are sure? (Y/N)");
      String answer = scanner.next();
      if (answer.equalsIgnoreCase("Y"))
        categoryService.deleteCategory(categoryId);
    }
    if (option == 4) {
      System.out.println("Enter hwo you want to search for category: 1.ID 2.Keyword 3.All categories");
      int option2 = scanner.nextInt();
      switch (option2) {
        case 1 -> {
          System.out.println("Enter category id: ");
          int categoryId = scanner.nextInt();
          Category category = categoryService.getCategoryById(categoryId);
          System.out.println("Category id: " + category.getCategory_id()+"\nCategory name: " + category.getName() + "\nCategory Description: " + category.getDescription());
        }
        case 2 -> {
          List<Category> categories = categoryService.getAllCategories();
          for(Category category : categories){
            System.out.println("Category id: " + category.getCategory_id()+"\nCategory name: " + category.getName() + "\nCategory Description: " + category.getDescription());
          }
        }
        case 3 -> {
          System.out.println("Enter keyword: ");
          String keyword = scanner.next();
          List<Category> categories = categoryService.searchCategories(keyword);
          for(Category category : categories){
            System.out.println("Category id: " + category.getCategory_id()+"\nCategory name: " + category.getName() + "\nCategory Description: " + category.getDescription());
          }
        }
        default -> System.out.println("Invalid option!");
      }
    }
  }
}