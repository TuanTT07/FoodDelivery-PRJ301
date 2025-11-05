/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.CartItem;
import model.Product;
import model.Store;
import utils.DBUtils;

public class CartItemDAO {

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

    public ArrayList<CartItem> getCartItemsByCartId(String cID) {
        ArrayList<CartItem> list = new ArrayList<>();
        String sql = "SELECT ci.CartItemID, ci.Quantity,\n"
                + "               p.ProductID, p.ProductName, p.ProductPrice,\n"
                + "               s.StoreID, s.StoreName,\n"
                + "               pic.PictureURL\n"
                + "        FROM tblCartItem ci\n"
                + "        JOIN tblProduct p ON p.ProductID = ci.ProductID\n"
                + "        JOIN tblStore s ON s.StoreID = p.StoreID\n"
                + "        OUTER APPLY (SELECT TOP 1 PictureURL FROM tblPicture WHERE ProductID = p.ProductID AND IsMain = 1) pic\n"
                + "        WHERE ci.CartID = ?\n"
                + "        ORDER BY s.StoreID, p.ProductName";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, cID);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                CartItem ci = new CartItem();
                ci.setCartItemID(rs.getString("CartItemID"));
                ci.setQuantity(rs.getInt("Quantity"));

                Product p = new Product();
                p.setProductID(rs.getString("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setProductPrice(rs.getDouble("ProductPrice"));
                p.setPictureURL(rs.getString("PictureURL"));

                Store s = new Store();
                s.setStoreID(rs.getString("StoreID"));
                s.setStoreName(rs.getString("StoreName"));
                p.setStore(s);

                ci.setProductID(p);

                list.add(ci);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
