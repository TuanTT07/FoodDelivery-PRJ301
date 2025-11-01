package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Category;
import model.Product;
import model.Store;
import utils.DBUtils;

public class ProductDAO {

    public String getLastProductId() {
        String sql = "SELECT TOP 1 ProductID FROM tblProduct ORDER BY ProductID DESC";
        try ( Connection c = DBUtils.getConnection();  PreparedStatement ps = c.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getString("ProductID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String generateNextProductID() {
        String last = getLastProductId();
        if (last == null || last.isEmpty()) {
            return "P001";
        }
        int n = Integer.parseInt(last.substring(1)) + 1;
        return String.format("P%03d", n);
    }

    public boolean insert(Product p) {
        String sql = "INSERT INTO tblProduct (ProductID, ProductName ,ProductPrice,ProductDesc ,CategoryID,StoreID ,IsActive)"
                + "VALUES(?,?,?,?,?,?,?)";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            if (p.getProductID() == null || p.getProductID().trim().length() == 0) {
                p.setProductID(generateNextProductID());
            }
            pst.setString(1, p.getProductID());
            pst.setString(2, p.getProductName());
            pst.setDouble(3, p.getProductPrice());
            pst.setString(4, p.getProductDesc());
            pst.setString(5, p.getCategoryID().getCategoryID());
            pst.setString(6, p.getStoreID().getStoreID());
            pst.setBoolean(7, true);

            return pst.executeUpdate() > 0;

        } catch (Exception e) {
        }
        return false;
    }

    public ArrayList<Product> getAllProductByStoreId(String storeId) {
        ArrayList<Product> listOfProduct = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tblProduct WHERE StoreID = ?";
            Connection conn = DBUtils.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, storeId);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductID(rs.getString("ProductID"));
                product.setProductName(rs.getString("ProductName"));
                product.setProductPrice(rs.getDouble("ProductPrice"));
                product.setProductDesc(rs.getString("ProductDesc"));

                CategoryDAO cateDAO = new CategoryDAO();
                Category cate = cateDAO.getCateByCateID(rs.getString("CategoryID"));
                product.setCategoryID(cate);

                StoreDAO storeDAO = new StoreDAO();
                Store store = storeDAO.getStoreByID(rs.getString("StoreID"));
                product.setStoreID(store);
                product.setIsActive(rs.getBoolean("IsActive"));
                listOfProduct.add(product);
            }
        } catch (Exception e) {
        }
        return listOfProduct;
    }
}
