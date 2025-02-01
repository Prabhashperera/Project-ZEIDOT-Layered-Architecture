package com.project.zeidot.controller.popups;

import com.project.zeidot.bo.custom.BOFactory;
import com.project.zeidot.bo.custom.FoodBatchSelectBO;
import com.project.zeidot.controller.DonationController;
import com.project.zeidot.dto.FoodBatchDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FoodBatchSelectController implements Initializable {
    @FXML
    private TableView<FoodBatchDTO> tableView;
    @FXML
    private TableColumn<FoodBatchDTO, String> FBID;
    @FXML
    private TableColumn<FoodBatchDTO, String> FoodAmount;
    @FXML
    private TableColumn<FoodBatchDTO, String> FBDate;
    @FXML
    private TableColumn<FoodBatchDTO, String> isAvailable;
    @FXML
    private TableColumn<FoodBatchDTO, String>  duration;
//    FoodBatchSelectDAOImpl foodBatchSelectDAOImpl = new FoodBatchSelectDAOImpl(); //Model
    FoodBatchSelectBO foodBatchSelectBO = (FoodBatchSelectBO) BOFactory.getInstance().getBOType(BOFactory.BOType.FOODBATCH_SELECT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FBID.setCellValueFactory(new PropertyValueFactory<>("foodBatchId"));
        FoodAmount.setCellValueFactory(new PropertyValueFactory<>("foodAmount"));
        FBDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        isAvailable.setCellValueFactory(new PropertyValueFactory<>("isAvailable"));
        duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        try {
            loadTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadTable() throws SQLException {
        // Convert ArrayList to ObservableList
        ArrayList<FoodBatchDTO> foodBatchDetails =  foodBatchSelectBO.getFoodBatchDetails();
        ObservableList<FoodBatchDTO> observableList = FXCollections.observableArrayList(foodBatchDetails);

        // Set the ObservableList to the TableView
        tableView.setItems(observableList);
    }
 
    public String getFoodBatchID() {
        FoodBatchDTO foodBatchDto = tableView.getSelectionModel().getSelectedItem();
        return foodBatchDto.getFoodBatchId();
    }//returns Table view selected FOODBatchID

    private DonationController donationController; // Instance of Controller
    public void setDonationController(DonationController donationController) {
        this.donationController = donationController;
    }//Initialize the Controller with Instance

    public void selectBtnOnAction() throws IOException {
        try{
            if (donationController != null) {
                String selectedBatchID = getFoodBatchID();
                donationController.batchIDInit(selectedBatchID);
            }else {
                new Alert(Alert.AlertType.ERROR , "Please Select a Batch!!").show();
            }
        }catch (Exception e){
            e.getMessage();
        }
    }

}
