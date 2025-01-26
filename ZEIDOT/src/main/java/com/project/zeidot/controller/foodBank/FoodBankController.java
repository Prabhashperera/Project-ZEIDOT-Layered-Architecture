package com.project.zeidot.controller.foodBank;

import com.project.zeidot.bo.custom.BOFactory;
import com.project.zeidot.bo.custom.FoodBankBO;
import com.project.zeidot.dto.FoodBankDto;
import com.project.zeidot.dao.custom.impl.foodBank.FoodBankDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FoodBankController implements Initializable {
    public TextField FBEmailTF;
    public TextField FBName;
    public TextField FBAddressTF;
    public Label FBDonationID;
    @FXML
    private TableColumn<FoodBankDto ,String> donationID;
    @FXML
    private TableColumn<FoodBankDto ,String> FBKName;
    @FXML
    private TableColumn<FoodBankDto ,String> FBKAddress;
    @FXML
    private TableColumn<FoodBankDto ,String> FBKEmail;
    @FXML
    private TableView<FoodBankDto> tableView;

    private final FoodBankBO foodBankBO = (FoodBankBO) BOFactory.getInstance().getBOType(BOFactory.BOType.FOODBANK); //LA
//    FoodBankDAOImpl fBKModel = new FoodBankDAOImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        donationID.setCellValueFactory(new PropertyValueFactory<>("FBKId"));
        FBKName.setCellValueFactory(new PropertyValueFactory<>("FBKName"));
        FBKAddress.setCellValueFactory(new PropertyValueFactory<>("FBKAddress"));
        FBKEmail.setCellValueFactory(new PropertyValueFactory<>("FBKEmail"));

        try {
            refreshPage();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void refreshPage() throws SQLException {
        loadTable();
        String nextID = foodBankBO.getNextFoodBankId();
        FBDonationID.setText(nextID);
        FBEmailTF.setText("");
        FBName.setText("");
        FBAddressTF.setText("");
    }

    public void saveBtnOnAction(ActionEvent event) {
        try{
            String donationId = FBDonationID.getText();
            // Validate FoodBank Name (FBName)
            // Must not be empty and must only contain letters and spaces
            String FBNames = FBName.getText();
            if (FBNames == null || !FBNames.matches("^[A-Za-z ]+$")) {
                new Alert(Alert.AlertType.ERROR, "Invalid FoodBank Name! It must contain only letters and spaces.", ButtonType.OK).show();
                return;
            }
            // Validate FoodBank Address (FBAddress)
            // Must not be empty and allows letters, numbers, spaces, and common address symbols (, . - # /)
            String FBAddress = FBAddressTF.getText();
            if (FBAddress == null || !FBAddress.matches("^[A-Za-z0-9 ,.\\-#/]+$")) {
                new Alert(Alert.AlertType.ERROR, "Invalid FoodBank Address! It must contain only valid address characters.", ButtonType.OK).show();
                return;
            }

            // Validate FoodBank Email (FBEmail)
            // Must follow a valid email format
            String FBEmail = FBEmailTF.getText();
            if (FBEmail == null || !FBEmail.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                new Alert(Alert.AlertType.ERROR, "Invalid FoodBank Email! Please enter a valid email address.", ButtonType.OK).show();
                return;
            }
            FoodBankDto dto = new FoodBankDto(donationId,FBAddress, FBNames,FBEmail);
            boolean isSaved = foodBankBO.saveFoodBank(dto);
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION , "Saved!!").show();
                refreshPage();
            }else {
                new Alert(Alert.AlertType.ERROR , "Save Failed!!").show();
                refreshPage();
            }
        } catch (NullPointerException | SQLException e) {
            new Alert(Alert.AlertType.ERROR , "Save Failed!!").show();
        }
    }
    public void editOnAction(ActionEvent event) throws SQLException {
        try {
            if (tableView.getSelectionModel().getSelectedItem() != null) {
                String FBKId = FBDonationID.getText();
                String FBAddress = FBAddressTF.getText();
                String FBName = FBKName.getText();
                String FBEmail = FBEmailTF.getText();
                FoodBankDto dto = new FoodBankDto(FBKId,FBAddress,FBName,FBEmail);
                boolean isUpdated = foodBankBO.editFoodBank(dto);
                if(isUpdated){
                    refreshPage();
                    new Alert(Alert.AlertType.CONFIRMATION , "Updated!!").show();
                }
            }
        }catch (Exception e) {
            System.out.println("null Selected Row");
        }
    }
    public void deleteOnAction(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            try {
                FoodBankDto selectedItem = tableView.getSelectionModel().getSelectedItem();
                boolean isDeleted = foodBankBO.deleteFoodBank(selectedItem.getFBKId());
                if(isDeleted){
                    new Alert(Alert.AlertType.CONFIRMATION , "Deleted!!").show();
                    refreshPage();
                }
            }catch(Exception e) {
                new Alert(Alert.AlertType.ERROR , "Delete Failed!!").show();
            }
        }
    }

    //Table Methods
    public void loadTable() throws SQLException {
        // Convert ArrayList to ObservableList
        ArrayList<FoodBankDto> foodBankDetails =  foodBankBO.getFoodBankDetails();
        ObservableList<FoodBankDto> observableList = FXCollections.observableArrayList(foodBankDetails);
        // Set the ObservableList to the TableView
        tableView.setItems(observableList);
    }

    public void tableOnMouseClickedAction(MouseEvent mouseEvent) {
        FoodBankDto selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            FBDonationID.setText(selectedItem.getFBKId());
            FBName.setText(selectedItem.getFBKName());
            FBAddressTF.setText(selectedItem.getFBKAddress());
            FBEmailTF.setText(selectedItem.getFBKEmail());
        }
    }
}
