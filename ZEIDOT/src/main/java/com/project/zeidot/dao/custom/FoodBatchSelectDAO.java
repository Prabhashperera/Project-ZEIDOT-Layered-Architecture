package com.project.zeidot.dao.custom;

import com.project.zeidot.dto.FoodBatchDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FoodBatchSelectDAO extends SuperDAO {
    ArrayList<FoodBatchDTO> getFoodBatchDetails() throws SQLException;
    boolean changeAvailability(String batchID) throws SQLException;
    boolean changeToAvailable(String batchID) throws SQLException;
}
