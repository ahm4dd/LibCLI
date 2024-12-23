import java.sql.SQLException;
import java.util.List;

public class ReviewService {
    private ProductService productService = new ProductService();
    private UserService userService = new UserService();
    private ReviewDataAO reviewDataAO = new ReviewDataAO();
    public void addReview(int user_id, int bookId, int rating) throws SQLException {
        reviewDataAO.addReview(user_id,bookId,rating);
    }

    public void deleteReview(int reviewId) throws SQLException, SQLException {
        if(!checkIfReviewExists(reviewId))
            System.out.println("Review doesn't exist");
        else
            reviewDataAO.deleteReview(reviewId);
    }

    public void deleteReviewForUser(int reviewId, int userId) throws SQLException {
        if(!checkIfReviewExists(reviewId))
            System.out.println("Review doesn't exist");
        if(!checkIfReviewBelongsToUser(reviewId, userId))
            System.out.println("Review doesn't belong to user");
        else
            reviewDataAO.deleteReview(reviewId);
    }

    public void updateReviewProductId(int reviewId, int productId) throws SQLException {
        if(!checkIfReviewExists(reviewId))
            System.out.println("Review doesn't exist");
        else
            reviewDataAO.updateReviewProductId(reviewId, productId);
    }

    public void updateReviewForUser(int reviewId, int userId, int newRating) throws SQLException {
        if(!checkIfReviewExists(reviewId))
            System.out.println("Review doesn't exist");
        if(!checkIfReviewBelongsToUser(reviewId, userId))
            System.out.println("Review doesn't belong to user");
        else
            reviewDataAO.updateReviewRating(reviewId, newRating);
    }

    private boolean checkIfReviewBelongsToUser(int reviewId, int userId) throws SQLException {
        return reviewDataAO.getReviewById(reviewId).getUser_id() == userId;
    }

    public Review getReviewById(int reviewId) throws SQLException {
        if(!checkIfReviewExists(reviewId)) {
            System.out.println("Review doesn't exist");
            return null;
        }
        else
            return reviewDataAO.getReviewById(reviewId);
    }

    public List<Review> getAllReviewsForProduct(int productId) throws SQLException {
        if(productService.getProductById(productId) == null) {
            System.out.println("Product doesn't exist");
            return null;
        }
        else
            return reviewDataAO.getAllReviewsForProduct(productId);
    }

    public List<Review> getAllReviewsForUser(int userId) throws SQLException {
        if(userService.getUserById(userId) == null) {
            System.out.println("User doesn't exist");
            return null;
        }
        else
            return reviewDataAO.getAllReviewsForUser(userId);
    }

    public void deleteAllReviewsForProduct(int productId) throws SQLException {
        if(productService.getProductById(productId) == null)
            System.out.println("Product doesn't exist");
        else
            reviewDataAO.deleteAllReviewsForProduct(productId);
    }

    public void deleteAllReviewsForUser(int userId) throws SQLException {
        if(userService.getUserById(userId) == null)
            System.out.println("User doesn't exist");
        else
            reviewDataAO.deleteAllReviewsForUser(userId);
    }

    public boolean checkIfReviewExists(int reviewId) throws SQLException {
        if(reviewDataAO.getReviewById(reviewId) == null)
            return false;
        else
            return true;
    }
}
