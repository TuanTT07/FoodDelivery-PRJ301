/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.CategoryStore;

/**
 *
 * @author ACER
 */
public class StoreCategoryDAO {

    public CategoryStore setCategoryStore(String id) {
        CategoryStore cs = new CategoryStore();
        if (id == null || id.trim().length() == 0) {
            return null;
        }
        if (id.equals("1")) {
            cs.setStoreCategoryId("SC001");
            cs.setStoreCategoryName("Đồ ăn");
            cs.setIsActive(true);
        } else if (id.equals("2")) {
            cs.setStoreCategoryId("SC002");
            cs.setStoreCategoryName("Đồ uống");
            cs.setIsActive(true);
        } else if (id.equals("3")) {
            cs.setStoreCategoryId("SC003");
            cs.setStoreCategoryName("Đồ chay");
            cs.setIsActive(true);
        } else if (id.equals("4")) {
            cs.setStoreCategoryId("SC004");
            cs.setStoreCategoryName("Ăn vặt");
            cs.setIsActive(true);
        } else if (id.equals("5")) {
            cs.setStoreCategoryId("SC005");
            cs.setStoreCategoryName("Đồ ngọt");
            cs.setIsActive(true);
        }
        return cs;
    }
}
