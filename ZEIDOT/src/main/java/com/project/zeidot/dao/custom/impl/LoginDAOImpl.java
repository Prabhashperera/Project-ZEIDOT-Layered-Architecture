package com.project.zeidot.dao.custom.impl;

import com.project.zeidot.dao.custom.LoginDAO;
import com.project.zeidot.db.DBConnection;
import com.project.zeidot.dto.UserDTO;
import com.project.zeidot.entity.User;
import com.project.zeidot.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO {
    @Override
    public boolean login(User entity) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select * from user where username = ? and password = ?", entity.getUsername(), entity.getPassword() );
        //Get all the users into a resultSet
        while (resultSet.next()) {
            String username = resultSet.getString("userName");
            String password = resultSet.getString("password");
            if (username.equals(entity.getUsername()) && password.equals(entity.getPassword())) {
                return true; //if Username & Password is Equal to any user in the database, returns True
            }
        }
        return false; //If not Returns False
    }

    @Override
    public boolean register(User entity) throws SQLException {
       return CrudUtil.execute("insert into user values(?,?)" , entity.getUsername(), entity.getPassword());
    }
}
