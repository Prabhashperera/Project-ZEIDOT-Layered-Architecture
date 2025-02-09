package com.project.zeidot.bo.custom.impl;

import com.project.zeidot.bo.custom.DonationBO;
import com.project.zeidot.dao.custom.DAOFactory;
import com.project.zeidot.dao.custom.DonationDAO;
import com.project.zeidot.dto.DonationDto;
import com.project.zeidot.entity.Donation;

import java.sql.SQLException;
import java.util.ArrayList;

public class DonationBOImpl implements DonationBO {

    private final DonationDAO donationDAO = (DonationDAO) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.DONATION);

    @Override
    public String getNextDonationId() throws SQLException {
        return donationDAO.getNextId();
    }

    @Override
    public boolean saveDonation(DonationDto dto) throws SQLException {
        return donationDAO.save(
                new Donation(dto.getDonationID(), dto.getDonationName(), dto.getFBId(), dto.getFoodBankID()));
    }

    @Override
    public boolean deleteDonation(String id) throws SQLException {
        return donationDAO.delete(id);
    }

    @Override
    public boolean updateDonation(DonationDto dto) throws SQLException {
        return donationDAO.update(
                new Donation(dto.getDonationID(), dto.getDonationName(), dto.getFBId(), dto.getFoodBankID()));
    }

    @Override
    public ArrayList<DonationDto> getAllDonations() throws SQLException {
        ArrayList<Donation> donations = donationDAO.getAllDonations();
        ArrayList<DonationDto> donationDtos = new ArrayList<>();

        for (Donation donation : donations) {
            DonationDto dto = new DonationDto();
            dto.setDonationID(donation.getDonationID());
            dto.setDonationName(donation.getDonationName());
            dto.setFBId(donation.getFBId());
            dto.setFoodBankID(donation.getFoodBankID());
            donationDtos.add(dto);
        }
        return donationDtos;
    }
}
