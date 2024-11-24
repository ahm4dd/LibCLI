public class Admin extends User{

    Admin(int userId, String username, String password, String email, String userType) {
        super(userId, username, password, email, userType);
    }

    public void accessLibrarySystem() {
        System.out.println("Admin can access library system.");
    }

    public void addBook(Book book) {

    }

    public void removeBook(Book book) {

    }

    public void manageMember(Member member){

    }

    public void updateBook(Book book) {

    }

    public void viewAllUsers() {

    }

}
