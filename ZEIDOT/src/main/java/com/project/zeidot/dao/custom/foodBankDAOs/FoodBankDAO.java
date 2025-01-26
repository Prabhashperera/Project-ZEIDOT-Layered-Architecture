package com.project.zeidot.dao.custom.foodBankDAOs;

import com.project.zeidot.dao.custom.CrudDAO;
import com.project.zeidot.dto.FoodBankDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FoodBankDAO extends CrudDAO<FoodBankDto> {
//    boolean saveFoodBank(FoodBankDto dto) throws SQLException;
//    boolean deleteFoodBank(String FBKId) throws SQLException;
//    boolean editFoodBank(FoodBankDto dto) throws SQLException;
//    String getNextFoodBankId() throws SQLException;
    ArrayList<FoodBankDto> getFoodBankDetails() throws SQLException;
}
