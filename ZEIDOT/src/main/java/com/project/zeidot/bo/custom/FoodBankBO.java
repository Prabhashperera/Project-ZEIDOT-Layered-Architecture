package com.project.zeidot.bo.custom;

import com.project.zeidot.dto.FoodBankDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FoodBankBO extends SuperBO {
    boolean saveFoodBank(FoodBankDto dto) throws SQLException;
    boolean deleteFoodBank(String FBKId) throws SQLException;
    boolean editFoodBank(FoodBankDto dto) throws SQLException;
    String getNextFoodBankId() throws SQLException;
    ArrayList<FoodBankDto> getFoodBankDetails() throws SQLException;
}
