import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class MagazineService {
    private MagazineDataAO magazineDataAO = new MagazineDataAO();

    public void addMagazine(String title, int authorId, int categoryId,int price, int availableCopies) throws SQLException {
        if(availableCopies < 0)
            System.out.println("Copies cannot be below 0.");
        else
            magazineDataAO.addMagazine(title, authorId, categoryId, price, availableCopies);
    }

    public void updateMagazineTitle(int magazineId, String title) throws SQLException {
        if(!checkIfMagazineExists(magazineId))
            System.out.println("Magazine does not exist.");
        else
            magazineDataAO.updateMagazineTitle(magazineId, title);
    }

    public void updateMagazinePrice(int magazineId, int price) throws SQLException {
        if(!checkIfMagazineExists(magazineId))
            System.out.println("Magazine does not exist.");
        else
            magazineDataAO.updateMagazinePrice(magazineId, price);
    }

    public void updateMagazineAuthor(int magazineId, int authorId) throws SQLException {
        if(!checkIfMagazineExists(magazineId))
            System.out.println("Magazine does not exist.");
        else
            magazineDataAO.updateMagazineAuthor(magazineId, authorId);
    }

    public void updateMagazineCategory(int magazineId, int categoryId) throws SQLException {
        if(!checkIfMagazineExists(magazineId))
            System.out.println("Magazine does not exist.");
        else
            magazineDataAO.updateMagazineCategory(magazineId, categoryId);
    }

    public void updateAvailableCopies(int magazineId, int newCopies) throws SQLException {
        if(!checkIfMagazineExists(magazineId))
            System.out.println("Magazine does not exist.");
        else
            magazineDataAO.updateAvailableCopies(magazineId, newCopies);
    }

    public void deleteMagazine(int magazineId) throws SQLException {
        if(!checkIfMagazineExists(magazineId))
            System.out.println("Magazine does not exist.");
        else
            magazineDataAO.deleteMagazine(magazineId);
    }

    public void deleteAllMagazines() throws SQLException {
        magazineDataAO.deleteAllMagazines();
    }

    public Magazine getMagazineById(int magazineId) throws SQLException {
        if(!checkIfMagazineExists(magazineId)) {
            System.out.println("Magazine does not exist.");
            return null;
        }
        else
            return magazineDataAO.getMagazineById(magazineId);
    }

    public List<Magazine> getAllMagazines() throws SQLException {
        return magazineDataAO.getAllMagazines();
    }

    public List<Magazine> searchMagazines(String keyword) throws SQLException {
        return magazineDataAO.searchMagazines(keyword);
    }

    public List<Magazine> searchMagazinesByCategory(int categoryId) throws SQLException {
        return magazineDataAO.searchMagazinesByCategory(categoryId);
    }

    public boolean checkIfMagazineExists(int magazineId) throws SQLException {
        return magazineDataAO.checkIfMagazineExists(magazineId);
    }
}