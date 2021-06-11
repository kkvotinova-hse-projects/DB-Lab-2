/*
 * Copyright (c) 2021 Ksuvot
 */

package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class startWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textInput;

    @FXML
    private Button buttonStart;

    @FXML
    void initialize() {
        buttonStart.setOnAction(actionEvent -> {
            buttonStart.getScene().getWindow().hide();

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/resources/mainWindow.fxml"));

            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent parent = fxmlLoader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.setTitle("HouSEhold DataBase");
            stage.showAndWait();

            if (!stage.isShowing()) {
                System.exit(0);
            }
        });
    }
}

