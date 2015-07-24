package com.LK;

import java.util.*;

/**
 * Created by lk1195 on 23.07.15.
 */
public class Branch {

    final int DEPOSIT_TRANSACTION = 0;
    final int WITHDRAW_TRANSACTION = 1;
    final int TRANSFER_TRANSACTION = 2;
    final int INTERESTS_TRANSACTION = 3;
    final int RECEIVE_TRANSACTION = 4;
    final int ERROR_TRANSACTION = -1;

    private int id;
    private HashMap<Integer, Customer> customers;
    private static int currId = 1;
    private ArrayList<Transaction> transactions;
    public int transactionId;

    public int getId() {
        return id;
    }

    /**
     * creates new branch
     */
    public Branch() {
        id = currId;
        customers = new HashMap<>();
        currId++;

        transactions = new ArrayList<>();

        transactionId = 0;
    }

    /**
     * adds a customer to the branch (By customer object)
     *
     * @param newCustomer
     */
    public void addCustomer(Customer newCustomer) {
        int userId = newCustomer.getId();
        customers.put(userId, newCustomer);
        newCustomer.setBranchId(id);
    }

    /**
     * adds customer by his data:
     *
     * @param name
     * @param surname
     * @param birthDate
     * @param sexMale
     * @param document
     * @param balance
     */
    public void addCustomerByData(String name, String surname,
                                  Date birthDate, boolean sexMale,
                                  String document, double balance) {

        Customer newCustomer = new Customer(name, surname, birthDate, sexMale, document, balance);
        addCustomer(newCustomer);
    }

    /**
     * deposit money to the user
     *
     * @param userId
     * @param sum
     */
    public void deposit(int userId, double sum) {
        Customer currCustomer = customers.get(userId);
        currCustomer.deposit(sum);
        Transaction newTransaction = new Transaction(transactionId, id, currCustomer.getId(),
                DEPOSIT_TRANSACTION, sum, currCustomer.getBalance());
        transactions.add(newTransaction);
        transactionId++;
    }

    /**
     * withdraw money from account with userId
     *
     * @param userId
     * @param sum
     */
    public void withdraw(int userId, double sum) {

        Customer currCustomer = customers.get(userId);
        Transaction newTransaction;

        if (currCustomer.withdraw(sum)) {
            newTransaction = new Transaction(transactionId, id, currCustomer.getId(),
                    WITHDRAW_TRANSACTION, sum, currCustomer.getBalance());
        } else {
            newTransaction = new Transaction(transactionId, id, currCustomer.getId(),
                    ERROR_TRANSACTION, sum, currCustomer.getBalance());
        }

        transactions.add(newTransaction);
        transactionId++;
    }

    /**
     * pays interest to the account with userId
     *
     * @param userId
     */
    public void payInterests(int userId) {

        Customer currCustomer = customers.get(userId);
        double sum = currCustomer.payInterests();
        String message = "interests for user " + userId + ", the sum is " + sum;

        Transaction newTransaction = new Transaction(transactionId, id, currCustomer.getId(),
                INTERESTS_TRANSACTION, sum, currCustomer.getBalance());
        transactions.add(newTransaction);
        transactionId++;
    }

    /**
     * transfer money from user with userId to receiverId
     *
     * @param userId
     * @param receiverId
     */
    public void transferInBranch(int userId, int receiverId, double sum) {

        Customer currCustomer = customers.get(userId);
        Customer receiverCustomer = customers.get(receiverId);
        Transaction newTransaction, newTransaction2;

        if (currCustomer.transfer(receiverCustomer, sum)) {
            newTransaction = new Transaction(transactionId, id, currCustomer.getId(),
                    receiverCustomer.getId(), TRANSFER_TRANSACTION, sum, currCustomer.getBalance());
            transactionId++;
            newTransaction2 = new Transaction(transactionId, id, receiverCustomer.getId(),
                    currCustomer.getId(), RECEIVE_TRANSACTION, sum, currCustomer.getBalance());
            transactionId++;
        } else {
            newTransaction = new Transaction(transactionId, id, currCustomer.getId(),
                    currCustomer.getId(), ERROR_TRANSACTION, sum, currCustomer.getBalance());
            transactionId++;
            newTransaction2 = new Transaction(transactionId, id, currCustomer.getId(),
                    currCustomer.getId(), ERROR_TRANSACTION, sum, currCustomer.getBalance());
            transactionId++;

        }


        transactions.add(newTransaction);
        transactions.add(newTransaction2);
    }

    /**
     * transfer money to user in another branches
     *
     * @param userId
     * @param receiverBranch
     * @param receiverId
     * @param sum
     */
    public void transferToAnotherBranch(int userId, Branch receiverBranch, int receiverId, double sum) {
        Customer currCustomer = customers.get(userId);

        Customer receiverCustomer = receiverBranch.getCustomerById(receiverId);
        Transaction newTransaction;

        if (currCustomer.transfer(receiverCustomer, sum)) {
            newTransaction = new Transaction(transactionId, id, currCustomer.getId(),
                    receiverCustomer.getId(), TRANSFER_TRANSACTION, sum, currCustomer.getBalance());

            receiverBranch.receiveFromAnotherBranch(userId, receiverId, sum);
        } else {
            newTransaction = new Transaction(transactionId, id, currCustomer.getId(),
                    ERROR_TRANSACTION, sum, currCustomer.getBalance());
        }

        transactions.add(newTransaction);
        transactionId++;
    }

    /**
     * receives money from user in another branch
     * @param userId
     * @param receiverId
     * @param sum
     */
    public void receiveFromAnotherBranch(int userId, int receiverId, double sum) {
        Customer user = customers.get(receiverId);
        Transaction newTransaction = new Transaction(transactionId, id, receiverId,
                userId, RECEIVE_TRANSACTION, sum, user.getBalance());
        transactions.add(newTransaction);
    }

    /**
     * prints all existed transactions
     */
    public void printAllTransactions() {
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }


    /**
     * print transaction of chosen customer
     * @param userId
     */
    public void printTransactionsOfCustomer(int userId) {
        for (Transaction t : transactions) {
            if (t.getCustomerId() == userId) {
                System.out.println(t);
            }
        }
    }

    /**
     *
     * @param userId
     * @return customer of this branch by his id
     */
    public Customer getCustomerById(int userId) {
        return customers.get(userId);
    }

    /**
     * random customer
     * @todo it doesn't work
     * @return
     */
    public Customer getRandomCustomer() {
        Random rand = new Random();
        int custSize = Customer.getCurrentId();

        int randCust = 0;

        /*do {
            randCust = rand.nextInt(custSize);
        } while(customers.get(randCust) != null);*/

        return customers.get(randCust);

    }

    /**
     * prints all customers of current branch
     */
    public void printCustomers() {
        for (int id : customers.keySet()) {
            Customer curr = customers.get(id);
            System.out.println(id + "  " + curr.getName());
        }
    }

    /**
     * check if user is in current branch
     * @param user_id
     * @return boolean
     */
    public boolean isUserInBranch(int user_id){
        return customers.containsKey(user_id);
    }

}
