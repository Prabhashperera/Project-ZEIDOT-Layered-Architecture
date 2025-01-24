package com.project.zeidot.bo.custom.impl;

import com.project.zeidot.bo.custom.FoodBatchSelectBO;
import com.project.zeidot.dao.DAOFactory;
import com.project.zeidot.dao.custom.FoodBatchSelectDAO;
import com.project.zeidot.dao.custom.impl.FoodBatchSelectDAOImpl;
import com.project.zeidot.dto.FoodBatchDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class FoodBatchSelectBOImpl implements FoodBatchSelectBO {

    private final FoodBatchSelectDAO foodBatchSelectDAO = (FoodBatchSelectDAO) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.FOODBACTH_SELECT);


    @Override
    public ArrayList<FoodBatchDto> getFoodBatchDetails() throws SQLException {
        return foodBatchSelectDAO.getFoodBatchDetails();
    }

    @Override
    public boolean changeAvailability(String batchID) throws SQLException {
        return foodBatchSelectDAO.changeAvailability(batchID);
    }

    @Override
    public boolean changeToAvailable(String batchID) throws SQLException {
        return foodBatchSelectDAO.changeToAvailable(batchID);
    }
}
