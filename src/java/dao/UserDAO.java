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
    //function for login

    public User loginByUsername(String username, String password) {
        try ( Connection conn = DBUtils.getConnection()) {
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

                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User loginByEmail(String email, String password) {
        try ( Connection conn = DBUtils.getConnection()) {
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

                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Create
    public boolean insert(User user) {
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "INSERT INTO tblUser (UserID, UserName, FullName, UserEmail, UserPassword, UserPhone, UserAddress, RoleID, CreatedAt, UpdatedAt)"
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, user.getUserID());
            pst.setString(2, user.getUserName());
            pst.setString(3, user.getUserFullName());
            pst.setString(4, user.getUserEmail());
            pst.setString(5, user.getUserPassword());
            pst.setString(6, user.getUserPhone());
            pst.setString(7, user.getUserAddress());
            pst.setString(8, user.getRoleID().getRoleID());
            pst.setTimestamp(9, Timestamp.valueOf(user.getCreatedAt()));
            if (user.getUpdatedAt() != null) {
                pst.setTimestamp(10, Timestamp.valueOf(user.getUpdatedAt()));
            } else {
                pst.setNull(10, java.sql.Types.TIMESTAMP);
            }
            int i = pst.executeUpdate();
            return i > 0;
        } catch (Exception e) {
        }
        return false;
    }

    //Read
}
