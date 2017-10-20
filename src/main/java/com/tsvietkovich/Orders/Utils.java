package com.tsvietkovich.Orders;

import java.util.Scanner;

public class Utils {

    public static String readNameClient(Scanner scanner){
        System.out.println("Enter your name:");
        return scanner.nextLine();
    }
    public static String readTelephone(Scanner scanner){
        System.out.println("Enter telephone:");
        return scanner.nextLine();
    }
    public static String readNameProduct(Scanner scanner){
        System.out.println("Enter name of product:");
        return scanner.nextLine();
    }
    public static Double readPriceProduct(Scanner scanner){
        System.out.println("Enter price of one item:");
        return Double.parseDouble(scanner.nextLine());
    }
    public static Integer readCountProduct(Scanner scanner){
        System.out.println("Enter count item:");
        return Integer.parseInt(scanner.nextLine());
    }
    public static Double getPriceByItems(Integer item,Double price){
        return item*price;
    }

}
