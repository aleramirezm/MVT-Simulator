package controller;

import javafx.scene.control.Alert;

public class Alerts {
    protected void showAlert(String body){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setContentText(body);
        alert.showAndWait();
    }
    protected void showInfo(String body){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Woohoo!");
        alert.setContentText(body);
        alert.showAndWait();
    }
}
