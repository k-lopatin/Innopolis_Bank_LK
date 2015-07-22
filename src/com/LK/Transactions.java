package com.LK;
/**

 */
public interface Transactions {

    /**
     * get money from account
     * @param sum
     */
    void withdraw(double sum);

    /**
     * put money to the account
     * @param sum
     */
    void deposit(double sum);

    /**
     * add percents of balance
     */
    void payInterests();

    /**
     *
     * @param reciever who will get money
     * @param sum
     */
    void transfer(Customer reciever, double sum);

    /**
     * write message to the log
     * @param message
     */
    void log(String message);

}