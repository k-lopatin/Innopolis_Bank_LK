package com.LK;

import java.util.Date;

/**
 * Created by lk1195 on 22.07.15.
 */

public class Customer implements Transactions {
    private final int SAVING_TYPE = 1;
    private final int BUSINESS_TYPE = 2;

    private static int currentId;
    private String name;
    private String surname;
    private Date birthDate;

    private int logCounter;

    private int id;

    /**
     * male - is true
     * female - false
     */
    private boolean sexMale;

    private String document;
    private int accountType;
    private double balance;
    private String[] log;

    /**
     * standard constructor for required field
     *
     * @param name
     * @param surname
     * @param birthDate
     * @param sexMale
     * @param document
     * @param balance
     */
    public Customer(String name, String surname, Date birthDate, boolean sexMale, String document, double balance) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.sexMale = sexMale;
        this.document = document;
        this.log = new String[100000];
        this.getAccountType();
        this.logCounter = 0;
    }

    private void getAccountType() {
        if (this.balance >= 5000000) {
            if (this.accountType != BUSINESS_TYPE) {
                logMessage("set account type to business");
                this.accountType = BUSINESS_TYPE;
            }
        } else {
            if (this.accountType != SAVING_TYPE) {
                logMessage("set account type to saving");
                this.accountType = SAVING_TYPE;
            }
        }
    }


    /**
     * get money from account
     *
     * @param sum
     */
    @Override
    public boolean withdraw(double sum) {
        if (sum > 0) {
            if (sum > this.balance) {
                logMessage("the sum on account is less than sum you want to withdraw");
                return false;
            }
            this.balance -= sum;

            logMessage("you withdraw " + sum);
            logBalance();
            return false;
        } else {
            logMessage("the sum is not correct");
            return false;
        }
    }

    /**
     * put money to the account
     *
     * @param sum
     */
    @Override
    public void deposit(double sum) {
        if (sum > 0) {
            this.balance += sum;
            logMessage("your deposit " + sum);
            this.logBalance();

        } else {
            logMessage("the sum is not correct");
        }
    }

    /**
     * pays interests to the person account
     */
    @Override
    public void payInterests() {
        switch (this.accountType) {
            case BUSINESS_TYPE:
                this.balance *= 1.01;
                break;
            case SAVING_TYPE:
                this.balance *= 1.05;
                break;
            default:
                logMessage("not defined account type");
        }
    }

    /**
     * Transfers money from your account, to the reciever account
     *
     * @param receiver who will get money
     * @param sum
     */
    @Override
    public void transfer(Customer receiver, double sum) {
        if (sum > 0) {
            if (this.withdraw(sum)) {
                receiver.deposit(sum);
            } else {
                logMessage("not enough money to transfer");
            }
            logMessage("you transfer " + sum);
        } else {
            logMessage("sum is not good");
        }
    }

    /**
     * write message to the log
     *
     * @param message
     */
    @Override
    public void logMessage(String message) {
        if (message.isEmpty()) return; //check if message is empty

        log[logCounter] = message;
        logCounter++;
    }

    /**
     * prints the entire log
     */
    public void printLog() {
        for (int i = 0; i < logCounter; i++) {
            System.out.println(log[i]);
        }
    }

    /**
     * prints current balance
     */
    public void logBalance() {
        logMessage("your current balance is " + balance);
    }
}
