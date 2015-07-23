package com.LK;

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
    private double sum;

    private String message;

    public Transaction(int id, int branchId, int customerId, int type, double sum) {
        this.id = id;
        this.branchId = branchId;
        this.customerId = customerId;
        this.type = type;
        this.sum = sum;
    }

    public Transaction(int id, int branchId, int customerId, int receiverId, int type, double sum) {
        this.id = id;
        this.branchId = branchId;
        this.customerId = customerId;
        this.type = type;
        this.receiverId = receiverId;
        this.sum = sum;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getMessage() {
        return message;
    }

    public int getId() {

        return id;
    }

    public int getBranchId() {
        return branchId;
    }

    public String toString(){
        String str = "Transaction #" + id + "\n";
        switch(type){
            case DEPOSIT_TRANSACTION:
                str += "user " + customerId + " deposit " + sum;
                break;
            case WITHDRAW_TRANSACTION:
                str += "user " + customerId + " withdraw " + sum;
                break;
            case INTERESTS_TRANSACTION:
                str += "user " + customerId + " gets interests " + sum;
                break;
            case TRANSFER_TRANSACTION:
                str += "user " + customerId + " transfer to user " + receiverId + " sum " + sum;
                break;
            case RECEIVE_TRANSACTION:
                str += "user " + receiverId + " recieves from user " + customerId + " sum " + sum;
                break;
            case ERROR_TRANSACTION:
                str += "error in transaction from user " + customerId + " with sum " + sum;
        }

        return str;
    }
}
