package com.project.zeidot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AppInit extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppInit.class.getResource("/view/login/loginFXML.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 550);
        stage.setTitle("ZEIDOT");
        stage.setResizable(false);
        scene.getStylesheets().add(getClass().getResource("/view/Css/mainCss.css").toExternalForm());
        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/view/images/icons8-pizza-48.png"))));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}