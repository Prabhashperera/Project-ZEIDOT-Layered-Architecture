module com.project.zeidot {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires net.sf.jasperreports.core;
    requires jdk.httpserver;
    requires jakarta.mail;
    requires jakarta.activation;
    requires java.base;

    opens com.project.zeidot to javafx.fxml;
    opens com.project.zeidot.controller to javafx.fxml;
    opens com.project.zeidot.controller.popups to javafx.fxml;
    exports com.project.zeidot.dto;
    exports com.project.zeidot.controller;
    exports com.project.zeidot;
    opens com.project.zeidot.dto to javafx.base;
    exports com.project.zeidot.controller.popups;
    opens com.project.zeidot.controller.foodBank to javafx.fxml;
    exports com.project.zeidot.controller.foodBank;
    opens com.project.zeidot.controller.delivery to javafx.fxml;
    exports com.project.zeidot.controller.delivery;
    opens com.project.zeidot.controller.mail to javafx.fxml;
    exports com.project.zeidot.controller.mail;
    exports com.project.zeidot.dao.custom;
    opens com.project.zeidot.dao.custom to javafx.fxml;
    exports com.project.zeidot.bo.custom;
    opens com.project.zeidot.bo.custom to javafx.fxml;
}
