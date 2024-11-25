import javax.swing.*;
import java.sql.SQLException;
import java.util.Scanner;
public class Admin extends User{
    private UserService userService = new UserService();
    private BookService bookService = new BookService();
    Admin(int userId, String username, String password, String email, String userType) {
        super(userId, username, password, email, userType);
    }

    public void accessLibrarySystem() throws SQLException {
        System.out.println("1.Add book 2.Remove book 3.Manage user 4.Update book 5.View all users: ");

        {
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            switch(option)
            {
                case 1 -> addBook();
                case 2 -> removeBook();
            }
        }
    }

    public void addBook() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter title: ");
        String title = scanner.nextLine();
        System.out.println("Enter author: ");
        String author = scanner.nextLine();
        System.out.println("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.println("Enter available copies: ");
        int availableCopies = scanner.nextInt();
        bookService.addBook(title, author, isbn, availableCopies);
    }

    public void removeBook() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book id: ");
        int bookId = scanner.nextInt();
        bookService.deleteBook(bookId);
    }

    public void manageMember(Member member){

    }

    public void updateBook(Book book) {

    }

    public void viewAllUsers() {

    }

}
