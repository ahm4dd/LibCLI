import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDataAO {

    public void updatePassword(int userId, String newPassword) throws SQLException {
        String query = "UPDATE users SET password = ? WHERE user_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setString(1, newPassword);
        stmt.setInt(2, userId);
        int resultSet = stmt.executeUpdate();
        if (resultSet > 0) {
            System.out.println("Password changed successfully!");
        } else {
            System.out.println("Password update failed. User not found.");
        }
    }

    public void updateUsername(int userId, String newUsername) throws SQLException {
        String query = "UPDATE users SET username = ? WHERE user_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setString(1, newUsername);
        stmt.setInt(2, userId);
        int resultSet = stmt.executeUpdate();
        if (resultSet > 0) {
            System.out.println("Username changed successfully!");
        } else {
            System.out.println("Username update failed. User not found.");
        }
    }

    public void updateEmail(int userId, String newEmail) throws SQLException {
        String query = "UPDATE users SET email = ? WHERE user_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setString(1, newEmail);
        stmt.setInt(2, userId);
        int resultSet = stmt.executeUpdate();
        if (resultSet > 0) {
            System.out.println("Email changed successfully!");
        } else {
            System.out.println("Email update failed. User not found.");
        }
    }

    public void deleteUser(int userId) throws SQLException {
        String query = "DELETE FROM users WHERE user_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, userId);
        int resultSet = stmt.executeUpdate();
        if (resultSet > 0) {
            System.out.println("User deleted successfully!");
        } else {
            System.out.println("Delete failed. User not found.");
        }
    }

    public void addUser(String username, String password, String email, String role) throws SQLException {
        String query = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, password);
        stmt.setString(3, email);
        stmt.setString(4, role);
        int resultSet = stmt.executeUpdate();
        if (resultSet > 0) {
            System.out.println("User added successfully!");
        } else {
            System.out.println("Failed to add user.");
        }
    }

    public User getUserByUsername(String username) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setString(1, username);
        ResultSet resultSet = stmt.executeQuery();
        if (resultSet.next()) {
            return createUserFromResultSet(resultSet);
        }
        return null;
    }

    public User getUserByEmail(String email) throws SQLException {
        String query = "SELECT * FROM users WHERE email = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setString(1, email);
        ResultSet resultSet = stmt.executeQuery();
        if (resultSet.next()) {
            return createUserFromResultSet(resultSet);
        }
        return null;
    }

    public User getUserById(int userId) throws SQLException {
        String query = "SELECT * FROM users WHERE user_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, userId);
        ResultSet resultSet = stmt.executeQuery();
        if (resultSet.next()) {
            return createUserFromResultSet(resultSet);
        }
        return null;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        Statement stmt = DBconnector.conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        while (resultSet.next()) {
            users.add(createUserFromResultSet(resultSet));
        }
        return users;
    }

    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        int userId = resultSet.getInt("user_id");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String email = resultSet.getString("email");
        String role = resultSet.getString("role");
        if ("Admin".equalsIgnoreCase(role)) {
            return new Admin(userId, username, password, email, role);
        } else {
            return new Member(userId, username, password, email, role);
        }
    }
}
