package com.project.zeidot.bo.custom.PopupsBOs;

import com.project.zeidot.dao.custom.DAOFactory;
import com.project.zeidot.dao.custom.PopupsDAOS.PopupDAO;
import com.project.zeidot.dto.FoodBankDTO;
import com.project.zeidot.entity.FoodBank;

import java.sql.SQLException;
import java.util.ArrayList;

public class SelectMailBOImpl implements PopupBO<FoodBankDTO> {

    private final PopupDAO<FoodBank> selectMailDAOPopup =
            (PopupDAO<FoodBank>) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.MAIL_SELECT);

    @Override
    public ArrayList<FoodBankDTO> getDetails() throws SQLException {
        ArrayList<FoodBankDTO> foodBankDTOArrayList = new ArrayList<>();
        ArrayList<FoodBank> foodBankArrayList = selectMailDAOPopup.getDetails();
        for (FoodBank foodBank : foodBankArrayList) {
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