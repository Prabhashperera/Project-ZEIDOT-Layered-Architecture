package com.project.zeidot.dto;

public class DonationDTO {
    private String donationID;
    private String donationName;
    private String FBId;
    private String foodBankID;

    public DonationDTO(String donationID, String donationName, String FBId, String foodBankID) {
        this.donationID = donationID;
        this.donationName = donationName;
        this.FBId = FBId;
        this.foodBankID = foodBankID;
    }

    public DonationDTO() {
    }


    // Getters and Setters
    public String getDonationID() {
        return donationID;
    }

    public void setDonationID(String donationID) {
        this.donationID = donationID;
    }

    public String getDonationName() {
        return donationName;
    }

    public void setDonationName(String donationName) {
        this.donationName = donationName;
    }

    public String getFBId() {
        return FBId;
    }

    public void setFBId(String FBId) {
        this.FBId = FBId;
    }

    public String getFoodBankID() {
        return foodBankID;
    }

    public void setFoodBankID(String foodBankID) {
        this.foodBankID = foodBankID;
    }
}
