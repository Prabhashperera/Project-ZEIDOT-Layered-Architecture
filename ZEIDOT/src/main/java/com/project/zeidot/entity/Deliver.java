package com.project.zeidot.entity;

public class Deliver {
    private String deliveryID;
    private String deliverDate;
    private String deliverTime;
    private String donationID;

    public Deliver(String deliveryID, String deliverDate, String deliverTime, String donationID) {
        this.deliveryID = deliveryID;
        this.deliverDate = deliverDate;
        this.deliverTime = deliverTime;
        this.donationID = donationID;
    }

    public Deliver() {

    }

    public String getDeliveryID() {
        return deliveryID;
    }

    public void setDeliveryID(String deliveryID) {
        this.deliveryID = deliveryID;
    }

    public String getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(String deliverDate) {
        this.deliverDate = deliverDate;
    }

    public String getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(String deliverTime) {
        this.deliverTime = deliverTime;
    }

    public String getDonationID() {
        return donationID;
    }

    public void setDonationID(String donationID) {
        this.donationID = donationID;
    }
}
