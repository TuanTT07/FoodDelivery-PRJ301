/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Time;//for LocalTime
import java.sql.Timestamp; //for LocalDateTime
import java.time.LocalDateTime;
import model.Role;
import model.User;
import utils.DBUtils;
import utils.PasswordUtils;

/**
 *
 * @author TuanTran
 */
public class UserDAO {

    public User getUserByID(String id) {
        User user1 = new User();
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "SELECT * FROM tblUser WHERE UserID= ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                user1.setUserID(rs.getString("UserID"));
                user1.setUserName(rs.getString("UserName"));
                user1.setUserFullName(rs.getString("FullName"));
                user1.setUserEmail(rs.getString("UserEmail"));
                user1.setUserPassword(rs.getString("UserPassword"));
                user1.setUserPhone(rs.getString("UserPhone"));
                user1.setUserAddress(rs.getString("UserAddress"));
                user1.setAvatarURL(rs.getString("AvatarURL"));
                Role role = new Role(rs.getString("RoleID"), null);
                user1.setRoleID(role);
                Timestamp tsCreated = rs.getTimestamp("CreatedAt");
                if (tsCreated != null) {
                    user1.setCreatedAt(tsCreated.toLocalDateTime());
                }

                Timestamp tsUpdated = rs.getTimestamp("UpdatedAt");
                if (tsUpdated != null) {
                    user1.setUpdatedAt(tsUpdated.toLocalDateTime());
                } else {
                    user1.setUpdatedAt(null);
                }
                user1.setStatus(rs.getBoolean("Status"));
                user1.setIsVerified(rs.getBoolean("IsVerified"));
                return user1;
            }
        } catch (Exception e) {
        }
        return user1;
    }

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
                user.setAvatarURL(rs.getString("AvatarURL"));
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

    public User getUserByEmail(String email) {
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "SELECT * FROM tblUser WHERE UserEmail = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, email);
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
                user.setAvatarURL(rs.getString("AvatarURL"));
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
    public boolean loginByUsername(String userName, String password) {
        try {
            User user = getUserByUsername(userName);
            if (user != null) {
                return user.getUserPassword().equals(password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean loginByEmail(String userName, String password) {
        try {
            User user = getUserByEmail(userName);
            if (user != null) {
                return user.getUserPassword().equals(password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //======================================================
    //                      Create
    //======================================================
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
                + "UserPassword, UserPhone, UserAddress, AvatarURL, RoleID, CreatedAt, UpdatedAt, Status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), NULL, ?)";
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
            pst.setString(8, user.getAvatarURL());
            pst.setString(9, user.getRoleID().getRoleID());
            pst.setBoolean(10, false); // Status = 0
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 1. Lưu verification code + expiry vào user
    public boolean saveVerificationCode(String userID, String code, Timestamp expiry) {
        String sql = "UPDATE tblUser SET VerificationCode = ?, VerificationExpiry = ?, IsVerified = 0 WHERE UserID = ?";
        try ( Connection conn = DBUtils.getConnection();  PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, code);
            pst.setTimestamp(2, expiry);
            pst.setString(3, userID);
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

// 2. Lấy userID (hoặc User object) theo verification code
    public User getUserByVerificationCode(String code) {
        String sql = "SELECT * FROM tblUser WHERE VerificationCode = ?";
        try ( Connection conn = DBUtils.getConnection();  PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, code);
            try ( ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setUserID(rs.getString("UserID"));
                    user.setUserName(rs.getString("UserName"));
                    user.setUserFullName(rs.getString("FullName"));
                    user.setUserEmail(rs.getString("UserEmail"));
                    user.setUserPassword(rs.getString("UserPassword"));
                    user.setUserPhone(rs.getString("UserPhone"));
                    user.setUserAddress(rs.getString("UserAddress"));
                    user.setAvatarURL(rs.getString("AvatarURL"));
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

// 3. Xác thực user (set IsVerified = 1, clear code)
    public boolean verifyUserByCode(String code) {
        String sql = "UPDATE tblUser SET IsVerified = 1, Status = 1, VerificationCode = NULL, VerificationExpiry = NULL WHERE VerificationCode = ?";
        try ( Connection conn = DBUtils.getConnection();  PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, code);
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

// 4. Optional: clear code (xoá mã sau khi xác thực hoặc hết hạn)
    public boolean clearVerificationForUser(String userID) {
        String sql = "UPDATE tblUser SET VerificationCode = NULL, VerificationExpiry = NULL WHERE UserID = ?";
        try ( Connection conn = DBUtils.getConnection();  PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, userID);
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //======================================================
    //                      Read
    //======================================================
    //function for search
    public ArrayList<User> getAllUser() {
        ArrayList<User> listUser = new ArrayList<>();
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "SELECT u.*, r.RoleName "
                    + "FROM tblUser u "
                    + "LEFT JOIN tblRole r ON u.RoleID = r.RoleID "
                    + "ORDER BY u.UserID DESC";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserID(rs.getString("UserID"));
                user.setUserName(rs.getString("UserName"));
                user.setUserFullName(rs.getNString("FullName")); // NVARCHAR → getNString
                user.setUserEmail(rs.getString("UserEmail"));
                user.setUserPassword(rs.getString("UserPassword"));
                user.setUserPhone(rs.getString("UserPhone"));
                user.setUserAddress(rs.getNString("UserAddress"));
                user.setAvatarURL(rs.getString("AvatarURL"));
                user.setStatus(rs.getBoolean("Status"));

                Role role = new Role(rs.getString("RoleID"), rs.getString("RoleName"));
                user.setRoleID(role);

                Timestamp c = rs.getTimestamp("CreatedAt");
                user.setCreatedAt(c != null ? c.toLocalDateTime() : null);
                Timestamp u = rs.getTimestamp("UpdatedAt");
                user.setUpdatedAt(u != null ? u.toLocalDateTime() : null);

                listUser.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listUser;
    }

    public ArrayList<User> getAllUserByFullName(String userFullName) {
        ArrayList<User> list = new ArrayList<>();
        String keyword = userFullName.trim();
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "SELECT u.*, r.RoleName "
                    + "FROM tblUser u "
                    + "LEFT JOIN tblRole r ON u.RoleID = r.RoleID "
                    + "WHERE u.FullName LIKE ? "
                    + "ORDER BY u.UserID DESC";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUserID(rs.getString("UserID"));
                user.setUserName(rs.getString("UserName"));
                user.setUserFullName(rs.getNString("FullName")); // NVARCHAR → getNString
                user.setUserEmail(rs.getString("UserEmail"));
                user.setUserPassword(rs.getString("UserPassword"));
                user.setUserPhone(rs.getString("UserPhone"));
                user.setUserAddress(rs.getNString("UserAddress"));
                user.setAvatarURL(rs.getString("AvatarURL"));
                user.setStatus(rs.getBoolean("Status"));

                Role role = new Role(rs.getString("RoleID"), rs.getString("RoleName"));
                user.setRoleID(role);

                Timestamp c = rs.getTimestamp("CreatedAt");
                user.setCreatedAt(c != null ? c.toLocalDateTime() : null);
                Timestamp u = rs.getTimestamp("UpdatedAt");
                user.setUpdatedAt(u != null ? u.toLocalDateTime() : null);

                list.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //======================================================
    //                      Update
    //======================================================
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

    public boolean updatePasswordByUserName(String userName, String hashedPassword) {
        String sql = "UPDATE tblUser SET UserPassword=?, UpdatedAt=GETDATE() WHERE UserName=?";
        try ( Connection c = DBUtils.getConnection();  PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, hashedPassword);
            ps.setString(2, userName);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateByUserName(User user) {

        try {
            Connection c = DBUtils.getConnection();
            String sql
                    = "UPDATE tblUser"
                    + " SET FullName=?, UserEmail=?, UserPhone=?, UserAddress=?, AvatarURL=?, RoleID=?, UpdatedAt = GETDATE(), Status=? WHERE UserName=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setNString(1, user.getUserFullName());
            ps.setString(2, user.getUserEmail());
            ps.setString(3, user.getUserPhone());
            ps.setNString(4, user.getUserAddress());
            ps.setString(6, user.getRoleID().getRoleID());
            ps.setString(5, user.getAvatarURL());
            ps.setBoolean(7, user.isStatus());
            ps.setString(8, user.getUserName());

            int rows = ps.executeUpdate();

            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //======================================================
    //                      Delete
    //======================================================
    public boolean softDelete(String userID) {
        try {
            Connection c = DBUtils.getConnection();
            String sql = "UPDATE tblUser SET status=0"
                    + "      WHERE UserID=?";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, userID);

            int i = ps.executeUpdate();
            return i > 0;
        } catch (Exception e) {
        }
        return false;
    }
}
