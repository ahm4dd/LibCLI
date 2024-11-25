import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class BookDataAO {


    public void addBook(String title, String author, String isbn, int availableCopies) throws SQLException {
        if (availableCopies < 0)
        {
            System.out.println("Copies cannot be below 0.");
        }

        else
        {
            String query = "insert into books (title,author,isbn,available_copies) VALUES (?,?,?,?)";
            PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
            stmt.setString(1,title);
            stmt.setString(2,author);
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

    public void updateBookAuthor(int bookId, String newAuthor) throws SQLException {
        String query = "Update books set author = ? where book_id = "+bookId;
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setString(1,newAuthor);
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
            String author = resultSet.getString("author");
            String isbn = resultSet.getString("isbn");
            int availableCopies = resultSet.getInt("available_copies");
            Book book = new Book(bookId1, title, author, isbn, availableCopies);
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
            String author = resultSet.getString("author");
            String isbn = resultSet.getString("isbn");
            int availableCopies = resultSet.getInt("available_copies");
            Book book = new Book(book_id,title,author,isbn,availableCopies);
            books.add(book);

        }
        return books;
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
            String author = resultSet.getString("author");
            String isbn = resultSet.getString("isbn");
            int availableCopies = resultSet.getInt("available_copies");
            Book book = new Book(book_id,title,author,isbn,availableCopies);
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
            String author = resultSet.getString("author");
            int availableCopies = resultSet.getInt("available_copies");
            Book book = new Book(book_id,title,author,isbn,availableCopies);
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
