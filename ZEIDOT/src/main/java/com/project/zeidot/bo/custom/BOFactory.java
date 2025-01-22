package com.project.zeidot.bo.custom;

import com.project.zeidot.bo.custom.impl.FoodBatchBOImpl;
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
        FOOD, FOODBATCH;
    }

    public SuperBO getBOType(BOType type) {
        return switch (type) {
            case FOOD -> new FoodManageBOImpl();
            case FOODBATCH -> new FoodBatchBOImpl();
        };
    }
}
