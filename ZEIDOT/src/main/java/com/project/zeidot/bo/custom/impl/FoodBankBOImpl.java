package com.project.zeidot.bo.custom.impl;

import com.project.zeidot.bo.custom.FoodBankBO;
import com.project.zeidot.dao.custom.DAOFactory;
import com.project.zeidot.dao.custom.foodBankDAOs.FoodBankDAO;
import com.project.zeidot.dto.FoodBankDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class FoodBankBOImpl implements FoodBankBO {
    private final FoodBankDAO foodBankDAO = (FoodBankDAO) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.FOODBANK);

    @Override
    public boolean saveFoodBank(FoodBankDto dto) throws SQLException {
        return foodBankDAO.save(dto);
    }

    @Override
    public boolean deleteFoodBank(String FBKId) throws SQLException {
        return foodBankDAO.delete(FBKId);
    }

    @Override
    public boolean editFoodBank(FoodBankDto dto) throws SQLException {
        return foodBankDAO.update(dto);
    }

    @Override
    public String getNextFoodBankId() throws SQLException {
        return foodBankDAO.getNextId();
    }

    @Override
    public ArrayList<FoodBankDto> getFoodBankDetails() throws SQLException {
        return foodBankDAO.getFoodBankDetails();
    }
}
