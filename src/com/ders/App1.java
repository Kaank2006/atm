package com.ders;

import java.io.*;
import java.text.NumberFormat;
import java.util.ArrayList;

public class App1 {
    public App1() {
    }
    public static void main(String[] args) {
        boolean fullext = true;
        boolean iniext = true;
        boolean accext = true;
        boolean connectorLoop = true;
        Client clientObj = new Client();
        Account hesap = new Account();
        Connector connector = new Connector();


        while(fullext) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String job;
        while(iniext) {
            try {
                System.out.println("What would you like to do?\n1.Login\n2.Sign up\n3.Exit");
                job = reader.readLine();
                switch (job) {
                    case "1" -> {
                        System.out.println("Please enter your client ID.");
                        String ClientID = reader.readLine();
                        if ((ClientID.equalsIgnoreCase("") || (Integer.parseInt(ClientID) <= 0))) {
                            System.out.println("Invalid ID: " + ClientID);
                        } else {
                            clientObj = checkClientExist(Integer.parseInt(ClientID));
                            //  If client exists
                            if (clientObj.getClientNumber() != 0) {
                                System.out.println("Client found");
                                iniext = false;
                            } else {
                                boolean signup = true;
                                String answer;
                                while (signup) {
                                    System.out.println("Client not found, would you like to sign up?");
                                    answer = reader.readLine();
                                    if (answer.equals("yes")) {
                                        String name;
                                        String surname;
                                        String city;
                                        System.out.println("Please enter your name.");
                                        name = reader.readLine();
                                        System.out.println("Please enter your surname.");
                                        surname = reader.readLine();
                                        System.out.println("please enter the city you live.");
                                        city = reader.readLine();
                                        Client newClient = new Client(name, surname, city);
                                        int newClientID = connector.createClient(newClient);
                                        System.out.printf("Client created! welcome %s %s, Client ID:%s\n", name, surname, newClientID);
                                        newClient.setClientNumber(newClientID);
                                        connector.getAccounts(newClientID);
                                        clientObj = newClient;
                                        iniext = false;
                                        signup = false;
                                    } else if (!answer.equals("no")) {
                                        System.out.println("invalid answer");
                                    } else
                                        signup = false;
                                }
                            }
                        }
                    }
                    case "2" -> {
                        String name;
                        String surname;
                        String city;
                        System.out.println("Please enter your name.");
                        name = reader.readLine();
                        System.out.println("Please enter your surname.");
                        surname = reader.readLine();
                        System.out.println("please enter the city you live.");
                        city = reader.readLine();
                        Client newClient = new Client(name, surname, city);
                        int newClientID = connector.createClient(newClient);
                        System.out.printf("Client created! welcome %s %s, Client ID:%s\n", name, surname, newClientID);
                        newClient.setClientNumber(newClientID);
                        clientObj = newClient;
                        iniext = false;
                    }
                    case "3" -> {
                        System.out.println("Bye!");
                        fullext = false;
                        iniext = false;
                        accext = false;
                    }
                    default -> System.out.println("Invalid selection.");
                }
            } catch (IOException var25) {
                var25.printStackTrace();
            }
        }

        while(accext) {
            try {
                ArrayList<Account> clientAccounts =  clientObj.getAccounts();
                clientObj.setClientAccounts(clientAccounts);
                if(clientAccounts.size() == 0){
                    int newAccId = clientObj.addAccount();
                    Account newAccount = new Account();
                    System.out.println("Created account for you! Account name: New Account \nAccount ID: " + newAccId);
                    newAccount.setAccountName("New Account");
                }

                System.out.println("Please see your accounts:");
                for(Account currentAcc : clientAccounts){
                    System.out.printf("Account name:%s \nAccount ID:%s \nBalance:%s \n", currentAcc.getAccountName(),
                            currentAcc.getAccountNumber(),
                            NumberFormat.getCurrencyInstance().format(currentAcc.getBalance()));
                }
                System.out.println("What would you like to do? \n1.Add Account\n2.Select Account\n3.Logout");
                job = reader.readLine();
                String amount;
                int intAmount;
                switch(job) {
                    case "1" -> {
                        int newAccId = clientObj.addAccount();
                        System.out.println("Created account! Account ID: " + newAccId);
                    }
                    case "2" -> {
                        boolean accountMenu = true;
                        while(accountMenu){
                            boolean found = false;
                            System.out.println("Please see your accounts:");
                            for(Account currentAcc : clientAccounts){
                                System.out.println("Account ID: " + currentAcc.getAccountNumber() + "\nBalance: " +
                                        NumberFormat.getCurrencyInstance().format(currentAcc.getBalance()));
                            }
                            System.out.println("Enter your Account ID or press 0 to go back.");
                            String selectedAcc = reader.readLine();
                            if(Integer.parseInt(selectedAcc) == 0){
                                accountMenu = false;
                            }
                            try{
                                for(Account currentAcc : clientAccounts){
                                    if(currentAcc.getAccountNumber() == Integer.parseInt(selectedAcc)){
                                        found = true;
                                        hesap = currentAcc;
                                    }
                                }
                                if(found){
                                    System.out.println("Account found! Account ID: " + hesap.getAccountNumber());
                                    boolean accountActionMenu = true;
                                    while(accountActionMenu){
                                        System.out.println("Please select: \n1.Deposit\n2.Withdraw\n3.Go Back");
                                        String accountJob = reader.readLine();
                                        switch(accountJob) {
                                            case "1" -> {   //  DEPOSIT
                                                System.out.println("How much would you like to deposit?");
                                                amount = reader.readLine();

                                                try {
                                                    intAmount = Integer.parseInt(amount);
                                                    if(intAmount > 0){
                                                        hesap.addBalance(intAmount);
                                                        String result = NumberFormat.getCurrencyInstance().format(intAmount);
                                                        System.out.println( result + " deposited.");
                                                    }
                                                } catch (Exception var28) {
                                                    System.out.println("Invalid number.");
                                                } finally {
                                                    String result = NumberFormat.getCurrencyInstance().format(hesap.getBalance());
                                                    System.out.println("Your current balance is: " + result);
                                                }
                                            }
                                            case "2" -> {   //  WITHDRAW
                                                System.out.println("How much would you like to withdraw?");
                                                amount = reader.readLine();
                                                intAmount = Integer.parseInt(amount);
                                                if(intAmount > hesap.getBalance()){
                                                    System.out.println("you don't have that much money");
                                                } else {
                                                    try {
                                                        intAmount = Integer.parseInt(amount);
                                                        hesap.deductBalance(intAmount);
                                                        String result = NumberFormat.getCurrencyInstance().format(intAmount);
                                                        System.out.println(result + " withdrawn.");
                                                    } catch (Exception ex) {
                                                        System.out.println("Invalid number.");
                                                    }
                                                }
                                                String result = NumberFormat.getCurrencyInstance().format(hesap.getBalance());
                                                System.out.println("Your current balance is: " + result);
                                            }
                                            case "3" ->   //  GO BACK
                                                accountActionMenu = false;
                                                //accountMenu = false;
                                        }
                                    }
                                }
                            }
                            catch (Exception ex){
                                ex.printStackTrace();
                            }
                        }
                    }

                    case "3" -> {
                        System.out.println("Bye!");
                        accext = false;
                        iniext = true;
                    }
                    default -> System.out.println("Invalid selection.");
                }
            } catch (Exception  ex) {
                ex.printStackTrace();
            }
        }
    }

    }

    public static Client checkClientExist(int clientID){
        Connector connector = new Connector();
        Client returnID = new Client();
        ArrayList<Client>clientList = connector.getClients();
        for(Client currentClient: clientList){
            if (currentClient.getClientNumber() == (clientID)){
                returnID = currentClient;
                System.out.printf("Client retrieved. ID: %s, Name: %s\n", currentClient.getClientNumber(),
                        currentClient.getClientName());
            }
        }
        return returnID;
    }
}
