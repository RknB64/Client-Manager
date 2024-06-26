module cda.clientmanager {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens cda.clientmanager to javafx.fxml;
    exports cda.clientmanager;
    exports cda.clientmanager.controller;
    opens cda.clientmanager.controller to javafx.fxml;
    exports cda.clientmanager.controller.utils;
    opens cda.clientmanager.controller.utils to javafx.fxml;
}