package com.project.zeidot.bo.custom.impl;

import com.project.zeidot.bo.custom.FoodBatchDetailsBO;
import com.project.zeidot.dao.custom.DAOFactory;
import com.project.zeidot.dao.custom.FoodBatchDetailsDAO;
import com.project.zeidot.dao.custom.QueryDAO;
import com.project.zeidot.dto.CustomFoodBatchDetails;
import com.project.zeidot.dto.FoodBatchDetailsDTO;
import com.project.zeidot.entity.FoodBatchDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class FoodBatchDetailsBOImpl implements FoodBatchDetailsBO {
    private final QueryDAO foodBatchDetailsDAO = (QueryDAO) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.FOODBATCH_DETAILS);

    @Override
    public ArrayList<FoodBatchDetailsDTO> getFoodBatchDetails(String FBId) throws SQLException {
        ArrayList<FoodBatchDetailsDTO> foodBatchDetailsDTOArrayList = new ArrayList<>();
        ArrayList<CustomFoodBatchDetails> foodBatchDetails = foodBatchDetailsDAO.getDetails(FBId);

        for (CustomFoodBatchDetails foodBatchDetail : foodBatchDetails) {
            //Food Batch Conversion
            FoodBatchDetailsDTO foodBatchDetailsDTO = new FoodBatchDetailsDTO();
            foodBatchDetailsDTO.setFoodName(foodBatchDetail.getFoodName());
            foodBatchDetailsDTO.setFoodId(foodBatchDetail.getFoodID());
            foodBatchDetailsDTO.setDuration(foodBatchDetail.getDuration());
            foodBatchDetailsDTO.setFoodWeight(foodBatchDetail.getFoodWeight());
            foodBatchDetailsDTOArrayList.add(foodBatchDetailsDTO);
        }

        return foodBatchDetailsDTOArrayList;
    }
}
