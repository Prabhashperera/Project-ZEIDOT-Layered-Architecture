package com.project.zeidot.bo.custom.PopupsBOs;

import com.project.zeidot.bo.custom.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PopupBO <T> extends SuperBO {
    ArrayList<T> getDetails() throws SQLException;
}
