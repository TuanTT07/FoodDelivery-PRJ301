/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Time;//for LocalTime
import java.sql.Timestamp; //for LocalDateTime
import model.Role;
import model.User;
import utils.DBUtils;

/**
 *
 * @author TuanTran
 */
public class UserDAO {

    public User getUserByUsername(String userName) {
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "SELECT * FROM tblUser WHERE"
                    + " UserName = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, userName);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUserID(rs.getString("UserID"));
                user.setUserName(rs.getString("UserName"));
                user.setUserFullName(rs.getString("FullName"));
                user.setUserEmail(rs.getString("UserEmail"));
                user.setUserPassword(rs.getString("UserPassword"));
                user.setUserPhone(rs.getString("UserPhone"));
                user.setUserAddress(rs.getString("UserAddress"));
                Role role = new Role(rs.getString("RoleID"), null);
                user.setRoleID(role);
                Timestamp tsCreated = rs.getTimestamp("CreatedAt");
                if (tsCreated != null) {
                    user.setCreatedAt(tsCreated.toLocalDateTime());
                }

                Timestamp tsUpdated = rs.getTimestamp("UpdatedAt");
                if (tsUpdated != null) {
                    user.setUpdatedAt(tsUpdated.toLocalDateTime());
                } else {
                    user.setUpdatedAt(null);
                }
                user.setStatus(rs.getBoolean("Status"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //function for login
    public User loginByUsername(String username, String password) {
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "SELECT * FROM tblUser WHERE UserName = ? AND UserPassword = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserID(rs.getString("UserID"));
                user.setUserName(rs.getString("UserName"));
                user.setUserFullName(rs.getString("FullName"));
                user.setUserEmail(rs.getString("UserEmail"));
                user.setUserPassword(rs.getString("UserPassword"));
                user.setUserPhone(rs.getString("UserPhone"));
                user.setUserAddress(rs.getString("UserAddress"));
                Role role = new Role(rs.getString("RoleID"), null);
                user.setRoleID(role);
                Timestamp tsCreated = rs.getTimestamp("CreatedAt");
                if (tsCreated != null) {
                    user.setCreatedAt(tsCreated.toLocalDateTime());
                }

                Timestamp tsUpdated = rs.getTimestamp("UpdatedAt");
                if (tsUpdated != null) {
                    user.setUpdatedAt(tsUpdated.toLocalDateTime());
                } else {
                    user.setUpdatedAt(null);
                }
                user.setStatus(rs.getBoolean("Status"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User loginByEmail(String email, String password) {
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "SELECT * FROM tblUser WHERE UserEmail = ? AND UserPassword = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserID(rs.getString("UserID"));
                user.setUserName(rs.getString("UserName"));
                user.setUserFullName(rs.getString("FullName"));
                user.setUserEmail(rs.getString("UserEmail"));
                user.setUserPassword(rs.getString("UserPassword"));
                user.setUserPhone(rs.getString("UserPhone"));
                user.setUserAddress(rs.getString("UserAddress"));
                Role role = new Role(rs.getString("RoleID"), null);
                user.setRoleID(role);
                Timestamp tsCreated = rs.getTimestamp("CreatedAt");
                if (tsCreated != null) {
                    user.setCreatedAt(tsCreated.toLocalDateTime());
                }

                Timestamp tsUpdated = rs.getTimestamp("UpdatedAt");
                if (tsUpdated != null) {
                    user.setUpdatedAt(tsUpdated.toLocalDateTime());
                } else {
                    user.setUpdatedAt(null);
                }
                user.setStatus(rs.getBoolean("Status"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Create
    //ham check email va phone da ton tai
    public boolean existsByEmail(String email) {
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "SELECT 1 FROM tblUser WHERE UserEmail = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            try ( ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existsByPhone(String phone) {
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "SELECT 1 FROM tblUser WHERE UserPhone = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, phone);
            try ( ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy ID cuối cùng để Service sinh ID tiếp theo
    public String getLastUserId() {
        String sql = "SELECT TOP 1 UserID FROM tblUser ORDER BY UserID DESC";
        try ( Connection c = DBUtils.getConnection();  PreparedStatement ps = c.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getString("UserID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String generateNextUserID() {
        String last = getLastUserId(); // ví dụ U004
        if (last == null || last.isEmpty()) {
            return "U001";
        }
        int n = Integer.parseInt(last.substring(1)) + 1;
        return String.format("U%03d", n);
    }

    public boolean insert(User user) {
        // Set ID
        if (user.getUserID() == null || user.getUserID().trim().isEmpty()) {
            user.setUserID(generateNextUserID());
        }

        String sql = "INSERT INTO tblUser (UserID, UserName, FullName, UserEmail, "
                + "UserPassword, UserPhone, UserAddress, RoleID, CreatedAt, UpdatedAt, Status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), NULL, ?)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, user.getUserID());
            pst.setString(2, user.getUserName());
            pst.setString(3, user.getUserFullName());
            pst.setString(4, user.getUserEmail());
            pst.setString(5, user.getUserPassword());
            pst.setString(6, user.getUserPhone());
            pst.setString(7, user.getUserAddress());
            pst.setString(8, user.getRoleID().getRoleID());
            pst.setBoolean(9, true); // Status = 1
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //Read
    //function for search
    public ArrayList<UserDAO> getAllUser() {
        ArrayList<UserDAO> listUser = new ArrayList<>();
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "SELECT * FROM tblUser";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserID(rs.getString("UserID"));
                user.setUserName(rs.getString("UserName"));
                user.setUserFullName(rs.getString("FullName"));
                user.setUserEmail(rs.getString("UserEmail"));
                user.setUserPhone(rs.getString("UserPhone"));
                user.setUserAddress(rs.getString("UserAddress"));
                //Role role = user.setRoleID(rs.getString("RoleID"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listUser;
    }

    public User getUserByID(String id) {
        User res = new User();
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "SELECT * FROM tblUser WHERE UserID= ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                res.setUserID(rs.getString("UserID"));
                res.setUserName(rs.getString("UserName"));
                res.setUserFullName(rs.getString("FullName"));
                res.setUserEmail(rs.getString("UserEmail"));
                res.setUserPassword(rs.getString("UserPassword"));
                res.setUserPhone(rs.getString("UserPhone"));
                res.setUserAddress(rs.getString("UserAddress"));
                Role role = new Role(rs.getString("RoleID"), null);
                res.setRoleID(role);
                Timestamp tsCreated = rs.getTimestamp("CreatedAt");
                if (tsCreated != null) {
                    res.setCreatedAt(tsCreated.toLocalDateTime());
                }

                Timestamp tsUpdated = rs.getTimestamp("UpdatedAt");
                if (tsUpdated != null) {
                    res.setUpdatedAt(tsUpdated.toLocalDateTime());
                } else {
                    res.setUpdatedAt(null);
                }
                res.setStatus(rs.getBoolean("Status"));
                return res;
            }
        } catch (Exception e) {
        }
        return res;
    }

    public boolean changeRoleStoreOwner(String id) {
        String sql = "UPDATE tblUser SET RoleID = ? WHERE UserID = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "S002");
            pst.setString(2, id);
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }
}
