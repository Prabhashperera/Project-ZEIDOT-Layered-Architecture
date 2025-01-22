package com.project.zeidot.dao;

import com.project.zeidot.dao.custom.impl.FoodBatchDAOImpl;
import com.project.zeidot.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

public class FoodBatchTimeCheckThreadModel {
    FoodBatchDAOImpl foodBatchDAOImpl = new FoodBatchDAOImpl();
    boolean isPassedTime = false;
    public boolean checkTime() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String query = "SELECT batchDuration , FBId FROM foodBatch where isAvailable = 'Available'";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String batchDuration = rs.getString("batchDuration");
            String FBId = rs.getString("FBId");
            isPassedTime = isPassedTime(batchDuration, FBId);
        }
        return isPassedTime;
    }

    public boolean isPassedTime(String time , String FBId) throws SQLException {
        LocalTime batchTime = LocalTime.parse(time);
        LocalTime currentTime = LocalTime.now();

        System.out.println("Batch time: " + batchTime);
        System.out.println("Current time: " + currentTime);

        if (batchTime.isBefore(currentTime)) {
            deleteTimeOutBatch(FBId);
            return true;
        } else {
            System.out.println("Time not outtttt");
        }
        return false;
    }

    public void deleteTimeOutBatch(String FBid) throws SQLException {
        foodBatchDAOImpl.delete(FBid);
        foodBatchDAOImpl.deleteFoodsOfDeletedBatch(FBid);
        System.out.println("Deleted All the 00 Batchs");
    }
}
