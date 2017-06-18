package pl.szotaa.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class RootLayoutController
{

    @FXML
    private void handleAbout()
    {
        System.out.println("About");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("CaptchaGenerator 1.0");
        alert.setContentText("Created by Jakub Szota");
        alert.show();
    }

    @FXML
    private void handleExit()
    {
        Platform.exit();
    }
}
