import java.sql.*;
public class DBconnector {
    public static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_system?user=root&password=root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Connection getConn()
    {
        return conn;
    }
}
