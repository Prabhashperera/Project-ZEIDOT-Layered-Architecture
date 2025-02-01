package com.project.zeidot.entity;

public class Food {
    private String foodID;
    private String foodName;
    private String weight;
    private String duration;

    public Food(String foodID, String foodName, String weight, String duration) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.weight = weight;
        this.duration = duration;
    }

    public Food() {

    }

    @Override
    public String toString() {
        return "FoodDto{" +
                "foodID='" + foodID + '\'' +
                ", foodName='" + foodName + '\'' +
                ", weight='" + weight + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }

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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
