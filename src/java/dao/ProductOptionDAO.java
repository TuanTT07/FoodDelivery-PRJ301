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
import model.ProductOption;
import utils.DBUtils;

/**
 *
 * @author ACER
 */
public class ProductOptionDAO {

    public String getLastProductOptionId() {
        String sql = "SELECT TOP 1 OptionID FROM tblProductOption ORDER BY OptionID DESC";
        try ( Connection c = DBUtils.getConnection();  PreparedStatement ps = c.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getString("OptionID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String generateNextProductOptionID() {
        String last = getLastProductOptionId();
        if (last == null || last.isEmpty()) {
            return "O001";
        }
        int n = Integer.parseInt(last.substring(1)) + 1;
        return String.format("O%03d", n);
    }

    public boolean insertProductOption(ProductOption pot) {
        String sql = "INSERT INTO tblProductOption(OptionID,OptionType,OptionValue,ExtraPrice,ProductID)"
                + "VALUES(?,?,?,?,?)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            if (pot.getOptionID() == null || pot.getOptionID().trim().length() == 0) {
                pot.setOptionID(generateNextProductOptionID());
            }

            pst.setString(1, pot.getOptionID());
            pst.setString(2, pot.getOptionType());
            pst.setString(3, pot.getOptionValue());
            pst.setDouble(4, pot.getExtraPrice());
            pst.setString(5, pot.getProductID().getProductID());

            return pst.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public ArrayList<ProductOption> getProductOptionByPID(String productID) {
        ArrayList<ProductOption> listOfProductOption = new ArrayList<>();
        String sql = "SELECT * FROM tblProductOption WHERE ProductID = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, productID);
            ResultSet rs = pst.executeQuery();

            ProductDAO pDAO = new ProductDAO();
            Product p = pDAO.getProductByProductID(productID);
            
            while (rs.next()) {
                ProductOption po = new ProductOption();
                po.setOptionID(rs.getString("OptionID"));
                po.setOptionType(rs.getString("OptionType"));
                po.setOptionValue(rs.getString("OptionValue"));
                po.setExtraPrice(rs.getDouble("ExtraPrice"));

                po.setProductID(p);
                listOfProductOption.add(po);
            }
        } catch (Exception e) {
        }
        return listOfProductOption;
    }
}
