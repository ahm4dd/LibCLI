import java.sql.SQLException;
import java.util.List;

import static java.lang.System.exit;

public class UserService {
  private UserDataAO userDataAO = new UserDataAO();

  // Register User
  public void registerUser(String username, String password, String email, String role) throws SQLException {
    if (userDataAO.getUserByUsername(username) != null) {
      System.out.println("Username already exists!");
    } else if (userDataAO.getUserByEmail(email) != null) {
      System.out.println("Email already exists!");
    } else if (!role.equalsIgnoreCase("Admin") && !role.equalsIgnoreCase("Member")) {
      System.out.println("Invalid role! Must be either 'Admin' or 'Member'.");
    } else {
      userDataAO.addUser(username, password, email, role);
    }
  }

  // Update Password
  public void updatePassword(int userId, String oldPassword, String newPassword) throws SQLException {
    User user = userDataAO.getUserById(userId);
    if (user == null) {
      System.out.println("User not found!");
    } else if (!user.getPassword().equals(oldPassword)) {
      System.out.println("Incorrect old password!");
    } else if (oldPassword.equals(newPassword)) {
      System.out.println("New password cannot be the same as the old password!");
    } else {
      userDataAO.updatePassword(userId, newPassword);
    }
  }

  // Update Username
  public void updateUsername(int userId, String oldUsername, String password,String newUsername) throws SQLException {
    User user = userDataAO.getUserById(userId);
    if (user == null) {
      System.out.println("User not found!");
    } else if (!user.getUsername().equalsIgnoreCase(oldUsername)) {
      System.out.println("Incorrect old username!");
    } else if (userDataAO.getUserByUsername(newUsername) != null) {
      System.out.println("The new username already exists!");
    } else if (!user.getPassword().equals(password)) {
      System.out.println("Incorrect password!");
    }
    else {
      userDataAO.updateUsername(userId, newUsername);
    }
  }

  // Update Email
  public void updateEmail(int userId, String password,String oldEmail,String newEmail) throws SQLException {
    User user = userDataAO.getUserById(userId);
    if (user == null) {
      System.out.println("User not found!");
    } else if (!user.getEmail().equalsIgnoreCase(oldEmail)) {
      System.out.println("Incorrect old email!");
    } else if (userDataAO.getUserByEmail(newEmail) != null) {
      System.out.println("The new email already exists!");
    } else if (!user.getPassword().equals(password)) {
      System.out.println("Incorrect password!");
    }
    else {
      userDataAO.updateEmail(userId, newEmail);
    }
  }

  // Delete User
  public void deleteUser(int userId) throws SQLException {
    User user = userDataAO.getUserById(userId);
    if (user == null) {
      System.out.println("User not found!");
    } else if ("Admin".equalsIgnoreCase(user.getUserType())) {
      System.out.println("Admin cannot be deleted!");
    } else {
      userDataAO.deleteUser(userId);
    }
  }

  // Get User by ID
  public User getUserById(int userId) throws SQLException {
    User user = userDataAO.getUserById(userId);
    if (user == null) {
      System.out.println("User not found!");
      return null;
    }
    return user;
  }

  // Get User by Email
  public User getUserByEmail(String email) throws SQLException {
    User user = userDataAO.getUserByEmail(email);
    if (user == null) {
      System.out.println("User not found!");
      return null;
    }
    return user;
  }

  // Get User by Username
  public User getUserByUsername(String username) throws SQLException {
    User user = userDataAO.getUserByUsername(username);
    if (user == null) {
      System.out.println("User not found!");
      return null;
    }
    return user;
  }

  // Get All Users
  public List<User> getAllUsers() throws SQLException {
    return userDataAO.getAllUsers();
  }

  // Authenticate User
  public boolean authenticateUser(String username, String password) throws SQLException {
    User user = userDataAO.getUserByUsername(username);
    if (user == null) {
      System.out.println("User not found!");
      return false;
    } else if (!user.getPassword().equals(password)) {
      System.out.println("Incorrect password!");
      return false;
    }
    return true;
  }

  // Login User
  public User loginUser(String username, String password) throws SQLException {
    if (authenticateUser(username, password)) {
      return userDataAO.getUserByUsername(username);
    }
    return null;
  }

  // Logout
  public void logout() {
    exit(0);
  }
}
