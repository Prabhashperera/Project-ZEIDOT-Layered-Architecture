package com.project.zeidot.bo.custom.PopupsBOs;

import com.project.zeidot.dao.custom.DAOFactory;
import com.project.zeidot.dao.custom.PopupsDAOS.PopupDAO;
import com.project.zeidot.dto.FoodBankDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class SelectMailBOImpl implements PopupBO<FoodBankDTO> {

    private final PopupDAO<FoodBankDTO> selectMailDAOPopup =
            (PopupDAO<FoodBankDTO>) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.MAIL_SELECT);

    @Override
    public ArrayList<FoodBankDTO> getDetails() throws SQLException {
        return selectMailDAOPopup.getDetails();
    }
}