package com.project.zeidot.bo.custom.impl;

import com.project.zeidot.bo.custom.BOFactory;
import com.project.zeidot.bo.custom.FoodManageBO;
import com.project.zeidot.dao.DAOFactory;
import com.project.zeidot.dao.custom.FoodManageDAO;
import com.project.zeidot.dao.custom.impl.FoodManageDAOImpl;
import com.project.zeidot.db.DBConnection;
import com.project.zeidot.dto.BatchDetailsDto;
import com.project.zeidot.dto.FoodDto;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FoodManageBOImpl implements FoodManageBO {
    FoodManageDAO foodManageDAOImpl = (FoodManageDAO) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.FOOD);

    // Food Manage Model Instance //LA
    @Override
    public boolean updateFood(FoodDto food) throws SQLException {
        return foodManageDAOImpl.update(food);
    }

    @Override
    public boolean deleteFood(String name) throws SQLException {
        return foodManageDAOImpl.delete(name);
    }

    @Override
    public boolean saveFood(FoodDto dto) throws SQLException {
        Connection conn = null;
        try {
            conn = DBConnection.getInstance().getConnection();
            conn.setAutoCommit(false);

            boolean isSaved = foodManageDAOImpl.save(dto);
            if (!isSaved) {
                conn.rollback();
                throw new SQLException("Failed to save food details.");
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e; // Propagate the exception for higher-level handling
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
            }
        }
    }

    @Override
    public String getNextCustomerId() throws SQLException {
        return foodManageDAOImpl.getNextId();
    }

    @Override
    public ArrayList<FoodDto> getAllCustomers() throws SQLException {
        return foodManageDAOImpl.getAllCustomers();
    }

    @Override
    public double getCurrentWeight(String FBId) throws SQLException {
        return foodManageDAOImpl.getCurrentWeight(FBId);
    }

    @Override
    public boolean updateAmount(double CurrentWeight, double foodWeight) throws SQLException {
        return foodManageDAOImpl.updateAmount(CurrentWeight, foodWeight);
    }

    @Override
    public boolean decreaseAmount(double CurrentWeight, double foodWeight) throws SQLException {
        return foodManageDAOImpl.decreaseAmount(CurrentWeight, foodWeight);
    }

    @Override
    public void updateAmountWhenUpdate(String weight, String foodID) throws SQLException {
        foodManageDAOImpl.updateAmountWhenUpdate(weight, foodID);
    }

    @Override
    public double getFoodWeight(String foodID) throws SQLException {
        return foodManageDAOImpl.getFoodWeight(foodID);
    }

    @Override
    public boolean decreaseAmountWhenUpdate(double CurrentWeight, double newWight) throws SQLException {
        return foodManageDAOImpl.decreaseAmountWhenUpdate(CurrentWeight, newWight);
    }

    @Override
    public String getCurrentBatchID() throws SQLException {
        return foodManageDAOImpl.getCurrentBatchID();
    }
}
