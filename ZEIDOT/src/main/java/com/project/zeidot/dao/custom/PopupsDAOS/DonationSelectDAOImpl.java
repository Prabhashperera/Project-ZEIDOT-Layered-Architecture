package com.project.zeidot.dao.custom.PopupsDAOS;

import com.project.zeidot.db.DBConnection;
import com.project.zeidot.dto.DonationDto;
import com.project.zeidot.entity.Donation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DonationSelectDAOImpl implements PopupDAO<Donation> {
    public ArrayList<Donation> getDetails() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM donation WHERE isDelivered = 'NO'";
        PreparedStatement ps = con.prepareStatement(query);

        ArrayList<Donation> detailList = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Donation dto = new Donation();
            dto.setDonationID(rs.getString(1));
            dto.setDonationName(rs.getString(2));
            dto.setFBId(rs.getString(3));
            dto.setFoodBankID(rs.getString(4));
            detailList.add(dto);
        }
        return detailList;
    }
}
