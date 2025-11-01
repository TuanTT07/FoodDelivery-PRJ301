/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;


public class Product {
    private String productID;
    private String productName;
    private double productPrice;
    private String productDesc;
    private Category categoryID;
    private Store storeID;
    private boolean isActive;

    public Product() {
    }

    public Product(String productName, double productPrice, String productDesc, Category categoryID, Store storeID) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDesc = productDesc;
        this.categoryID = categoryID;
        this.storeID = storeID;
    }

    public Product(String productID, String productName, double productPrice, String productDesc, Category categoryID, Store storeID, boolean isActive) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDesc = productDesc;
        this.categoryID = categoryID;
        this.storeID = storeID;
        this.isActive = isActive;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Category getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Category categoryID) {
        this.categoryID = categoryID;
    }

    public Store getStoreID() {
        return storeID;
    }

    public void setStoreID(Store storeID) {
        this.storeID = storeID;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    
}
