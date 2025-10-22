/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import java.time.LocalDateTime;


public class Order {
    private String orderID;    
    private double totalPrice;
    private String status;
    private LocalDateTime orderDate;
    private String deliveryAddress;
    private User userID;
    private Voucher voucherID;
    private Payment paymentID;

    public Order() {
    }

    public Order(String orderID, double totalPrice, String status, LocalDateTime orderDate, String deliveryAddress, User userID, Voucher voucherID, Payment paymentID) {
        this.orderID = orderID;
        this.totalPrice = totalPrice;
        this.status = status;
        this.orderDate = orderDate;
        this.deliveryAddress = deliveryAddress;
        this.userID = userID;
        this.voucherID = voucherID;
        this.paymentID = paymentID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public Voucher getVoucherID() {
        return voucherID;
    }

    public void setVoucherID(Voucher voucherID) {
        this.voucherID = voucherID;
    }

    public Payment getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(Payment paymentID) {
        this.paymentID = paymentID;
    }
    
    
}
