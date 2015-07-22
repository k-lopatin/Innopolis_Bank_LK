package com.LK;

/**
 * Created by lk1195 on 22.07.15.
 */

public class Customer implements Transactions {
    private String name;

    public void Customer(String name) {
        this.name = name;
    }

    @Override
    public void withdraw(double sum) {

    }

    @Override
    public void deposit(double sum) {

    }

    @Override
    public void payInterests() {

    }

    @Override
    public void transfer(Customer reciever, double sum) {

    }
}
