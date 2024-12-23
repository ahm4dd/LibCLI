
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDataAO {

    public void addAuthor(String firstName, String lastName, String bio) throws SQLException {
        String query = "insert into author (first_name, last_name, bio) VALUES (?, ?, ?)";

        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setString(1, firstName);
        stmt.setString(2, lastName);
        stmt.setString(3, bio);

        int resultSet = stmt.executeUpdate();
    }

    public Author getAuthorById(int authorId) throws SQLException {
        String query = "select * from author where author_id =" + authorId;
        Statement stmt = DBconnector.conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(query);

        while (resultSet.next()) {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String bio = resultSet.getString("bio");
            Author author = new Author(authorId, firstName, lastName, bio);
            return author;
        }
        return null;
    }

    // Retrieve authors by name (either first or last name)
    public List<Author> getAuthorsByName(String name) throws SQLException {
        List<Author> authors = new ArrayList<>();
        String query = "SELECT * FROM author WHERE first_name LIKE ? OR last_name LIKE ?";
        try (PreparedStatement stmt = DBconnector.conn.prepareStatement(query)) {
            stmt.setString(1, "%" + name + "%");
            stmt.setString(2, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                authors.add(new Author(
                        rs.getInt("author_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("bio")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving authors by name: " + e.getMessage());
            throw e;
        }
        return authors;
    }

    public void deleteAuthor(int authorId) throws SQLException {
        String query = "delete from author where author_id =" + authorId;
        Statement stmt = DBconnector.conn.createStatement();
        int resultSet = stmt.executeUpdate(query);
    }

    public void updateFirstName(int authorId, String newFirstName) throws SQLException {
        String query = "UPDATE author SET first_name = ? where author_id = " + authorId;
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setString(1, newFirstName);
        int resultSet = stmt.executeUpdate();
    }

    public void updateLastName(int authorId, String newLastName) throws SQLException {
        String query = "update author set last_name = ? where author_id = " + authorId;
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setString(1, newLastName);
        int resultSet = stmt.executeUpdate();
    }

    public void updateBio(int authorId, String bio) throws SQLException {
        String query = "update author set bio = ? where author_id = " + authorId;
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setString(1, bio);
        int resultSet = stmt.executeUpdate();
    }


    public List<Author> getAllAuthors() throws SQLException {
        List<Author> authors = new ArrayList<Author>();
        String query = "Select * from author";
        Statement stmt = DBconnector.conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        while(resultSet.next()){
            int authorId = resultSet.getInt("author_id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String bio = resultSet.getString("bio");
            Author author = new Author(authorId,firstName,lastName,bio);
            authors.add(author);
        }
        return authors;
    }
}