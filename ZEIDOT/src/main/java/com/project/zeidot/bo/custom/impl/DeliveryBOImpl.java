package com.project.zeidot.bo.custom.impl;

import com.project.zeidot.bo.custom.DeliveryBO;
import com.project.zeidot.dao.DAOFactory;
import com.project.zeidot.dao.custom.deliveryDAOs.DeliveryDAO;
import com.project.zeidot.dao.custom.impl.delivery.DeliveryDAOImpl;
import com.project.zeidot.dto.DeliverDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryBOImpl implements DeliveryBO {

    private final DeliveryDAO deliveryDAO = (DeliveryDAO) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.DELIVER);

    @Override
    public ArrayList<DeliverDto> getDeliveryDetails() throws SQLException {
        return deliveryDAO.getDeliveryDetails();
    }
    @Override
    public boolean isDeliveredToYes(String donID) throws SQLException {
        return deliveryDAO.isDeliveredToYes(donID);
    }

    @Override
    public boolean isDeliveredToNo(String donID) throws SQLException {
        return deliveryDAO.isDeliveredToNo(donID);
    }

    @Override
    public boolean update(DeliverDto updatedDto) throws SQLException {
        return deliveryDAO.update(updatedDto);
    }

    @Override
    public boolean delete(String name) throws SQLException {
        return deliveryDAO.delete(name);
    }

    @Override
    public boolean save(DeliverDto savedDto) throws SQLException {
        return deliveryDAO.save(savedDto);
    }

    @Override
    public String getNextId() throws SQLException {
        return deliveryDAO.getNextId();
    }
}
