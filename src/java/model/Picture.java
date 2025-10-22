/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;


public class Picture {
    private String pictureID;
    private String url;
    private String type; // user / store / product
    private String ownerID;
    private boolean isMain;

    public Picture() {
    }

    public Picture(String pictureID, String url, String type, String ownerID, boolean isMain) {
        this.pictureID = pictureID;
        this.url = url;
        this.type = type;
        this.ownerID = ownerID;
        this.isMain = isMain;
    }

    public String getPictureID() {
        return pictureID;
    }

    public void setPictureID(String pictureID) {
        this.pictureID = pictureID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public boolean isIsMain() {
        return isMain;
    }

    public void setIsMain(boolean isMain) {
        this.isMain = isMain;
    }
    
    
}
