import java.sql.*;
import java.util.List;

public class AuthorService {

    private AuthorDataAO authorDataAO;

    public AuthorService() {
        this.authorDataAO = new AuthorDataAO();
    }

    // Add a new author
    public void addAuthor(String firstName, String lastName, String bio) throws SQLException {
        // Check if author details are valid
        if (firstName == null || firstName.isEmpty()) {
            System.out.println("First name cannot be empty.");
            return;
        }
        if (lastName == null || lastName.isEmpty()) {
            System.out.println("Last name cannot be empty.");
            return;
        }

        // Add author via DAO (handling database operations)
        authorDataAO.addAuthor(firstName, lastName, bio);
        System.out.println("Author added successfully.");
    }

    // Update an author's first name
    public void updateFirstName(int authorId, String firstName) throws SQLException {
        if (authorId <= 0) {
            System.out.println("Invalid author ID.");
            return;
        }
        if (firstName == null || firstName.isEmpty()) {
            System.out.println("First name cannot be empty.");
            return;
        }

        // Update author's first name via DAO
        authorDataAO.updateFirstName(authorId, firstName);
        System.out.println("Author's first name updated successfully.");
    }

    // Update an author's last name
    public void updateLastName(int authorId, String lastName) throws SQLException {
        if (authorId <= 0) {
            System.out.println("Invalid author ID.");
            return;
        }
        if (lastName == null || lastName.isEmpty()) {
            System.out.println("Last name cannot be empty.");
            return;
        }

        // Update author's last name via DAO
        authorDataAO.updateLastName(authorId, lastName);
        System.out.println("Author's last name updated successfully.");
    }

    // Update an author's bio
    public void updateBio(int authorId, String bio) throws SQLException {
        if (authorId <= 0) {
            System.out.println("Invalid author ID.");
            return;
        }
        if (bio == null || bio.isEmpty()) {
            System.out.println("Bio cannot be empty.");
            return;
        }

        // Update author's bio via DAO
        authorDataAO.updateBio(authorId, bio);
        System.out.println("Author's bio updated successfully.");
    }

    // Delete an author by ID
    public void deleteAuthor(int authorId) throws SQLException {
        if (authorId <= 0) {
            System.out.println("Invalid author ID.");
            return;
        }

        // Delete author via DAO
        authorDataAO.deleteAuthor(authorId);
        System.out.println("Author deleted successfully.");
    }

    // Get an author by ID
    public Author getAuthorById(int authorId) throws SQLException {
        if (authorId <= 0) {
            System.out.println("Invalid author ID.");
            return null;
        }

        // Get author details via DAO
        return authorDataAO.getAuthorById(authorId);
    }

    // Get all authors
    public List<Author> getAllAuthors() throws SQLException {
        // Get all authors via DAO
        return authorDataAO.getAllAuthors();
    }

    // Get authors by name (either first or last name)
    public List<Author> getAuthorsByName(String name) throws SQLException {
        if (name == null || name.isEmpty()) {
            System.out.println("Invalid name.");
            return null;
        }

        // Get authors by name via DAO
        return authorDataAO.getAuthorsByName(name);
    }
}
