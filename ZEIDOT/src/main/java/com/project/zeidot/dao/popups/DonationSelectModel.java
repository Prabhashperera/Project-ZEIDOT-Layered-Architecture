package com.project.zeidot.dao.popups;

import com.project.zeidot.db.DBConnection;
import com.project.zeidot.dto.DonationDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DonationSelectModel {
    public ArrayList<DonationDto> getDeliveryDetails() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM donation WHERE isDelivered = 'NO'";
        PreparedStatement ps = con.prepareStatement(query);

        ArrayList<DonationDto> detailList = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            DonationDto dto = new DonationDto();
            dto.setDonationID(rs.getString(1));
            dto.setDonationName(rs.getString(2));
            dto.setFBId(rs.getString(3));
            dto.setFoodBankID(rs.getString(4));
            detailList.add(dto);
        }
        return detailList;
    }
}
