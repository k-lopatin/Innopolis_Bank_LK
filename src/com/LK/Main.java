package com.LK;

import java.util.Random;

import java.util.Date;

public class Main {

    static Customer[] customers = new Customer[3];

    public static void main(String[] args) {


        customers[0] = new Customer("Igor", "Kustov", new Date(1990, 1, 10), true, "0010 456444", 5001);
        customers[1] = new Customer("Alex", "Mercel", new Date(1987, 3, 20), true, "0012 489944", 1001111111);
        customers[2] = new Customer("Dima", "Boston", new Date(1979, 4, 30), true, "0015 785468", 5000000);

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int randUser = random.nextInt(3);
            Customer currUser = customers[randUser];
            doRandomOperation(currUser);
        }
        for(int i=0; i<3; i++){
            System.out.println("LOG OF " + i + " USER");
            customers[i].printLog();
        }
        // write your code here
    }

    public static void doRandomOperation(Customer user) {
        Random random = new Random();
        int randOperation = random.nextInt(4);
        switch (randOperation) {
            case 0: //withdraw
                int sum = random.nextInt(2000);
                user.withdraw(sum);
                break;
            case 1: //deposit
                sum = random.nextInt(2000);
                user.deposit(sum);
                break;
            case 2: //pay interests
                user.payInterests();
                break;
            case 3: //transfer
                Customer nextUser;
                do {
                    int randUser = random.nextInt(3);
                    nextUser = customers[randUser];
                } while (!user.equals(nextUser));
                sum = random.nextInt(2000);
                user.transfer(nextUser, sum);
        }
        // write your code here
    }
}
