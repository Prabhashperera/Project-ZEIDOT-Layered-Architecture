package com.project.zeidot.bo.custom;

import com.project.zeidot.dto.DeliverDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DeliveryBO extends SuperBO{
    ArrayList<DeliverDto> getDeliveryDetails() throws SQLException;
    boolean isDeliveredToYes(String donID) throws SQLException;
    boolean isDeliveredToNo(String donID) throws SQLException;
    boolean update(DeliverDto updatedDto) throws SQLException;
    boolean delete(String name) throws SQLException;
    boolean save(DeliverDto savedDto) throws SQLException;
    String getNextId() throws SQLException;
}
