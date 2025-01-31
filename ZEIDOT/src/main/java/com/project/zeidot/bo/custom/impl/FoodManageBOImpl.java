package com.project.zeidot.bo.custom.impl;

import com.project.zeidot.bo.custom.BOFactory;
import com.project.zeidot.bo.custom.FoodBatchBO;
import com.project.zeidot.bo.custom.FoodManageBO;
import com.project.zeidot.dao.custom.DAOFactory;
import com.project.zeidot.dao.custom.FoodManageDAO;
import com.project.zeidot.db.DBConnection;
import com.project.zeidot.dto.BatchDetailsDto;
import com.project.zeidot.dto.FoodDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

public class FoodManageBOImpl implements FoodManageBO {
    FoodManageDAO foodManageDAOImpl = (FoodManageDAO) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.FOOD);
    FoodBatchBO foodBatchBO = (FoodBatchBO) BOFactory.getInstance().getBOType(BOFactory.BOType.FOODBATCH);

    // Food Manage Model Instance //LA
    @Override
    public boolean updateFood(FoodDto food) throws SQLException {
        return foodManageDAOImpl.update(food);
    }

    @Override
    public boolean deleteFood(String name , String batch  ,String foodWeight) throws SQLException {
        Connection conn = null;
        try {
            conn = DBConnection.getInstance().getConnection();
            conn.setAutoCommit(false);
            boolean isDeleted = foodManageDAOImpl.delete(name);
            if (!isDeleted) {
                conn.rollback();
                return false;
            }
            //When deleting a Food / Time must change if its the Main time that Holds FoodBatch Expire time
            LocalTime newTime = foodBatchBO.checkTimeWhenDeleting(batch);
            foodBatchBO.updateFoodBatchTime(newTime, batch);
            //After Deleting a Food emediately calling Decrease amount of the food batch
            boolean isDecrease = foodManageDAOImpl.decreaseAmount(foodManageDAOImpl.getCurrentWeight(batch), Double.parseDouble(foodWeight));
            if (!isDecrease) {
                conn.rollback();
                return false;
            }
            conn.commit();
            return true;
        } catch (RuntimeException e) {
            if (conn != null) {
                conn.rollback();
                return false;
            }
            throw new RuntimeException(e);
        }finally {
            if (conn != null) {
                conn.setAutoCommit(true);

            }
        }
    }

    @Override
    public boolean saveFood(FoodDto dto , BatchDetailsDto batchDto) throws SQLException {
        Connection conn = null;
        try {
            conn = DBConnection.getInstance().getConnection();
            conn.setAutoCommit(false); // Disable auto-commit to manage transactions

            // Save batch details
            boolean isSaved = foodManageDAOImpl.save(dto);
            if (!isSaved) {
                conn.rollback(); //if Fails RollBack
                return false;
            }

            //Now this is Going to Model that Adding Values to Associate Entity in Database (FoodBatchDetails Table)
            boolean isAdded = foodBatchBO.setBatchDetailsValues(batchDto);
            if (!isAdded) {
                conn.rollback();
                return false;
            }
            // Update batch time
            LocalTime newTime = foodBatchBO.checkTime(LocalTime.parse(dto.getDuration()), batchDto.getBatchId());
            boolean isTimeUpdated = foodBatchBO.updateFoodBatchTime(newTime, batchDto.getBatchId());
            if (!isTimeUpdated) {
                conn.rollback();
                return false;
            }
            conn.commit();//If all are Passed , Commit
            //Transaction END
            return true;
        } catch (Exception e) {
            e.getMessage();
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
            }
        }
        return false;
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
