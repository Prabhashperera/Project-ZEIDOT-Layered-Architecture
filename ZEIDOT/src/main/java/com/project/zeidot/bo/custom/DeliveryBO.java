package com.project.zeidot.bo.custom;

import com.project.zeidot.dto.DeliverDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DeliveryBO extends SuperBO{
    ArrayList<DeliverDTO> getDeliveryDetails() throws SQLException;
    boolean isDeliveredToYes(String donID) throws SQLException;
    boolean isDeliveredToNo(String donID) throws SQLException;
    boolean update(DeliverDTO updatedDto) throws SQLException;
    boolean delete(String name) throws SQLException;
    boolean save(DeliverDTO savedDto) throws SQLException;
    String getNextId() throws SQLException;
}
