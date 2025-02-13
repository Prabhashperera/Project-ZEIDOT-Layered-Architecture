package com.project.zeidot.controller;

import com.project.zeidot.bo.custom.BOFactory;
import com.project.zeidot.bo.custom.FoodBatchBO;
import com.project.zeidot.bo.custom.FoodManageBO;
import com.project.zeidot.dao.custom.DAOFactory;
import com.project.zeidot.dao.custom.FoodBatchTimeCheckDAO;
import com.project.zeidot.dto.BatchDetailsDTO;
import com.project.zeidot.dto.FoodBatchDTO;
import com.project.zeidot.dto.FoodDTO;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FoodManageController implements Initializable {
    private Connection conn = null;
    public AnchorPane mainAnchor;

    public TextField foodNameTF; //Food Name
    public TextField foodWeightTF; //Food Weight
    public Label foodIDTF; //Food ID
    public MenuButton menuButton; //Hours Selection Button
    public Label date; // FoodBatch Date , Current Date
    public Label batchID; //FoodBatch BatchID

    @FXML
    private Label currentTimeLabel;// Current Time showing Label - only for showing
    LocalDateTime currentDateTime = LocalDateTime.now(); //Current Time - This is for database saving
    LocalTime currentTime = LocalTime.now();

    //Food Table - Table Columns
    @FXML
    private TableColumn<FoodDTO, String> idColumn;
    @FXML
    private TableColumn<FoodDTO, String> nameColumn;
    @FXML
    private TableColumn<FoodDTO, String> weightColumn;
    @FXML
    private TableColumn<FoodDTO, String> expireDateColumn;
    @FXML
    private TableView<FoodDTO> tableView;
    //Food Table End
    //Batch Table Start
    @FXML
    private TableColumn<FoodBatchDTO, String> FBId;
    @FXML
    private TableColumn<FoodBatchDTO, String> amount;
    @FXML
    private TableColumn<FoodBatchDTO, String> addedDate;
    @FXML
    private TableView<FoodBatchDTO> foodBatchTable;
    // Batch Table End

    //LA Factory Design Patter Applied
    private final FoodManageBO foodManageBO = (FoodManageBO) BOFactory.getInstance().getBOType(BOFactory.BOType.FOOD);
    private final FoodBatchBO foodBatchBO = (FoodBatchBO) BOFactory.getInstance().getBOType(BOFactory.BOType.FOODBATCH);
    private final FoodBatchTimeCheckDAO foodBatchTimeCheckImpl = (FoodBatchTimeCheckDAO) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.FOODBATCH_TIME_CHECK);
    // Food Batch Model Instance LA

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainAnchor.getStyleClass().add("main-pane"); // Style Class Css
        //Food Table Property Value Initialization Start--------------------------------
        idColumn.setCellValueFactory(new PropertyValueFactory<>("foodID"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        expireDateColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        currentTimeLabel.setText(currentDateTime.format(DateTimeFormatter.ofPattern("HH:mm")));
        //Food Table Property Value Initialization End----------------------------------

        //Food Batch Table Property Value Initialization Start -------------------------
        FBId.setCellValueFactory(new PropertyValueFactory<>("foodBatchId"));
        amount.setCellValueFactory(new PropertyValueFactory<>("foodAmount"));
        addedDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        //Food Batch Table Property Value Initialization Start -------------------------
        try {
            batchID.setText(foodBatchBO.getCurrentBatchID()); // When Food Manage Loading Current Batch ID is Taking and Showing
            realtimeThread(); // Realtime time Showing Method (This is a Thread)
            refreshPage(); // Refresh Page Method
            loadTableFood();// Table Data Loading Method
            checkEveryTime();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void refreshPage() throws SQLException {
        loadNextCustomerId();
        currentDate();
        loadTableFood();
        loadFoodBatchTable();
        foodNameTF.setText("");
        foodWeightTF.setText("");
        menuButton.setText("Hour");
    }

    public void loadNextCustomerId() throws SQLException {
        String nextFoodId = foodManageBO.getNextCustomerId(); //LA INJECTED
        System.out.println(nextFoodId);
        foodIDTF.setText(nextFoodId);
    } //Generating NextFoodID & set FoodID Text

    public void saveBtnOnAction(ActionEvent event) throws SQLException {
        // Input validation END--------------------------------------------------
        String foodID = foodIDTF.getText();
        String foodName = foodNameTF.getText();
        if (!foodName.matches("^[A-Za-z ]+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Food Name! Must contain only letters and spaces.", ButtonType.OK).show();
            return;
        }
        String foodWeight = foodWeightTF.getText();
        if (!foodWeight.matches("^\\d+(\\.\\d+)?$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Food Weight! Must be a valid number.", ButtonType.OK).show();
            return;
        }
        //----------------------------------------------------------------------
        // Parse and calculate new time (with plus Hours)
        LocalDateTime newDateTime = currentDateTime.plusHours(Long.parseLong(menuButton.getText()));
        String foodDuration = newDateTime.format(DateTimeFormatter.ofPattern("HH:mm"));

        // Initialize DTOs
        FoodDTO foodDto = new FoodDTO(foodID, foodName, foodWeight, foodDuration); //F001 , Rice , 20.4 , CurrentTime + PlusHours
        BatchDetailsDTO detailsDto = new BatchDetailsDTO(foodID , batchID.getText());
        //Save Process Starts Here ------------------------------------------------------------
        boolean isSaved = foodManageBO.saveFood(foodDto , detailsDto);
        if (isSaved) {
            amountUpdate(foodWeight);
            new Alert(Alert.AlertType.INFORMATION, "Successfully Saved!!", ButtonType.OK).show();
            refreshPage();
        }else {
            new Alert(Alert.AlertType.ERROR, "Something went wrong!!", ButtonType.OK).show();
            refreshPage();
        }

    } //LA OK
    public void deleteOnAction(ActionEvent event) {
        try {
            FoodDTO food = tableView.getSelectionModel().getSelectedItem();
            String foodID = food.getFoodID();
            String batch = batchID.getText();
            String foodWeight = foodWeightTF.getText();
            //Transaction Start
            //Deleting FoodID
            boolean isDeleted = foodManageBO.deleteFood(foodID , batch , foodWeight);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Successfully Deleted!!", ButtonType.OK).show();
                refreshPage();
            }else {
                new Alert(Alert.AlertType.ERROR, "Not Deleted!!", ButtonType.OK).show();
                refreshPage();
            }

        } catch (Exception e) {
            e.getMessage();
            new Alert(Alert.AlertType.ERROR, "An unexpected error occurred: " + e.getMessage(), ButtonType.OK).show();
        }
    } //LA OK
    public void editOnAction(ActionEvent event) {
        try {
            String foodID = foodIDTF.getText();
            String foodName = foodNameTF.getText();
            String foodWeight = foodWeightTF.getText();
            String foodDuration = menuButton.getText();
            FoodDTO dto = new FoodDTO(foodID, foodName, foodWeight, foodDuration);
            foodManageBO.updateAmountWhenUpdate(foodWeight , foodID);
            boolean isUpdated = foodManageBO.updateFood(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Updated", ButtonType.OK).show();
                refreshPage();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    } //LA OK

    public void setHour(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        menuButton.setText(menuItem.getText());
        System.out.println(menuButton.getText());
    } //Menu Button Value set to the Menu Button text

    private void loadTableFood() throws SQLException {
        //Load All FoodData From Model as An ArrayList<FoodDto> (Set of Food DTO Objects)
        ArrayList<FoodDTO> FoodsArrayList = foodManageBO.getAllCustomers();
        //Creating Observable List for TableView
        ObservableList<FoodDTO> FoodObsList = FXCollections.observableArrayList();

        for (FoodDTO foodDto : FoodsArrayList) {
            FoodDTO foodTM = new FoodDTO(foodDto.getFoodID(), foodDto.getWeight(), foodDto.getFoodName(), foodDto.getDuration());
            FoodObsList.add(foodTM);
        }

        tableView.setItems(FoodObsList);
    }
    public void loadFoodBatchTable() throws SQLException {
        try {
            ArrayList<FoodBatchDTO> batchDTOS = foodBatchBO.getAllBatchDetails(); // Renamed to batchDTOS

            ObservableList<FoodBatchDTO> observableBatchDTOS = FXCollections.observableArrayList();

            for (FoodBatchDTO batchDto : batchDTOS) {
                FoodBatchDTO batchTM = new FoodBatchDTO(
                        batchDto.getFoodBatchId(),
                        batchDto.getFoodAmount(),
                        batchDto.getDate(),
                        batchDto.getIsAvailable()
                );
                observableBatchDTOS.add(batchTM);
            }
            foodBatchTable.setItems(observableBatchDTOS);
        }catch (Exception e) {
            e.getMessage();
        }
    }

    //Delete On Action

    public void tableOnAction(MouseEvent tableViewSortEvent) {
        FoodDTO selectedItem = tableView.getSelectionModel().getSelectedItem();
        foodNameTF.setText(selectedItem.getFoodName());
        foodIDTF.setText(selectedItem.getFoodID());
        foodWeightTF.setText(selectedItem.getWeight());
        menuButton.setText(selectedItem.getDuration());
    }

    public void currentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = currentDate.format(formatter);
        date.setText(formattedDate);
    }

    //RealTime Clock With Threads
    public void realtimeThread() {
        Thread clockThread = new Thread(() -> {
            while (true) {
                try {
                    updateClock(); // Update the clock
                    Thread.sleep(1000); // Sleep for 1 second
                } catch (InterruptedException e) {
                    // Handle interruption
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        clockThread.setDaemon(true); // Set the thread as a daemon thread
        clockThread.start();
    }

    private void updateClock() {
        // Get the current time
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); // Create a formatter
        // Update the label in the JavaFX Application Thread
        Platform.runLater(() -> {
            currentTimeLabel.setText(currentTime.format(formatter)); // Update the label with formatted time
        });
    }

    public void newBatchOnAction(ActionEvent event) {
        try{
            String FbatchID = foodBatchBO.getCurrentBatchID();
            String nextBatchID = foodBatchBO.getNextBatchId();
            String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            FoodBatchDTO foodBatchDto = new FoodBatchDTO(FbatchID , "0" , currentDate , "Available");
            foodBatchBO.setBatchValues(foodBatchDto);
            batchID.setText(nextBatchID);
        }catch (NullPointerException | SQLException e) {
            e.getMessage();
        }
    }

    public void batchDetailsOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/homePage/foodBatchDetails.fxml"));
        stage.setResizable(false);
        Scene scene = new Scene(root , 1200 , 750);
        scene.getStylesheets().add(getClass().getResource("/view/Css/mainCss.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void amountUpdate(String foodWeight) throws SQLException {
        double weight = foodManageBO.getCurrentWeight(batchID.getText());
        boolean isUpdated = foodManageBO.updateAmount(weight , Double.parseDouble(foodWeight));
        if (isUpdated) {
            System.out.println("okkoma Hari");
        }
    } //Food ekak add weddi eke weight eka akthu wenaw
    //food amount ekata food Batch eke

    public void deleteBatchOnAction(ActionEvent event) {
        try {
            FoodBatchDTO selectedItem = foodBatchTable.getSelectionModel().getSelectedItem();
            foodBatchBO.deleteFoodsOfDeletedBatch(selectedItem.getFoodBatchId());
            boolean isBatchDeleted = foodBatchBO.deleteBatch(selectedItem.getFoodBatchId());
            if (isBatchDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "FoodBatch Deleted").showAndWait();
                refreshPage();
                System.out.println("okkoma Hari batch deleted");
            }
        }catch (Exception e) {
            e.getMessage();
        }
    } //Batch eka delete weddi wenna ona dewal methods tika

    public void checkEveryTime() {
        Thread checkThread = new Thread(() -> {
            try {
                while (true) {
                    boolean isDeleted = foodBatchTimeCheckImpl.checkTime();
                    if (isDeleted) {
                        refreshPage();
                        System.out.println("Refreshed And Deleted");
                    }else {
                        System.out.println("checked Not Deleted");
                    }
                    Thread.sleep(60000);
                }
            }catch (InterruptedException | SQLException e) {
                Thread.currentThread().interrupt();
            }
        });
        checkThread.setDaemon(true);
        checkThread.start();
    } //Deleting Expired Batch Sets

}
