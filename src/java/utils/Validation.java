/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author TuanTran
 */
public interface Validation {

    public static final String ROLE_ADMIN = "S001";
    public static final String ROLE_STORE_OWNER = "S002";
    public static final String ROLE_DRIVER = "S003";
    public static final String ROLE_MEMBER = "S004";
    public static final String EMAIL_REGEX
            = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    public static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$";
    public static final String FULLNAME_REGEX = "^[A-Za-zÀ-Ỹà-ỹ]+(\\s+[A-Za-zÀ-Ỹà-ỹ]+)+$";
    public static final String PHONE_REGEX = "^(\\+84|0)(3[2-9]|5[25689]|7[06-9]|8[1-689]|9[0-46-9])\\d{7}$";
}
