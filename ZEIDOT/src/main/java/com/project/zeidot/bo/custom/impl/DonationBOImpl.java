package com.project.zeidot.bo.custom.impl;

import com.project.zeidot.bo.custom.DonationBO;
import com.project.zeidot.dao.custom.DAOFactory;
import com.project.zeidot.dao.custom.DonationDAO;
import com.project.zeidot.dto.DonationDto;

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
        return donationDAO.save(dto);
    }

    @Override
    public boolean deleteDonation(String id) throws SQLException {
        return donationDAO.delete(id);
    }

    @Override
    public boolean updateDonation(DonationDto dto) throws SQLException {
        return donationDAO.update(dto);
    }

    @Override
    public ArrayList<DonationDto> getAllDonations() throws SQLException {
        return donationDAO.getAllDonations();
    }
}
