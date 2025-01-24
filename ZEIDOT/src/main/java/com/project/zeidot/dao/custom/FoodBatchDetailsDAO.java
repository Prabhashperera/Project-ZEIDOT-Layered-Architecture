package com.project.zeidot.dao.custom;

import com.project.zeidot.dto.FoodBatchDetailsDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FoodBatchDetailsDAO extends SuperDAO{
    ArrayList<FoodBatchDetailsDto> getFoodBatchDetails(String FBId) throws SQLException;
}
