package com.project.zeidot.controller;

import com.project.zeidot.controller.popups.FoodBankSelectController;
import com.project.zeidot.controller.popups.FoodBatchSelectController;
import com.project.zeidot.db.DBConnection;
import com.project.zeidot.dto.DonationDto;
import com.project.zeidot.dto.FoodDto;
import com.project.zeidot.dao.DonationModel;
import com.project.zeidot.dao.popups.FoodBatchSelectModel;
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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DonationController implements Initializable {
    private Connection conn =null;
    public TextField donationNameTF;
    public Label donationIDTF;
    public Button batchIDTF;
    public static String clickedFoodBatchID;
    public Button foodBankID;
    @FXML
    private TableColumn<FoodDto, String> DonationID;
    @FXML
    private TableColumn<FoodDto, String> DonationName;
    @FXML
    private TableColumn<FoodDto, String> FoodBatchID;
    @FXML
    private TableColumn<FoodDto, Integer> FoodBankID;
    @FXML
    private TableView<DonationDto> tableView;
    DonationModel donationModel = new DonationModel();
    FoodBatchSelectModel foodBatchSelectModel = new FoodBatchSelectModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DonationID.setCellValueFactory(new PropertyValueFactory<>("DonationID"));
        DonationName.setCellValueFactory(new PropertyValueFactory<>("DonationName"));
        FoodBatchID.setCellValueFactory(new PropertyValueFactory<>("FBId"));
        FoodBankID.setCellValueFactory(new PropertyValueFactory<>("FoodBankID"));
        try {
            loadDonationTable();
            loadNextDonationID();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveBtnOnAction(ActionEvent event) {
        try {
            String foodBankIDx = foodBankID.getText();
            String donationID = donationIDTF.getText();
            // Validate Donation Name (e.g., must contain letters and spaces)
            String donationName = donationNameTF.getText();
            if (donationName == null || !donationName.matches("^[A-Za-z ]+$")) {
                new Alert(Alert.AlertType.ERROR, "Invalid Donation Name! Must contain only letters and spaces.", ButtonType.OK).show();
                return;
            }
            String foodBatchID = batchIDTF.getText();
            //Transaction Start
            conn = DBConnection.getInstance().getConnection();
            conn.setAutoCommit(false);
            //Pass Data to Dto
            DonationDto dto = new DonationDto(donationID, donationName, foodBatchID , foodBankIDx);
            boolean isSaved = donationModel.saveDonation(dto); //Save Donation
            if (!isSaved) {
                conn.rollback();
                new Alert(Alert.AlertType.ERROR, "Failed to save Donation", ButtonType.OK).show();
                return;
            }
            boolean isUpdated = foodBatchSelectModel.changeAvailability(foodBatchID);
            if (!isUpdated) {
                conn.rollback();
                new Alert(Alert.AlertType.ERROR, "Failed to Update Availability Donation", ButtonType.OK).show();
                return;
            }
            conn.commit();
            new Alert(Alert.AlertType.CONFIRMATION , "Donation Saved Successfully!!!").show();
            refreshPage();
        }catch (Exception e){
            try {
                conn.rollback();
            } catch (SQLException ex) {
                new Alert(Alert.AlertType.ERROR, "Failed to Rollback Donation", ButtonType.OK).show();
            }
        }finally {
            try {
               if (conn != null) {
                   conn.setAutoCommit(true);
               }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR , "Failed to close connection", ButtonType.OK).show();
            }
        }

    } //Save Button
    public void deleteBtnOnAction(ActionEvent event) {
        DonationDto dto = tableView.getSelectionModel().getSelectedItem();
        String donationID = dto.getDonationID();
        try {
            boolean isDeleted = donationModel.deleteDonation(donationID);
            if (isDeleted) {
                boolean isChangeToAvailable = foodBatchSelectModel.changeToAvailable(batchIDTF.getText());
                if (isChangeToAvailable) {
                    System.out.println("Deleted BatchID Changed to Available");
                }
                new Alert(Alert.AlertType.CONFIRMATION , "Donation Deleted Successfully!!!").show();
                refreshPage();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void updateBtnOnAction(ActionEvent event) {
        try {
            String foodBankID = FoodBankID.getText();
            String donationID = donationIDTF.getText();
            String donationName = donationNameTF.getText();
            String foodBatchID = batchIDTF.getText();
            DonationDto dto = new DonationDto(donationID, donationName, foodBatchID , foodBankID);
            boolean isUpdated = donationModel.updateDonation(dto);
            if (isUpdated) {
                //This line doing change the current selected batch into available Food Batch Again
                boolean isChangedAvailability = foodBatchSelectModel.changeToAvailable(clickedFoodBatchID);
                if (isChangedAvailability) {
                    System.out.println("Current Food Batch Changed to Available Again");
                    //If it's updated to Available then The new Selected batchID will be Unavailable
                    boolean b = foodBatchSelectModel.changeAvailability(foodBatchID);
                    if (b) {
                        System.out.println("New Food Batch Changed to Unavailable");
                    }
                }else {
                    System.out.println("Not Changed");
                }
                new Alert(Alert.AlertType.CONFIRMATION , "Donation Update Successfully!!!").show();
                refreshPage();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void batchIDInit(String selectedBatchID) {
        System.out.println(selectedBatchID);
        batchIDTF.setText(selectedBatchID);
    } //Batch ID set to the Button
    public void bankIDInit(String selectedBatchID) {
        System.out.println(selectedBatchID);
        foodBankID.setText(selectedBatchID);
    } //Bank ID set to the Button


    public void loadNextDonationID() throws SQLException {
        String donationID = donationModel.getNextDonationId();
        System.out.println(donationID);
        donationIDTF.setText(donationID);
    } // Donation ID set to the Label

    public void loadDonationTable() throws SQLException {
        try {
            ArrayList<DonationDto> donationDTOS = donationModel.getAllDonations(); // Renamed to donationDTOS

            ObservableList<DonationDto> observableBatchDTOS = FXCollections.observableArrayList();

            for (DonationDto donationDto : donationDTOS) {
                DonationDto donationTM = new DonationDto(
                        donationDto.getDonationID(),
                        donationDto.getDonationName(),
                        donationDto.getFBId(),
                        donationDto.getFoodBankID()
                );
                observableBatchDTOS.add(donationTM);
            }
            tableView.setItems(observableBatchDTOS);
        } catch (Exception e) {
            e.getMessage();
        }
    } //Loading the Donation Table

    public void refreshPage() throws SQLException {
        loadDonationTable();
        loadNextDonationID();
        foodBankID.setText("Food Bank");
        batchIDTF.setText("Select Batch");
        donationNameTF.setText("");
    } //Refresh After Saving or Deleting table data

    public void onTableAction(MouseEvent mouseEvent) {
        DonationDto dto = tableView.getSelectionModel().getSelectedItem();
        donationIDTF.setText(dto.getDonationID());
        donationNameTF.setText(dto.getDonationName());
        batchIDTF.setText(dto.getFBId());
        foodBankID.setText(dto.getFoodBankID());
        clickedFoodBatchID = dto.getFoodBankID();
        System.out.println(clickedFoodBatchID); // When Clicked to Table, this static var is initilaizing
        //to the table FoodBatch ID, this is for Tracking selected FoodBatchID
    }


    //POPUPS okkomaaaaaaaaaaaaaaaaaaaaaaaaa
    public void foodBankOnAcion(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/homePage/popups/foodBankSelect.fxml"));
        // Load the root node from the FXML
        Parent root = loader.load();
        // Access the controller
        FoodBankSelectController foodBankSelectController = loader.getController();
        foodBankSelectController.setDonationController(this);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void foodBatchOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/homePage/popups/foodBatchSelect.fxml"));
        Parent root = loader.load();

        // Pass this DonationController instance to FoodBatchSelectController
        FoodBatchSelectController fbSelectController = loader.getController();
        fbSelectController.setDonationController(this);
        //////////////////////////////////////////////////////////

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } //Food Batch Selection BUtton Method
}
