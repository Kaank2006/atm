package com.ders;

public class Account {
    private int accountNumber = 1;
    private long balance = 0L;
    private String accountName;

    public Account() {
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(int accountNumber){
        this.accountNumber = accountNumber;
    }

    public long getBalance() {
        return this.balance;
    }
    public String getAccountName(){
        return this.accountName;
    }
    public void addBalance(int deposit) {

        this.balance += deposit;
    }

    public void deductBalance(int withdraw) {

        this.balance -= withdraw;
    }
    public void setBalance(long balance){
        this.balance = balance;
    }
    public void setAccountName(String accountName){
        this.accountName = accountName;
    }

}

