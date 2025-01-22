package com.project.zeidot.dto;

public class FoodBankDto {
    private String FBKId;
    private String FBKName;
    private String FBKAddress;
    private String FBKEmail;

    public FoodBankDto(String FBKId, String FBKAddress, String FBKName, String FBKEmail) {
        this.FBKId = FBKId;
        this.FBKName = FBKName;
        this.FBKAddress = FBKAddress;
        this.FBKEmail = FBKEmail;
    }

    public FoodBankDto() {

    }
    public FoodBankDto(String FBName,String FBKEmail) {
        this.FBKName = FBKName;
        this.FBKEmail = FBKEmail;
    }

    public void setFBKId(String FBKId) {
        this.FBKId = FBKId;
    }

    public String getFBKName() {
        return FBKName;
    }

    public void setFBKName(String FBKName) {
        this.FBKName = FBKName;
    }

    public String getFBKAddress() {
        return FBKAddress;
    }

    public void setFBKAddress(String FBKAddress) {
        this.FBKAddress = FBKAddress;
    }

    public String getFBKEmail() {
        return FBKEmail;
    }

    public void setFBKEmail(String FBKEmail) {
        this.FBKEmail = FBKEmail;
    }

    public String getFBKId() {
        return FBKId;
    }
}
