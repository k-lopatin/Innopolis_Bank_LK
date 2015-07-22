package com.LK;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Customer[] customers = new Customer[3];
        customers[0] = new Customer("Igor", "Kustov", new Date(1990, 1, 10), true, "0010 456444", 5000);
        customers[1] = new Customer("Alex", "Mercel", new Date(1987, 3, 20), true, "0012 489944", 10000000);
        customers[2] = new Customer("Dima", "Boston", new Date(1979, 4, 30), true, "0015 785468", 5000000);
	// write your code here
    }
}
