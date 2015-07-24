package com.LK;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by lk1195 on 23.07.15.
 */
public class Bank {

    ArrayList<Branch> branches;

    public Bank() {
        branches = new ArrayList<>();
    }

    /**
     * creates new empty branch
     */
    public void createBranch() {
        Branch br = new Branch();
        branches.add(br);
    }

    /**
     * prints branches of the bank
     */
    public void printBranches() {
        for (Branch br : branches) {
            int id = br.getId();
            System.out.println("branch " + id);

        }
    }

    /**
     * gets branch by its id
     * @param id
     * @return
     */
    public Branch getBranch(int id) {
        id--;
        if (id >= 0 && id < branches.size()) {
            return branches.get(id);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * adds a customer to branch with branchId
     * @param customer
     * @param branchId
     */
    public void addCustomerToBranch(Customer customer, int branchId) {
        branchId--;
        Branch currBranch = branches.get(branchId);
        currBranch.addCustomer(customer);
    }

    /**
     * gets branch that user is in
     * @param userId
     * @return
     */
    public Branch getBranchByUser(int userId) {
        for (Branch curr : branches) {
            if (curr.isUserInBranch(userId)) {
                return curr;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * prints all transaction in bank
     */
    public void printAllTransactions() {
        for (Branch br : branches) {
            System.out.println("Branch " + br.getId() + ":");
            br.printAllTransactions();
            System.out.println();
        }
    }
}
