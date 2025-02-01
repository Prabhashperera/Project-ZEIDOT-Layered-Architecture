package com.project.zeidot.dao.custom;

import com.project.zeidot.dto.UserDTO;
import com.project.zeidot.entity.User;

import java.sql.SQLException;

public interface LoginDAO extends SuperDAO {
    boolean login(User userDto) throws SQLException;
    boolean register(User userDto) throws SQLException;
}
