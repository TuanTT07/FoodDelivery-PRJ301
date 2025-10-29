/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;


public class CategoryStore {
    private String categoryID;
    private String categoryName;
    private Store storeId;
    private boolean isActive;

    public CategoryStore() {
    }

    public CategoryStore(String categoryID, String categoryName, Store storeId, boolean isActive) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.storeId = storeId;
        this.isActive = isActive;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Store getStoreId() {
        return storeId;
    }

    public void setStoreId(Store storeId) {
        this.storeId = storeId;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    
    
    
}
