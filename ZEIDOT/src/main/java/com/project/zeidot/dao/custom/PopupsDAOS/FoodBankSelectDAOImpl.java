package com.project.zeidot.dao.custom.PopupsDAOS;

import com.project.zeidot.db.DBConnection;
import com.project.zeidot.dto.FoodBankDTO;
import com.project.zeidot.entity.FoodBank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FoodBankSelectDAOImpl implements PopupDAO<FoodBank> {
    public ArrayList<FoodBank> getDetails() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM foodBank";
        PreparedStatement ps = con.prepareStatement(query);

        ArrayList<FoodBank> detailList = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            // Directly create a FoodBankDTO from the result set
            FoodBank dto = new FoodBank(
                    rs.getString(1), // FBKId
                    rs.getString(2), // FBKName
                    rs.getString(3), // FBKAddress
                    rs.getString(4)  // FBKEmail
            );
            detailList.add(dto);
        }
        return detailList;
    }
}
