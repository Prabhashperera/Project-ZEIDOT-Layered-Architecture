package com.project.zeidot.bo.custom;

import com.project.zeidot.dto.UserDTO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    boolean login(UserDTO userDto) throws SQLException;
    boolean register(UserDTO userDto) throws SQLException;
}
