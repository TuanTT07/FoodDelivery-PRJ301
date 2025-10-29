/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import java.time.LocalDateTime;


public class Event {
    private String eventID;
    private String eventTitle;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String bannerURL;   
    private boolean isActive;
    private Voucher voucherID;

    public Event() {
    }

    public Event(String eventID, String eventTitle, String description, LocalDateTime startDate, LocalDateTime endDate, String bannerURL, boolean isActive, Voucher voucherID) {
        this.eventID = eventID;
        this.eventTitle = eventTitle;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bannerURL = bannerURL;
        this.isActive = isActive;
        this.voucherID = voucherID;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getBannerURL() {
        return bannerURL;
    }

    public void setBannerURL(String bannerURL) {
        this.bannerURL = bannerURL;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Voucher getVoucherID() {
        return voucherID;
    }

    public void setVoucherID(Voucher voucherID) {
        this.voucherID = voucherID;
    }

    
    
    
}
