package com.project.zeidot.controller.delivery;

import com.project.zeidot.controller.popups.DonationSelectController;
import com.project.zeidot.dto.DeliverDto;
import com.project.zeidot.dao.delivery.DeliveryModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DeliveryController implements Initializable {
    public Label deliveryIDTF;
    public Label dateTF;
    public Label timeTF;
    public Button donationIDTF;
    @FXML
    private TableView<DeliverDto> tableView;
    @FXML
    private TableColumn<DeliverDto, String> deliveryID;
    @FXML
    private TableColumn<DeliverDto, String> deliveryDate;
    @FXML
    private TableColumn<DeliverDto, String> deliveryTime;
    @FXML
    private TableColumn<DeliverDto, String> donationID;

    DeliveryModel deliveryModel = new DeliveryModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deliveryID.setCellValueFactory(new PropertyValueFactory<>("deliveryID"));
        deliveryDate.setCellValueFactory(new PropertyValueFactory<>("deliverDate"));
        deliveryTime.setCellValueFactory(new PropertyValueFactory<>("deliverTime"));
        donationID.setCellValueFactory(new PropertyValueFactory<>("donationID"));
        try {
            loadTable();
            loadDeliveryID();
            setCurrentDate();
            setCurrentTime();
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveBtnOnAction(ActionEvent event) {
        try{
            String deliveryID = deliveryIDTF.getText();
            String date = dateTF.getText();
            String time = timeTF.getText();
            String donationID = donationIDTF.getText();
            if (deliveryID.isEmpty() || date.isEmpty() || time.isEmpty() || donationID.isEmpty()) {
                new Alert(Alert.AlertType.ERROR , "Cannot Be Empty Donation ID");
            }
            DeliverDto dto = new DeliverDto(deliveryID, date, time, donationID);
            boolean isSaved = deliveryModel.saveDeliver(dto);
            if (isSaved) {
                deliveryModel.isDeliveredToYes(donationID);
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION , "Deliver Saved");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editOnAction(ActionEvent event) {
        try {
            DeliverDto selectedItem = tableView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                String deliveryID = selectedItem.getDeliveryID();
                String date = selectedItem.getDeliverDate();
                String donID = selectedItem.getDonationID();
                String time = timeTF.getText();
                String donationID = donationIDTF.getText();
                DeliverDto dto = new DeliverDto(deliveryID, date, time, donationID);
                deliveryModel.isDeliveredToNo(donID);
                deliveryModel.isDeliveredToYes(donationID);
                boolean isUpdated = deliveryModel.updateDeliver(dto);
                if (isUpdated) {
                    new Alert(Alert.AlertType.INFORMATION , "Deliver Updated");
                    refreshPage();
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteOnAction(ActionEvent event) {
        try {
            DeliverDto selectedItem = tableView.getSelectionModel().getSelectedItem();
            String deliveryID = selectedItem.getDeliveryID();
            String donationID = selectedItem.getDonationID();
            boolean isDeleted = deliveryModel.deleteDeliver(deliveryID);
            if (isDeleted) {
                deliveryModel.isDeliveredToNo(donationID);
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION , "Deliver Deleted");
            }
        }catch (Exception e) {
            new Alert(Alert.AlertType.ERROR , "Not Saved").show();
        }
    }

    public void loadDeliveryID() throws SQLException {
        String DeliveryId = deliveryModel.getNextDonationID();
        deliveryIDTF.setText(DeliveryId);
        System.out.println( "Generated Delivery ID : " + DeliveryId);
    }
    public void setCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        dateTF.setText(currentDate.toString());
    }
    public void setCurrentTime() {
        Thread timeThread = new Thread(() -> {
            while (true) {
                LocalTime currentTime = LocalTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                String formattedTime = currentTime.format(formatter);

                // Ensure UI updates occur on the JavaFX Application Thread
                Platform.runLater(() -> timeTF.setText(formattedTime));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore the interrupted status
                    break; // Exit loop if interrupted
                }
            }
        });
        timeThread.setDaemon(true); // Allow thread to exit when the application exits
        timeThread.start();
    }


    public void donationSelectOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/homePage/popups/donationSelectPage.fxml"));
        Parent root = loader.load();
        DonationSelectController donationController = loader.getController();
        donationController.setDonationController(this);
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void setDonationID(String donationID) {
        donationIDTF.setText(donationID);
    }

    public void loadTable() throws SQLException {
        // Convert ArrayList to ObservableList
        ArrayList<DeliverDto> foodBankDetails = deliveryModel.getDeliveryDetails();
        ObservableList<DeliverDto> observableList = FXCollections.observableArrayList(foodBankDetails);
        // Set the ObservableList to the TableView
        tableView.setItems(observableList);
    }

    public void refreshPage() throws SQLException {
        loadTable();
        loadDeliveryID();
        donationIDTF.setText("Donation");
    }

    public void onMouseClickedOnTableAction(MouseEvent mouseEvent) {
        DeliverDto selectedItem = tableView.getSelectionModel().getSelectedItem();
        donationIDTF.setText(selectedItem.getDonationID());
        deliveryIDTF.setText(selectedItem.getDeliveryID());
    }
}
