package cda.clientmanager.controller;

import cda.clientmanager.controller.utils.PageUtils;
import cda.clientmanager.model.Client;
import cda.clientmanager.model.ClientDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DeleteClientController extends PageUtils {

    @FXML
    private TextField lastNameField;
    @FXML
    private TextField firstNameField;

    public void initialize() {
        setAnchors();
    }

    public void onDeleteButton() {
        String lastName = lastNameField.getText().trim();
        String firstName = firstNameField.getText().trim();

        Client client = ClientDAO.getInstance().getClientByFullName(lastName, firstName);

        if (client != null) {
            boolean confirm1 = confirmBox("Etes-vous sûr de vouloir supprimer ce client ?");
            if (confirm1) {
                boolean confirm2 = confirmBox("Etes-vous vraiment certain de vouloir la suppression de " + client.getFirstName() + " " + client.getLastName() + " ?");
                if (confirm2) {
                    boolean confirm3 = confirmBox("La suppression d'un client est irréversible. Souhaitez-vous toujours continuer ?");
                    if (confirm3) {
                        ClientDAO.getInstance().deleteClient(client);
                        messageBox("Le contrat de suppression est envoyé. Nous vous contacterons dans les prochains jours pour le règlement.");
                        firstNameField.setText("");
                        lastNameField.setText("");
                    } else {
                        messageBox("Contrat annulé.");
                    }
                } else {
                    messageBox("Suppression annulée.");
                }
            } else {
                messageBox("Suppression annulée.");
            }
        } else {
            messageBox("Ce client n'est pas répertorié.");
        }
    }
}
