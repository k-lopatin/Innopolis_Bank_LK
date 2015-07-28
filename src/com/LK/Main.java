package com.LK;

import java.util.Random;

import java.util.Date;
import java.util.Scanner;

public class Main {
    private static Bank bank;
    static Customer[] customers = new Customer[3];

    final static int INFINITY = 999999999;

    public static void main(String[] args) {


        bank = new Bank();
        bank.createBranch();
        bank.createBranch();

        customers[0] = new Customer("Igor", "Kustov", new Date(1990, 1, 10), true, "0010 456444", 5001);
        customers[1] = new Customer("Alex", "Mercel", new Date(1987, 3, 20), true, "0012 489944", 1001111111);
        customers[2] = new Customer("Dima", "Boston", new Date(1979, 4, 30), true, "0015 785468", 5000000);


        bank.addCustomerToBranch(customers[0], 1);
        bank.addCustomerToBranch(customers[1], 2);
        bank.addCustomerToBranch(customers[2], 2);

        menu();

    }

    public static void menu() {
        while (true) {
            System.out.println();
            System.out.println("Choose the branch. 0 - show all transactions. -1 - exit");
            bank.printBranches();
            Scanner sc = new Scanner(System.in);
            int branchId = sc.nextInt();
            if (branchId == -1) {
                return;
            }
            if (branchId == 0) {
                bank.printAllTransactions();
                continue;
            }

            try {

                chooseACustomer(branchId);

            } catch (IllegalArgumentException e) {
                System.out.println("Enter correct branch id");
            }
        }


    }

    /**
     * @param branchId
     */
    public static void chooseACustomer(int branchId) {
        Branch branch = bank.getBranch(branchId);
        System.out.println("Choose a customer. If 0 - show all transactions");
        branch.printCustomers();

        Scanner sc = new Scanner(System.in);
        int customerId = sc.nextInt();

        if (customerId == 0) {
            branch.printAllTransactions();
            return;
        }

        System.out.println("Choose an operation");
        System.out.println("0 - exit");
        System.out.println("1 - deposit");
        System.out.println("2 - withdraw");
        System.out.println("3 - pay interests");
        System.out.println("4 - transfer");
        System.out.println("5 - show transactions");

        int operation = sc.nextInt();
        int sum = 0;
        try {

            switch (operation) {
                case 1:
                    sum = getSum();
                    branch.deposit(customerId, sum);
                    break;
                case 2:
                    sum = getSum();
                    branch.withdraw(customerId, sum);
                    break;
                case 3:
                    branch.payInterests(customerId);
                    break;
                case 4:
                    transfer(branch, branchId, customerId);
                    break;
                case 5:
                    branch.printTransactionsOfCustomer(customerId);
                    break;

            }
        } catch (NullPointerException e){
            System.out.println("ERROR! Check your data");
        }

        System.out.println("Enter user to print transaction (if 0 - all transactions of the bank)");

        int user = getUser();
        if (user == 0) {
            branch.printAllTransactions();
        } else {
            branch.printTransactionsOfCustomer(user);
        }


    }

    /**
     * do the transfer, catch errors
     *
     * @param branch
     * @param branchId
     * @param customerId
     */
    private static void transfer(Branch branch, int branchId, int customerId) {
        try {
            int receiver = getUser();
            int sum = getSum();
            Branch receiverBranch = bank.getBranchByUser(receiver);

            if (receiverBranch.getId() == branchId) {
                branch.transferInBranch(customerId, receiver, sum);
            } else {
                branch.transferToAnotherBranch(customerId, receiverBranch, receiver, sum);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error in data");
        }
    }

    /**
     * scan sum to operate
     *
     * @return
     */
    private static int getSum() {
        System.out.println("Enter sum:");
        Scanner sc = new Scanner(System.in);
        int sum = sc.nextInt();
        if (sum > 0 && sum < INFINITY) {
            return sum;
        } else {
            return 0;
        }
    }

    /**
     * scan user id
     *
     * @return
     */
    private static int getUser() {
        System.out.println("Enter id of user:");
        Scanner sc = new Scanner(System.in);
        int user = sc.nextInt();
        if (user > 0 && user < INFINITY) {
            return user;
        } else {
            return 0;
        }
    }

    /**
     * this function is not used
     *
     * @param branch
     * @param user
     */
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
