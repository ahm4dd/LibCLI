import java.sql.*;
import java.util.List;

public class CategoryService {

    private CategoryDataAO categoryDataAO;

    public CategoryService() {
        this.categoryDataAO = new CategoryDataAO();
    }

    // Add a new category
    public void addCategory(String name, String description) throws SQLException {
        if (name == null || name.isEmpty()) {
            System.out.println("Category name cannot be empty.");
            return;
        }
        if (description == null || description.isEmpty()) {
            System.out.println("Category description cannot be empty.");
            return;
        }

        // Add category via DAO
        categoryDataAO.addCategory(name, description);
        System.out.println("Category added successfully.");
    }

    // Update a category's name
    public void updateCategoryName(int categoryId, String newName) throws SQLException {
        if (categoryId <= 0) {
            System.out.println("Invalid category ID.");
            return;
        }
        if (newName == null || newName.isEmpty()) {
            System.out.println("Category name cannot be empty.");
            return;
        }

        // Update category's name via DAO
        categoryDataAO.updateCategoryName(categoryId, newName);
        System.out.println("Category name updated successfully.");
    }

    // Update a category's description
    public void updateCategoryDescription(int categoryId, String newDescription) throws SQLException {
        if (categoryId <= 0) {
            System.out.println("Invalid category ID.");
            return;
        }
        if (newDescription == null || newDescription.isEmpty()) {
            System.out.println("Category description cannot be empty.");
            return;
        }

        // Update category's description via DAO
        categoryDataAO.updateCategoryDescription(categoryId, newDescription);
        System.out.println("Category description updated successfully.");
    }

    // Delete a category by ID
    public void deleteCategory(int categoryId) throws SQLException {
        if (categoryId <= 0) {
            System.out.println("Invalid category ID.");
            return;
        }

        // Delete category via DAO
        categoryDataAO.deleteCategory(categoryId);
        System.out.println("Category deleted successfully.");
    }

    // Get a category by ID
    public Category getCategoryById(int categoryId) throws SQLException {
        if (categoryId <= 0) {
            System.out.println("Invalid category ID.");
            return null;
        }

        // Get category by ID via DAO
        return categoryDataAO.getCategoryById(categoryId);
    }

    // Get all categories
    public List<Category> getAllCategories() throws SQLException {
        // Get all categories via DAO
        return categoryDataAO.getAllCategories();
    }

    // Search categories by name (partial match)
    public List<Category> searchCategoriesByName(String name) throws SQLException {
        if (name == null || name.isEmpty()) {
            System.out.println("Category name cannot be empty.");
            return null;
        }

        // Search categories by name via DAO
        return categoryDataAO.searchCategories(name);
    }
}
