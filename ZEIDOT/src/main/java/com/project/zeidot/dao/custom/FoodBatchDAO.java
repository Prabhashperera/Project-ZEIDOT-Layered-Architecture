package com.project.zeidot.dao.custom;

import com.project.zeidot.dto.BatchDetailsDto;
import com.project.zeidot.dto.FoodBatchDto;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

public interface FoodBatchDAO {
    public String getCurrentBatchID() throws SQLException;
    public String getNextBatchId() throws SQLException;
    public String setBatchValues(FoodBatchDto foodBatchDto) throws SQLException;
    public boolean setBatchDetailsValues(BatchDetailsDto dto) throws SQLException;
    public ArrayList<FoodBatchDto> getAllBatchDetails() throws SQLException;
    public boolean updateFoodBatchTime(LocalTime newTime , String batchID) throws SQLException;
    public LocalTime checkTime(LocalTime time, String batchID) throws SQLException;
    public LocalTime checkTimeWhenDeleting(String batchID) throws SQLException;
    public boolean deleteBatch(String batchID) throws SQLException;
    public void deleteFoodsOfDeletedBatch(String batchID) throws SQLException;
    public void deleteFoodOnebyOne(String foodID) throws SQLException;
}
