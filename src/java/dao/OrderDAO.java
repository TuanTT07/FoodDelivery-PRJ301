/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Order;
import utils.DBUtils;

/**
 *
 * @author ACER
 */
public class OrderDAO {

    private String generateNextOrderID() {
        String sql = "SELECT TOP 1 OrderID FROM tblOrder ORDER BY OrderID DESC";
        try (
                 Connection conn = DBUtils.getConnection();  PreparedStatement pst = conn.prepareStatement(sql);  ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                String lastID = rs.getString("OrderID");
                int num = Integer.parseInt(lastID.substring(1)) + 1;
                return String.format("O%03d", num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "O001";
    }

    public boolean insertOrder(Order o) {
        String sql = "INSERT INTO tblOrder(OrderID, TotalPrice, OrderDate, DeliveryAddress, UserID, VoucherID, PaymentID)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            if (o.getOrderID() == null || o.getOrderID().trim().length() == 0) {
                o.setOrderID(generateNextOrderID());
            }
            pst.setString(1, o.getOrderID());
            pst.setDouble(2, o.getTotalPrice());
            pst.setTimestamp(3, java.sql.Timestamp.valueOf(o.getOrderDate()));
            pst.setString(4, o.getDeliveryAddress());
            pst.setString(5, o.getUserID().getUserID());
            pst.setString(6, o.getVoucherID() != null ? o.getVoucherID().getVoucherID() : null);
            pst.setString(7, null); // PaymentID tạm thời NULL

            if (pst.execute()) {
                return true;
            }

        } catch (Exception e) {
        }
        return false;
    }

}
