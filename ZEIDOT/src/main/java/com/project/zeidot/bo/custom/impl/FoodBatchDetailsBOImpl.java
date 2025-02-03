package com.project.zeidot.bo.custom.impl;

import com.project.zeidot.bo.custom.FoodBatchDetailsBO;
import com.project.zeidot.dao.custom.DAOFactory;
import com.project.zeidot.dao.custom.FoodBatchDetailsDAO;
import com.project.zeidot.dto.FoodBatchDetailsDTO;
import com.project.zeidot.entity.FoodBatchDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class FoodBatchDetailsBOImpl implements FoodBatchDetailsBO {
    private final FoodBatchDetailsDAO foodBatchDetailsDAO = (FoodBatchDetailsDAO) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.FOODBATCH_DETAILS);

    @Override
    public ArrayList<FoodBatchDetailsDTO> getFoodBatchDetails(String FBId) throws SQLException {
        ArrayList<FoodBatchDetailsDTO> foodBatchDetailsDTOArrayList = new ArrayList<>();
        ArrayList<FoodBatchDetails> foodBatchDetails = foodBatchDetailsDAO.getFoodBatchDetails(FBId);

        for (FoodBatchDetails foodBatchDetail : foodBatchDetails) {
            //Food Batch Conversion
            FoodBatchDetailsDTO foodBatchDetailsDTO = new FoodBatchDetailsDTO();
            foodBatchDetailsDTO.setFoodName(foodBatchDetail.getFoodName());
            foodBatchDetailsDTO.setFoodId(foodBatchDetail.getFoodId());
            foodBatchDetailsDTO.setDuration(foodBatchDetail.getDuration());
            foodBatchDetailsDTO.setFoodWeight(foodBatchDetail.getFoodWeight());
            foodBatchDetailsDTOArrayList.add(foodBatchDetailsDTO);
        }

        return foodBatchDetailsDTOArrayList;
    }
}
