
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


    public List<Book> getBooksByAuthor(int authorId) throws SQLException {
        List<Book> books = new ArrayList<Book>();
        String query = "Select * from books inner join author ON author.author_id = "+ authorId + "AND books.author_id = author.author_id";
        Statement stmt = DBconnector.conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        while(resultSet.next()){
            int bookId = resultSet.getInt("books.book_id");
            String title = resultSet.getString("books.title");
            int price = resultSet.getInt("books.price");
            String isbn = resultSet.getString("books.isbn");
            int availableCopies = resultSet.getInt("books.available_copies");
            Book book = new Book(bookId,title,authorId,isbn,availableCopies);
            books.add(book);
        }
        return books;
    }

    public void deleteAuthor(int authorId) throws SQLException {
        String query = "delete from author where author_id =" + authorId;
        Statement stmt = DBconnector.conn.createStatement();
        int resultSet = stmt.executeUpdate(query);
    }

    public void updateFirstName(int authorId, String newFirstName) throws SQLException {
        String query = "UPDATE author SET first_name = ? where authorId = " + authorId;
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
