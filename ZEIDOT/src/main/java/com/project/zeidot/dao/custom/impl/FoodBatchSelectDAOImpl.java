package com.project.zeidot.dao.custom.impl;

import com.project.zeidot.dao.custom.FoodBatchSelectDAO;
import com.project.zeidot.db.DBConnection;
import com.project.zeidot.dto.FoodBatchDTO;
import com.project.zeidot.entity.FoodBatch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FoodBatchSelectDAOImpl implements FoodBatchSelectDAO{
    @Override
    public ArrayList<FoodBatch> getFoodBatchDetails() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM foodBatch WHERE isAvailable = 'Available'";
        PreparedStatement ps = con.prepareStatement(query);

        ArrayList<FoodBatch> detailList = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            FoodBatch dto = new FoodBatch();
            dto.setFoodBatchId(rs.getString(1));
            dto.setFoodAmount(rs.getString(2));
            dto.setDate(rs.getString(3));
            dto.setIsAvailable(rs.getString(4));
            dto.setDuration(rs.getString(5));
            detailList.add(dto);
        }
        return detailList;
    }

    @Override
    public boolean changeAvailability(String batchID) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String query = "UPDATE foodBatch SET isAvailable = 'UnAvailable' WHERE FBId = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, batchID);
        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean changeToAvailable(String batchID) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String query = "UPDATE foodBatch SET isAvailable = 'Available' WHERE FBId = ?";
        if (!batchID.equals(null)) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, batchID);
            return ps.executeUpdate() > 0;
        }
        return false;
    }
}
