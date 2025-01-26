package com.project.zeidot.dao.custom.PopupsDAOS;

import com.project.zeidot.dao.custom.SuperDAO;
import com.project.zeidot.dto.FoodBankDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PopupDAO <T> extends SuperDAO {
    ArrayList<T> getDetails() throws SQLException;
}
