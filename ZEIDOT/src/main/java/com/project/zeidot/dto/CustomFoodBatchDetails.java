package com.project.zeidot.dto;

public class CustomFoodBatchDetails {
    private String foodID;
    private String foodName;
    private String foodWeight;
    private String duration;

    // No-argument constructor
    public CustomFoodBatchDetails() {
    }

    // Parameterized constructor for convenience
    public CustomFoodBatchDetails(String foodID, String foodName, String foodWeight, String duration) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.foodWeight = foodWeight;
        this.duration = duration;
    }

    // Getters and Setters
    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
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

    public void setFoodWeight(String foodWeight) {
        this.foodWeight = foodWeight;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    // Optional: toString method for easy debugging and logging
    @Override
    public String toString() {
        return "FoodBatchDetailsDTO{" +
                "foodID='" + foodID + '\'' +
                ", foodName='" + foodName + '\'' +
                ", foodWeight='" + foodWeight + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
