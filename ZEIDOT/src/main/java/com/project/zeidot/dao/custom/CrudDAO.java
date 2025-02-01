package com.project.zeidot.dao.custom;

import java.sql.SQLException;

public interface CrudDAO <T> extends SuperDAO {
    boolean update(T updatedDto) throws SQLException;
    boolean delete(String name) throws SQLException;
    boolean save(T savedDto) throws SQLException;
    String getNextId() throws SQLException;
}
