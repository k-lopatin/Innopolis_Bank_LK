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
     *
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
    public boolean withdraw(double sum) {

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
     * @param receiver who will get money
     * @param sum
     */
    @Override
    public void transfer(Customer receiver, double sum) {
        if(sum > 0){
            if( this.withdraw(sum) ){
                receiver.deposit(sum);
            }
        } else {
            logMessage("Sum is not good");
        }
    }

    /**
     * write message to the log
     *
     * @param message
     */
    @Override
    public void logMessage(String message) {
        if(message.isEmpty()) return; //check if message is empty

        int cur = log.length + 1;
        log[cur] = message;
    }

    /**
     * prints the entire log
     */
    public void printLog() {
        for (String s : log){
            System.out.println(s);
        }
    }

    /**
     * prints current balance
     */
    public void printBalance() {
        System.out.printf("На вашем счету %.2f\n", balance);
    }
}
