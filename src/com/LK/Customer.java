package com.LK;

/**
 * Created by lk1195 on 22.07.15.
 */

public class Customer implements Transactions {
    private String name;

    public void Customer(String name) {
        this.name = name;
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
}
