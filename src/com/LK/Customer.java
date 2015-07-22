package com.LK;

import java.util.Date;

/**
 * Created by lk1195 on 22.07.15.
 */

public class Customer implements Transactions {
    private int id;
    private String name;
    private String surname;
    private Date birthDate;

    /**
     * male - is true
     * female - false
     */
    private boolean sexMale;

    private String document;
    private int accountType;
    private String[] log;

    /**
     * standard constructor for required fields
     * @param id
     * @param name
     * @param surname
     * @param birthDate
     * @param sexMale
     * @param document
     * @param accountType
     */
    public Customer(int id, String name, String surname, Date birthDate, boolean sexMale, String document, int accountType) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.sexMale = sexMale;
        this.document = document;
        this.accountType = accountType;
    }

    /**
     * get money from account
     *
     * @param sum
     */
    @Override
    public void withdraw(double sum) {

    }

    /**
     * put money to the account
     *
     * @param sum
     */
    @Override
    public void deposit(double sum) {

    }

    /**
     * pays interests to the person account
     */
    @Override
    public void payInterests() {

    }

    /**
     * Transfers money from your account, to the reciever account
     *
     * @param reciever who will get money
     * @param sum
     */
    @Override
    public void transfer(Customer reciever, double sum) {

    }

    /**
     * write message to the log
     *
     * @param message
     */
    @Override
    public void log(String message) {

    }

    /**
     * prints the entire log
     */
    public void printLog(){

    }

    /**
     * prints current balance
     */
    public void printBalance(){

    }
}
