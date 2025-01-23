package com.project.zeidot.bo.custom;

import com.project.zeidot.bo.custom.impl.FoodBatchBOImpl;
import com.project.zeidot.bo.custom.impl.FoodBatchDetailsBOImpl;
import com.project.zeidot.bo.custom.impl.FoodBatchTimeCheckBOImpl;
import com.project.zeidot.bo.custom.impl.FoodManageBOImpl;

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
        FOOD, FOODBATCH , FOODBATCH_DETAILS , FOODBATCH_TIME_CHECK;
    }

    public SuperBO getBOType(BOType type) {
        return switch (type) {
            case FOOD -> new FoodManageBOImpl();
            case FOODBATCH -> new FoodBatchBOImpl();
            case FOODBATCH_DETAILS -> new FoodBatchDetailsBOImpl();
            case FOODBATCH_TIME_CHECK -> new FoodBatchTimeCheckBOImpl();
        };
    }
}
