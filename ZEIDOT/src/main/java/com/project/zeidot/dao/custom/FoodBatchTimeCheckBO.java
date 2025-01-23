package com.project.zeidot.dao.custom;

import com.project.zeidot.bo.custom.SuperBO;

import java.sql.SQLException;

public interface FoodBatchTimeCheckBO extends SuperBO {
    boolean checkTime(String batchDuration , String FBId) throws SQLException;
    boolean isPassedTime(String time , String FBId) throws SQLException;
    void deleteTimeOutBatch(String FBid) throws SQLException;
}
