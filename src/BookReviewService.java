import java.sql.SQLException;
import java.util.List;

public class BookReviewService {
    private BookReviewDataAO bookReviewDataAO = new BookReviewDataAO();
    private BookService bookService = new BookService();
    private UserService userService = new UserService();
    public void addBookReview(int user_id,int bookId,int rating) throws SQLException {
        bookReviewDataAO.addBookReview(user_id,bookId,rating);
    }

    public void deleteBookReview(int reviewId) throws SQLException, SQLException {
        if(!checkIfBookReviewExists(reviewId))
            System.out.println("Review doesn't exist");
        else
            bookReviewDataAO.deleteBookReview(reviewId);
    }

    public void deleteBookReviewForUser(int reviewId, int userId) throws SQLException {
        if(!checkIfBookReviewExists(reviewId))
            System.out.println("Review doesn't exist");
        if(!checkIfBookReviewBelongsToUser(reviewId, userId))
            System.out.println("Review doesn't belong to user");
        else
            bookReviewDataAO.deleteBookReview(reviewId);
    }

    public void updateBookReviewUserId(int reviewId, int newUserId) throws SQLException {
        if(!checkIfBookReviewExists(reviewId))
            System.out.println("Review doesn't exist");
        else
            bookReviewDataAO.updateBookReviewUserId(reviewId, newUserId);
    }

    public void updateBookReviewBookId(int reviewId, int newBookId) throws SQLException {
        if(!checkIfBookReviewExists(reviewId))
            System.out.println("Review doesn't exist");
        else
            bookReviewDataAO.updateBookReviewBookId(reviewId, newBookId);
    }

    public void updateBookReviewRating(int reviewId, int newRating) throws SQLException {
        if(!checkIfBookReviewExists(reviewId))
            System.out.println("Review doesn't exist");
        else
            bookReviewDataAO.updateBookReviewRating(reviewId, newRating);
    }

    public void updateBookReviewForUser(int reviewId, int userId, int newRating) throws SQLException {
        if(!checkIfBookReviewExists(reviewId))
            System.out.println("Review doesn't exist");
        if(!checkIfBookReviewBelongsToUser(reviewId, userId))
            System.out.println("Review doesn't belong to user");
        else
            bookReviewDataAO.updateBookReviewRating(reviewId, newRating);
    }

    private boolean checkIfBookReviewBelongsToUser(int reviewId, int userId) throws SQLException {
        return bookReviewDataAO.getBookReviewById(reviewId).getUser_id() == userId;
    }

    public BookReview getBookReviewById(int reviewId) throws SQLException {
        if(!checkIfBookReviewExists(reviewId)) {
            System.out.println("Review doesn't exist");
            return null;
        }
        else
            return bookReviewDataAO.getBookReviewById(reviewId);
    }

    public List<BookReview> getAllBookReviewsForBook(int bookId) throws SQLException {
        if(!bookService.checkIfBookIsAvailable(bookId)) {
            System.out.println("Book doesn't exist");
            return null;
        }
        else
            return bookReviewDataAO.getAllBookReviewsForBook(bookId);
    }

    public List<BookReview> getAllBookReviewsForUser(int userId) throws SQLException {
        if(userService.getUserById(userId) == null) {
            System.out.println("Book doesn't exist");
            return null;
        }
        else
            return bookReviewDataAO.getAllBookReviewsForUser(userId);
    }

    public void deleteAllBookReviewsForBook(int bookId) throws SQLException {
        if(!bookService.checkIfBookIsAvailable(bookId))
            System.out.println("Book doesn't exist");
        else
            bookReviewDataAO.deleteAllBookReviewsForBook(bookId);
    }

    public void deleteAllBookReviewsForUser(int userId) throws SQLException {
        if(userService.getUserById(userId) == null)
            System.out.println("Book doesn't exist");
        else
            bookReviewDataAO.deleteAllBookReviewsForUser(userId);
    }

    public boolean checkIfBookReviewExists(int reviewId) throws SQLException {
        if(getBookReviewById(reviewId) == null)
            return false;
        else
            return true;
    }

}
