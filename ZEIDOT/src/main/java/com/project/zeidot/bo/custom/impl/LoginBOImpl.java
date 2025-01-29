package com.project.zeidot.bo.custom.impl;

import com.project.zeidot.bo.custom.LoginBO;
import com.project.zeidot.dao.DAOFactory;
import com.project.zeidot.dao.custom.impl.LoginDAOImpl;
import com.project.zeidot.dto.UserDto;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    private final LoginDAOImpl loginDAO = (LoginDAOImpl) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.LOGIN);

    @Override
    public boolean login(UserDto userDto) throws SQLException {
        return loginDAO.login(userDto);
    }

    @Override
    public boolean register(UserDto userDto) throws SQLException {
        return loginDAO.register(userDto);
    }
}
