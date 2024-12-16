import java.sql.SQLException;
import java.util.List;

import static java.lang.System.exit;

public class UserService {
  private UserDataAO userDataAO = new UserDataAO();

  public void registerUser(String username, String password, String email, String userType) throws SQLException {

    if (getUserByUsername(username) != null)
      System.out.println("Username already exists!");
    else if (getUserByEmail(email) != null)
      System.out.println("Email already exists!");
    else
      userDataAO.addUser(username, password, email, userType);
  }

  public void updatePassword(int userId, String oldPassword, String newPassword) throws SQLException {

    if (getUserById(userId) == null)
      System.out.println("User not found!");
    else if (getUserById(userId).getPassword() != oldPassword)
      System.out.println("Incorrect old password!");
    else if (getUserById(userId).getPassword() == newPassword)
      System.out.println("New password cannot be the same as the old password!");
    else
      userDataAO.updatePassword(userId, oldPassword, newPassword);
  }

  public void updateUsername(int userId, String oldUsername, String newUsername) throws SQLException {

    if (getUserById(userId) == null)
      System.out.println("User not found!");
    else if (getUserById(userId).getUsername() != oldUsername)
      System.out.println("Incorrect old username!");
    else if (getUserByUsername(newUsername) != null)
      System.out.println("The new username already exists!");
    else
      userDataAO.updateUsername(userId, oldUsername, newUsername);
  }

  public void updateEmail(int userId, String oldEmail, String newEmail) throws SQLException {

    if (getUserById(userId) == null)
      System.out.println("User not found!");
    else if (getUserById(userId).getEmail() != oldEmail)
      System.out.println("Incorrect old email!");
    else if (getUserByEmail(newEmail) != null)
      System.out.println("The new email already exists!");
    else
      userDataAO.updateEmail(userId, oldEmail, newEmail);
  }

  public void deleteUser(int userId) throws SQLException {

    if (getUserById(userId) == null)
      System.out.println("User not found!");

    else
      userDataAO.deleteUser(userId);
  }

  public User getUserById(int userId) throws SQLException {
    return userDataAO.getUserById(userId);
  }

  public User getUserByEmail(String email) throws SQLException {
    return userDataAO.getUserByEmail(email);
  }

  public User getUserByUsername(String username) throws SQLException {
    return userDataAO.getUserByUsername(username);
  }

  public List<User> getAllUsers() throws SQLException {
    return userDataAO.getAllUsers();
  }

  public boolean authenticateUser(String username, String password) throws SQLException {
    User user = getUserByUsername(username);

    if (user == null) {
      System.out.println("User not found!");
      return false;
    } else if (user.getPassword() == password) {
      System.out.println("Logged in successfully!");
      return false;
    } else
      return true;
  }

  public User loginUser(String username, String password) throws SQLException {
    User user = getUserByUsername(username);
    if (authenticateUser(username, password)) {
      return user;
    } else
      return null;
  }

  public void logout(){
    exit(0);
  }
}
