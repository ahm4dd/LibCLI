import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorService {
    private AuthorDataAO authorDataAO = new AuthorDataAO();

    public void addAuthor(String firstName, String lastName,String bio) throws SQLException {
        authorDataAO.addAuthor(firstName,lastName,bio);
    }

    public Author getAuthorById(int authorId) throws SQLException {
        if(!checkIfAuthorExists(authorId)) {
            System.out.println("Author doesn't exist");
            return null;
        }
        else
            return authorDataAO.getAuthorById(authorId);
    }

    public List<Book> getBooksByAuthor(int authorId) throws SQLException{
        if(!checkIfAuthorExists(authorId)){
            System.out.println("Author doesn't exist");
            return null;
        }
        else
            return authorDataAO.getBooksByAuthor(authorId);
    }

    public List<Magazine> getMagazinesByAuthor(int authorId) throws SQLException {
        if(!checkIfAuthorExists(authorId)){
            System.out.println("Author doesn't exist");
            return null;
        }
        else
            return authorDataAO.getMagazinesByAuthor(authorId);
    }

    public void deleteAuthor(int authorId) throws SQLException{
        if(!checkIfAuthorExists(authorId))
            System.out.println("Author doesn't exist");

        else
            authorDataAO.deleteAuthor(authorId);
    }

    public void updateFirstName(int authorId, String newFirstName) throws SQLException {
        if(!checkIfAuthorExists(authorId))
            System.out.println("Author doesn't exist");
        else
            authorDataAO.updateFirstName(authorId,newFirstName);
    }

    public void updateLastName(int authorId, String newLastName) throws SQLException {
        if(!checkIfAuthorExists(authorId))
            System.out.println("Author doesn't exist");
        else
            authorDataAO.updateLastName(authorId,newLastName);
    }

    public void updateBio(int authorId, String newBio) throws SQLException {
        if(!checkIfAuthorExists(authorId))
            System.out.println("Author doesn't exist");
        else
            authorDataAO.updateBio(authorId,newBio);
    }

    public List<Author> getAllAuthors() throws SQLException{
        return authorDataAO.getAllAuthors();
    }

    public boolean checkIfAuthorExists(int authorId) throws SQLException {
        if(authorDataAO.getAuthorById(authorId) != null)
            return true;
        else
            return false;
    }
}
