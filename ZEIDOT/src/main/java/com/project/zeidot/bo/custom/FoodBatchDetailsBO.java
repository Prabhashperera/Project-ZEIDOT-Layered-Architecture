package com.project.zeidot.bo.custom;

import com.project.zeidot.dto.FoodBatchDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FoodBatchDetailsBO extends SuperBO {
    ArrayList<FoodBatchDetailsDTO> getFoodBatchDetails(String FBId) throws SQLException;
}
