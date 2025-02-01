package com.project.zeidot.dao.custom.PopupsDAOS;

import com.project.zeidot.db.DBConnection;
import com.project.zeidot.dto.FoodBankDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FoodBankSelectDAOImpl implements PopupDAO<FoodBankDTO> {
    public ArrayList<FoodBankDTO> getDetails() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM foodBank";
        PreparedStatement ps = con.prepareStatement(query);

        ArrayList<FoodBankDTO> detailList = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            FoodBankDTO dto = new FoodBankDTO();
            dto.setFBKId(rs.getString(1));
            dto.setFBKName(rs.getString(2));
            dto.setFBKAddress(rs.getString(3));
            dto.setFBKEmail(rs.getString(4));
            detailList.add(dto);
        }
        return detailList;
    }
}
