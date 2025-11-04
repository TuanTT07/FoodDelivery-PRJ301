/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Role;
import utils.DBUtils;

/**
 *
 * @author ACER
 */
public class RoleDAO {

    public Role setRole(String action) {
        Role role = new Role();
        if (action == null || action.trim().length() == 0) {
            return null;
        }
        if (action.equals("signUpUser")) {
            role.setRoleID("S004");
            role.setRoleName("member");
        } else if (action.equals("signUpDelivery")) {
            role.setRoleID("S003");
            role.setRoleName("delivery");
        } else if (action.equals("signUpStore")) {
            role.setRoleID("S002");
            role.setRoleName("store_owner");
        }
        return role;
    }
    
    public Role getById(String roleId) {
        String sql = "SELECT RoleID, RoleName FROM tblRole WHERE RoleID = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, roleId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Role role = new Role();
                    role.setRoleID(rs.getString("RoleID"));
                    role.setRoleName(rs.getString("RoleName"));
                    return role;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
