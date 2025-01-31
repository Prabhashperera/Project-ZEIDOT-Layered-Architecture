package com.project.zeidot.bo.custom.impl;

import com.project.zeidot.dao.custom.DAOFactory;
import com.project.zeidot.dao.custom.FoodBatchDAO;
import com.project.zeidot.bo.custom.FoodBatchTimeCheckBO;

import java.sql.SQLException;
import java.time.LocalTime;

public class FoodBatchTimeCheckBOImpl implements FoodBatchTimeCheckBO {

    private final FoodBatchDAO foodBatchDAOImpl = (FoodBatchDAO) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.FOODBATCH);

    @Override
    public boolean checkTime(String batchDuration , String FBId) throws SQLException {
        return isPassedTime(batchDuration, FBId);
    }


    //Main Business Logic
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
