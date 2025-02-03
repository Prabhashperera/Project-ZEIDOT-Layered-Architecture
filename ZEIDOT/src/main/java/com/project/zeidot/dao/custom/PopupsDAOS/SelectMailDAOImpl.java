package com.project.zeidot.dao.custom.PopupsDAOS;

import com.project.zeidot.db.DBConnection;
import com.project.zeidot.dto.FoodBankDTO;
import com.project.zeidot.entity.FoodBank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelectMailDAOImpl implements PopupDAO<FoodBank> {

    public ArrayList<FoodBank> getDetails() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String query = "SELECT FBName , emailAddress FROM foodBank";
        PreparedStatement ps = con.prepareStatement(query);

        ArrayList<FoodBank> detailList = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            FoodBank dto = new FoodBank();
            dto.setFBKName(rs.getString("FBName"));
            dto.setFBKEmail(rs.getString("emailAddress"));
            detailList.add(dto);
        }
        return detailList;
    }
}
