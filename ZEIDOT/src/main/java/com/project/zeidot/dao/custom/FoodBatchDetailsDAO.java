package com.project.zeidot.dao.custom;

import com.project.zeidot.dto.FoodBatchDetailsDTO;
import com.project.zeidot.entity.FoodBatchDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FoodBatchDetailsDAO extends SuperDAO{
    ArrayList<FoodBatchDetails> getFoodBatchDetails(String FBId) throws SQLException;
}
