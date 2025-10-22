/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import java.time.LocalTime;


public class Store {
    private String storeID;
    private String storeName;
    private String storeAddress;
    private double storeRating;
    private LocalTime openTime;
    private LocalTime closeTime;
    private User ownerUserID;

    public Store() {
    }

    public Store(String storeID, String storeName, String storeAddress, double storeRating, LocalTime openTime, LocalTime closeTime, User ownerUserID) {
        this.storeID = storeID;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.storeRating = storeRating;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.ownerUserID = ownerUserID;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public double getStoreRating() {
        return storeRating;
    }

    public void setStoreRating(double storeRating) {
        this.storeRating = storeRating;
    }

    public LocalTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }

    public User getOwnerUserID() {
        return ownerUserID;
    }

    public void setOwnerUserID(User ownerUserID) {
        this.ownerUserID = ownerUserID;
    }
    
    
    
}
