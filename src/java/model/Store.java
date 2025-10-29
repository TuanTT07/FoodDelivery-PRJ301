package model;

import java.time.LocalDateTime;

public class Store {

    private String storeID;
    private String storeName;
    private String storeAddress;   
    private String city;
    private String district;
    private double storeRating;
    private String openTime;       
    private String closeTime;
    private User ownerUserID;    
    private CategoryStore storeCategoryId;
    private String storePhone;
    private String storeEmail;
    private String description;
    private boolean status;        
    private String bankAccountName;
    private String bankAccountNumber;
    private String bankName;
    private String logoURL;
    private String bannerURL;
    private boolean is24Hours;
    private LocalDateTime createdAt;
          
    
    
    public Store() {
        this.storeRating = 0;
        this.status = true;
        this.is24Hours = false;
    }

    // Constructor đầy đủ

    public Store(String storeID, String storeName, String storeAddress, String city, String district, double storeRating, String openTime, String closeTime, User ownerUserID, CategoryStore storeCategoryId, String storePhone, String storeEmail, String description, boolean status, String bankAccountName, String bankAccountNumber, String bankName, String logoURL, String bannerURL, boolean is24Hours, LocalDateTime createdAt) {
        this.storeID = storeID;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.city = city;
        this.district = district;
        this.storeRating = storeRating;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.ownerUserID = ownerUserID;
        this.storeCategoryId = storeCategoryId;
        this.storePhone = storePhone;
        this.storeEmail = storeEmail;
        this.description = description;
        this.status = status;
        this.bankAccountName = bankAccountName;
        this.bankAccountNumber = bankAccountNumber;
        this.bankName = bankName;
        this.logoURL = logoURL;
        this.bannerURL = bannerURL;
        this.is24Hours = is24Hours;
        this.createdAt = createdAt;
    }

    
    
    
    // ===== Getter & Setter =====

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public double getStoreRating() {
        return storeRating;
    }

    public void setStoreRating(double storeRating) {
        this.storeRating = storeRating;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public User getOwnerUserID() {
        return ownerUserID;
    }

    public void setOwnerUserID(User ownerUserID) {
        this.ownerUserID = ownerUserID;
    }

    public CategoryStore getStoreCategoryId() {
        return storeCategoryId;
    }

    public void setStoreCategoryId(CategoryStore storeCategoryId) {
        this.storeCategoryId = storeCategoryId;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    public String getStoreEmail() {
        return storeEmail;
    }

    public void setStoreEmail(String storeEmail) {
        this.storeEmail = storeEmail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public String getBannerURL() {
        return bannerURL;
    }

    public void setBannerURL(String bannerURL) {
        this.bannerURL = bannerURL;
    }

    public boolean isIs24Hours() {
        return is24Hours;
    }

    public void setIs24Hours(boolean is24Hours) {
        this.is24Hours = is24Hours;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    
        
   
}
