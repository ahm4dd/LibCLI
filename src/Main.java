import java.sql.SQLException;
import java.util.*;
import java.util.Scanner;

public class Main {

    private static void register() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        System.out.println("Enter username: ");
        String username = scanner.next();
        System.out.println("Enter password: ");
        String password = scanner.next();
        System.out.println("Enter email: ");
        String email = scanner.next();
        userService.registerUser(username,password,email,"Member");
    }

    private static User login() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        System.out.println("Enter username: ");
        String username = scanner.next();
        System.out.println("Enter password: ");
        String password = scanner.next();
        return userService.loginUser(username,password);
    }


    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        DBconnector dBconnector = new DBconnector();
        UserService userService = new UserService();
        while(true) {
            System.out.println("Enter 1.Register 2.Login: ");
            int option = scanner.nextInt();
            if(option == 0) {
                break;
            }
            switch (option) {
                case 1 -> register();
                case 2 -> {
                    User user = login();
                    user.accessLibrarySystem();
                }
            }
        }
    }
}