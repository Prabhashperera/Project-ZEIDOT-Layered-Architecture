package com.project.zeidot.dao.custom.impl;

import com.project.zeidot.dao.custom.QueryDAO;
import com.project.zeidot.dto.CustomFoodBatchDetails;
import com.project.zeidot.entity.FoodBatchDetails;
import com.project.zeidot.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FoodBatchDetailsDAOImpl implements QueryDAO<CustomFoodBatchDetails> {
    @Override
    public ArrayList<CustomFoodBatchDetails> getDetails(String FBId) throws SQLException {
        String query = "SELECT f.foodID, f.foodName, f.FoodWeight, f.duration\n" +
                "FROM food f\n" +
                "JOIN foodbatchdetails fb ON f.foodID = fb.foodID\n" +
                "JOIN foodbatch fbatch ON fb.FBId = fbatch.FBId\n" +
                "WHERE fbatch.FBId = ?";

        ResultSet rs = CrudUtil.execute(query, FBId);

        ArrayList<CustomFoodBatchDetails> detailList = new ArrayList<>();
        while (rs.next()) {
            CustomFoodBatchDetails dto = new CustomFoodBatchDetails();
            dto.setFoodID(rs.getString("FoodID"));
            dto.setFoodName(rs.getString("FoodName"));
            dto.setFoodWeight(rs.getString("FoodWeight"));
            dto.setDuration(rs.getString("duration"));
            detailList.add(dto);
        }
        return detailList;
    }
}
