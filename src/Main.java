import java.sql.SQLException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        DBconnector dBconnector = new DBconnector();
        UserService userService = new UserService();
        User user = userService.getUserById(1);
        ArrayList<User> users = (ArrayList<User>) userService.getAllUsers();
        System.out.println(users.get(0).getUsername());

    }
}