/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

/**
 *
 * @author TuanTran
 */
public class PaymentMethod {
    private String paymentMethodID;
    private String paymentMethodName;
    private String paymentMethodDesc;
    private boolean isActive;

    public PaymentMethod() {
    }

    public PaymentMethod(String paymentMethodID, String paymentMethodName, String paymentMethodDesc, boolean isActive) {
        this.paymentMethodID = paymentMethodID;
        this.paymentMethodName = paymentMethodName;
        this.paymentMethodDesc = paymentMethodDesc;
        this.isActive = isActive;
    }

    public String getPaymentMethodID() {
        return paymentMethodID;
    }

    public void setPaymentMethodID(String paymentMethodID) {
        this.paymentMethodID = paymentMethodID;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    public String getPaymentMethodDesc() {
        return paymentMethodDesc;
    }

    public void setPaymentMethodDesc(String paymentMethodDesc) {
        this.paymentMethodDesc = paymentMethodDesc;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    
}
