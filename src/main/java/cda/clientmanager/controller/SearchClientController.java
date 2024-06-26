package cda.clientmanager.controller;

import cda.clientmanager.App;
import cda.clientmanager.controller.utils.PageUtils;
import cda.clientmanager.controller.utils.SearchManager;
import cda.clientmanager.model.Client;
import cda.clientmanager.model.ClientDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.function.Function;

public class SearchClientController extends PageUtils {

    @FXML
    private VBox clientCardContainer;

    @FXML
    private Label subTitle;
    @FXML
    private TextField searchByLastNameField;
    @FXML
    private TextField searchByFirstNameField;
    @FXML
    private TextField searchByIdField;

    public void initialize() {
        setAnchors();
        searchByLastNameField.setVisible(false);
        searchByLastNameField.setManaged(false);
        searchByFirstNameField.setVisible(false);
        searchByFirstNameField.setManaged(false);
        searchByIdField.setVisible(false);
        searchByIdField.setManaged(false);

        switch (SearchManager.getSearchBy()) {

            case "LastName":
                subTitle.setText("Rechercher un client par son nom");
                searchByLastNameField.setVisible(true);
                searchByLastNameField.setManaged(true);
                searchByFirstNameField.setVisible(false);
                searchByFirstNameField.setManaged(false);
                searchByIdField.setVisible(false);
                searchByIdField.setManaged(false);
                break;

            case "FirstName":
                subTitle.setText("Rechercher un client par son prénom");
                searchByLastNameField.setVisible(false);
                searchByLastNameField.setManaged(false);
                searchByFirstNameField.setVisible(true);
                searchByFirstNameField.setManaged(true);
                searchByIdField.setVisible(false);
                searchByIdField.setManaged(false);
                break;

            case "Id":
                subTitle.setText("Rechercher un client par son identifiant");
                searchByLastNameField.setVisible(false);
                searchByLastNameField.setManaged(false);
                searchByFirstNameField.setVisible(false);
                searchByFirstNameField.setManaged(false);
                searchByIdField.setVisible(true);
                searchByIdField.setManaged(true);
                break;
        }
    }

    public void onSearchButton() {

        clientCardContainer.getChildren().clear();

        switch (SearchManager.getSearchBy()) {
            case "LastName":
                searchClient(searchByLastNameField, ClientDAO.getInstance()::getClientByLastName);
                break;

            case "FirstName":
                searchClient(searchByFirstNameField, ClientDAO.getInstance()::getClientByFirstName);
                break;

            case "Id":
                searchClient(searchByIdField, ClientDAO.getInstance()::getClientById);
                break;
        }
    }

    private void showClientCard(Client client) {
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

    private void searchClient(TextField textField, Function<String, Client> getClientByMethod) {
        String search = textField.getText().trim();
        Client client = getClientByMethod.apply(search);
        if (client != null) {
            showClientCard(client);
        } else {
            messageBox("Ce client n'est pas répertorié.");
        }
    }
}
