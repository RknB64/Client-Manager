package cda.clientmanager.model;

import java.io.Serializable;
import java.util.Random;

public class Client implements Serializable {

    private String id;
    private String lastName;
    private String firstName;
    private String telephone;
    private String address;
    private int zipCode;
    private String town;

    public Client(String lastName, String firstName, String telephone, String address, int zipCode, String town) {
        Random random = new Random();
        this.id = "" + random.nextInt(0,9) + System.currentTimeMillis();
        this.lastName = lastName;
        this.firstName = firstName;
        this.telephone = telephone;
        this.address = address;
        this.zipCode = zipCode;
        this.town = town;
    }

    public String getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
