import java.util.Date;

public class Transaction {
    private int transactionId;
    private int userId;
    private int product_id;
    private Date checkoutDate;
    private int cost;

    Transaction(int transactionId, int userId, int product_id, Date checkoutDate,int cost) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.product_id = product_id;
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

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
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
