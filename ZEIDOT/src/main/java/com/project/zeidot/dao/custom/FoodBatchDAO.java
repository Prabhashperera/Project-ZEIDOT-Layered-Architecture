package com.project.zeidot.dao.custom;

import com.project.zeidot.dto.BatchDetailsDTO;
import com.project.zeidot.dto.FoodBatchDTO;
import com.project.zeidot.entity.FoodBatch;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

public interface FoodBatchDAO extends CrudDAO<FoodBatch> {
    public String getCurrentBatchID() throws SQLException;
//    public String getNextBatchId() throws SQLException;
    public String setBatchValues(FoodBatch foodBatchDto) throws SQLException;
    public boolean setBatchDetailsValues(BatchDetailsDTO dto) throws SQLException;
    public ArrayList<FoodBatch> getAllBatchDetails() throws SQLException;
    public boolean updateFoodBatchTime(LocalTime newTime , String batchID) throws SQLException;
    public LocalTime checkTime(LocalTime time, String batchID) throws SQLException;
    public LocalTime checkTimeWhenDeleting(String batchID) throws SQLException;
//    public boolean deleteBatch(String batchID) throws SQLException;
    public void deleteFoodsOfDeletedBatch(String batchID) throws SQLException;
    public void deleteFoodOnebyOne(String foodID) throws SQLException;
}
