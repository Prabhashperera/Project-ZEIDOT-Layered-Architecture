package com.project.zeidot.dao.custom;

import com.project.zeidot.dto.CustomFoodBatchDetails;
import com.project.zeidot.entity.FoodBatchDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO<T> extends SuperDAO {
    ArrayList<T> getDetails(String FBId) throws SQLException;
}
