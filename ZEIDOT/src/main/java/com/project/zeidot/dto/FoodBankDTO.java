package com.project.zeidot.dto;

import com.project.zeidot.entity.FoodBank;

public class FoodBankDTO extends FoodBank {
    private String FBKId;
    private String FBKName;
    private String FBKAddress;
    private String FBKEmail;

    public FoodBankDTO(String FBKId, String FBKAddress, String FBKName, String FBKEmail) {
        this.FBKId = FBKId;
        this.FBKName = FBKName;
        this.FBKAddress = FBKAddress;
        this.FBKEmail = FBKEmail;
    }

    public FoodBankDTO() {

    }
    public FoodBankDTO(String FBName, String FBKEmail) {
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
