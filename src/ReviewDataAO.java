import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDataAO {

    public void addBookReview(int user_id,int product_id,int rating) throws SQLException {
        String query = "insert into reviews (product_id, user_id, rating) VALUES (?, ?, ?)";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, product_id);
        stmt.setInt(2, user_id);
        stmt.setInt(3, rating);
        int resultSet = stmt.executeUpdate();
    }

    public void deleteBookReview(int reviewId) throws SQLException {
        String query = "delete from reviews where review_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, reviewId);
        int resultSet = stmt.executeUpdate();
    }

    public Review getBookReviewById(int reviewId) throws SQLException {
        String query = "select * from reviews where review_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, reviewId);
        ResultSet resultSet = stmt.executeQuery();
        while(resultSet.next())
        {
            int review_id = resultSet.getInt("review_id");
            int user_id = resultSet.getInt("user_id");
            int product_id = resultSet.getInt("product_id");
            int rating = resultSet.getInt("rating");
            Review review = new Review(review_id, user_id, product_id, rating);
            return review;
        }
        return null;
    }


    public void updateBookReviewProductId(int reviewId, int newProductId) throws SQLException {
        String query = "Update book_review set product_id = ? where review_id = "+reviewId;
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,newProductId);
        int resultSet = stmt.executeUpdate();
    }

    public void updateBookReviewRating(int reviewId, int newRating) throws SQLException {
        String query = "Update book_review set rating = ? where review_id = "+reviewId;
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,newRating);
        int resultSet = stmt.executeUpdate();
    }

    public List<Review> getAllBookReviewsForProduct(int productId) throws SQLException {
        List<Review> reviews = new ArrayList<Review>();
        String query = "select * from book_review where product_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, productId);
        ResultSet resultSet = stmt.executeQuery();
        while(resultSet.next())
        {
            int review_id = resultSet.getInt("review_id");
            int user_id = resultSet.getInt("user_id");
            int rating = resultSet.getInt("rating");
            Review review = new Review(review_id, user_id, productId, rating);
            reviews.add(review);
        }
        return reviews;
    }

    public List<Review> getAllBookReviewsForUser(int userId) throws SQLException {
        List<Review> reviews = new ArrayList<Review>();
        String query = "select * from book_review where user_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, userId);
        ResultSet resultSet = stmt.executeQuery();
        while(resultSet.next())
        {
            int review_id = resultSet.getInt("review_id");
            int product_id = resultSet.getInt("product_id");
            int rating = resultSet.getInt("rating");
            Review review = new Review(review_id, userId, product_id, rating);
            reviews.add(review);
        }
        return reviews;
    }

    public void deleteAllBookReviewsForProduct(int productId) throws SQLException {
        String query = "delete from book_review where product_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, productId);
        int resultSet = stmt.executeUpdate();
    }

    public void deleteAllBookReviewsForUser(int userId) throws SQLException {
        String query = "delete from book_review where user_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, userId);
        int resultSet = stmt.executeUpdate();
    }

}
