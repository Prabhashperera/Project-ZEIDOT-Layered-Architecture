package com.project.zeidot.dao.custom.deliveryDAOs;

import com.project.zeidot.dao.custom.CrudDAO;
import com.project.zeidot.dto.DeliverDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DeliveryDAO extends CrudDAO<DeliverDTO> {
    ArrayList<DeliverDTO> getDeliveryDetails() throws SQLException;
    boolean isDeliveredToYes(String donID) throws SQLException;
    boolean isDeliveredToNo(String donID) throws SQLException;
}
