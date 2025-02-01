package com.project.zeidot.dto;

public class FoodBatchDetailsDTO {
    private String foodId;
    private String foodName;
    private String foodWeight;
    private String duration;

    public FoodBatchDetailsDTO(String foodId, String foodName, String foodWeight, String duration) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodWeight = foodWeight;
        this.duration = duration;
    }

    public FoodBatchDetailsDTO() {

    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodWeight() {
        return foodWeight;
    }

    public void setFoodWeight(String foodWight) {
        this.foodWeight = foodWight;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
