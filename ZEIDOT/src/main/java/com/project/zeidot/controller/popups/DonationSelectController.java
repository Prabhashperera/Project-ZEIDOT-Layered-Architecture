package com.project.zeidot.controller.popups;

import com.project.zeidot.bo.custom.BOFactory;
import com.project.zeidot.bo.custom.PopupsBOs.PopupBO;
import com.project.zeidot.controller.delivery.DeliveryController;
import com.project.zeidot.dto.DonationDTO;
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

public class DonationSelectController implements Initializable {
    @FXML
    private TableView<DonationDTO> tableView;
    @FXML
    private TableColumn<DonationDTO, String> donationID;
    @FXML
    private TableColumn<DonationDTO, String> donationName;
    @FXML
    private TableColumn<DonationDTO, String> FBId;
    @FXML
    private TableColumn<DonationDTO, String> foodBankID;
    private DeliveryController donationController;
//    private DonationSelectDAOImpl donationModel = new DonationSelectDAOImpl();
    private final PopupBO<DonationDTO> donationSelectBO =
        (PopupBO<DonationDTO>) BOFactory.getInstance().getBOType(BOFactory.BOType.SELECT_DONATION); //LA OBJECT

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        donationID.setCellValueFactory(new PropertyValueFactory<>("donationID"));
        donationName.setCellValueFactory(new PropertyValueFactory<>("donationName"));
        FBId.setCellValueFactory(new PropertyValueFactory<>("FBId"));
        foodBankID.setCellValueFactory(new PropertyValueFactory<>("foodBankID"));

        try {
            loadTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadTable() throws SQLException {
        // Convert ArrayList to ObservableList
        ArrayList<DonationDTO> foodBankDetails = donationSelectBO.getDetails(); //LA
        ObservableList<DonationDTO> observableList = FXCollections.observableArrayList(foodBankDetails);
        // Set the ObservableList to the TableView
        tableView.setItems(observableList);
    }

    public void setDonationController(DeliveryController donationController) {
        this.donationController = donationController;
    }

    public void getDonationID() {
        DonationDTO selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            donationController.setDonationID(selectedItem.getDonationID());
        }
    }

    public void selectBtnOnAction(ActionEvent event) {
        getDonationID();
    }
}
