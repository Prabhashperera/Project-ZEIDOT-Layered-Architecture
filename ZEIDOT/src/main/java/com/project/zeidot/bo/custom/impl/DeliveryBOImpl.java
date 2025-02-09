package com.project.zeidot.bo.custom.impl;

import com.project.zeidot.bo.custom.DeliveryBO;
import com.project.zeidot.dao.custom.DAOFactory;
import com.project.zeidot.dao.custom.deliveryDAOs.DeliveryDAO;
import com.project.zeidot.dto.DeliverDTO;
import com.project.zeidot.entity.Deliver;

import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryBOImpl implements DeliveryBO {

    private final DeliveryDAO deliveryDAO = (DeliveryDAO) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.DELIVER);

    @Override
    public ArrayList<DeliverDTO> getDeliveryDetails() throws SQLException {
        ArrayList<DeliverDTO> deliverDtos = new ArrayList<>();
        ArrayList<Deliver> delivers = deliveryDAO.getDeliveryDetails();
        for (Deliver deliver : delivers) {
            DeliverDTO deliverDto = new DeliverDTO();
            deliverDto.setDeliverDate(deliver.getDeliverDate());
            deliverDto.setDeliverTime(deliver.getDeliverTime());
            deliverDto.setDeliveryID(deliver.getDeliveryID());
            deliverDto.setDonationID(deliver.getDonationID());
            deliverDtos.add(deliverDto);
        }
        return deliverDtos;
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
    public boolean update(DeliverDTO updatedDto) throws SQLException {
        return deliveryDAO.update(new Deliver(updatedDto.getDeliveryID() ,
                updatedDto.getDeliverDate(), updatedDto.getDeliverTime(),
                updatedDto.getDonationID()));
    }

    @Override
    public boolean delete(String name) throws SQLException {
        return deliveryDAO.delete(name);
    }

    @Override
    public boolean save(DeliverDTO savedDto) throws SQLException {
        return deliveryDAO.save(new Deliver(savedDto.getDeliveryID() ,
                savedDto.getDeliverDate(), savedDto.getDeliverTime(),
                savedDto.getDonationID()));
    }

    @Override
    public String getNextId() throws SQLException {
        return deliveryDAO.getNextId();
    }
}
