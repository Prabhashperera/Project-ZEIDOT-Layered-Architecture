package com.project.zeidot.bo.custom.impl;

import com.project.zeidot.bo.custom.FoodBatchDetailsBO;
import com.project.zeidot.dao.custom.DAOFactory;
import com.project.zeidot.dao.custom.FoodBatchDetailsDAO;
import com.project.zeidot.dto.FoodBatchDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class FoodBatchDetailsBOImpl implements FoodBatchDetailsBO {
    private final FoodBatchDetailsDAO foodBatchDetailsDAO = (FoodBatchDetailsDAO) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.FOODBATCH_DETAILS);

    @Override
    public ArrayList<FoodBatchDetailsDTO> getFoodBatchDetails(String FBId) throws SQLException {
        return foodBatchDetailsDAO.getFoodBatchDetails(FBId);
    }
}
