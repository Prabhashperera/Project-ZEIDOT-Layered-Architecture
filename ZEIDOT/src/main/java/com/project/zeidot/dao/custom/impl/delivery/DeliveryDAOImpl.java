package com.project.zeidot.dao.custom.impl.delivery;

import com.project.zeidot.dao.custom.deliveryDAOs.DeliveryDAO;
import com.project.zeidot.db.DBConnection;
import com.project.zeidot.dto.DeliverDto;
import com.project.zeidot.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryDAOImpl implements DeliveryDAO {
    private String nextDonationID;
    // Generate the next batch ID based on the last batch ID in the database
    @Override
    public String getNextId() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT deliveryID FROM delivery ORDER BY deliveryID DESC LIMIT 1");

        if (rs.next()) {
            String lastId = rs.getString(1); // Get the last batch ID
            if (lastId != null && lastId.length() > 1) {
                String substring = lastId.substring(1);  // Extract the numeric part (after 'B')
                try {
                    int i = Integer.parseInt(substring); // Convert the numeric part to an integer
                    int newIdIndex = i + 1; // Increment the number by 1
                    nextDonationID = String.format("E%03d", newIdIndex);
                    return nextDonationID; // Return the new batch ID in format Bnnn
                } catch (NumberFormatException e) {
                    throw new SQLException("Invalid batch ID format in the database: " + lastId);
                }
            }
        }
        nextDonationID = "E001";
        return nextDonationID;
    }

    @Override
    public boolean save(DeliverDto dto) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO delivery VALUES(?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, dto.getDeliveryID());
        ps.setString(2, dto.getDeliverDate());
        ps.setString(3,  dto.getDeliverTime());
        ps.setString(4 , dto.getDonationID());
        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean delete(String deliveryID) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM delivery WHERE deliveryID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, deliveryID);
        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean update(DeliverDto dto) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "UPDATE delivery SET donationID = ? , deliverTime = ? WHERE deliveryID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, dto.getDonationID());
        ps.setString(2 , dto.getDeliverTime());
        ps.setString(3, dto.getDeliveryID());
        return ps.executeUpdate() > 0;
    }

    @Override
    public ArrayList<DeliverDto> getDeliveryDetails() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM delivery";
        PreparedStatement ps = con.prepareStatement(query);

        ArrayList<DeliverDto> detailList = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            DeliverDto dto = new DeliverDto();
            dto.setDeliveryID(rs.getString(1));
            dto.setDeliverDate(rs.getString(2));
            dto.setDeliverTime(rs.getString(3));
            dto.setDonationID(rs.getString(4));
            detailList.add(dto);
        }
        return detailList;
    }

    @Override
    public boolean isDeliveredToYes(String donID) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String query = "UPDATE donation SET isDelivered = 'YES' WHERE donationID = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, donID);
        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean isDeliveredToNo(String donID) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String query = "UPDATE donation SET isDelivered = 'NO' WHERE donationID = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, donID);
        return ps.executeUpdate() > 0;
    }
}
