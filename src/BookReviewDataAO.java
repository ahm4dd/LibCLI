import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookReviewDataAO {

    public void addBookReview(int user_id,int bookId,int rating) throws SQLException {
        String query = "insert into book_review (book_id, user_id, rating) VALUES (?, ?, ?)";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, bookId);
        stmt.setInt(2, user_id);
        stmt.setInt(3, rating);
        int resultSet = stmt.executeUpdate();
    }

    public void deleteBookReview(int reviewId) throws SQLException {
        String query = "delete from book_review where review_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, reviewId);
        int resultSet = stmt.executeUpdate();
    }

    public BookReview getBookReviewById(int reviewId) throws SQLException {
        String query = "select * from book_review where review_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, reviewId);
        ResultSet resultSet = stmt.executeQuery();
        while(resultSet.next())
        {
            int review_id = resultSet.getInt("review_id");
            int user_id = resultSet.getInt("user_id");
            int book_id = resultSet.getInt("book_id");
            int rating = resultSet.getInt("rating");
            BookReview bookReview = new BookReview(review_id, user_id, book_id, rating);
            return bookReview;
        }
        return null;
    }

    public void updateBookReviewUserId(int reviewId, int newUserId) throws SQLException {
        String query = "Update book_review set user_id = ? where review_id = "+reviewId;
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,newUserId);
        int resultSet = stmt.executeUpdate();
    }

    public void updateBookReviewBookId(int reviewId, int newBookId) throws SQLException {
        String query = "Update book_review set book_id = ? where review_id = "+reviewId;
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,newBookId);
        int resultSet = stmt.executeUpdate();
    }

    public void updateBookReviewRating(int reviewId, int newRating) throws SQLException {
        String query = "Update book_review set rating = ? where review_id = "+reviewId;
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,newRating);
        int resultSet = stmt.executeUpdate();
    }

    public List<BookReview> getAllBookReviewsForBook(int bookId) throws SQLException {
        List<BookReview> reviews = new ArrayList<BookReview>();
        String query = "select * from book_review where book_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, bookId);
        ResultSet resultSet = stmt.executeQuery();
        while(resultSet.next())
        {
            int review_id = resultSet.getInt("review_id");
            int user_id = resultSet.getInt("user_id");
            int rating = resultSet.getInt("rating");
            BookReview bookReview = new BookReview(review_id, user_id, bookId, rating);
            reviews.add(bookReview);
        }
        return reviews;
    }

    public List<BookReview> getAllBookReviewsForUser(int userId) throws SQLException {
        List<BookReview> reviews = new ArrayList<BookReview>();
        String query = "select * from book_review where user_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, userId);
        ResultSet resultSet = stmt.executeQuery();
        while(resultSet.next())
        {
            int review_id = resultSet.getInt("review_id");
            int book_id = resultSet.getInt("book_id");
            int rating = resultSet.getInt("rating");
            BookReview bookReview = new BookReview(review_id, userId, book_id, rating);
            reviews.add(bookReview);
        }
        return reviews;
    }

    public void deleteAllBookReviewsForBook(int bookId) throws SQLException {
        String query = "delete from book_review where book_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, bookId);
        int resultSet = stmt.executeUpdate();
    }

    public void deleteAllBookReviewsForUser(int userId) throws SQLException {
        String query = "delete from book_review where user_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, userId);
        int resultSet = stmt.executeUpdate();
    }

}
