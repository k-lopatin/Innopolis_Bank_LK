package com.LK;

import java.util.Random;

import java.util.Date;

public class Main {

    static Customer[] customers = new Customer[3];
    static Branch[] branches = new Branch[2];

    public static void main(String[] args) {


        customers[0] = new Customer("Igor", "Kustov", new Date(1990, 1, 10), true, "0010 456444", 5001);
        customers[1] = new Customer("Alex", "Mercel", new Date(1987, 3, 20), true, "0012 489944", 1001111111);
        customers[2] = new Customer("Dima", "Boston", new Date(1979, 4, 30), true, "0015 785468", 5000000);



        branches[0] = new Branch();
        branches[0].addCustomer(customers[0]);
        branches[1] = new Branch();
        branches[1].addCustomer(customers[1]);
        branches[1].addCustomer(customers[2]);


        branches[1].printCustomers();
        /*Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int randBranch = random.nextInt(2);
            Branch currBranch = branches[randBranch];
            Customer currUser = currBranch.getRandomCustomer();
            doRandomOperation(currBranch, currUser);
        }
        branches[0].printAllTransactions();
        branches[1].printAllTransactions();
       /* for(int i=0; i<3; i++){
            System.out.println("LOG OF " + i + " USER");
            customers[i].printLog();
        }*/
    }

    public static void doRandomOperation(Branch branch, Customer user) {
        Random random = new Random();
        int randOperation = random.nextInt(4);
        int sum = 0;
        switch (randOperation) {
            case 0: //withdraw
                sum = random.nextInt(2000);
                branch.withdraw(user.getId(), sum);
                break;
            case 1: //deposit
                sum = random.nextInt(2000);
                branch.deposit(user.getId(), sum);
                break;
            case 2: //pay interests
                user.payInterests();
                break;
            case 3: //transfer
                Customer nextUser;
                do {
                    nextUser = branch.getRandomCustomer();
                } while (!user.equals(nextUser));
                sum = random.nextInt(2000);
                branch.transferInBranch(user.getId(), nextUser.getId(), sum);
        }
    }
}
