import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
public class BookDataAO {


    public void addBook(String title, int authorId, String isbn, int availableCopies) throws SQLException {
        if (availableCopies < 0)
        {
            System.out.println("Copies cannot be below 0.");
        }

        else
        {
            String query = "insert into books (title,author_id,isbn,available_copies) VALUES (?,?,?,?)";
            PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
            stmt.setString(1,title);
            stmt.setInt(2,authorId);
            stmt.setString(3,isbn);
            stmt.setInt(4,availableCopies);
            int resultSet = stmt.executeUpdate();
            if(resultSet != 0)
                System.out.println("Added book successfully!");
        }
    }

    public void updateBookTitle(int bookId, String newName) throws SQLException {
        String query = "Update books set title = ? where book_id = "+bookId;
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setString(1,newName);
        int resultSet = stmt.executeUpdate();
        if(resultSet != 0)
            System.out.println("Book title updated successfully!");
    }

    public void updateBookAuthor(int bookId, int newAuthorId) throws SQLException {
        String query = "Update books set author_id = ? where book_id = "+bookId;
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,newAuthorId);
        int resultSet = stmt.executeUpdate();
        if(resultSet != 0)
            System.out.println("Book author updated successfully!");
    }

    public void updateBookIsbn(int bookId, String newIsbn) throws SQLException {
        String query = "Update books set isbn = ? where book_id = "+ bookId;
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setString(1,newIsbn);
        int resultSet = stmt.executeUpdate();
        if(resultSet != 0)
            System.out.println("Book ISBN updated successfully!");
    }


    public void deleteBook(int bookId) throws SQLException {
        String query = "delete from books where book_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,bookId);
        int resultSet = stmt.executeUpdate();
        System.out.println(resultSet);
        if(resultSet != 0)
            System.out.println("Book deleted successfully!");
    }

    public Book getBookById(int bookId) throws SQLException {
        String query = "select * from books where book_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,bookId);

        ResultSet resultSet = stmt.executeQuery();
        while(resultSet.next()) {
            int bookId1 = resultSet.getInt("book_id");
            String title = resultSet.getString("title");
            int authorId = resultSet.getInt("author_id");
            String isbn = resultSet.getString("isbn");
            int availableCopies = resultSet.getInt("available_copies");
            Book book = new Book(bookId1, title, authorId, isbn, availableCopies);
            return book;
        }
        return null;
    }

    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<Book>();
        String query = "Select * from books";
        Statement stmt = DBconnector.conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        while(resultSet.next())
        {
            int book_id = resultSet.getInt("book_id");
            String title = resultSet.getString("title");
            int authorId = resultSet.getInt("author_id");
            String isbn = resultSet.getString("isbn");
            int availableCopies = resultSet.getInt("available_copies");
            Book book = new Book(book_id,title,authorId,isbn,availableCopies);
            books.add(book);

        }
        return books;
    }

    public Author getBookAuthor(int bookId) throws SQLException {
        String query = "Select books.author_id, author.first_name, author.last_name, author.bio from books INNER JOIN author ON books.author_id = author.author_id AND books.book_id = " + bookId;
        Statement stmt = DBconnector.conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        while(resultSet.next()){
            int authorId = resultSet.getInt("books.author_id");
            String firstName = resultSet.getString("author.first_name");
            String lastName = resultSet.getString("author.last_name");
            String bio = resultSet.getString("author.bio");
            Author author = new Author(authorId,firstName,lastName,bio);
            return author;
        }
        return null;
    }
    

    public List<Book> searchBooks(String keyword) throws SQLException {
        List<Book> books = new ArrayList<Book>();
        String query = "select * from books where title like \"%"+keyword+"%\"";
        Statement stmt = DBconnector.conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        while(resultSet.next())
        {
            int book_id = resultSet.getInt("book_id");
            String title = resultSet.getString("title");
            int authorId = resultSet.getInt("author_id");
            String isbn = resultSet.getString("isbn");
            int availableCopies = resultSet.getInt("available_copies");
            Book book = new Book(book_id,title,authorId,isbn,availableCopies);
            books.add(book);
        }
        return books;
    }

    public Book searchBooksByIsbn(String isbn) throws SQLException {
        String query = "select * from books where isbn = "+isbn;
        Statement stmt = DBconnector.conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        while(resultSet.next())
        {
            int book_id = resultSet.getInt("book_id");
            String title = resultSet.getString("title");
            int authorId = resultSet.getInt("author_id");
            int availableCopies = resultSet.getInt("available_copies");
            Book book = new Book(book_id,title,authorId,isbn,availableCopies);
            return book;
        }
        return null;
    }

    public boolean checkIfBookIsAvailable(int bookId) throws SQLException {
        String query = "select * from books where book_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,bookId);
        ResultSet resultSet = stmt.executeQuery();
        while(resultSet.next())
        {
            int availableCopies = resultSet.getInt("available_copies");
            if(availableCopies > 0)
                return true;
        }
        return false;
    }

    public void updateAvailableCopies(int bookId, int newCopies) throws SQLException {
        String query = "update books set available_copies = ? where book_id = "+ bookId;
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,newCopies);
        int resultSet = stmt.executeUpdate();
        if(resultSet !=0)
            System.out.println("Copies updated successfully!");
    }
}
