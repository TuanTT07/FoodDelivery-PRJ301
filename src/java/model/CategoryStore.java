/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author ACER
 */
public class CategoryStore implements Serializable {

    private static final long serialVersionUID = 1L;

    private String storeCategoryId;
    private String storeCategoryName;
    private boolean IsActive;

    public CategoryStore() {
    }

    public CategoryStore(String storeCategoryId, String storeCategoryName, boolean IsActive) {
        this.storeCategoryId = storeCategoryId;
        this.storeCategoryName = storeCategoryName;
        this.IsActive = IsActive;
    }

    public String getStoreCategoryId() {
        return storeCategoryId;
    }

    public void setStoreCategoryId(String storeCategoryId) {
        this.storeCategoryId = storeCategoryId;
    }

    public String getStoreCategoryName() {
        return storeCategoryName;
    }

    public void setStoreCategoryName(String storeCategoryName) {
        this.storeCategoryName = storeCategoryName;
    }

    public boolean isIsActive() {
        return IsActive;
    }

    public void setIsActive(boolean IsActive) {
        this.IsActive = IsActive;
    }

}
