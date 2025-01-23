package com.project.zeidot.dao.custom.impl;

import com.project.zeidot.bo.custom.BOFactory;
import com.project.zeidot.bo.custom.impl.FoodBatchTimeCheckBOImpl;
import com.project.zeidot.dao.custom.FoodBatchTimeCheckBO;
import com.project.zeidot.dao.custom.FoodBatchTimeCheckDAO;
import com.project.zeidot.db.DBConnection;
import com.project.zeidot.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;


public class FoodBatchTimeCheckThreadDAOImpl implements FoodBatchTimeCheckDAO {
    FoodBatchTimeCheckBO foodBatchTimeCheckBO = (FoodBatchTimeCheckBO) BOFactory.getInstance().getBOType(BOFactory.BOType.FOODBATCH_TIME_CHECK);
    boolean isPassedTime = false;

    @Override
    public boolean checkTime() throws SQLException {
        String query = "SELECT batchDuration , FBId FROM foodBatch where isAvailable = 'Available'";
        ResultSet rs = CrudUtil.execute(query);
        while (rs.next()) {
            String batchDuration = rs.getString("batchDuration");
            String FBId = rs.getString("FBId");
//            isPassedTime = isPassedTime(batchDuration, FBId);
            isPassedTime = foodBatchTimeCheckBO.checkTime(batchDuration, FBId);
        }
        return isPassedTime;
    }

//    public boolean isPassedTime(String time , String FBId) throws SQLException {
//        LocalTime batchTime = LocalTime.parse(time);
//        LocalTime currentTime = LocalTime.now();
//
//        System.out.println("Batch time: " + batchTime);
//        System.out.println("Current time: " + currentTime);
//
//        if (batchTime.isBefore(currentTime)) {
//            deleteTimeOutBatch(FBId);
//            return true;
//        } else {
//            System.out.println("Time not outtttt");
//        }
//        return false;
//    }
//
//    public void deleteTimeOutBatch(String FBid) throws SQLException {
//        foodBatchDAOImpl.delete(FBid);
//        foodBatchDAOImpl.deleteFoodsOfDeletedBatch(FBid);
//        System.out.println("Deleted All the 00 Batchs");
//    }
}
