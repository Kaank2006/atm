package com.ders;

import java.util.ArrayList;

public class Client {
    private int clientNumber = 0;
    private String clientName;
    private String clientSurname;
    private String clientCity;
    private ArrayList<Account> clientAccounts = new ArrayList<>();

    public ArrayList<Account> getAccounts() {
        return clientAccounts;
    }

    public int addAccount() {
        Account yeniHesap = new Account();
        yeniHesap.setAccountNumber(clientAccounts.size() + 1);
        clientAccounts.add(yeniHesap);
        return yeniHesap.getAccountNumber();
    }
    public Client(String clientName, String clientSurname, String clientCity){
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.clientCity = clientCity;
    }
    public Client(){}
    public int getClientNumber() {
        return this.clientNumber;
    }
    public String getClientSurname() {
        return this.clientSurname;
    }
    public String getClientCity() {
        return this.clientCity;
    }
    public String getClientName() {
        return this.clientName;
    }
    public void setClientName(String name){
        clientName = name;
    }
    public void setClientNumber(int number) {
        clientNumber = number;
    }
    public void setClientSurname(String surname) {
        clientSurname = surname;
    }
    public void setClientCity(String city) {
        clientCity = city;
    }
    public void setClientAccounts(ArrayList<Account> clientAccounts){
        this.clientAccounts = clientAccounts;
    }
}
