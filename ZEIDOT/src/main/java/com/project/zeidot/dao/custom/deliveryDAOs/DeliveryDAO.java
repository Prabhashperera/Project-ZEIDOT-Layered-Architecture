package com.project.zeidot.dao.custom.deliveryDAOs;

import com.project.zeidot.dao.custom.CrudDAO;
import com.project.zeidot.dto.DeliverDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DeliveryDAO extends CrudDAO<DeliverDto> {
    ArrayList<DeliverDto> getDeliveryDetails() throws SQLException;
    boolean isDeliveredToYes(String donID) throws SQLException;
    boolean isDeliveredToNo(String donID) throws SQLException;
}
