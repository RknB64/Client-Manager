package cda.clientmanager.controller;

import cda.clientmanager.App;
import cda.clientmanager.controller.utils.PageUtils;
import cda.clientmanager.model.Client;
import cda.clientmanager.model.ClientDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class ClientListController extends PageUtils {

    @FXML
    private VBox clientCardContainer;

    public void initialize() {
        setAnchors();
        List<Client> clientList = ClientDAO.getInstance().getClients();

        for (Client client : clientList) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/component/clientCard.fxml"));
                Parent clientCard = fxmlLoader.load();

                ClientCardController clientCardController = fxmlLoader.getController();
                clientCardController.initializeClientCard(client);

                clientCardContainer.getChildren().add(clientCard);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        }
}

