import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public void updateBookTitle(int bookId, String newName) {

    }

    public void updateBookAuthor(int bookId, String newAuthor) {

    }

    public void updateBookIsbn(int bookId, String newIsbn) {

    }

    public void updateBookAvailableCopies(int bookId, int availableCopies) {

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

    public List<Book> getAllBooks() {
        return null;
    }

    public List<Book> searchBooks(String keyword) {
        return null;
    }

    public boolean checkIfBookIsAvailable(int bookId) {
        return false;
    }

    public void updateAvailableCopies(int bookId, int newCopies) {

    }
}
