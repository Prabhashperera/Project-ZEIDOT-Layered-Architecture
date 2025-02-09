package com.project.zeidot.bo.custom.PopupsBOs;

import com.project.zeidot.dao.custom.DAOFactory;
import com.project.zeidot.dao.custom.PopupsDAOS.PopupDAO;
import com.project.zeidot.dto.DonationDto;
import com.project.zeidot.entity.Donation;

import java.sql.SQLException;
import java.util.ArrayList;

public class DonationSelectBOImpl implements PopupBO<DonationDto>{

    private final PopupDAO<Donation> donationDtoPopupDAO =
            (PopupDAO<Donation>) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.DONATION_SELECT);

    @Override
    public ArrayList<DonationDto> getDetails() throws SQLException {
        ArrayList<Donation> donations = donationDtoPopupDAO.getDetails();
        ArrayList<DonationDto> donationDtos = new ArrayList<>();

        for (Donation donation : donations) {
            DonationDto dto = new DonationDto();
            dto.setFBId(donation.getFBId());
            dto.setDonationName(donation.getDonationName());
            dto.setFoodBankID(donation.getFoodBankID());
            dto.setDonationID(donation.getDonationID());
            donationDtos.add(dto);
        }
        return donationDtos;
    }
}
