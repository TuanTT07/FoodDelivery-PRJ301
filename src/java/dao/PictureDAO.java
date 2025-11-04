/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import model.Picture;
import model.Product;
import utils.DBUtils;

/**
 *
 * @author ACER
 */
public class PictureDAO {

    public String getLastProductPictureId() {
        String sql = "SELECT TOP 1 PictureID FROM tblPicture ORDER BY PictureID DESC";
        try ( Connection c = DBUtils.getConnection();  PreparedStatement ps = c.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getString("PictureID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String generateNextProductPictureID() {
        String last = getLastProductPictureId();
        if (last == null || last.isEmpty()) {
            return "PIC001";
        }
        int n = Integer.parseInt(last.substring(3)) + 1;
        return String.format("PIC%03d", n);
    }

    public boolean insertPicture(Picture p) {
        String sql = "INSERT INTO tblPicture (PictureID, ProductID, PictureURL, UploadDate , IsMain) VALUES (?, ?, ?, ?,?)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);

            if (p.getPictureId() == null || p.getPictureId().trim().length() == 0) {
                p.setPictureId(generateNextProductPictureID());
            }
            pst.setString(1, p.getPictureId());
            pst.setString(2, p.getProductId().getProductID());
            pst.setString(3, p.getPictureURL());
            pst.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));

            if (p.isIsMain()) {
                pst.setBoolean(5, true);
            } else {
                pst.setBoolean(5, false);
            }
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Picture> getProductPictureByPID(String productID) {
        ArrayList<Picture> listOfPicture = new ArrayList<>();
        String sql = "SELECT * FROM tblPicture WHERE ProductID = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, productID);
            ResultSet rs = pst.executeQuery();

            ProductDAO pDAO = new ProductDAO();
            Product p = pDAO.getProductByProductID(productID);

            while (rs.next()) {
                Picture pic = new Picture();

                pic.setProductId(p);
                pic.setPictureURL(rs.getString("PictureURL"));
                Timestamp ts = rs.getTimestamp("UploadDate");
                if (ts != null) {
                    pic.setUploadDate(ts.toLocalDateTime());
                }
                pic.setIsMain(rs.getBoolean("IsMain"));

                listOfPicture.add(pic);
            }
        } catch (Exception e) {
        }
        return listOfPicture;
    }
}
