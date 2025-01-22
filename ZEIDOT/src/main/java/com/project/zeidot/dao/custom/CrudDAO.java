package com.project.zeidot.dao.custom;

import com.project.zeidot.dto.FoodDto;

import java.sql.SQLException;

public interface CrudDAO <T> {
    boolean update(T updatedDto) throws SQLException;
    boolean delete(String name) throws SQLException;
    boolean save(T savedDto) throws SQLException;
    String getNextId() throws SQLException;
}
