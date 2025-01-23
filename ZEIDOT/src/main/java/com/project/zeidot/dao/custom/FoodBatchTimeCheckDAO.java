package com.project.zeidot.dao.custom;

import java.sql.SQLException;

public interface FoodBatchTimeCheckDAO {
    boolean checkTime() throws SQLException;
}
