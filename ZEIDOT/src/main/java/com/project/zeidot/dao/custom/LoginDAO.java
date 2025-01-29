package com.project.zeidot.dao.custom;

import com.project.zeidot.dto.UserDto;

import java.sql.SQLException;

public interface LoginDAO extends SuperDAO {
    boolean login(UserDto userDto) throws SQLException;
    boolean register(UserDto userDto) throws SQLException;
}
