package com.ders;

import java.util.ArrayList;

public class Client {
    private int clientNumber;
    private String clientName;
    private ArrayList<Account> clientAccounts = new ArrayList<Account>();

    public ArrayList<Account> getAccounts(){
        return clientAccounts;
    }

    public int addAccount(){
        Account yeniHesap = new Account();
        clientAccounts.add(yeniHesap);
        return yeniHesap.getAccountNumber();
    }
}
