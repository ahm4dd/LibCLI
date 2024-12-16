import java.util.Date;

public class Transaction {
    private int transactionId;
    private int userId;
    private int bookId;
    private Date checkoutDate;
    private int cost;

    Transaction(int transactionId, int userId, int bookId, Date checkoutDate,int cost) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.bookId = bookId;
        this.checkoutDate = new Date();
        this.cost=cost;
    }


    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

}
