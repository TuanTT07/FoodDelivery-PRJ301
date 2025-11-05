/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.CartItem;
import utils.DBUtils;

public class CartIteamDAO {

    private String generateNextCartItemID() {
        String sql = "SELECT TOP 1 CartItemID FROM tblCartItem ORDER BY CartItemID DESC";
        try (
                 Connection conn = DBUtils.getConnection();  PreparedStatement pst = conn.prepareStatement(sql);  ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                String lastID = rs.getString("CartItemID");
                int num = Integer.parseInt(lastID.substring(2)) + 1;
                return String.format("CI%03d", num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "CI001";
    }

    public boolean insertCartItem(CartItem ci) {
        String sql = "INSERT INTO tblCartItem (CartItemID, CartID, ProductID, Quantity)" + "VALUES (?,?,?,?)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            if (ci.getCartItemID() == null || ci.getCartItemID().trim().length() == 0) {
                ci.setCartItemID(generateNextCartItemID());
            }

            pst.setString(1, ci.getCartItemID());
            pst.setString(2, ci.getCartID().getCartID());
            pst.setString(3, ci.getProductID().getProductID());
            pst.setInt(4, ci.getQuantity());

            return pst.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

}
