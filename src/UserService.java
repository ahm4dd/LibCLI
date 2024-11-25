import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserDataAO userDataAO = new UserDataAO();

    public void registerUser(String username, String password, String email, String userType) throws SQLException {
        userDataAO.addUser(username,password,email,userType);
    }

    public void updatePassword(int userId, String oldPassword, String newPassword) throws SQLException {
        userDataAO.updatePassword(userId,oldPassword,newPassword);
    }

    public void updateUsername(int userId, String oldUsername, String newUsername) throws SQLException {
        userDataAO.updateUsername(userId,oldUsername,newUsername);
    }

    public void updateEmail(int userId, String oldEmail, String newEmail) throws SQLException {
        userDataAO.updateEmail(userId,oldEmail,newEmail);
    }

    public void deleteUser(int userId) throws SQLException {
        userDataAO.deleteUser(userId);
    }

    public User getUserById(int userId) throws SQLException {
        return userDataAO.getUserById(userId);
    }

    public User getUserByUsername(String username) throws SQLException {
        return userDataAO.getUserByUsername(username);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDataAO.getAllUsers();
    }

    public boolean authenticateUser(String username, String password) throws SQLException {
        return userDataAO.authenticateUser(username,password);
    }

    public User loginUser(String username, String password) throws SQLException {
        return userDataAO.login(username,password);
    }
}
