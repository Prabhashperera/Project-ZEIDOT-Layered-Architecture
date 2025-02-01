package com.project.zeidot.dao.custom;

import com.project.zeidot.dto.DonationDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DonationDAO extends CrudDAO<DonationDTO>{
//    String getNextDonationId() throws SQLException;
//    boolean saveDonation(DonationDto dto) throws SQLException;
//    boolean deleteDonation(String id) throws SQLException;
//    boolean updateDonation(DonationDto dto) throws SQLException;
    ArrayList<DonationDTO> getAllDonations() throws SQLException;
}
