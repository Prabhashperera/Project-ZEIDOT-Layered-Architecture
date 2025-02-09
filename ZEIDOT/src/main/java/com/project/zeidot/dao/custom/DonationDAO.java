package com.project.zeidot.dao.custom;

import com.project.zeidot.dto.DonationDto;
import com.project.zeidot.entity.Donation;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DonationDAO extends CrudDAO<Donation>{
//    String getNextDonationId() throws SQLException;
//    boolean saveDonation(DonationDto dto) throws SQLException;
//    boolean deleteDonation(String id) throws SQLException;
//    boolean updateDonation(DonationDto dto) throws SQLException;
    ArrayList<Donation> getAllDonations() throws SQLException;
}
