package model;

public class Store {

    private String storeID;
    private String storeName;
    private String storeAddress;   
    private String city;
    private String district;
    private String description;
    private double storeRating;    
    private String openTime;       
    private String closeTime;
    private boolean is24Hours;     
    private String bankAccountName;
    private String bankAccountNumber;
    private String bankName;
    private String logoURL;
    private String coverURL;
    private boolean status;       
    private User ownerUserID;      

    public Store() {
        this.storeRating = 0;
        this.status = true;
        this.is24Hours = false;
    }

    // Constructor đầy đủ
    public Store(String storeID, String storeName, String storeAddress, String city, String district,
            String description, double storeRating, String openTime, String closeTime, boolean is24Hours,
            String bankAccountName, String bankAccountNumber, String bankName,
            String logoURL, String coverURL, boolean status, User ownerUserID) {
        this.storeID = storeID;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.city = city;
        this.district = district;
        this.description = description;
        this.storeRating = storeRating;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.is24Hours = is24Hours;
        this.bankAccountName = bankAccountName;
        this.bankAccountNumber = bankAccountNumber;
        this.bankName = bankName;
        this.logoURL = logoURL;
        this.coverURL = coverURL;
        this.status = status;
        this.ownerUserID = ownerUserID;
    }

    public Store(String storeName, String storeAddress, String city, String district, String description, double storeRating, String openTime, String closeTime, boolean is24Hours, String bankAccountName, String bankAccountNumber, String bankName, String logoURL, String coverURL, boolean status, User ownerUserID) {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.city = city;
        this.district = district;
        this.description = description;
        this.storeRating = storeRating;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.is24Hours = is24Hours;
        this.bankAccountName = bankAccountName;
        this.bankAccountNumber = bankAccountNumber;
        this.bankName = bankName;
        this.logoURL = logoURL;
        this.coverURL = coverURL;
        this.status = status;
        this.ownerUserID = ownerUserID;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public boolean isIs24Hours() {
        return is24Hours;
    }

    public void setIs24Hours(boolean is24Hours) {
        this.is24Hours = is24Hours;
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

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User getOwnerUserID() {
        return ownerUserID;
    }

    public void setOwnerUserID(User ownerUserID) {
        this.ownerUserID = ownerUserID;
    }

}
