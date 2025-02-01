package com.project.zeidot.dao.custom;

import com.project.zeidot.dto.UserDTO;

import java.sql.SQLException;

public interface LoginDAO extends SuperDAO {
    boolean login(UserDTO userDto) throws SQLException;
    boolean register(UserDTO userDto) throws SQLException;
}
