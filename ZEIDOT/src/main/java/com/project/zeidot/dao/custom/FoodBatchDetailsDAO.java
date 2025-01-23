package com.project.zeidot.dao.custom;

import com.project.zeidot.dto.FoodBatchDetailsDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FoodBatchDetailsDAO{
    ArrayList<FoodBatchDetailsDto> getFoodBatchDetails(String FBId) throws SQLException;
}
