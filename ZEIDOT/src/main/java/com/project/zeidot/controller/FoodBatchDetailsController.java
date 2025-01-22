package com.project.zeidot.controller;

import com.project.zeidot.dto.FoodBatchDetailsDto;
import com.project.zeidot.dao.FoodBatchDetailsModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FoodBatchDetailsController implements Initializable {
    public TextField batchID;
    @FXML
    private TableView<FoodBatchDetailsDto> tableView;
    @FXML
    private TableColumn<FoodBatchDetailsModel, String> foodID;
    @FXML
    private TableColumn<FoodBatchDetailsModel, String> foodName;
    @FXML
    private TableColumn<FoodBatchDetailsModel, String> foodWeight;
    @FXML
    private TableColumn<FoodBatchDetailsModel, String> duration;

    FoodBatchDetailsModel FBDmodel = new FoodBatchDetailsModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // In the initialize() or another setup method
        foodID.setCellValueFactory(new PropertyValueFactory<>("foodId"));
        foodName.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        foodWeight.setCellValueFactory(new PropertyValueFactory<>("foodWeight"));
        duration.setCellValueFactory(new PropertyValueFactory<>("duration"));

    }
    public void searchBtnOnAction(ActionEvent event) {
        try{
            String FBId = batchID.getText();
            ArrayList<FoodBatchDetailsDto> foodBatchDetails = FBDmodel.getFoodBatchDetails(FBId);
            loadTable(foodBatchDetails);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTable(ArrayList<FoodBatchDetailsDto> foodBatchDetails) {
        // Convert ArrayList to ObservableList
        ObservableList<FoodBatchDetailsDto> observableList = FXCollections.observableArrayList(foodBatchDetails);

        // Set the ObservableList to the TableView
        tableView.setItems(observableList);
    }
}
