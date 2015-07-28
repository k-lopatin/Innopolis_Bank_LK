package com.LK;

import java.text.DecimalFormat;

/**
 * Created by lk1195 on 23.07.15.
 */
public class Transaction {

    final int DEPOSIT_TRANSACTION = 0;
    final int WITHDRAW_TRANSACTION = 1;
    final int TRANSFER_TRANSACTION = 2;
    final int INTERESTS_TRANSACTION = 3;
    final int RECEIVE_TRANSACTION = 4;
    final int ERROR_TRANSACTION = -1;

    private int id;
    private int branchId;
    private int customerId;
    private int receiverId;
    private int type;
    private double balance;
    private double sum;

    private String message;

    /**
     * Constructor for transaction with one user
     * @param id
     * @param branchId
     * @param customerId
     * @param type
     * @param sum
     * @param balance
     */
    public Transaction(int id, int branchId, int customerId, int type, double sum, double balance) {
        this.id = id;
        this.branchId = branchId;
        this.customerId = customerId;
        this.type = type;
        this.sum = sum;
        this.balance = balance;
    }

    /**
     * Constructor for transaction with two users (i.e transfer)
     * @param id
     * @param branchId
     * @param customerId
     * @param receiverId
     * @param type
     * @param sum
     * @param balance
     */
    public Transaction(int id, int branchId, int customerId, int receiverId, int type, double sum, double balance) {
        this.id = id;
        this.branchId = branchId;
        this.customerId = customerId;
        this.type = type;
        this.receiverId = receiverId;
        this.sum = sum;
        this.balance = balance;
    }

    public int getCustomerId() {
        return customerId;
    }


    /**
     * implements to string method
     * @return
     */
    @Override
    public String toString(){
        String str = "Transaction #" + id + "\n";

        DecimalFormat df = new DecimalFormat("#.##");
        String sumStr = df.format(sum);
        String balanceStr = df.format(balance);


        switch(type){
            case DEPOSIT_TRANSACTION:
                str += "user " + customerId + " deposit " + sumStr;
                break;
            case WITHDRAW_TRANSACTION:
                str += "user " + customerId + " withdraw " + sumStr;
                break;
            case INTERESTS_TRANSACTION:
                str += "user " + customerId + " gets interests " + sumStr;
                break;
            case TRANSFER_TRANSACTION:
                str += "user " + customerId + " transfer to user " + receiverId + " sum " + sumStr;
                break;
            case RECEIVE_TRANSACTION:
                str += "user " + customerId + " recieves from user " + receiverId + " sum " + sumStr;
                break;
            case ERROR_TRANSACTION:
                str += "error in transaction from user " + customerId + " with sum " + sumStr;
        }
        str += "\n Balance is " + balanceStr;

        return str;
    }
}
