package com.project.zeidot.dao.custom;

import com.project.zeidot.dto.FoodBatchDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FoodBatchDetailsDAO extends SuperDAO{
    ArrayList<FoodBatchDetailsDTO> getFoodBatchDetails(String FBId) throws SQLException;
}
