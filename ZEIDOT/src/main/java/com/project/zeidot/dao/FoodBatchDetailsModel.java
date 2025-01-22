package com.project.zeidot.dao;

import com.project.zeidot.db.DBConnection;
import com.project.zeidot.dto.FoodBatchDetailsDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FoodBatchDetailsModel {
    public ArrayList<FoodBatchDetailsDto> getFoodBatchDetails(String FBId) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String query = "SELECT f.foodID, f.foodName, f.FoodWeight, f.duration\n" +
                "FROM food f\n" +
                "JOIN foodbatchdetails fb ON f.foodID = fb.foodID\n" +
                "JOIN foodbatch fbatch ON fb.FBId = fbatch.FBId\n" +
                "WHERE fbatch.FBId = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1 , FBId);

        ArrayList<FoodBatchDetailsDto> detailList = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            FoodBatchDetailsDto dto = new FoodBatchDetailsDto();
            dto.setFoodId(rs.getString("FoodID"));
            dto.setFoodName(rs.getString("FoodName"));
            dto.setFoodWeight(rs.getString("FoodWeight"));
            dto.setDuration(rs.getString("duration"));
            detailList.add(dto);
        }
        return detailList;
    }
}
