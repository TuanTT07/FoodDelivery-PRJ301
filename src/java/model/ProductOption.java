/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

/**
 *
 * @author TuanTran
 */
public class ProductOption {
    private String optionID;
    private String optionType;
    private String optionValue;
    private double extraPrice;
    private Product productID;

    public ProductOption() {
    }

    public ProductOption(String optionID, String optionType, String optionValue, double extraPrice, Product productID) {
        this.optionID = optionID;
        this.optionType = optionType;
        this.optionValue = optionValue;
        this.extraPrice = extraPrice;
        this.productID = productID;
    }

    public String getOptionID() {
        return optionID;
    }

    public void setOptionID(String optionID) {
        this.optionID = optionID;
    }

    public String getOptionType() {
        return optionType;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    public double getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(double extraPrice) {
        this.extraPrice = extraPrice;
    }

    public Product getProductID() {
        return productID;
    }

    public void setProductID(Product productID) {
        this.productID = productID;
    }

    
    
}
