package com.project.zeidot.dao.custom;

import com.project.zeidot.dto.FoodDTO;
import com.project.zeidot.entity.Food;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FoodManageDAO extends CrudDAO<Food> {
     ArrayList<Food> getAllCustomers() throws SQLException;
     double getCurrentWeight(String FBId) throws SQLException;
     boolean updateAmount(double CurrentWeight , double foodWeight) throws SQLException;
     boolean decreaseAmount(double CurrentWeight , double foodWeight) throws SQLException;
     void updateAmountWhenUpdate(String weight , String foodID) throws SQLException;
     double getFoodWeight(String foodID) throws SQLException;
     boolean decreaseAmountWhenUpdate(double CurrentWeight , double newWight) throws SQLException;
     String getCurrentBatchID() throws SQLException;

}
