import java.util.List;

public class Member extends User{

    public void accessLibrarySystem() {
        System.out.println("Member can access library system.");
    }

    public void borrowBook(int bookId) {

    }

    public void returnBook(int bookId) {

    }

    public void manageMember(){

    }

    public List<Book> viewBorrowedBooks() {
        return null;
    }

    public boolean checkIfBookIsAvailable(int bookId) {
        return false;
    }
}
