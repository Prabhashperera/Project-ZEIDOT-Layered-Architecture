package com.project.zeidot.dao.custom.impl;

import com.project.zeidot.dao.custom.DAOFactory;
import com.project.zeidot.dao.custom.FoodBatchDetailsDAO;
import com.project.zeidot.dao.custom.QueryDAO;
import com.project.zeidot.dto.CustomFoodBatchDetails;
import com.project.zeidot.dto.FoodBatchDetailsDTO;
import com.project.zeidot.entity.FoodBatchDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    private final QueryDAO foodBatchDetailsDAO = (QueryDAO) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.FOODBATCH_DETAILS);


    @Override
    public ArrayList<CustomFoodBatchDetails> getDetails(String FBId) throws SQLException {
        return foodBatchDetailsDAO.getDetails(FBId);
    }
}
