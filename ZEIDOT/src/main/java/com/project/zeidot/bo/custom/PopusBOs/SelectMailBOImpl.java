package com.project.zeidot.bo.custom.PopusBOs;

import com.project.zeidot.dao.DAOFactory;
import com.project.zeidot.dao.custom.PopupsDAOS.PopupDAO;
import com.project.zeidot.dto.FoodBankDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class SelectMailBOImpl implements PopupBO<FoodBankDto> {

    private final PopupDAO<FoodBankDto> selectMailDAOPopup =
            (PopupDAO<FoodBankDto>) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.MAIL_SELECT);

    @Override
    public ArrayList<FoodBankDto> getDetails() throws SQLException {
        return selectMailDAOPopup.getDetails();
    }
}