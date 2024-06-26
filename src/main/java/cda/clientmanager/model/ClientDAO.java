package cda.clientmanager.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    private static final String RELATIVE_DIRECTORY_PATH = "src/main/java/cda/clientmanager";
    private static final String DATA_DIRECTORY = "datas";
    private static final String DATA_FILENAME = "data.dat";

    private final File file;
    private List<Client> clientList = new ArrayList<>();

    private static ClientDAO instance = null;

    public static ClientDAO getInstance() {
        if(instance == null) {
            instance = new ClientDAO();
        }
        return instance;
    }

    private ClientDAO() {

        File dataDir = new File(RELATIVE_DIRECTORY_PATH, DATA_DIRECTORY);
        if(!dataDir.exists()) {
            dataDir.mkdirs();
        }
        this.file = new File(dataDir, DATA_FILENAME);
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Client> getClients() {
        try {
            FileInputStream readData = new FileInputStream(this.file);
            ObjectInputStream readStream = new ObjectInputStream(readData);
            this.clientList = (ArrayList<Client>) readStream.readObject();
            readStream.close();
        } catch (EOFException e) {
            this.clientList = new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.clientList;
    }

    public Client getClientById(String id) {
        Client client = null;
        this.clientList = getClients();
        int counter = 0;
        while (client == null && counter < this.clientList.size()) {
            Client currentClient = this.clientList.get(counter);
            if (currentClient.getId().equals(id)) {
                client = currentClient;
            }
            counter++;
        }
        return client;
    }

    public Client getClientByLastName(String name) {
        Client client = null;
        this.clientList = getClients();
        int counter = 0;
        while (client == null && counter < this.clientList.size()) {
            Client currentClient = this.clientList.get(counter);
            if (currentClient.getLastName().equals(name)) {
                client = currentClient;
            }
            counter++;
        }
        return client;
    }

    public Client getClientByFirstName(String name) {
        Client client = null;
        this.clientList = getClients();
        int counter = 0;
        while (client == null && counter < this.clientList.size()) {
            Client currentClient = this.clientList.get(counter);
            if (currentClient.getFirstName().equals(name)) {
                client = currentClient;
            }
            counter++;
        }
        return client;
    }

    public Client getClientByFullName(String lastName, String firstName) {
        Client client = null;
        this.clientList = getClients();
        int counter = 0;
        while (client == null && counter < this.clientList.size()) {
            Client currentClient = this.clientList.get(counter);
            if (currentClient.getLastName().equals(lastName) && currentClient.getFirstName().equals(firstName)) {
                client = currentClient;
            }
            counter++;
        }
        return client;
    }

    public boolean addClient(Client client) {
        boolean added = false;
        try {
            this.clientList = getClients();
            this.clientList.add(client);
            saveToFile(clientList);
            added = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return added;
    }

    public boolean updateClient(Client client) {
        boolean updated = false;
        try {
            this.clientList = getClients();
            String id = client.getId();
            int indexOfClient = -1;
            int counter = 0;
            while (indexOfClient == -1 && counter < this.clientList.size()){
                if (this.clientList.get(counter).getId().equals(id)) {
                    indexOfClient = counter;
                    this.clientList.set(indexOfClient,client);
                    saveToFile(clientList);
                    updated = true;
                }
                counter++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return updated;
    }


    public boolean deleteClient(Client client) {
        boolean deleted = false;
        try {
            this.clientList = getClients();
            String id = client.getId();
            int indexOfClient = -1;
            int counter = 0;
            while (indexOfClient == -1 && counter < this.clientList.size()){
                if (this.clientList.get(counter).getId().equals(id)) {
                    indexOfClient = counter;
                    this.clientList.remove(indexOfClient);
                    saveToFile(clientList);
                    deleted = true;
                }
                counter++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleted;
    }

    public boolean saveToFile(List<Client> clientList) {
        boolean saved = false;

        try {
            FileOutputStream writeData = new FileOutputStream(this.file);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            writeStream.writeObject(clientList);
            writeStream.close();
            saved = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return saved;
    }

}
