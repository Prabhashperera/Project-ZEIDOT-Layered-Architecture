package com.project.zeidot.dao.custom.impl.foodBank;

import com.project.zeidot.dao.custom.foodBankDAOs.FoodBankDAO;
import com.project.zeidot.db.DBConnection;
import com.project.zeidot.dto.FoodBankDTO;
import com.project.zeidot.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FoodBankDAOImpl implements FoodBankDAO {
    public boolean save(FoodBankDTO dto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "INSERT INTO foodBank  values(? , ? , ? , ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, dto.getFBKId());
        ps.setString(2 , dto.getFBKName());
        ps.setString(3 , dto.getFBKAddress());
        ps.setString(4 , dto.getFBKEmail());

        int rows = ps.executeUpdate();
        return rows > 0;
    }
    public boolean delete(String FBKId) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "DELETE FROM foodBank WHERE foodBankID = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, FBKId);
        int rows = ps.executeUpdate();
        return rows > 0;
    }
    public boolean update(FoodBankDTO dto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE foodBank SET address = ?, FBName = ?, emailAddress = ? WHERE foodBankID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, dto.getFBKAddress());
        ps.setString(2 , dto.getFBKName());
        ps.setString(3 , dto.getFBKEmail());
        ps.setString(4 , dto.getFBKId());
        int rows = ps.executeUpdate();
        return rows > 0;
    }

    public String getNextId() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT FoodBankID FROM foodBank ORDER BY FoodBankID DESC LIMIT 1");

        if (rs.next()) {
            String lastId = rs.getString(1); // Get the last customer ID
            if (lastId != null && lastId.length() > 1) {
                // Extract the numeric part, make sure there is at least one character after 'C'
                String substring = lastId.substring(1); // Extract the numeric part (after 'C')
                try {
                    int i = Integer.parseInt(substring); // Convert the numeric part to an integer
                    int newIdIndex = i + 1; // Increment the number by 1
                    return String.format("R%03d", newIdIndex); // Return the new customer ID in format Cnnn
                } catch (NumberFormatException e) {
                    // Handle cases where the numeric part is invalid
                    throw new SQLException("Invalid customer ID format in the database: " + lastId);
                }
            }
        }
        // Return default customer ID if no records are found or ID is improperly formatted
        return "R001";
    }

    public ArrayList<FoodBankDTO> getFoodBankDetails() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM foodBank";
        PreparedStatement ps = con.prepareStatement(query);

        ArrayList<FoodBankDTO> detailList = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            FoodBankDTO dto = new FoodBankDTO();
            dto.setFBKId(rs.getString(1));
            dto.setFBKAddress(rs.getString(2));
            dto.setFBKName(rs.getString(3));
            dto.setFBKEmail(rs.getString(4));
            detailList.add(dto);
        }
        return detailList;
    }
}
