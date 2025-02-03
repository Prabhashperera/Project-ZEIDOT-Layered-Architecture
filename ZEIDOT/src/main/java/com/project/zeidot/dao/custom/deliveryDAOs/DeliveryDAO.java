package com.project.zeidot.dao.custom.deliveryDAOs;

import com.project.zeidot.dao.custom.CrudDAO;
import com.project.zeidot.dto.DeliverDTO;
import com.project.zeidot.entity.Deliver;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DeliveryDAO extends CrudDAO<Deliver> {
    ArrayList<Deliver> getDeliveryDetails() throws SQLException;
    boolean isDeliveredToYes(String donID) throws SQLException;
    boolean isDeliveredToNo(String donID) throws SQLException;
}
