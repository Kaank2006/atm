package com.ders;

import java.io.*;
import java.text.NumberFormat;
import java.util.ArrayList;

public class App {
    public App() {
    }

    public static void main(String[] args) {
        boolean fullext = true;
        boolean iniext = true;
        boolean accext = true;
        Client clientObj = new Client();
        Account hesap = new Account();



        while(fullext) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String job;
            while(iniext) {
                try {
                    System.out.println("What would you like to do?\n1.Login\n2.Exit");
                    job = reader.readLine();
                    if (job.equals("1")) {
                        System.out.println("Please enter your username.");
                        String userName = reader.readLine();
                        if (userName.equalsIgnoreCase("")) {
                            System.out.println("Invalid username: " + userName);
                        }
                        //  If client exists
                        else if(checkClientExist(userName) != 0){
                            System.out.println("Client found");
                        }
                        else {
                            System.out.println("Client not found, creating one...");
                            clientObj = new Client();
                            clientObj.setClientNumber(1);
                            clientObj.setClientName(userName);
                            try{
                                OutputStreamWriter writer = new OutputStreamWriter(
                                        new FileOutputStream("clients.csv"), "UTF-8");
                                BufferedWriter bufWriter = new BufferedWriter(writer);
                                // client
                                // id,name,accounts(dash - separated)
                                bufWriter.write(String.format("%s,%s,%s\n",1,userName,1));
                                bufWriter.close();
                            }
                            catch (Exception e){
                                e.printStackTrace();
                            }
                            System.out.println("Client created!" +
                                    "\nClient number: " + clientObj.getClientNumber() +
                                    "\nClient name: " + clientObj.getClientName());
                            System.out.println("Welcome " + userName + "!");
                            iniext = false;
                        }
                    } else if (job.equals("2")) {
                        fullext = false;
                        iniext = false;
                        accext = false;
                    } else {
                        System.out.println("Invalid selection.");
                    }
                } catch (IOException var25) {
                    var25.printStackTrace();
                }
            }

            while(accext) {
                try {
                    ArrayList<Account> clientAccounts =  clientObj.getAccounts();
                    if(clientAccounts.size() == 0){
                        int newAccId = clientObj.addAccount();
                        System.out.println("Created account for you!. Account ID: " + newAccId);
                    }
                    System.out.println("Please see your accounts:");
                    for(Account currentAcc : clientAccounts){
                        System.out.println("Account ID: " + currentAcc.getAccountNumber() + "\nBalance: " + NumberFormat.getCurrencyInstance().format(currentAcc.getBalance()));
                    }
                    System.out.println("What would you like to do? \n1.Add Account\n2.Select Account\n3.Logout");
                    job = reader.readLine();
                    String amount;
                    int intAmount;
                    switch(job) {
                        case "1" -> {
                            int newAccId = clientObj.addAccount();
                            System.out.println("Created account!. Account ID: " + newAccId);
                        }
                        case "2" -> {
                            boolean accountMenu = true;
                            while(accountMenu){
                                boolean found = false;
                                System.out.println("Please see your accounts:");
                                for(Account currentAcc : clientAccounts){
                                    System.out.println("Account ID: " + currentAcc.getAccountNumber() + "\nBalance: " + NumberFormat.getCurrencyInstance().format(currentAcc.getBalance()));
                                }
                                System.out.println("Enter your Account ID or press 0 to go back.");
                                String selectedAcc = reader.readLine();
                                if(Integer.parseInt(selectedAcc) == 0){
                                    accountMenu = false;
                                    break;
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
                                                case "3" -> {   //  GO BACK
                                                    accountActionMenu = false;
                                                    //accountMenu = false;
                                                }
                                            }
                                        }
                                    }
                                }
                                catch (Exception ex){

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

    public static int checkClientExist(String clientName){
        int result = 0;
        try{
            FileReader fileReader = new FileReader("clients.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String fullData = "";
            String line;
            System.out.println("Reading clients");
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                fullData += line;
                String[] clientDetails = line.split(",");
                System.out.println("client id:" + clientDetails[0]);
                System.out.println("client name:" + clientDetails[1]);
                System.out.println("client accounts:" + clientDetails[2]);

                if(clientName.equals(clientDetails[1])){
                    result = Integer.parseInt(clientDetails[0]);
                }


            }
            bufferedReader.close();

            // client add
            write.(fullData + "2,user2,2\n");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
