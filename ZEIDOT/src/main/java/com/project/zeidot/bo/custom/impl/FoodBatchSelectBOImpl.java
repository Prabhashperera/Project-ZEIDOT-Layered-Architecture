package com.project.zeidot.bo.custom.impl;

import com.project.zeidot.bo.custom.FoodBatchSelectBO;
import com.project.zeidot.dao.custom.DAOFactory;
import com.project.zeidot.dao.custom.FoodBatchSelectDAO;
import com.project.zeidot.dto.FoodBatchDTO;
import com.project.zeidot.entity.FoodBatch;

import java.sql.SQLException;
import java.util.ArrayList;

public class FoodBatchSelectBOImpl implements FoodBatchSelectBO {

    private final FoodBatchSelectDAO foodBatchSelectDAO = (FoodBatchSelectDAO) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.FOODBACTH_SELECT);


    @Override
    public ArrayList<FoodBatchDTO> getFoodBatchDetails() throws SQLException {
        ArrayList<FoodBatchDTO> foodBatchDTOArrayList = new ArrayList<>();
        ArrayList<FoodBatch> foodBatchArrayList = foodBatchSelectDAO.getFoodBatchDetails();

        for (FoodBatch fbDto : foodBatchArrayList) {
            FoodBatchDTO foodBatchDTO = new FoodBatchDTO();
            foodBatchDTO.setFoodBatchId(fbDto.getFoodBatchId());
            foodBatchDTO.setFoodAmount(fbDto.getFoodAmount());
            foodBatchDTO.setDate(fbDto.getDate());
            foodBatchDTO.setIsAvailable(fbDto.getIsAvailable());
            foodBatchDTO.setDuration(fbDto.getDuration());
            foodBatchDTOArrayList.add(foodBatchDTO);
        }

        return foodBatchDTOArrayList;
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
