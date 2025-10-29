/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import java.time.LocalDateTime;
import model.enums.PaymentStatus;

public class Payment {
    private String paymentID;
    private PaymentStatus paymentStatus;
    private String transactionID;
    private LocalDateTime paymentDate;
    private double amount;
    private PaymentMethod paymentMethodID;

    public Payment() {
    }

    public Payment(String paymentID, PaymentStatus paymentStatus, String transactionID, LocalDateTime paymentDate, double amount, PaymentMethod paymentMethodID) {
        this.paymentID = paymentID;
        this.paymentStatus = paymentStatus;
        this.transactionID = transactionID;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.paymentMethodID = paymentMethodID;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentMethod getPaymentMethodID() {
        return paymentMethodID;
    }

    public void setPaymentMethodID(PaymentMethod paymentMethodID) {
        this.paymentMethodID = paymentMethodID;
    }

    
    
    
}
