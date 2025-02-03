package com.project.zeidot.bo.custom.impl;

import com.project.zeidot.bo.custom.FoodBankBO;
import com.project.zeidot.dao.custom.DAOFactory;
import com.project.zeidot.dao.custom.foodBankDAOs.FoodBankDAO;
import com.project.zeidot.dto.FoodBankDTO;
import com.project.zeidot.entity.FoodBank;

import java.sql.SQLException;
import java.util.ArrayList;

public class FoodBankBOImpl implements FoodBankBO {
    private final FoodBankDAO foodBankDAO = (FoodBankDAO) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.FOODBANK);

    @Override
    public boolean saveFoodBank(FoodBankDTO dto) throws SQLException {
        return foodBankDAO.save(new FoodBank(dto.getFBKId() , dto.getFBKName() , dto.getFBKAddress() , dto.getFBKEmail()));
    }

    @Override
    public boolean deleteFoodBank(String FBKId) throws SQLException {
        return foodBankDAO.delete(FBKId);
    }

    @Override
    public boolean editFoodBank(FoodBankDTO dto) throws SQLException {
        return foodBankDAO.update(new FoodBank(dto.getFBKId() , dto.getFBKName() , dto.getFBKAddress() , dto.getFBKEmail()));
    }

    @Override
    public String getNextFoodBankId() throws SQLException {
        return foodBankDAO.getNextId();
    }

    @Override
    public ArrayList<FoodBankDTO> getFoodBankDetails() throws SQLException {
        ArrayList<FoodBankDTO> foodBankDTOs = new ArrayList<>();
        ArrayList<FoodBank> foodBankArrayList = foodBankDAO.getFoodBankDetails();
        for (FoodBank foodBank : foodBankArrayList) {
            FoodBankDTO foodBankDTO = new FoodBankDTO();
            foodBankDTO.setFBKAddress(foodBank.getFBKAddress());
            foodBankDTO.setFBKName(foodBank.getFBKName());
            foodBankDTO.setFBKId(foodBank.getFBKId());
            foodBankDTOs.add(foodBankDTO);
        }
        return foodBankDTOs;
    }
}
