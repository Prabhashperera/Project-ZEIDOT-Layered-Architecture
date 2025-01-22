package com.project.zeidot.dao;

import com.project.zeidot.db.DBConnection;
import com.project.zeidot.dto.DonationDto;
import com.project.zeidot.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DonationModel {
    public String getNextDonationId() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT DonationID FROM donation ORDER BY DonationID DESC LIMIT 1");

        if (rs.next()) {
            String lastId = rs.getString(1); // Get the last customer ID
            if (lastId != null && lastId.length() > 1) {
                // Extract the numeric part, make sure there is at least one character after 'C'
                String substring = lastId.substring(1); // Extract the numeric part (after 'C')
                try {
                    int i = Integer.parseInt(substring); // Convert the numeric part to an integer
                    int newIdIndex = i + 1; // Increment the number by 1
                    return String.format("D%03d", newIdIndex); // Return the new customer ID in format Cnnn
                } catch (NumberFormatException e) {
                    // Handle cases where the numeric part is invalid
                    throw new SQLException("Invalid customer ID format in the database: " + lastId);
                }
            }
        }
        // Return default customer ID if no records are found or ID is improperly formatted
        return "D001";
    }

    public boolean saveDonation(DonationDto dto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "INSERT INTO donation values(? , ? , ?, ? , ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, dto.getDonationID());
        ps.setString(2 , dto.getDonationName());
        ps.setString(3 , dto.getFBId());
        ps.setString(4 , dto.getFoodBankID());
        ps.setString(5 , "NO");
        int rows = ps.executeUpdate();
        return rows > 0;
    }
    public boolean deleteDonation(String id) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement("DELETE FROM Donation WHERE donationID = ?");
        ps.setString(1, id);
        return ps.executeUpdate() > 0;
    }
    public boolean updateDonation(DonationDto dto) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String query = "Update donation SET donationName = ? , FBId = ? WHERE donationID = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1 , dto.getDonationName());
        ps.setString(2 , dto.getFBId());
        ps.setString(3 , dto.getDonationID());
        int rows = ps.executeUpdate();
        return rows > 0;
    }

    public ArrayList<DonationDto> getAllDonations() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from donation");

        ArrayList<DonationDto> donationDtos = new ArrayList<>();

        while (rst.next()) {
            DonationDto donationDto = new DonationDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
            donationDtos.add(donationDto);
        }
        return donationDtos;
    }
}
