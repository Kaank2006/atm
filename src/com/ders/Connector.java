package com.ders;

import java.sql.*;
import java.util.ArrayList;

public class Connector {
    // JDBC driver name and database URL

    public Connector() {
    }

    static final String DB_URL = "jdbc:mariadb://localhost/atm";
    static final String USER = "cekirdeksizdomates";
    static final String PASS = "onaylamiyorum";

    public ArrayList<Client> getClients() {
        ArrayList<Client> clientList = new ArrayList<>();
        try {
            Connection dbCon = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = dbCon.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM client");
            // ID(int)
//  1 | acct1
            //  2 | acct2

            while (result.next()) {
                Client client = new Client();
                client.setClientNumber(result.getInt("C_ID"));
                client.setClientName(result.getString("C_NAME"));
                client.setClientSurname(result.getString("C_SURNAME"));
                client.setClientCity(result.getString("C_CITY"));
                System.out.printf("Client retrieved. ID: %s, Name: %s\n", client.getClientNumber(),
                        client.getClientName());
                clientList.add(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientList;
    }
    public ArrayList<Account> getAllAccounts() {
        ArrayList<Account> clientList = new ArrayList<>();
        try {
            Connection dbCon = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = dbCon.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM account");
            // ID(int)
//  1 | acct1
            //  2 | acct2

            while (result.next()) {
                Account account = new Account();
                account.setAccountNumber(result.getInt("A_ID"));
                account.setBalance(Long.parseLong(result.getString("A_BALANCE")));
                System.out.printf("Account retrieved. ID: %s, Balance: %s\n", account.getAccountNumber(),
                        account.getBalance());
                clientList.add(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientList;
    }
    public int createClient(Client client){
        int returnValue = 0;
        try {
            Connection dbCon = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "INSERT into client values (0, '" + client.getClientName() + "', '" + client.getClientSurname() + "', '" + client.getClientCity() + "')";
            PreparedStatement pstmt = dbCon.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.executeUpdate();
            ResultSet result = pstmt.getGeneratedKeys();
            if (result.next()) {
                returnValue = result.getInt(1);
                System.out.printf("Client created. ID: %s\n", returnValue);
            }
            } catch(Exception e){
                e.printStackTrace();
            }
        return returnValue;
    }
    public ArrayList<Account> getAccounts(int ClientID) {
        ArrayList<Account> clientList = new ArrayList<>();
        try {
            Connection dbCon = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = dbCon.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM account WHERE A_C_ID = " + ClientID);
            // ID(int)
//  1 | acct1
            //  2 | acct2

            while (result.next()) {
                Account account = new Account();
                account.setAccountNumber(result.getInt("A_ID"));
                account.setAccountName(result.getString("A_NAME"));
                account.setBalance(Long.parseLong(result.getString("A_BALANCE")));
                System.out.printf("Account retrieved. ID: %s, Balance: %s\n", account.getAccountNumber(),
                        account.getBalance());
                clientList.add(account);
            }  } catch (Exception e) {
            e.printStackTrace();
        }
        return clientList;
    }
}
