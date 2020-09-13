package com.ders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        boolean fullext = true;
        boolean iniext = true; //welcome
        boolean accext = true;
        Client client = new Client();
        Account hesap = new Account();

        while(fullext) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            // scanner ^
            while (iniext) {
                try {
                    System.out.println("What would you like to do?\n1.Login\n2.Exit");
                    String job = reader.readLine();
                    if (job.equals("1")){
                        System.out.println("Please enter your username.");
                        String userName = reader.readLine();
                        if (userName.equalsIgnoreCase("")) {
                            System.out.println("Invalid username: " + userName);
                        } else {
                            if(true){/*tanımlı değilse*/
                                client = new Client();
                                //hesap = new Account();
                                System.out.println("Account not found, creating one.\nAccount number: " + hesap.getAccountNumber() + "\nBalance: " + hesap.getBalance());
                            }
                        /* else {// tanım ilse

                        } */
                            System.out.println("Welcome " + userName + "!");
                            iniext = false;
                        }
                    }
                    else if (job.equals("2")){
                        fullext = false;
                        iniext = false;
                        accext = false;
                    }
                    else{
                        System.out.println("Invalid selection.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Kullanıcı giriş yaptı, işlemler yapacak
            // Account list
            // Deposit - 1
            // Withdraw - 2
            // exit - 3

            while (accext){
                    try {
                    System.out.println("What would you like to do? Enter the number in front of the action you'd like to do.\n1.Deposit\n2.Withdraw\n3.Logout");
                    String job = reader.readLine();

                    ArrayList<Account> clientAccounts = client.getAccounts();
                    if(clientAccounts.isEmpty()){
                        // create new account
                            
                    }

                    switch (job) {
                        case "1" -> {
                            System.out.println("How much would you like to deposit?");
                            String amount = reader.readLine();
                            try {
                                int intAmount = Integer.parseInt(amount);
                                hesap.addBalance(intAmount);
                                System.out.println(amount + "$ deposited.");
                            } catch (Exception e) {
                                System.out.println("Invalid number.");
                            } finally {
                                System.out.println("Your current balance is: " + hesap.getBalance() + "$");
                            }
                        }
                        case "2" -> {
                            System.out.println("How much would you like to withdraw?");
                            String amount = reader.readLine();
                            System.out.println(amount + "$ withdrawn.");
                            try {
                                int intAmount = Integer.parseInt(amount);
                                hesap.deductBalance(intAmount);
                                System.out.println(amount + "$ withdrawn.");
                            } catch (Exception e) {
                                System.out.println("Invalid number.");
                            } finally {
                                System.out.println("Your current balance is: " + hesap.getBalance() + "$");
                            }
                        }
                        case "3" -> {
                            System.out.println("Bye!");
                            accext = false;
                            iniext = true;
                        }



                        default -> System.out.println("Invalid selection.");
                    }

                    } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
