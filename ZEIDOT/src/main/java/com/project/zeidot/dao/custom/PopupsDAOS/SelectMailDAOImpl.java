package com.project.zeidot.dao.custom.PopupsDAOS;

import com.project.zeidot.db.DBConnection;
import com.project.zeidot.dto.FoodBankDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelectMailDAOImpl implements PopupDAO<FoodBankDTO> {

    public ArrayList<FoodBankDTO> getDetails() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String query = "SELECT FBName , emailAddress FROM foodBank";
        PreparedStatement ps = con.prepareStatement(query);

        ArrayList<FoodBankDTO> detailList = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            FoodBankDTO dto = new FoodBankDTO();
            dto.setFBKName(rs.getString("FBName"));
            dto.setFBKEmail(rs.getString("emailAddress"));
            detailList.add(dto);
        }
        return detailList;
    }
}
