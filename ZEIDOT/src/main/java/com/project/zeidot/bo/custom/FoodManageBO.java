package com.project.zeidot.bo.custom;

import com.project.zeidot.dto.BatchDetailsDTO;
import com.project.zeidot.dto.FoodDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FoodManageBO extends SuperBO{
    boolean updateFood(FoodDTO food) throws SQLException;
    boolean deleteFood(String name , String batch , String foodWeight) throws SQLException;
    boolean saveFood(FoodDTO dto , BatchDetailsDTO dtos) throws SQLException;
    String getNextCustomerId() throws SQLException;
    ArrayList<FoodDTO> getAllCustomers() throws SQLException;
    double getCurrentWeight(String FBId) throws SQLException;
    boolean updateAmount(double CurrentWeight , double foodWeight) throws SQLException;
    boolean decreaseAmount(double CurrentWeight , double foodWeight) throws SQLException;
    void updateAmountWhenUpdate(String weight , String foodID) throws SQLException;
    double getFoodWeight(String foodID) throws SQLException;
    boolean decreaseAmountWhenUpdate(double CurrentWeight , double newWight) throws SQLException;
    String getCurrentBatchID() throws SQLException;
}
