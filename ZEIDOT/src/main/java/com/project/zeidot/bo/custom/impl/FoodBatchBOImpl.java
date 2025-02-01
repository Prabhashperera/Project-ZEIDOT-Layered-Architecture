package com.project.zeidot.bo.custom.impl;

import com.project.zeidot.bo.custom.FoodBatchBO;
import com.project.zeidot.dao.custom.DAOFactory;
import com.project.zeidot.dao.custom.FoodBatchDAO;
import com.project.zeidot.dto.BatchDetailsDTO;
import com.project.zeidot.dto.FoodBatchDTO;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

public class FoodBatchBOImpl implements FoodBatchBO {
    private final FoodBatchDAO foodBatchDAO = (FoodBatchDAO) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.FOODBATCH);
    @Override
    public String getCurrentBatchID() throws SQLException {
        return foodBatchDAO.getCurrentBatchID();
    }

    @Override
    public String getNextBatchId() throws SQLException {
        return foodBatchDAO.getNextId();
    }

    @Override
    public String setBatchValues(FoodBatchDTO foodBatchDto) throws SQLException {
        return foodBatchDAO.setBatchValues(foodBatchDto);
    }

    @Override
    public boolean setBatchDetailsValues(BatchDetailsDTO dto) throws SQLException {
        return foodBatchDAO.setBatchDetailsValues(dto);
    }

    @Override
    public ArrayList<FoodBatchDTO> getAllBatchDetails() throws SQLException {
        return foodBatchDAO.getAllBatchDetails();
    }

    @Override
    public boolean updateFoodBatchTime(LocalTime newTime, String batchID) throws SQLException {
        return foodBatchDAO.updateFoodBatchTime(newTime, batchID);
    }

    @Override
    public LocalTime checkTime(LocalTime time, String batchID) throws SQLException {
        return foodBatchDAO.checkTime(time, batchID);
    }

    @Override
    public LocalTime checkTimeWhenDeleting(String batchID) throws SQLException {
        return foodBatchDAO.checkTimeWhenDeleting(batchID);
    }

    @Override
    public boolean deleteBatch(String batchID) throws SQLException {
        return foodBatchDAO.delete(batchID);
    }

    @Override
    public void deleteFoodsOfDeletedBatch(String batchID) throws SQLException {
        foodBatchDAO.deleteFoodsOfDeletedBatch(batchID);
    }

    @Override
    public void deleteFoodOnebyOne(String foodID) throws SQLException {
        foodBatchDAO.deleteFoodOnebyOne(foodID);
    }
}
