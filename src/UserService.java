import java.sql.SQLException;
import java.util.List;

import static java.lang.System.exit;

public class UserService {
  private UserDataAO userDataAO = new UserDataAO();

  public void registerUser(String username, String password, String email, String userType) throws SQLException {

    if (userDataAO.getUserByUsername(username) != null)
      System.out.println("Username already exists!");
    else if (userDataAO.getUserByEmail(email) != null)
      System.out.println("Email already exists!");
    else
      userDataAO.addUser(username, password, email, userType);
  }

  public void updatePassword(int userId, String oldPassword, String newPassword) throws SQLException {

    if (userDataAO.getUserById(userId) == null)
      System.out.println("User not found!");
    else if (!userDataAO.getUserById(userId).getPassword().equalsIgnoreCase(oldPassword))
      System.out.println("Incorrect old password!");
    else if (userDataAO.getUserById(userId).getPassword().equalsIgnoreCase(newPassword))
      System.out.println("New password cannot be the same as the old password!");
    else
      userDataAO.updatePassword(userId, newPassword);
  }

  public void updateUsername(int userId,String oldUsername,String password, String newUsername) throws SQLException {
    if(!authenticateUser(oldUsername,password)) {
      System.out.println("Incorrect password!");
    }
    if (userDataAO.getUserById(userId) == null)
      System.out.println("User not found!");
    else if (!userDataAO.getUserById(userId).getUsername().equalsIgnoreCase(oldUsername))
      System.out.println("Incorrect old username!");
    else if (userDataAO.getUserByUsername(newUsername) != null)
      System.out.println("The new username already exists!");
    else
      userDataAO.updateUsername(userId, newUsername);
  }

  public void updateEmail(int userId, String password,String oldEmail, String newEmail) throws SQLException {
    if(!authenticateUser(oldEmail,password)) {
      System.out.println("Incorrect password!");
    }
    if (userDataAO.getUserById(userId) == null)
      System.out.println("User not found!");
    else if (!userDataAO.getUserById(userId).getEmail().equalsIgnoreCase(oldEmail))
      System.out.println("Incorrect old email!");
    else if (userDataAO.getUserByEmail(newEmail) != null)
      System.out.println("The new email already exists!");
    else
      userDataAO.updateEmail(userId, newEmail);
  }

  public void deleteUser(int userId) throws SQLException {
    if(userDataAO.getUserById(userId).getUserType().equalsIgnoreCase("Admin"))
      System.out.println("Admin cannot be deleted!");
    if (userDataAO.getUserById(userId) == null)
      System.out.println("User not found!");
    else
      userDataAO.deleteUser(userId);
  }

  public User getUserById(int userId) throws SQLException {
    if (userDataAO.getUserById(userId) == null)
      System.out.println("User not found!");
    if (userDataAO.getUserById(userId).getUserType().equalsIgnoreCase("Admin"))
      System.out.println("Admin cannot be retrieved!");
    return userDataAO.getUserById(userId);
  }

  public User getUserByEmail(String email) throws SQLException {
    if (userDataAO.getUserByEmail(email) == null)
      System.out.println("User not found!");
    if (userDataAO.getUserByEmail(email).getUserType().equalsIgnoreCase("Admin"))
      System.out.println("Admin cannot be retrieved!");
    return userDataAO.getUserByEmail(email);
  }

  public User getUserByUsername(String username) throws SQLException {
    if (userDataAO.getUserByUsername(username) == null)
      System.out.println("User not found!");
    if (userDataAO.getUserByUsername(username).getUserType().equalsIgnoreCase("Admin"))
      System.out.println("Admin cannot be retrieved!");
    return userDataAO.getUserByUsername(username);
  }

  public List<User> getAllUsers() throws SQLException {
    return userDataAO.getAllUsers();
  }

  public boolean authenticateUser(String username, String password) throws SQLException {
    User user = userDataAO.getUserByUsername(username);

    if (user == null) {
      System.out.println("User not found!");
      return false;
    } else if (user.getPassword().equalsIgnoreCase(password)) {
      return true;
    } else
      return false;
  }

  public User loginUser(String username, String password) throws SQLException {
    User user = userDataAO.getUserByUsername(username);
    if (authenticateUser(username, password)) {
      return user;
    } else
      return null;
  }

  public void logout(){
    exit(0);
  }
}
