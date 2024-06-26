package cda.clientmanager;

import cda.clientmanager.model.ClientDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/mainLayout.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Client Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        System.out.println("JavaFX version: " + System.getProperty("javafx.version"));
        ClientDAO clientDAO = ClientDAO.getInstance();
//        List<Client> clientList = clientDAO.getClients();
//        System.out.println(clientList);
//
//        Client c1 = new Client("Jon", "JohnnyJon", "0123456789", "ici", 56000, "Vannes");
//        Client c2 = new Client( "Kazama", "Jin", "9876543210", "là-bas", 62000, "Quelque part");
//        clientList.add(c1);
//        clientList.add(c2);
//        clientDAO.saveToFile(clientList);
//
//        clientList = clientDAO.getClients();
//        System.out.println(clientList);
//
//        Client clientToDelete = clientDAO.getClientByLastName("Jon");
//        System.out.println(clientToDelete);
//
//        Client clientToUpdate = clientDAO.getClientByFirstName("Jin");
//        clientToUpdate.setFirstName("Asuka");
//        System.out.println(clientToUpdate);
//
//        clientDAO.updateClient(clientToUpdate);
//
//        clientDAO.deleteClient(clientToDelete);
        System.out.println(clientDAO.getClients());

        launch();
    }
}