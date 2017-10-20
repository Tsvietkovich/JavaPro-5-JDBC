package com.tsvietkovich.Orders;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static OrderManager orderManager = new OrderManager();
    private static String nameClient;
    private static String nameProduct;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try{
            orderManager.startActions();
            nameClient = Utils.readNameClient(scanner);
            String telephone = Utils.readTelephone(scanner);
            orderManager.addToClients(nameClient,telephone);
            String option = new String();
            while (!("done").equals(option.trim())) {
                System.out.println("Select option:");
                System.out.println("(Add) - Add product in grocery basket");
                System.out.println("(View) - View all orders");
                option = scanner.nextLine();
                switch (option) {
                    case "Add":
                        nameProduct = Utils.readNameProduct(scanner);
                        Double price = Utils.readPriceProduct(scanner);
                        Integer count = Utils.readCountProduct(scanner);
                        orderManager.addToProducts(nameProduct,price,count);
                        orderManager.makeOrder(nameClient,nameProduct);
                        break;
                    case "View":
                        orderManager.veiwOrder();
                        break;
                    default:
                        return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            orderManager.endActions();
        }

    }
}
