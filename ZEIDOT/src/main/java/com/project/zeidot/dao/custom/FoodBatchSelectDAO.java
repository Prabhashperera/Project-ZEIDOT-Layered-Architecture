package com.project.zeidot.dao.custom;

import com.project.zeidot.dto.FoodBatchDTO;
import com.project.zeidot.entity.FoodBatch;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FoodBatchSelectDAO extends SuperDAO {
    ArrayList<FoodBatch> getFoodBatchDetails() throws SQLException;
    boolean changeAvailability(String batchID) throws SQLException;
    boolean changeToAvailable(String batchID) throws SQLException;
}
