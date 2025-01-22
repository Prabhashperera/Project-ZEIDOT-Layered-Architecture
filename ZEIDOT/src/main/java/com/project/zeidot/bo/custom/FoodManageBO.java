package com.project.zeidot.bo.custom;

import com.project.zeidot.dao.custom.CrudDAO;
import com.project.zeidot.dto.BatchDetailsDto;
import com.project.zeidot.dto.FoodDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FoodManageBO extends SuperBO{
    boolean updateFood(FoodDto food) throws SQLException;
    boolean deleteFood(String name) throws SQLException;
    boolean saveFood(FoodDto dto , BatchDetailsDto dtos) throws SQLException;
    String getNextCustomerId() throws SQLException;
    ArrayList<FoodDto> getAllCustomers() throws SQLException;
    double getCurrentWeight(String FBId) throws SQLException;
    boolean updateAmount(double CurrentWeight , double foodWeight) throws SQLException;
    boolean decreaseAmount(double CurrentWeight , double foodWeight) throws SQLException;
    void updateAmountWhenUpdate(String weight , String foodID) throws SQLException;
    double getFoodWeight(String foodID) throws SQLException;
    boolean decreaseAmountWhenUpdate(double CurrentWeight , double newWight) throws SQLException;
    String getCurrentBatchID() throws SQLException;
}
