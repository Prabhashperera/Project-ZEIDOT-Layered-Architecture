package com.project.zeidot.dao.custom;

import com.project.zeidot.dto.DonationDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DonationDAO extends CrudDAO<DonationDto>{
//    String getNextDonationId() throws SQLException;
//    boolean saveDonation(DonationDto dto) throws SQLException;
//    boolean deleteDonation(String id) throws SQLException;
//    boolean updateDonation(DonationDto dto) throws SQLException;
    ArrayList<DonationDto> getAllDonations() throws SQLException;
}
