import java.sql.SQLException;
import java.util.List;

public class ReviewService {
    private ReviewDataAO reviewDataAO = new ReviewDataAO();
    private BookService bookService = new BookService();
    private UserService userService = new UserService();
    private MagazineService magazineService = new MagazineService();
    public void addBookReview(int user_id,int bookId,int rating) throws SQLException {
        reviewDataAO.addBookReview(user_id,bookId,rating);
    }

    public void deleteBookReview(int reviewId) throws SQLException, SQLException {
        if(!checkIfReviewExists(reviewId))
            System.out.println("Review doesn't exist");
        else
            reviewDataAO.deleteBookReview(reviewId);
    }

    public void deleteBookReviewForUser(int reviewId, int userId) throws SQLException {
        if(!checkIfReviewExists(reviewId))
            System.out.println("Review doesn't exist");
        if(!checkIfBookReviewBelongsToUser(reviewId, userId))
            System.out.println("Review doesn't belong to user");
        else
            reviewDataAO.deleteBookReview(reviewId);
    }

    public void updateBookReviewProductId(int reviewId, int productId) throws SQLException {
        if(!checkIfReviewExists(reviewId))
            System.out.println("Review doesn't exist");
        else
            reviewDataAO.updateBookReviewProductId(reviewId, productId);
    }

    public void updateBookReviewForUser(int reviewId, int userId, int newRating) throws SQLException {
        if(!checkIfReviewExists(reviewId))
            System.out.println("Review doesn't exist");
        if(!checkIfBookReviewBelongsToUser(reviewId, userId))
            System.out.println("Review doesn't belong to user");
        else
            reviewDataAO.updateBookReviewRating(reviewId, newRating);
    }

    private boolean checkIfBookReviewBelongsToUser(int reviewId, int userId) throws SQLException {
        return reviewDataAO.getBookReviewById(reviewId).getUser_id() == userId;
    }

    public Review getBookReviewById(int reviewId) throws SQLException {
        if(!checkIfReviewExists(reviewId)) {
            System.out.println("Review doesn't exist");
            return null;
        }
        else
            return reviewDataAO.getBookReviewById(reviewId);
    }

    public List<Review> getAllBookReviewsForProduct(int productId) throws SQLException {
        if(!bookService.checkIfBookIsAvailable(productId)) {
            System.out.println("Book doesn't exist");
            return null;
        }
        if(!magazineService.checkIfMagazineExists(productId)) {
            System.out.println("Magazine doesn't exist");
            return null;
        }
        else
            return reviewDataAO.getAllBookReviewsForProduct(productId);
    }

    public List<Review> getAllBookReviewsForUser(int userId) throws SQLException {
        if(userService.getUserById(userId) == null) {
            System.out.println("Book doesn't exist");
            return null;
        }
        else
            return reviewDataAO.getAllBookReviewsForUser(userId);
    }

    public void deleteAllBookReviewsForProduct(int productId) throws SQLException {
        if(!bookService.checkIfBookIsAvailable(productId))
            System.out.println("Book doesn't exist");
        else
            reviewDataAO.deleteAllBookReviewsForProduct(productId);
    }

    public void deleteAllBookReviewsForUser(int userId) throws SQLException {
        if(userService.getUserById(userId) == null)
            System.out.println("Book doesn't exist");
        else
            reviewDataAO.deleteAllBookReviewsForUser(userId);
    }

    public boolean checkIfReviewExists(int reviewId) throws SQLException {
        if(reviewDataAO.getBookReviewById(reviewId) == null)
            return false;
        else
            return true;
    }

}
