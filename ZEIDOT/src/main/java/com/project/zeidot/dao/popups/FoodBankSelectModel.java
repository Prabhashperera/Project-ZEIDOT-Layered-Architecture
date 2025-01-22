package com.project.zeidot.dao.popups;

import com.project.zeidot.db.DBConnection;
import com.project.zeidot.dto.FoodBankDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FoodBankSelectModel {
    public ArrayList<FoodBankDto> getFoodBankDetails() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM foodBank";
        PreparedStatement ps = con.prepareStatement(query);

        ArrayList<FoodBankDto> detailList = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            FoodBankDto dto = new FoodBankDto();
            dto.setFBKId(rs.getString(1));
            dto.setFBKName(rs.getString(2));
            dto.setFBKAddress(rs.getString(3));
            dto.setFBKEmail(rs.getString(4));
            detailList.add(dto);
        }
        return detailList;
    }
}
