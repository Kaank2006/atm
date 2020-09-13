package com.ders;

public class Account {
    private int accountNumber = 1;
    private int balance = 0;

    public int getAccountNumber(){
        return accountNumber;
    }

    /*public void setAccountNumber(int newAccountNumber){
        accountNumber = newAccountNumber;
    }*/

    public int getBalance(){
        return balance;
    }

    public void setBalance(int newBalance){
        balance = newBalance;
    }
    public void addBalance(int deposit){
        balance = balance + deposit;
    }
    public void deductBalance(int withdraw){
        balance = balance - withdraw;
    }

}