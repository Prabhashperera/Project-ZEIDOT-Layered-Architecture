package com.project.zeidot.controller;

import com.project.zeidot.bo.custom.BOFactory;
import com.project.zeidot.bo.custom.FoodBatchDetailsBO;
import com.project.zeidot.dao.DAOFactory;
import com.project.zeidot.dao.custom.FoodBatchDetailsDAO;
import com.project.zeidot.dto.FoodBatchDetailsDto;
import com.project.zeidot.dao.custom.impl.FoodBatchDetailsDAOImpl;
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

    private final FoodBatchDetailsBO foodBatchDetailsBO = (FoodBatchDetailsBO) BOFactory.getInstance().getBOType(BOFactory.BOType.FOODBATCH_DETAILS);

    public TextField batchID;
    @FXML
    private TableView<FoodBatchDetailsDto> tableView;
    @FXML
    private TableColumn<FoodBatchDetailsDAOImpl, String> foodID;
    @FXML
    private TableColumn<FoodBatchDetailsDAOImpl, String> foodName;
    @FXML
    private TableColumn<FoodBatchDetailsDAOImpl, String> foodWeight;
    @FXML
    private TableColumn<FoodBatchDetailsDAOImpl, String> duration;

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
            ArrayList<FoodBatchDetailsDto> foodBatchDetails = foodBatchDetailsBO.getFoodBatchDetails(FBId);
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
