/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Product;
import model.ProductDetail;
import utils.DBUtils;

/**
 *
 * @author ACER
 */
public class ProductDetailDAO {

    public String getLastProductDetailId() {
        String sql = "SELECT TOP 1 DetailID FROM tblProductDetail ORDER BY DetailID DESC";
        try ( Connection c = DBUtils.getConnection();  PreparedStatement ps = c.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getString("DetailID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String generateNextProductDetailID() {
        String last = getLastProductDetailId();
        if (last == null || last.isEmpty()) {
            return "D001";
        }
        int n = Integer.parseInt(last.substring(1)) + 1;
        return String.format("D%03d", n);
    }

    public boolean insertProductDetail(ProductDetail pd) {

        String sql = "INSERT INTO tblProductDetail(DetailID ,Size,Combo,ExtraInfo ,ProductID)" + " VALUES(?,?,?,?,?)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            if (pd.getDetailID() == null || pd.getDetailID().trim().length() == 0) {
                pd.setDetailID(generateNextProductDetailID());
            }
            pst.setString(1, pd.getDetailID());
            pst.setString(2, pd.getSize());
            pst.setString(3, pd.getCombo());
            pst.setString(4, pd.getExtraInfo());
            pst.setString(5, pd.getProductID().getProductID());

            return pst.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public ArrayList<ProductDetail> getProductDetailByPID(String productID) {
        ArrayList<ProductDetail> listOfProductDetail = new ArrayList<>();
        String sql = "SELECT * FROM tblProductDetail WHERE ProductID = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, productID);
            ResultSet rs = pst.executeQuery();

            ProductDAO pDAO = new ProductDAO();
            Product p = pDAO.getProductByProductID(productID);
            
            while (rs.next()) {
                ProductDetail pd = new ProductDetail();
                pd.setDetailID(rs.getString("DetailID"));
                pd.setSize(rs.getString("Size"));
                pd.setCombo(rs.getString("Combo"));
                pd.setExtraInfo(rs.getString("ExtraInfo"));

                pd.setProductID(p);
                listOfProductDetail.add(pd);
            }
        } catch (Exception e) {
        }
        return listOfProductDetail;
    }
}
