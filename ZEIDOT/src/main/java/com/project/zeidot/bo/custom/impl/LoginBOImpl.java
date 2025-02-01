package com.project.zeidot.bo.custom.impl;

import com.project.zeidot.bo.custom.LoginBO;
import com.project.zeidot.dao.custom.DAOFactory;
import com.project.zeidot.dao.custom.impl.LoginDAOImpl;
import com.project.zeidot.dto.UserDTO;
import com.project.zeidot.entity.User;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    private final LoginDAOImpl loginDAO = (LoginDAOImpl) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.LOGIN);

    @Override
    public boolean login(UserDTO userDto) throws SQLException {
        return loginDAO.login(new User(userDto.getUsername(), userDto.getPassword()));
    }

    @Override
    public boolean register(UserDTO userDto) throws SQLException {
        return loginDAO.register(new User(userDto.getUsername(), userDto.getPassword()));
    }
}
