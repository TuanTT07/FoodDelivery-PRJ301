/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Cart;
import model.User;
import utils.DBUtils;

public class CartDAO {

    private String generateNextCartID() {
        String sql = "SELECT TOP 1 CartID FROM tblCart ORDER BY CartID DESC";
        try (
                 Connection conn = DBUtils.getConnection();  PreparedStatement pst = conn.prepareStatement(sql);  ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                String lastID = rs.getString("CartID");
                int num = Integer.parseInt(lastID.substring(4)) + 1;
                return String.format("CART%03d", num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "CART001";
    }

    public boolean insertCart(Cart c) {
        String sql = "INSERT INTO tblCart (CartID, UserID, TotalPrice)" + "VALUES (?,?,?)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            if (c.getCartID() == null || c.getCartID().trim().length() == 0) {
                c.setCartID(generateNextCartID());
            }
            pst.setString(1, c.getCartID());
            pst.setString(2, c.getUserID().getUserID());
            pst.setDouble(3, c.getTotalPrice());

            return pst.executeUpdate() > 0;

        } catch (Exception e) {
        }
        return false;
    }

    public Cart getCartByUserId(String uId) {
        String sql = "SELECT * FROM tblCart WHERE UserID=?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, uId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Cart c = new Cart();
                c.setCartID(rs.getString("CartID"));
                UserDAO uDAO = new UserDAO();
                User u = uDAO.getUserByID(rs.getString("userID"));
                c.setUserID(u);
                c.setTotalPrice(rs.getDouble("TotalPrice"));
                return c;
            }
        } catch (Exception e) {
        }
        return null;
    }

}
