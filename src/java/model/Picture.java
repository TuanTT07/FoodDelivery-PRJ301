    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

public class Picture {

    private String pictureId;
    private Product productId;
    private String pictureURL;
    private LocalDateTime uploadDate;
    private boolean isMain;

    public Picture() {
    }

    public Picture(String pictureId, Product productId, String pictureURL, LocalDateTime uploadDate, boolean isMain) {
        this.pictureId = pictureId;
        this.productId = productId;
        this.pictureURL = pictureURL;
        this.uploadDate = uploadDate;
        this.isMain = isMain;
    }

    public Picture(Product productId, String pictureURL,  boolean isMain) {
        this.productId = productId;
        this.pictureURL = pictureURL;
        this.isMain = isMain;
    }

    
    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    public boolean isIsMain() {
        return isMain;
    }

    public void setIsMain(boolean isMain) {
        this.isMain = isMain;
    }

    
}
