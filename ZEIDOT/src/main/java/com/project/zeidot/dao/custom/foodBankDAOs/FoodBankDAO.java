package com.project.zeidot.dao.custom.foodBankDAOs;

import com.project.zeidot.dao.custom.CrudDAO;
import com.project.zeidot.dto.FoodBankDTO;
import com.project.zeidot.entity.FoodBank;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FoodBankDAO extends CrudDAO<FoodBank> {
//    boolean saveFoodBank(FoodBankDto dto) throws SQLException;
//    boolean deleteFoodBank(String FBKId) throws SQLException;
//    boolean editFoodBank(FoodBankDto dto) throws SQLException;
//    String getNextFoodBankId() throws SQLException;
    ArrayList<FoodBank> getFoodBankDetails() throws SQLException;
}
