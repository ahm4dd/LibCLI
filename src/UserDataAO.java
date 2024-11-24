import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class UserDataAO {

    public User login(String username, String password) throws SQLException {
        String query = "Select * from users where username = ? and password = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setString(1,username);
        stmt.setString(2,password);
        ResultSet resultSet = stmt.executeQuery();
        while(resultSet.next())
        {
            int user_id = resultSet.getInt("user_id");
            String email = resultSet.getString("email");
            String userType = resultSet.getString("role");

            if(userType.equalsIgnoreCase("Admin"))
            {
                Admin admin = new Admin(user_id,username,password,email,userType);
                return admin;
            }
            else if(userType.equalsIgnoreCase("Member"))
            {
                Member member = new Member(user_id,username,password,email,userType);
                return member;
            }

        }
        return null;
    }

    public void updatePassword(int userId, String oldPassword, String newPassword) throws SQLException {
        String query1 = "Select password from users where userId = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query1);
        ResultSet resultSet1 = stmt.executeQuery();
        if(resultSet1.next()){
            if(oldPassword.equalsIgnoreCase(resultSet1.getString("password"))){
                String query2 = "Update users set password = ? where usersId = "+userId;
                PreparedStatement stmt2 = DBconnector.conn.prepareStatement(query2);
                int resultSet2 = stmt2.executeUpdate();
                System.out.println("Password changed successfully!");
            }
            else if(oldPassword.equalsIgnoreCase(resultSet1.getString("password")))
                System.out.println("Error, the old password you provided doesn't match the current password!");
        }
    }


    public void deleteUser(User user) throws SQLException {
        String query = "Select * from users where username = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setString(1, user.getUsername());
        int resultSet = stmt.executeUpdate();
    }

    public void addUser(String username, String password, String email, String userType) throws SQLException {

        if(getUserByUsername(username) != null){
            System.out.println("Username already exists!");
        }

        else {
            String query = "insert into users (username, password, email, role) VALUES (?,?,?,?)";
            PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.setString(4, userType);

            int resultSet = stmt.executeUpdate();
            System.out.println("User added successfully!");
        }
    }

    public User getUserByUsername(String username) throws SQLException {
        String query = "Select * from users where username = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setString(1, username);
        ResultSet resultSet = stmt.executeQuery();
        while(resultSet.next())
        {
            int user_id = resultSet.getInt("user_id");
            String username1 = resultSet.getString("username");
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

    public boolean authenticateUser(String username, String password) throws SQLException {
        String query = "Select * from users where username = ? and password = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        ResultSet resultSet = stmt.executeQuery();
        while(resultSet.next())
        {
            return true;
        }
        return false;
    }
}
