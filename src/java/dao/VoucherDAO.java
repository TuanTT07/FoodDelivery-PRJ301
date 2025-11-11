/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Voucher;
import utils.DBUtils;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.Role;
import model.User;

/**
 *
 * @author vanli
 */
public class VoucherDAO {

    public VoucherDAO() {
    }

    public Voucher getVoucherByID(String id) {
        Voucher v = null;
        String sql = "SELECT * FROM tblVoucher WHERE VoucherID = ?";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                v = new Voucher();
                v.setVoucherID(rs.getString("VoucherID"));
                v.setVoucherCode(rs.getString("VoucherCode"));
                v.setDescription(rs.getNString("Description"));
                v.setDiscountPercent(rs.getDouble("DiscountPercent"));
                v.setMinOrderAmount(rs.getDouble("MinOrderAmount"));
                v.setMaxDiscountAmount(rs.getDouble("MaxDiscountAmount"));

                Timestamp start = rs.getTimestamp("StartDate");
                Timestamp end = rs.getTimestamp("EndDate");
                Timestamp created = rs.getTimestamp("CreatedAt");

                v.setStartDate(start != null ? start.toLocalDateTime() : null);
                v.setEndDate(end != null ? end.toLocalDateTime() : null);
                v.setCreatedAt(created != null ? created.toLocalDateTime() : null);

                v.setIsActive(rs.getBoolean("IsActive"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return v;
    }

    public boolean existsByCode(String code) {
        String sql = "SELECT 1 FROM tblVoucher WHERE VoucherCode = ?";
        try ( Connection c = DBUtils.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, code);
            try ( ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String nextVoucherId(Connection cn) throws SQLException {
        String sql = "SELECT MAX(VoucherID) FROM dbo.tblVoucher";
        try ( PreparedStatement ps = cn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            String max = (rs.next() ? rs.getString(1) : null); // ví dụ V012
            int n = (max != null && max.matches("V\\d+")) ? Integer.parseInt(max.substring(1)) : 0;
            return String.format("V%03d", n + 1);
        }
    }

    public boolean insertVoucher(Voucher v) {
        String sql
                = "INSERT INTO dbo.tblVoucher "
                + "(VoucherID, VoucherCode, Description, DiscountPercent, MinOrderAmount, MaxDiscountAmount, "
                + " StartDate, EndDate, IsActive, CreatedAt) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try ( Connection cn = DBUtils.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {

            String id = nextVoucherId(cn);
            v.setVoucherID(id);

            ps.setString(1, id);
            ps.setString(2, v.getVoucherCode());
            ps.setNString(3, v.getDescription());
            ps.setBigDecimal(4, BigDecimal.valueOf(v.getDiscountPercent()));
            ps.setBigDecimal(5, BigDecimal.valueOf(v.getMinOrderAmount()));
            ps.setBigDecimal(6, BigDecimal.valueOf(v.getMaxDiscountAmount()));
            ps.setTimestamp(7, Timestamp.valueOf(v.getStartDate()));
            ps.setTimestamp(8, Timestamp.valueOf(v.getEndDate()));
            ps.setBoolean(9, v.isIsActive());
            ps.setTimestamp(10, Timestamp.valueOf(
                    v.getCreatedAt() != null ? v.getCreatedAt() : LocalDateTime.now()));

            return ps.executeUpdate() > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    

    public ArrayList<Voucher> getAllVoucher() {
        ArrayList<Voucher> list = new ArrayList<>();
        String sql = "SELECT * FROM tblVoucher ";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement pst = conn.prepareStatement(sql);  ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Voucher v = new Voucher();

                v.setVoucherID(rs.getString("VoucherID"));
                v.setVoucherCode(rs.getString("VoucherCode"));
                v.setDescription(rs.getNString("Description"));
                v.setDiscountPercent(rs.getDouble("DiscountPercent"));
                v.setMinOrderAmount(rs.getDouble("MinOrderAmount"));
                v.setMaxDiscountAmount(rs.getDouble("MaxDiscountAmount"));

                Timestamp start = rs.getTimestamp("StartDate");
                Timestamp end = rs.getTimestamp("EndDate");
                Timestamp created = rs.getTimestamp("CreatedAt");

                v.setStartDate(start != null ? start.toLocalDateTime() : null);
                v.setEndDate(end != null ? end.toLocalDateTime() : null);
                v.setCreatedAt(created != null ? created.toLocalDateTime() : null);

                v.setIsActive(rs.getBoolean("IsActive"));

                list.add(v);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean updateVoucher(Voucher v) {
    String sql = "UPDATE tblVoucher SET "
            + "VoucherCode = ?, "
            + "Description = ?, "
            + "DiscountPercent = ?, "
            + "MinOrderAmount = ?, "
            + "MaxDiscountAmount = ?, "
            + "StartDate = ?, "
            + "EndDate = ?, "
            + "IsActive = ? "
            + "WHERE VoucherID = ?";

    try (Connection conn = DBUtils.getConnection();
         PreparedStatement pst = conn.prepareStatement(sql)) {

        pst.setString(1, v.getVoucherCode());
        pst.setNString(2, v.getDescription());
        pst.setDouble(3, v.getDiscountPercent());
        pst.setDouble(4, v.getMinOrderAmount());
        pst.setDouble(5, v.getMaxDiscountAmount());
        pst.setTimestamp(6, v.getStartDate() != null ? Timestamp.valueOf(v.getStartDate()) : null);
        pst.setTimestamp(7, v.getEndDate() != null ? Timestamp.valueOf(v.getEndDate()) : null);
        pst.setBoolean(8, v.isIsActive());
        pst.setString(9, v.getVoucherID());

        int rows = pst.executeUpdate();
        return rows > 0; // true nếu có ít nhất 1 dòng được update

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


}
