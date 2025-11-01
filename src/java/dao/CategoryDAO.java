/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Category;
import model.Store;
import utils.DBUtils;

/**
 *
 * @author ACER
 */
public class CategoryDAO {

    public String getLastUserId() {
        String sql = "SELECT TOP 1 CategoryID FROM tblCategory ORDER BY CategoryID DESC";
        try ( Connection c = DBUtils.getConnection();  PreparedStatement ps = c.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getString("CategoryID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String generateNextCateID() {
        String last = getLastUserId(); // ví dụ U004
        if (last == null || last.isEmpty()) {
            return "C001";
        }
        int n = Integer.parseInt(last.substring(1)) + 1;
        return String.format("C%03d", n);
    }

    public boolean insertCate(Category c) {
        try {
            String sql = "INSERT INTO tblCategory(CategoryID,CategoryName, StoreID,IsActive)"
                    + " VALUES(?,?,?,?)";

            Connection conn = DBUtils.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            if (c.getCategoryID() == null || c.getCategoryID().trim().length() == 0) {
                c.setCategoryID(generateNextCateID());
            }
            pst.setString(1, c.getCategoryID());
            pst.setString(2, c.getCategoryName());
            pst.setString(3, c.getStoreId().getStoreID());
            pst.setBoolean(4, true);
            return pst.executeUpdate() > 0;

        } catch (Exception e) {
        }
        return false;
    }

    public boolean getCateByName(String name) {
        try {
            System.out.println(name);
            String sql = "SELECT * FROM tblCategory WHERE CategoryName LIKE  ?";

            Connection conn = DBUtils.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            if (name == null || name.trim().length() == 0) {
                return false;
            }
            pst.setString(1, "%" + name + "%");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public ArrayList<Category> getAllCateByStoreID(String storeID) {
        ArrayList<Category> listOfCate = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tblCategory WHERE StoreID = ?";
            Connection conn = DBUtils.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, storeID);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Category cate = new Category();
                cate.setCategoryID(rs.getString("CategoryID"));
                cate.setCategoryName(rs.getString("CategoryName"));
                StoreDAO storeDAO = new StoreDAO();
                Store store = storeDAO.getStoreByID(rs.getString("StoreID"));
                cate.setStoreId(store);
                String txtActive = rs.getString("IsActive");
                if (txtActive.equals("1")) {
                    cate.setIsActive(true);
                } else {
                    cate.setIsActive(false);
                }

                listOfCate.add(cate);
            }
        } catch (Exception e) {
        }
        return listOfCate;
    }
}
