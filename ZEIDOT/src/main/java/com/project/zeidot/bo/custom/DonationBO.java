package com.project.zeidot.bo.custom;

import com.project.zeidot.dto.DonationDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DonationBO extends SuperBO {
    String getNextDonationId() throws SQLException;
    boolean saveDonation(DonationDTO dto) throws SQLException;
    boolean deleteDonation(String id) throws SQLException;
    boolean updateDonation(DonationDTO dto) throws SQLException;
    ArrayList<DonationDTO> getAllDonations() throws SQLException;
}
