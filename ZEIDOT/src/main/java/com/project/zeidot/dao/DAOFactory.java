package com.project.zeidot.dao;

import com.project.zeidot.dao.custom.impl.FoodBatchDAOImpl;
import com.project.zeidot.dao.custom.impl.FoodBatchDetailsDAOImpl;
import com.project.zeidot.dao.custom.impl.FoodBatchTimeCheckThreadDAOImpl;
import com.project.zeidot.dao.custom.impl.FoodManageDAOImpl;

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
        FOOD , FOODBATCH , FOODBATCH_DETAILS , FOODBATCH_TIME_CHECK;
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
                    default:
                        return null;
        }
    }

}
