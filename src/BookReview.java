public class BookReview {
    private int review_id;
    private int user_id;
    private int book_id;
    private int rating;
    private String review;


    BookReview(int review_id, int user_id, int book_id, int rating, String review){
        this.review_id = review_id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.rating = rating;
        this.review = review;
    }

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
