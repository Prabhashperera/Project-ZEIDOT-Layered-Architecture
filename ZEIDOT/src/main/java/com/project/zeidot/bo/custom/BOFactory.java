package com.project.zeidot.bo.custom;

import com.project.zeidot.bo.custom.PopusBOs.DonationSelectBOImpl;
import com.project.zeidot.bo.custom.PopusBOs.FoodBankSelectBOImpl;
import com.project.zeidot.bo.custom.PopusBOs.SelectMailBOImpl;
import com.project.zeidot.bo.custom.impl.*;

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
        FOOD, FOODBATCH , FOODBATCH_DETAILS , FOODBATCH_TIME_CHECK , DONATION , FOODBATCH_SELECT , FOODBANK,
        SELECT_FOODBANK , SELECT_DONATION , SELECT_MAIL, LOGIN;
    }

    public SuperBO getBOType(BOType type) {
        return switch (type) {
            case FOOD -> new FoodManageBOImpl();
            case FOODBATCH -> new FoodBatchBOImpl();
            case FOODBATCH_DETAILS -> new FoodBatchDetailsBOImpl();
            case FOODBATCH_TIME_CHECK -> new FoodBatchTimeCheckBOImpl();
            case DONATION -> new DonationBOImpl();
            case FOODBATCH_SELECT -> new FoodBatchSelectBOImpl();
            case FOODBANK -> new FoodBankBOImpl();
            case SELECT_FOODBANK -> new FoodBankSelectBOImpl();
            case SELECT_DONATION -> new DonationSelectBOImpl();
            case SELECT_MAIL -> new SelectMailBOImpl();
            case LOGIN -> new LoginBOImpl();
            default -> null;
        };
    }
}
