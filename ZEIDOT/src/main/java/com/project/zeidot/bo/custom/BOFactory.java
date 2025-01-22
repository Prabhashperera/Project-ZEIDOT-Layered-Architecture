package com.project.zeidot.bo.custom;

import com.project.zeidot.bo.custom.impl.FoodBatchBOImpl;
import com.project.zeidot.bo.custom.impl.FoodManageBOImpl;
import com.project.zeidot.dao.DAOFactory;

public class BOFactory {
    private static BOFactory instance;
    private BOFactory() {}

    public static BOFactory getInstance() {
        if (instance == null) {
            instance = new BOFactory();
        }
        return instance;
    }

    public enum BOType {
        FOOD , FOODBATCH;
    }
    public Object getBO(BOType type) {
        switch (type) {
            case FOOD:
                return new FoodManageBOImpl();
                case FOODBATCH:
                    return new FoodBatchBOImpl();
                    default:
                        return null;
        }
    }

}
