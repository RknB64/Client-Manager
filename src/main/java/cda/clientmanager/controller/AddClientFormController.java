package cda.clientmanager.controller;

import cda.clientmanager.controller.utils.PageUtils;
import cda.clientmanager.model.Client;
import cda.clientmanager.model.ClientDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddClientFormController extends PageUtils {

    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField telephoneTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField zipCodeTextField;
    @FXML
    private TextField townTextField;

    public void initialize() {
        setAnchors();
    }

    public void onAddClientButton() {
        try {
            String lastName = lastNameTextField.getText().trim();
            String firstName = firstNameTextField.getText().trim();
            String telephone = telephoneTextField.getText().trim();
            String address = addressTextField.getText().trim();
            String zipCodeString = zipCodeTextField.getText().trim();

            String town = townTextField.getText().trim();

            if (!lastName.isBlank() && !firstName.isBlank() && !telephone.isBlank() && !address.isBlank() && !zipCodeString.isBlank() && !town.isBlank()) {

                int zipCode = 0;
                boolean confirm = false;

                if (zipCodeString.length() == 5) {
                    try {
                        zipCode = Integer.parseInt(zipCodeString);
                        confirm = confirmBox("Ajouter le client ?");
                    } catch (NumberFormatException e) {
                        messageBox("Veuillez entrer un code postal valide");
                    }
                } else {
                    messageBox("Veuillez entrer un code postal à 5 chiffres.");
                }

                if (confirm) {
                    Client clientToAdd = new Client(lastName, firstName, telephone, address, zipCode, town);
                    ClientDAO.getInstance().addClient(clientToAdd);

                    messageBox("Client ajouté avec succès");

                    loadOtherFxml("clientList");
                }

            } else {
                messageBox("Veuillez remplir tous les champs.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
