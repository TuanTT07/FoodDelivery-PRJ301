/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACER
 */
public class BankAccount {
    private String bankAccountName;
    private String bankNumber;
    private String bankName;
    private String OwnerId;

    public BankAccount() {
    }

    public BankAccount(String bankAccountName, String bankNumber, String bankName, String OwnerId) {
        this.bankAccountName = bankAccountName;
        this.bankNumber = bankNumber;
        this.bankName = bankName;
        this.OwnerId = OwnerId;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getOwnerId() {
        return OwnerId;
    }

    public void setOwnerId(String OwnerId) {
        this.OwnerId = OwnerId;
    }
    
    
    
}
