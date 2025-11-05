/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author vanli
 */
import org.mindrot.jbcrypt.BCrypt;
public class PasswordUtils {
    private static final int COST = 12; 
  public static String hash(String plain) {
    return BCrypt.hashpw(plain, BCrypt.gensalt(COST)); // gồm cả salt
  }
  public static boolean matches(String plain, String hashed) {
    return BCrypt.checkpw(plain, hashed);
  }
}
