package com.project.zeidot.dao.custom.impl;

import com.project.zeidot.dao.custom.DonationDAO;
import com.project.zeidot.db.DBConnection;
import com.project.zeidot.dto.DonationDto;
import com.project.zeidot.entity.Donation;
import com.project.zeidot.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DonationDAOImpl implements DonationDAO{
    public String getNextId() throws SQLException {
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

    public boolean save(Donation entity) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "INSERT INTO donation values(? , ? , ?, ? , ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, entity.getDonationID());
        ps.setString(2 , entity.getDonationName());
        ps.setString(3 , entity.getFBId());
        ps.setString(4 , entity.getFoodBankID());
        ps.setString(5 , "NO");
        int rows = ps.executeUpdate();
        return rows > 0;
    }
    public boolean delete(String id) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement("DELETE FROM Donation WHERE donationID = ?");
        ps.setString(1, id);
        return ps.executeUpdate() > 0;
    }
    public boolean update(Donation entity) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String query = "Update donation SET donationName = ? , FBId = ? , FoodBankID = ? WHERE donationID = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1 , entity.getDonationName());
        ps.setString(2 , entity.getFBId());
        ps.setString(3 , entity.getFoodBankID());
        ps.setString(4 , entity.getDonationID());

        int rows = ps.executeUpdate();
        return rows > 0;
    }

    public ArrayList<Donation> getAllDonations() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from donation");

        ArrayList<Donation> donations = new ArrayList<>();

        while (rst.next()) {
            Donation donation = new Donation(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
            donations.add(donation);
        }
        return donations;
    }
}
