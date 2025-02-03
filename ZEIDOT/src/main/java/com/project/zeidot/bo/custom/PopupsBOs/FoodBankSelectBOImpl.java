package com.project.zeidot.bo.custom.PopupsBOs;

import com.project.zeidot.dao.custom.DAOFactory;
import com.project.zeidot.dao.custom.PopupsDAOS.PopupDAO;
import com.project.zeidot.dto.FoodBankDTO;
import com.project.zeidot.entity.FoodBank;

import java.sql.SQLException;
import java.util.ArrayList;

public class FoodBankSelectBOImpl implements PopupBO<FoodBankDTO>{

    private final PopupDAO<FoodBankDTO> foodBankDtoPopupDAO =
            (PopupDAO<FoodBankDTO>) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.FOODBANK_SELECT);

    @Override
    public ArrayList<FoodBankDTO> getDetails() throws SQLException {
        return foodBankDtoPopupDAO.getDetails();
    }
}
