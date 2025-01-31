package com.project.zeidot.dao.custom.impl;

import com.project.zeidot.dao.custom.FoodManageDAO;
import com.project.zeidot.db.DBConnection;
import com.project.zeidot.dto.FoodDto;
import com.project.zeidot.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FoodManageDAOImpl implements FoodManageDAO {
    @Override
    public boolean update(FoodDto food) throws SQLException {
        String query = "UPDATE food SET foodWeight = ?, FoodName = ? , duration = ? WHERE foodID = ?";
        int rows = CrudUtil.execute(query , food.getWeight() , food.getFoodName(), food.getDuration(), food.getFoodID());
        return rows > 0;
    }
    @Override
    public boolean delete(String name) throws SQLException {
        String query = "DELETE FROM food WHERE foodID = ?";
        return CrudUtil.execute(query , name);
    }
    @Override
    public boolean save(FoodDto dto) throws SQLException {
        String query = "insert into food values(?,?,?,?)";
        return CrudUtil.execute(query , dto.getFoodID() , dto.getWeight(), dto.getFoodName(), dto.getDuration());
    }

    @Override
    public String getNextId() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT foodID FROM food ORDER BY foodID DESC LIMIT 1");

        if (rs.next()) {
            String lastId = rs.getString(1); // Get the last customer ID
            if (lastId != null && lastId.length() > 1) {
                // Extract the numeric part, make sure there is at least one character after 'C'
                String substring = lastId.substring(1); // Extract the numeric part (after 'C')
                try {
                    int i = Integer.parseInt(substring); // Convert the numeric part to an integer
                    int newIdIndex = i + 1; // Increment the number by 1
                    return String.format("F%03d", newIdIndex); // Return the new customer ID in format Cnnn
                } catch (NumberFormatException e) {
                    // Handle cases where the numeric part is invalid
                    throw new SQLException("Invalid customer ID format in the database: " + lastId);
                }
            }
        }
        // Return default customer ID if no records are found or ID is improperly formatted
        return "F001";
    }

    @Override
    public ArrayList<FoodDto> getAllCustomers() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from food");

        ArrayList<FoodDto> foodDtos = new ArrayList<>();

        while (rst.next()) {
            FoodDto customerDTO = new FoodDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
            foodDtos.add(customerDTO);
        }
        return foodDtos;
    }

    //food Batch amount change Mehthods

    @Override
    public double getCurrentWeight(String FBId) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT foodAmount FROM foodBatch WHERE FBId = ?" , FBId);
        if (rst.next()) {
            System.out.println(rst.getString(1) + "Current weight returned");
            return Double.parseDouble(rst.getString(1));
        }
        return 0;
    }

    @Override
    public boolean updateAmount(double CurrentWeight , double foodWeight) throws SQLException {
        double w = CurrentWeight + foodWeight;
        String lastWeight = String.valueOf(w);
        System.out.println(lastWeight);
        return CrudUtil.execute("UPDATE foodBatch SET FoodAmount = ? WHERE FBId = ?" , lastWeight , getCurrentBatchID());
    } //When Food Saved Increase by count

    @Override
    public boolean decreaseAmount(double CurrentWeight , double foodWeight) throws SQLException {
        if (CurrentWeight > 0) {
            double w = CurrentWeight - foodWeight;
            String lastWeight = String.valueOf(w);
            System.out.println(lastWeight);
            return CrudUtil.execute("UPDATE foodBatch SET FoodAmount = ? WHERE FBId = ?" , lastWeight , getCurrentBatchID());
        }
        return false;
    }//When Deleted a food Decreasing by count

    @Override
    public void updateAmountWhenUpdate(String weight , String foodID) throws SQLException {
        double currentFoodWeight = getFoodWeight(foodID); // 40
        double newWeight = Double.parseDouble(weight); //20

        if (currentFoodWeight > newWeight) {
            boolean isUpdated = decreaseAmountWhenUpdate(currentFoodWeight, newWeight);
            if (isUpdated) {
                System.out.println("Adu unaaa");
            }
        }

    }

    @Override
    public double getFoodWeight(String foodID) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT foodWeight FROM food WHERE foodID = ?");
        if (rst.next()) {
            System.out.println(rst.getString(1) + "Current food Weight returned");
            return Double.parseDouble(rst.getString(1));
        }
        return 0;
    }
    @Override
    public boolean decreaseAmountWhenUpdate(double CurrentWeight , double newWight) throws SQLException {
        if (CurrentWeight > 0) {
            double w = CurrentWeight - newWight;
            String lastWeight = String.valueOf(w);
            System.out.println(lastWeight);
            return CrudUtil.execute("UPDATE foodWeight SET foodWeight = ? WHERE foodID = ?" , lastWeight , getCurrentBatchID());
        }
        return false;
    }//When Deleted a food Decreasing by count

    @Override
    public String getCurrentBatchID() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT FBId FROM foodBatch ORDER BY FBId DESC LIMIT 1");

        if (rs.next()) {
            return rs.getString(1); // Return the latest batch ID
        }
        return null; // Return null if no batches exist
    }
    //food Batch amount change Mehthods End


}
