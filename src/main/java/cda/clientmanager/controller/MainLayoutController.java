package cda.clientmanager.controller;

import cda.clientmanager.App;
import cda.clientmanager.controller.utils.SearchManager;
import cda.clientmanager.model.Client;
import cda.clientmanager.model.ClientDAO;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;

public class MainLayoutController {
    @FXML
    private VBox sideButtonsPanel;
    @FXML
    private Button showAllClients;
    @FXML
    private Button deleteAClient;
    @FXML
    private Button modifyData;
    @FXML
    private Button searchByLastName;
    @FXML
    private Button searchByFirstName;
    @FXML
    private Button searchById;
    @FXML
    private AnchorPane mainWindow;

    @FXML
    public void initialize() {
        initializeSideButtons();
        sideButtonsPanel.setVisible(false);
    }

    @FXML
    protected void onButtonClick(ActionEvent event) {

        initializeSideButtons();

        Button clickedButton = (Button) event.getSource();
        String buttonText = clickedButton.getText();

        switch (buttonText) {
            case "Consulter les fiches clients":
                loadFxml("clientList");
                onConsultingMenu();
                break;

            case "Afficher tous les clients":
                onConsultingMenu();
                loadFxml("clientList");
                break;

            case "Modifier des données":
                onConsultingMenu();
                loadFxml("updateClient");
                break;

            case "Supprimer un client":
                onConsultingMenu();
                loadFxml("deleteClient");
                break;

            case "Rechercher un client":
                onSearchMenu();
                SearchManager.setSearchBy("LastName");
                loadFxml("searchClient");
                break;

            case "Recherche par nom":
                onSearchMenu();
                SearchManager.setSearchBy("LastName");
                loadFxml("searchClient");
                break;

            case "Recherche par prénom":
                onSearchMenu();
                SearchManager.setSearchBy("FirstName");
                loadFxml("searchClient");
                break;

            case "Recherche par identifiant":
                onSearchMenu();
                SearchManager.setSearchBy("Id");
                loadFxml("searchClient");
                break;

            case "Ajouter une fiche client":
                sideButtonsPanel.setVisible(false);
                loadFxml("addClientForm");
                break;

            case "Quitter l'application":
                event.consume();
                List<Client> clientList = ClientDAO.getInstance().getClients();
                ClientDAO.getInstance().saveToFile(clientList);
                Platform.exit();
                System.exit(0);
                break;
        }

    }

    private void initializeSideButtons() {

        showAllClients.setVisible(false);
        showAllClients.setManaged(false);
        deleteAClient.setVisible(false);
        deleteAClient.setManaged(false);
        modifyData.setVisible(false);
        modifyData.setManaged(false);
        searchByLastName.setVisible(false);
        searchByLastName.setManaged(false);
        searchByFirstName.setVisible(false);
        searchByFirstName.setManaged(false);
        searchById.setVisible(false);
        searchById.setManaged(false);
    }

    private void showSidePannel() {
        if (!sideButtonsPanel.isVisible()){
            sideButtonsPanel.setVisible(true);
            TranslateTransition fadeIn = new TranslateTransition(Duration.millis(500), sideButtonsPanel);
            fadeIn.setFromX(-sideButtonsPanel.getWidth());
            fadeIn.setToX(0);
            fadeIn.play();
        }
    }

    private void onConsultingMenu() {
        showAllClients.setVisible(true);
        showAllClients.setManaged(true);
        deleteAClient.setVisible(true);
        deleteAClient.setManaged(true);
        modifyData.setVisible(true);
        modifyData.setManaged(true);
        searchByLastName.setVisible(false);
        searchByLastName.setManaged(false);
        searchByFirstName.setVisible(false);
        searchByFirstName.setManaged(false);
        searchById.setVisible(false);
        searchById.setManaged(false);
        showSidePannel();
    }

    private void onSearchMenu() {
        searchByLastName.setVisible(true);
        searchByLastName.setManaged(true);
        searchByFirstName.setVisible(true);
        searchByFirstName.setManaged(true);
        searchById.setVisible(true);
        searchById.setManaged(true);
        showAllClients.setVisible(false);
        showAllClients.setManaged(false);
        deleteAClient.setVisible(false);
        deleteAClient.setManaged(false);
        modifyData.setVisible(false);
        modifyData.setManaged(false);
        showSidePannel();
    }

    private void loadFxml(String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/" + fxml + ".fxml"));
            Parent childContent = fxmlLoader.load();
            mainWindow.getChildren().setAll(childContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
