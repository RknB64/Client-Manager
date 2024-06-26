package cda.clientmanager.controller;

import cda.clientmanager.model.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ClientCardController {

    @FXML
    private Label clientDetailTitle;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label idLabel;
    @FXML
    private Label telephoneLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label zipCodeLabel;
    @FXML
    private Label townLabel;

    public void initializeClientCard(Client client) {
        clientDetailTitle.setText("Détail Client n° " + client.getId());
        lastNameLabel.setText("Nom : " + client.getLastName());
        firstNameLabel.setText("Prénom : " + client.getFirstName());
        idLabel.setText("Identifiant : " + client.getId());
        telephoneLabel.setText("Téléphone : " + client.getTelephone());
        addressLabel.setText("Adresse : " + client.getAddress());
        zipCodeLabel.setText("Code postal : " + client.getZipCode());
        townLabel.setText("Ville : " + client.getTown());
    }
}
