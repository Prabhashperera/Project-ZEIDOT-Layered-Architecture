package com.project.zeidot.controller.popups;

import com.project.zeidot.controller.DonationController;
import com.project.zeidot.dto.FoodBankDto;
import com.project.zeidot.dao.foodBank.FoodBankModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FoodBankSelectController implements Initializable {

    public TableView<FoodBankDto> tableView;
    @FXML
    private TableColumn<FoodBankDto, String> FoodBankID;
    @FXML
    private TableColumn<FoodBankDto, String> Name;
    @FXML
    private TableColumn<FoodBankDto, String> Address;
    @FXML
    private TableColumn<FoodBankDto, String> Email;
    FoodBankModel FoodBankModel = new FoodBankModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FoodBankID.setCellValueFactory(new PropertyValueFactory<>("FBKId"));
        Name.setCellValueFactory(new PropertyValueFactory<>("FBKName"));
        Address.setCellValueFactory(new PropertyValueFactory<>("FBKAddress"));
        Email.setCellValueFactory(new PropertyValueFactory<>("FBKEmail"));
        try {
            loadTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectBtnOnAction(ActionEvent event) {
        try {
            FoodBankDto selectedItem = tableView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                donationController.bankIDInit(selectedItem.getFBKId());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private DonationController donationController;
    public void setDonationController(DonationController donationController) {
        this.donationController = donationController;
    }

    public void loadTable() throws SQLException {
        // Convert ArrayList to ObservableList
        ArrayList<FoodBankDto> foodBankDetails = FoodBankModel.getFoodBankDetails();
        ObservableList<FoodBankDto> observableList = FXCollections.observableArrayList(foodBankDetails);
        // Set the ObservableList to the TableView
        tableView.setItems(observableList);
    }

}
