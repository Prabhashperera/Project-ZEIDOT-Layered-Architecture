package com.project.zeidot.dao.custom;

import java.sql.SQLException;

public interface FoodBatchTimeCheckDAO extends SuperDAO {
    boolean checkTime() throws SQLException;
}
