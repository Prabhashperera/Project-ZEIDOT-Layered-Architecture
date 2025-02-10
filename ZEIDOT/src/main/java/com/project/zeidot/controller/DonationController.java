package com.project.zeidot.controller;

import com.project.zeidot.bo.custom.BOFactory;
import com.project.zeidot.bo.custom.DonationBO;
import com.project.zeidot.bo.custom.FoodBatchSelectBO;
import com.project.zeidot.controller.popups.FoodBankSelectController;
import com.project.zeidot.controller.popups.FoodBatchSelectController;
import com.project.zeidot.db.DBConnection;
import com.project.zeidot.dto.DonationDto;
import com.project.zeidot.dto.FoodDTO;
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
    private TableColumn<FoodDTO, String> DonationID;
    @FXML
    private TableColumn<FoodDTO, String> DonationName;
    @FXML
    private TableColumn<FoodDTO, String> FoodBatchID;
    @FXML
    private TableColumn<FoodDTO, Integer> FoodBankID;
    @FXML
    private TableView<DonationDto> tableView;

    private final DonationBO donationBO = (DonationBO) BOFactory.getInstance().getBOType(BOFactory.BOType.DONATION);
    private final FoodBatchSelectBO foodBatchSelectBO = (FoodBatchSelectBO) BOFactory.getInstance().getBOType(BOFactory.BOType.FOODBATCH_SELECT);

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
//            conn = DBConnection.getInstance().getConnection();
//            conn.setAutoCommit(false);
            //Pass Data to Dto
            DonationDto dto = new DonationDto(donationID, donationName, foodBatchID , foodBankIDx);
            boolean isSaved = donationBO.saveDonation(dto); //Save Donation
            if (!isSaved) {
                new Alert(Alert.AlertType.ERROR, "Failed to save Donation", ButtonType.OK).show();
                refreshPage();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Donation Saved Successfully!!", ButtonType.OK).show();
                refreshPage();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    } //Save Button
    public void deleteBtnOnAction(ActionEvent event) {
        DonationDto dto = tableView.getSelectionModel().getSelectedItem();
        String donationID = dto.getDonationID();
        String batchID = batchIDTF.getText();
        try {
            boolean isDeleted = donationBO.deleteDonation(donationID , batchID);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION , "Donation Deleted Successfully!!!").show();
            }else {
                new Alert(Alert.AlertType.ERROR , "Failed to delete Donation!!!").show();
            }
            refreshPage();
        }catch (Exception e){
            e.getMessage();
        }
    }
    public void updateBtnOnAction(ActionEvent event) {
        try {
            String foodBankID = FoodBankID.getText();
            String donationID = donationIDTF.getText();
            String donationName = donationNameTF.getText();
            String foodBatchID = batchIDTF.getText();
            DonationDto dto = new DonationDto(donationID, donationName, foodBatchID , foodBankID);
            boolean isUpdated = donationBO.updateDonation(dto);
            if (isUpdated) {
                //This line doing change the current selected batch into available Food Batch Again
                boolean isChangedAvailability = foodBatchSelectBO.changeToAvailable(clickedFoodBatchID); //LA
                if (isChangedAvailability) {
                    System.out.println("Current Food Batch Changed to Available Again");
                    //If it's updated to Available then The new Selected batchID will be Unavailable
                    boolean b = foodBatchSelectBO.changeAvailability(foodBatchID); //LA
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
        String donationID = donationBO.getNextDonationId();
        System.out.println(donationID);
        donationIDTF.setText(donationID);
    } // Donation ID set to the Label

    public void loadDonationTable() throws SQLException {
        try {
            ArrayList<DonationDto> donations = donationBO.getAllDonations(); // Renamed to donationDTOS

            ObservableList<DonationDto> observableBatchDTOS = FXCollections.observableArrayList();

            for (DonationDto donation : donations) {
                DonationDto donationTM = new DonationDto(
                        donation.getDonationID(),
                        donation.getDonationName(),
                        donation.getFBId(),
                        donation.getFoodBankID()
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


    //--------------------------------------------------------------Controlling Navigation Sections
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
