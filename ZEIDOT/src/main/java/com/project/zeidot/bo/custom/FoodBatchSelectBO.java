package com.project.zeidot.bo.custom;

import com.project.zeidot.dto.FoodBatchDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FoodBatchSelectBO extends SuperBO {
    ArrayList<FoodBatchDTO> getFoodBatchDetails() throws SQLException;
    boolean changeAvailability(String batchID) throws SQLException;
    boolean changeToAvailable(String batchID) throws SQLException;
}
