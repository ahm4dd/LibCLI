import java.util.List;

public class Member extends User{
    private UserService userService = new UserService();
    Member(int userId, String username, String password, String email, String userType) {
        super(userId, username, password, email, userType);
    }

    public void accessLibrarySystem() {
        System.out.println("1.");
    }

    public void borrowBook() {

    }

    public void returnBook() {

    }

    public void manageMember(){

    }

    public List<Book> viewBorrowedBooks() {
        return null;
    }

    public boolean checkIfBookIsAvailable() {
        return false;
    }
}
