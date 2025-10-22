/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import java.util.logging.Logger;

/**
 *
 * @author TuanTran
 */
public class ProductTopping {
    private Product productID;
    private Topping toppingID;

    public ProductTopping() {
    }

    public ProductTopping(Product productID, Topping toppingID) {
        this.productID = productID;
        this.toppingID = toppingID;
    }

    public Product getProductID() {
        return productID;
    }

    public void setProductID(Product productID) {
        this.productID = productID;
    }

    public Topping getToppingID() {
        return toppingID;
    }

    public void setToppingID(Topping toppingID) {
        this.toppingID = toppingID;
    }
    
    
    
}
