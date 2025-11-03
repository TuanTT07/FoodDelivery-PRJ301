/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;


public class ProductDetail {
    private String detailID;
    private String size;
    private String combo;
    private String extraInfo;
    private Product productID;

    public ProductDetail() {
    }

    public ProductDetail(String detailID, String size, String combo, String extraInfo, Product productID) {
        this.detailID = detailID;
        this.size = size;
        this.combo = combo;
        this.extraInfo = extraInfo;
        this.productID = productID;
    }

    public ProductDetail(String size, String combo, String extraInfo, Product productID) {
        this.size = size;
        this.combo = combo;
        this.extraInfo = extraInfo;
        this.productID = productID;
    }
    

    public String getDetailID() {
        return detailID;
    }

    public void setDetailID(String detailID) {
        this.detailID = detailID;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCombo() {
        return combo;
    }

    public void setCombo(String combo) {
        this.combo = combo;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public Product getProductID() {
        return productID;
    }

    public void setProductID(Product productID) {
        this.productID = productID;
    }
    
    
}
