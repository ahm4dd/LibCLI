import java.sql.SQLException;
import java.util.List;
public class CategoryService {
    private CategoryDataAO categoryDataAO = new CategoryDataAO();

    public void addCategory(String name,String description) throws SQLException {
        categoryDataAO.addCategory(name, description);
    }

    public void deleteCategory(int categoryId) throws SQLException {
        if(!checkIfCategoryExists(categoryId))
            System.out.println("Category doesn't exist");
        else
            categoryDataAO.deleteCategory(categoryId);
    }

    public void updateCategoryName(int categoryId, String newName) throws SQLException {
        if(!checkIfCategoryExists(categoryId))
            System.out.println("Category doesn't exist");
        else
            categoryDataAO.updateCategoryName(categoryId, newName);
    }

    public void updateCategoryDescription(int categoryId, String newDescription) throws SQLException {
        if(!checkIfCategoryExists(categoryId))
            System.out.println("Category doesn't exist");
        else
            categoryDataAO.updateCategoryDescription(categoryId, newDescription);
    }

    public Category getCategoryById(int categoryId) throws SQLException {
        if(!checkIfCategoryExists(categoryId)){
            System.out.println("Category doesn't exist");
            return null;
        }
        else
            return categoryDataAO.getCategoryById(categoryId);
    }

    public List<Category> getAllCategories() throws SQLException {
        return categoryDataAO.getAllCategories();
    }

    public List<Category> searchCategories(String keyword) throws SQLException {
        return categoryDataAO.searchCategories(keyword);
    }

    public boolean checkIfCategoryExists(int categoryId) throws SQLException {
        if(categoryDataAO.getCategoryById(categoryId) == null)
            return false;
        else
            return true;
    }
}
