package com.project.zeidot.bo.custom.impl;

import com.project.zeidot.bo.custom.DonationBO;
import com.project.zeidot.bo.custom.FoodBatchSelectBO;
import com.project.zeidot.dao.custom.DAOFactory;
import com.project.zeidot.dao.custom.DonationDAO;
import com.project.zeidot.dao.custom.FoodBatchSelectDAO;
import com.project.zeidot.db.DBConnection;
import com.project.zeidot.dto.DonationDto;
import com.project.zeidot.entity.Donation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class DonationBOImpl implements DonationBO {

    private final DonationDAO donationDAO = (DonationDAO) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.DONATION);
    private final FoodBatchSelectDAO fbSelectDAO = (FoodBatchSelectDAO) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.FOODBACTH_SELECT);
    @Override
    public String getNextDonationId() throws SQLException {
        return donationDAO.getNextId();
    }

    @Override
    public boolean saveDonation(DonationDto dto) throws SQLException {
        Connection connection = null;
        connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            boolean isSaved = donationDAO.save(
                    new Donation(dto.getDonationID(), dto.getDonationName(), dto.getFBId(), dto.getFoodBankID()));
            if (isSaved) {
                boolean isUpdated = fbSelectDAO.changeAvailability(dto.getFBId()); //LA
                if (isUpdated) {
                    connection.commit();
                    return true;
                }
            }
        }catch (Exception e) {
            connection.rollback();
        }
        finally {
            connection.setAutoCommit(true);
        }
        return false;
    }

    @Override
    public boolean deleteDonation(String id , String batchID) throws SQLException {
        Connection connection = null;
        connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            boolean isDeleted = donationDAO.delete(id);
            if (isDeleted) {
                boolean isChangeToAvailable = fbSelectDAO.changeToAvailable(batchID); //LA
                if (isChangeToAvailable) {
                    connection.commit();
                    return true;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            connection.setAutoCommit(true);
        }
        return false;
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
