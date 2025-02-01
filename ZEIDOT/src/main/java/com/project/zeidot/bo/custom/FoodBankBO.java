package com.project.zeidot.bo.custom;

import com.project.zeidot.dto.FoodBankDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FoodBankBO extends SuperBO {
    boolean saveFoodBank(FoodBankDTO dto) throws SQLException;
    boolean deleteFoodBank(String FBKId) throws SQLException;
    boolean editFoodBank(FoodBankDTO dto) throws SQLException;
    String getNextFoodBankId() throws SQLException;
    ArrayList<FoodBankDTO> getFoodBankDetails() throws SQLException;
}
