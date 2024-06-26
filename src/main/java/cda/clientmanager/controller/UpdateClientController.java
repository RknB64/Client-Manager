package cda.clientmanager.controller;

import cda.clientmanager.App;
import cda.clientmanager.controller.utils.PageUtils;
import cda.clientmanager.model.Client;
import cda.clientmanager.model.ClientDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class UpdateClientController extends PageUtils {

    @FXML
    private VBox clientCardContainer;
    @FXML
    private VBox modificationBox;

    @FXML
    private Label subTitle;
    @FXML
    private TextField lastNameSearchField;
    @FXML
    private TextField firstNameSearchField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField telephoneField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField zipCodeField;
    @FXML
    private TextField townField;

    private Client clientToUpdate;

    public void initialize() {
        setAnchors();
        modificationBox.setVisible(false);
        clientCardContainer.getChildren().clear();
    }

    public void onSearchButton() {
        String lastName = lastNameSearchField.getText().trim();
        String firstName = firstNameSearchField.getText().trim();

        clientToUpdate = ClientDAO.getInstance().getClientByFullName(lastName, firstName);

        if (clientToUpdate != null) {
            subTitle.setText("Modifier le client n°" + clientToUpdate.getId());
            showClientCard(clientToUpdate);
            lastNameField.setText(clientToUpdate.getLastName());
            firstNameField.setText(clientToUpdate.getFirstName());
            telephoneField.setText(clientToUpdate.getTelephone());
            addressField.setText(clientToUpdate.getAddress());
            zipCodeField.setText("" + clientToUpdate.getZipCode());
            townField.setText(clientToUpdate.getTown());
            modificationBox.setVisible(true);
        } else {
            messageBox("Ce client n'est pas répertorié");
        }
    }

    public void onModifyButton() {
        try {
            String lastName = lastNameField.getText().trim();
            String firstName = firstNameField.getText().trim();
            String telephone = telephoneField.getText().trim();
            String address = addressField.getText().trim();
            String zipCodeString = zipCodeField.getText().trim();
            String town = townField.getText().trim();

            if (!lastName.isBlank() && !firstName.isBlank() && !telephone.isBlank() && !address.isBlank() && !zipCodeString.isBlank() && !town.isBlank()) {

                int zipCode = 0;
                boolean confirm = false;

                if (zipCodeString.length() == 5) {
                    try {
                        zipCode = Integer.parseInt(zipCodeString);
                        confirm = confirmBox("Modifier le client ?");
                    } catch (NumberFormatException e) {
                        messageBox("Veuillez entrer un code postal valide");
                    }
                } else {
                    messageBox("Veuillez entrer un code postal à 5 chiffres.");
                }

                if (confirm) {
                    clientToUpdate.setLastName(lastName);
                    clientToUpdate.setFirstName(firstName);
                    clientToUpdate.setTelephone(telephone);
                    clientToUpdate.setAddress(address);
                    clientToUpdate.setZipCode(zipCode);
                    clientToUpdate.setTown(town);
                    ClientDAO.getInstance().updateClient(clientToUpdate);

                    messageBox("Client modifié avec succès");

                    loadOtherFxml("clientList");
                }

            } else {
                messageBox("Veuillez remplir tous les champs.");
            }

        } catch (IOException e) {
            e.printStackTrace();
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
}
