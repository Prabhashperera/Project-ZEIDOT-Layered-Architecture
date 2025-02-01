package com.project.zeidot.bo.custom.PopupsBOs;

import com.project.zeidot.dao.custom.DAOFactory;
import com.project.zeidot.dao.custom.PopupsDAOS.PopupDAO;
import com.project.zeidot.dto.DonationDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class DonationSelectBOImpl implements PopupBO<DonationDTO>{

    private final PopupDAO<DonationDTO> donationDtoPopupDAO =
            (PopupDAO<DonationDTO>) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.DONATION_SELECT);

    @Override
    public ArrayList<DonationDTO> getDetails() throws SQLException {
        return donationDtoPopupDAO.getDetails();
    }
}
