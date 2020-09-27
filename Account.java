

package com.company;

public class Account {
    private int accountNumber = 1;
    private long balance = 0L;

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

    public void addBalance(int deposit) {

        this.balance += deposit;
    }

    public void deductBalance(int withdraw) {

        this.balance -= withdraw;
    }
}
