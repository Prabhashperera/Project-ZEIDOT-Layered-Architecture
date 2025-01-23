package com.project.zeidot.dao;

import com.project.zeidot.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory instance;
    private DAOFactory() {}

    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    public enum DAOType {
        FOOD , FOODBATCH , FOODBATCH_DETAILS , FOODBATCH_TIME_CHECK , DONATION;
    }
    public Object getDAOType(DAOType type) {
        switch (type) {
            case FOOD:
                return new FoodManageDAOImpl();
                case FOODBATCH:
                    return new FoodBatchDAOImpl();
                    case FOODBATCH_DETAILS:
                        return new FoodBatchDetailsDAOImpl();
                        case FOODBATCH_TIME_CHECK:
                            return new FoodBatchTimeCheckThreadDAOImpl();
                            case DONATION:
                                return new DonationDAOImpl();
                    default:
                        return null;
        }
    }

}
