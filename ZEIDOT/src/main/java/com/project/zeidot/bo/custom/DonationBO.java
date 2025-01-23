package com.project.zeidot.bo.custom;

import com.project.zeidot.dto.DonationDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DonationBO extends SuperBO {
    String getNextDonationId() throws SQLException;
    boolean saveDonation(DonationDto dto) throws SQLException;
    boolean deleteDonation(String id) throws SQLException;
    boolean updateDonation(DonationDto dto) throws SQLException;
    ArrayList<DonationDto> getAllDonations() throws SQLException;
}
