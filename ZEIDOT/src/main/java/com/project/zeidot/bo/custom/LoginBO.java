package com.project.zeidot.bo.custom;

import com.project.zeidot.dto.UserDto;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    boolean login(UserDto userDto) throws SQLException;
    boolean register(UserDto userDto) throws SQLException;
}
