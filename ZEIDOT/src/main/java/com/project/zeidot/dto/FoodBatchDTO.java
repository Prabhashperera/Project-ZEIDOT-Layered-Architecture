package com.project.zeidot.dto;

public class FoodBatchDTO {
    private String foodBatchId;
    private String foodAmount;
    private String date;
    private String isAvailable;
    private String duration;

    public FoodBatchDTO() {

    }
    public FoodBatchDTO(String foodBatchId, String foodAmount, String date, String isAvailable, String duration) {
        this.foodBatchId = foodBatchId;
        this.foodAmount = foodAmount;
        this.date = date;
        this.isAvailable = isAvailable;
        this.duration = duration;
    }

    public String getFoodBatchId() {
        return foodBatchId;
    }

    public void setFoodBatchId(String foodBatchId) {
        this.foodBatchId = foodBatchId;
    }

    public String getFoodAmount() {
        return foodAmount;
    }

    public FoodBatchDTO(String foodBatchId, String foodAmount, String date , String isAvailable) {
        this.foodBatchId = foodBatchId;
        this.foodAmount = foodAmount;
        this.date = date;
        this.isAvailable = isAvailable;
    }

    public void setFoodAmount(String foodAmount) {
        this.foodAmount = foodAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
