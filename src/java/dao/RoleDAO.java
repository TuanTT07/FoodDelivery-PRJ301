/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Role;

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
        }
        return role;
    }
}
