package com.project.zeidot.bo.custom.PopupsBOs;

import com.project.zeidot.dao.DAOFactory;
import com.project.zeidot.dao.custom.PopupsDAOS.PopupDAO;
import com.project.zeidot.dto.DonationDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class DonationSelectBOImpl implements PopupBO<DonationDto>{

    private final PopupDAO<DonationDto> donationDtoPopupDAO =
            (PopupDAO<DonationDto>) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.DONATION_SELECT);

    @Override
    public ArrayList<DonationDto> getDetails() throws SQLException {
        return donationDtoPopupDAO.getDetails();
    }
}
