package com.company;

import java.util.ArrayList;

public class Client {
    private int clientNumber;
    private String clientName;
    private ArrayList<Account> clientAccounts = new ArrayList<Account>();

    public ArrayList<Account> getAccounts() {
        return clientAccounts;
    }

    public int addAccount() {
        Account yeniHesap = new Account();
        yeniHesap.setAccountNumber(clientAccounts.size() + 1);
        clientAccounts.add(yeniHesap);
        return yeniHesap.getAccountNumber();
    }


    public int getClientNumber() {
        return this.clientNumber;
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
}
