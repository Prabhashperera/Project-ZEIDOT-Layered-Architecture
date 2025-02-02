package com.project.zeidot.controller;

import com.project.zeidot.bo.custom.BOFactory;
import com.project.zeidot.bo.custom.impl.LoginBOImpl;
import com.project.zeidot.dto.UserDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public TextField userNameTF;
    public TextField passwordTF;
    public AnchorPane mainPanel;
    @FXML
    private ImageView imageView;

    private final LoginBOImpl loginBO = (LoginBOImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.LOGIN);

    public void loginOnAction(ActionEvent event) {
        try {
            String userName = userNameTF.getText();
            String password = passwordTF.getText();
            UserDTO dto = new UserDTO(userName, password);
            boolean isOK = loginBO.login(dto); //Check if is correct //LA
            if (isOK) { // if True immediately Redirect to mainLayoutFXML
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/view/homePage/mainLayout.fxml"));
                Scene scene = new Scene(root , 1200 , 750);
                stage.setResizable(false); //Cannot resize
                scene.getStylesheets().add(getClass().getResource("/view/Css/mainCss.css").toExternalForm());
                stage.setScene(scene);
                stage.show();
            }else {
                //new Alert(Alert.AlertType.ERROR , "Login failed").show();
                System.out.println("Login failed");
                userNameTF.setStyle("-fx-border-color: red; -fx-border-width: 0 0 1px 0; -fx-background-color: #fff");
                passwordTF.setStyle("-fx-border-color: red; -fx-border-width: 0 0 1px 0; -fx-background-color: #fff");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("src/main/resources/view/images/undraw_Cooking_p7m1.png");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }

    public void registerOnAction(ActionEvent event) throws SQLException {
        String userName = userNameTF.getText();
        String password = passwordTF.getText();
        UserDTO dto = new UserDTO(userName, password);
        boolean register = loginBO.register(dto);
        if (register) {
            new Alert(Alert.AlertType.INFORMATION, "Register successful").show();
        }

    }
}
