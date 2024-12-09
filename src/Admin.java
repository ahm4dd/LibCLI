import java.util.List;
import java.sql.*;
import java.util.Scanner;
public class Admin extends User{
    private UserService userService = new UserService();
    private BookService bookService = new BookService();

    Admin(int userId, String username, String password, String email, String userType) {
        super(userId, username, password, email, userType);
    }

    public void accessLibrarySystem() throws SQLException {
        System.out.println(
                "1.Add book 2.Remove book 3.Manage user 4.Update book 5.View all users\n" +
                "6.Search book 7.Search user 8.Search transaction 9.Add transaction 10.Modify transaction\n" +
                "11.View all books 12.View all transactions: ");
        {
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            switch(option)
            {
                case 1 -> addBook();
                case 2 -> removeBook();
                case 3 -> manageMember();
                case 4 -> updateBook();
                case 5 -> viewAllUsers();
                case 6 -> searchBook();
                case 7 -> test();
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
        String isbn = scanner.nextLine();
        System.out.println("Enter available copies: ");
        int availableCopies = scanner.nextInt();
        bookService.addBook(title, authorId, categoryId,isbn, availableCopies);
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
        if(user == null)
        {
            System.out.println("couldn't find the user");
        }

        else if (user.getUserType().equalsIgnoreCase("Admin")) {
            System.out.println("Cannot display an admin user!");
        }

        else{
            System.out.println("User id: "+ user.getUserId() +"\nUsername: "+ user.getUsername()+"\nPassword: " + user.getPassword() + "\nEmail: "+ user.getEmail());

            System.out.println("Enter what you want to change 1.Username 2.Password 3.Email: ");
            int option = scanner.nextInt();
            switch (option)
            {
                case 1 -> {
                    System.out.println("Enter new username: ");
                    String newUsername = scanner.next();
                    userService.updateUsername(user.getUserId(),user.getUsername(),newUsername);
                }
                case 2 -> {
                    System.out.println("Enter new password: ");
                    String newPassword = scanner.next();
                    userService.updatePassword(user.getUserId(),user.getPassword(),newPassword);
                }
                case 3 -> {
                    System.out.println("Enter new email: ");
                    String newEmail = scanner.next();
                    userService.updateEmail(user.getUserId(),user.getEmail(),newEmail);
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
        if(book == null)
        {
            System.out.println("Couldn't find the book provided!");
        }
        else
        {
            System.out.println("Book id: "+book.getBookId()+"\nTitle: "+book.getTitle()+"\nAuthor: "+book.getAuthor()+"ISBN: "+book.getIsbn()+"\nAvailable copies: "+book.getAvailableBooks());
            System.out.println("\n1.Enter what you want to change 1.Title 2.Author 3.ISBN 4.Available copies: ");
            int option = scanner.nextInt();

            switch (option)
            {
                case 1 ->
                {
                    System.out.println("Enter new title: ");
                    String newTitle = scanner.next();
                    bookService.updateBookTitle(book.getBookId(),newTitle);
                }

                case 2 ->
                {
                    System.out.println("Enter new author: ");
                    int newAuthorId = scanner.nextInt();
                    bookService.updateBookAuthor(book.getBookId(),newAuthorId);
                }

                case 3 ->
                {
                    System.out.println("Enter new ISBN: ");
                    String newIsbn = scanner.next();
                    bookService.updateBookIsbn(book.getBookId(),newIsbn);
                }

                case 4 ->
                {
                    System.out.println("Enter new available copies: ");
                    int newAvailableCopies = scanner.nextInt();
                    bookService.updateAvailableCopies(book.getBookId(),newAvailableCopies);
                }
            }
        }
    }

    public void viewAllUsers() throws SQLException {
        List<User> users = userService.getAllUsers();
        for(User user : users)
        {
            if(user.getUserType().equalsIgnoreCase("Member"))
                System.out.println("------------------------------\nUser Id: "+ user.getUserId() +"\nUsername: "+ user.getUsername()+"\nPassword: " + user.getPassword() + "\nEmail: "+ user.getEmail());
            else if(user.getUserType().equalsIgnoreCase("Admin"))
                System.out.println("------------------------------\nUser Id: "+ user.getUserId() +"\nUsername: "+ user.getUsername()+"\nEmail: "+ user.getEmail());
        }
    }

    public void searchBook() throws SQLException {
        System.out.println("Enter book title: ");
        Scanner scanner = new Scanner(System.in);
        String title = scanner.next();
        List<Book> book = bookService.searchBooks(title);
        for(Book book1 : book)
        {
            System.out.println("------------------------------\nBook id: "+book1.getBookId()+"\nTitle: "+book1.getTitle()+"\nAuthor: "+book1.getAuthor()+"ISBN: "+book1.getIsbn()+"\nAvailable copies: "+book1.getAvailableBooks());
        }
    }

    public void viewAllBooks() throws SQLException {
        List<Book> books = bookService.getAllBooks();
        for(Book book : books)
        {
            System.out.println("------------------------------\nBook id: "+book.getBookId()+"\nTitle: "+book.getTitle()+"\nAuthor: "+book.getAuthor()+"ISBN: "+book.getIsbn()+"\nAvailable copies: "+book.getAvailableBooks());
        }
    }

    public void viewTransactions() throws SQLException {

    }
    public void test() throws SQLException {
        BookDataAO bookDataAO = new BookDataAO();
        Author author = bookDataAO.getBookAuthor(1);
        System.out.println("Author id: "+ author.getAuthorId()+ "\nAuthor name: " + author.getFirstName()+" "+ author.getLastName());
    }
}
