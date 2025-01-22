package com.project.zeidot.controller;

import com.project.zeidot.db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainLayoutController implements Initializable {
    public AnchorPane middlePane;
    public Button foodManageBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            middlePane.getChildren().clear(); //When Loading to the main Layout Immediately Navigate into Home Page
            AnchorPane load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/homePage/homePage.fxml")));
            middlePane.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void foodManageOnAction(ActionEvent event) throws IOException { //Food Manage Navigation
        middlePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/homePage/foodManage.fxml")));
        middlePane.getChildren().add(load);
    }

    public void donationOnAction(ActionEvent event) throws IOException {
        middlePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/homePage/donationPage.fxml")));
        middlePane.getChildren().add(load);
    }

    public void foodBankOnAction(ActionEvent event) throws IOException {
        middlePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/homePage/foodBank/foodBank.fxml")));
        middlePane.getChildren().add(load);
    }

    public void foodBatchDetailsOnAction(ActionEvent event) throws IOException {
        middlePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/homePage/foodBatchDetails.fxml"));
        middlePane.getChildren().add(load);
    }

    public void deleveryManageOnAction(ActionEvent event) throws IOException {
        middlePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/homePage/delivery/deliveryPage.fxml"));
        middlePane.getChildren().add(load);
    }

    public void generateReportOnAction(ActionEvent event) {
        try {
            // Load and compile the JRXML file
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/view/report/donationDetailsReport.jrxml")
            );

            // Obtain database connection
            Connection conn = DBConnection.getInstance().getConnection();

            // Fill the report with data
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    null, // Replace null with parameters if needed
                    conn
            );
            // Display the report
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException | SQLException e) {
            e.printStackTrace(); // Print stack trace for debugging
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Report Error");
            alert.setHeaderText("Cannot Load the Report!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void sendMailOnAction(ActionEvent event) throws IOException {
        middlePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/homePage/mail/sendMailPage.fxml"));
        middlePane.getChildren().add(load);
    }
}