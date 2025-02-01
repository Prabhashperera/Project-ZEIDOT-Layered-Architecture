package com.project.zeidot.controller.popups;

import com.project.zeidot.bo.custom.BOFactory;
import com.project.zeidot.bo.custom.PopupsBOs.PopupBO;
import com.project.zeidot.controller.mail.SendMailController;
import com.project.zeidot.dto.FoodBankDTO;
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

public class SelectMailController implements Initializable {
    @FXML
    private TableView<FoodBankDTO> tableView;
    @FXML
    private TableColumn<FoodBankDTO, String> FBName;
    @FXML
    private TableColumn<FoodBankDTO, Integer> emailAddress;
    private SendMailController sendMailController; //Controller Instance
    private final PopupBO<FoodBankDTO> selectMailBO =
            (PopupBO<FoodBankDTO>) BOFactory.getInstance().getBOType(BOFactory.BOType.SELECT_MAIL); //LA OBJECT

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FBName.setCellValueFactory(new PropertyValueFactory<>("FBKName"));
        emailAddress.setCellValueFactory(new PropertyValueFactory<>("FBKEmail"));

        try {
            loadTable();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setSendMailController(SendMailController sendMailController) {
        this.sendMailController = sendMailController;
    }

    public void loadTable() throws SQLException {
        // Convert ArrayList to ObservableList
        ArrayList<FoodBankDTO> foodBankDetails = selectMailBO.getDetails(); //LA
        ObservableList<FoodBankDTO> observableList = FXCollections.observableArrayList(foodBankDetails);
        // Set the ObservableList to the TableView
        tableView.setItems(observableList);
    }

    public void selectEmailBtnOnAction(ActionEvent event) {
        FoodBankDTO selectedItem = tableView.getSelectionModel().getSelectedItem();
        sendMailController.setMailToBtn(selectedItem.getFBKEmail());
    }
}
