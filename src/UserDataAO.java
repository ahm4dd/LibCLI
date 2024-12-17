import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class UserDataAO {

    public void updatePassword(int userId, String oldPassword, String newPassword) throws SQLException {
        String query1 = "Select password from users where user_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query1);
        stmt.setInt(1,userId);
        ResultSet resultSet1 = stmt.executeQuery();
        if(resultSet1.next()){
                String query2 = "Update users set password = ? where user_id = "+userId;
                PreparedStatement stmt2 = DBconnector.conn.prepareStatement(query2);
                stmt2.setString(1,newPassword);
                int resultSet2 = stmt2.executeUpdate();
                System.out.println("Password changed successfully!");
        }
    }

    public void updateUsername(int userId, String oldUsername, String newUsername) throws SQLException {
            String query1 = "Select username from users where user_id = ?";
            PreparedStatement stmt = DBconnector.conn.prepareStatement(query1);
            stmt.setInt(1,userId);
            ResultSet resultSet1 = stmt.executeQuery();
            if (resultSet1.next()) {
                if (oldUsername.equalsIgnoreCase(resultSet1.getString("username"))) {
                    String query2 = "Update users set username = ? where user_id = " + userId;
                    PreparedStatement stmt2 = DBconnector.conn.prepareStatement(query2);
                    stmt2.setString(1, newUsername);
                    int resultSet2 = stmt2.executeUpdate();
                    System.out.println("Username changed successfully!");
                } else if (!(oldUsername.equalsIgnoreCase(resultSet1.getString("username"))))
                    System.out.println("Error, the old username you provided doesn't match the current username!");
        }
    }

    public void updateEmail(int userId, String oldEmail, String newEmail) throws SQLException {
            String query1 = "Select email from users where user_id = ?";
            PreparedStatement stmt = DBconnector.conn.prepareStatement(query1);
            stmt.setInt(1,userId);
            ResultSet resultSet1 = stmt.executeQuery();
            if (resultSet1.next()) {
                if (oldEmail.equalsIgnoreCase(resultSet1.getString("email"))) {
                    String query2 = "Update users set email = ? where user_id = " + userId;
                    PreparedStatement stmt2 = DBconnector.conn.prepareStatement(query2);
                    stmt2.setString(1, newEmail);
                    int resultSet2 = stmt2.executeUpdate();
                    System.out.println("Email changed successfully!");
                } else if (!(oldEmail.equalsIgnoreCase(resultSet1.getString("email"))))
                    System.out.println("Error, the old email you provided doesn't match the current email!");
        }
    }

    public void deleteUser(int userId) throws SQLException {
        String query = "delete from users where user_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, userId);
        int resultSet = stmt.executeUpdate();
    }

    public void addUser(String username, String password, String email, String userType) throws SQLException {
            String query = "insert into users (username, password, email, role) VALUES (?,?,?,?)";
            PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.setString(4, userType);
            int resultSet = stmt.executeUpdate();
            System.out.println("User added successfully!");
    }

    public User getUserByUsername(String username) throws SQLException {
        String query = "Select * from users where username = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setString(1, username);
        ResultSet resultSet = stmt.executeQuery();
        while(resultSet.next())
        {
            int user_id = resultSet.getInt("user_id");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            String role = resultSet.getString("role");
            if(role.equalsIgnoreCase("Admin")){
                Admin admin = new Admin(user_id,username,password,email,role);
                return admin;
            }
            else if(role.equalsIgnoreCase("Member")){
                Member member = new Member(user_id,username,password,email,role);
                return member;
            }
        }
        return null;
    }

    public User getUserByEmail(String email) throws SQLException {
        String query = "Select * from users where email = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setString(1, email);
        ResultSet resultSet = stmt.executeQuery();
        while(resultSet.next())
        {
            int user_id = resultSet.getInt("user_id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role");
            if(role.equalsIgnoreCase("Admin")){
                Admin admin = new Admin(user_id,username,password,email,role);
                return admin;
            }
            else if(role.equalsIgnoreCase("Member")){
                Member member = new Member(user_id,username,password,email,role);
                return member;
            }
        }
        return null;
    }

    public User getUserById(int userId) throws SQLException {
        String query = "Select * from users where user_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, userId);
        ResultSet resultSet = stmt.executeQuery();
        while(resultSet.next())
        {
            int user_id = resultSet.getInt("user_id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            String role = resultSet.getString("role");
            if(role.equalsIgnoreCase("Admin")){
                Admin admin = new Admin(user_id,username,password,email,role);
                return admin;
            }
            else if(role.equalsIgnoreCase("Member")){
                Member member = new Member(user_id,username,password,email,role);
                return member;
            }
        }
        return null;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<User>();
        String query = "Select * from users";
        Statement stmt = DBconnector.conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        while(resultSet.next())
        {
            int user_id = resultSet.getInt("user_id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            String userType = resultSet.getString("role");
            
            if(userType.equalsIgnoreCase("Admin")){
                Admin admin = new Admin(user_id,username,password,email,userType);
                users.add(admin);
            }
            else if(userType.equalsIgnoreCase("Member"))
            {
                Member member = new Member(user_id,username,password,email,userType);
                users.add(member);
            }
        }
        return users;
    }
}
