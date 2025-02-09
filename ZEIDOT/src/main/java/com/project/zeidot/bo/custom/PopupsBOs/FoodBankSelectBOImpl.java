package com.project.zeidot.bo.custom.PopupsBOs;

import com.project.zeidot.dao.custom.DAOFactory;
import com.project.zeidot.dao.custom.PopupsDAOS.PopupDAO;
import com.project.zeidot.dto.FoodBankDTO;
import com.project.zeidot.entity.FoodBank;

import java.sql.SQLException;
import java.util.ArrayList;

public class FoodBankSelectBOImpl implements PopupBO<FoodBankDTO>{

    private final PopupDAO<FoodBank> foodBankDtoPopupDAO =
            (PopupDAO<FoodBank>) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.FOODBANK_SELECT);

    @Override
    public ArrayList<FoodBankDTO> getDetails() throws SQLException {
        ArrayList<FoodBankDTO> foodBankDTOArrayList = new ArrayList<>();
        ArrayList<FoodBank> foodBanks = foodBankDtoPopupDAO.getDetails();

        for (FoodBank foodBank : foodBanks) {
            FoodBankDTO foodBankDTO = new FoodBankDTO();
            foodBankDTO.setFBKId(foodBank.getFBKId());
            foodBankDTO.setFBKName(foodBank.getFBKName());
            foodBankDTO.setFBKAddress(foodBank.getFBKAddress());
            foodBankDTO.setFBKEmail(foodBank.getFBKEmail());
            foodBankDTOArrayList.add(foodBankDTO);
        }
        return foodBankDTOArrayList;
    }
}
