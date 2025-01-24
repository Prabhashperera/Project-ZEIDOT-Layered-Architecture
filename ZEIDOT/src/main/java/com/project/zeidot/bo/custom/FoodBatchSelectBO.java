package com.project.zeidot.bo.custom;

import com.project.zeidot.dto.FoodBatchDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FoodBatchSelectBO extends SuperBO {
    ArrayList<FoodBatchDto> getFoodBatchDetails() throws SQLException;
    boolean changeAvailability(String batchID) throws SQLException;
    boolean changeToAvailable(String batchID) throws SQLException;
}
