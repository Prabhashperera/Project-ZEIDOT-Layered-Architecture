package com.project.zeidot.bo.custom;

import com.project.zeidot.dto.FoodBatchDetailsDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FoodBatchDetailsBO extends SuperBO {
    ArrayList<FoodBatchDetailsDto> getFoodBatchDetails(String FBId) throws SQLException;
}
