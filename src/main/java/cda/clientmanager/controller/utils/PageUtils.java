package cda.clientmanager.controller.utils;

import cda.clientmanager.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public abstract class PageUtils {

    @FXML
    private AnchorPane childWindow;

    protected void setAnchors() {
        AnchorPane.setTopAnchor(childWindow, 0.0);
        AnchorPane.setBottomAnchor(childWindow, 0.0);
        AnchorPane.setLeftAnchor(childWindow, 0.0);
        AnchorPane.setRightAnchor(childWindow, 0.0);
    }

    protected void loadOtherFxml(String fxml) throws IOException {
        AnchorPane viewContent = FXMLLoader.load(App.class.getResource("view/" + fxml + ".fxml"));
        childWindow.getChildren().setAll(viewContent);
    }

    protected boolean confirmBox(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(message);

        ButtonType confirmButton = new ButtonType("Confirmer");
        ButtonType cancelButton = new ButtonType("Annuler");

        alert.getButtonTypes().setAll(confirmButton,cancelButton);

        return alert.showAndWait().get() == confirmButton;
    }

    protected void messageBox(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
