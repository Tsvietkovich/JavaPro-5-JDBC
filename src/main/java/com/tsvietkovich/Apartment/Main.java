package com.tsvietkovich.Apartment;

import com.tsvietkovich.DbProperties;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static ApartmentInformation info = new ApartmentInformation();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Apartment apartment = new Apartment();
        try {
            apartment.getDB();
            String option = new String();
            while (!("exit").equals(option.trim())) {
                System.out.println("Select option:");
                System.out.println("1.Select apartments by district:");
                System.out.println("2.Select apartments by address:");
                System.out.println("3.Select apartments by square:");
                System.out.println("4.Select apartments by rooms:");
                System.out.println("5.Select apartments by price:");
                option = scanner.nextLine();
                switch (option) {
                    case "1":
                        apartment.getApartmentByDistrict(byDistrict(scanner));
                        break;
                    case "2":
                        apartment.getApartmentByAddress(byAddress(scanner));
                        break;
                    case "3":
                        apartment.getApartmentBySquare(bySquare(scanner));
                        break;
                    case "4":
                        apartment.getApartmentByRooms(byRooms(scanner));
                        break;
                    case "5":
                        apartment.getApartmentByPrice(byPrice(scanner));
                        break;
                    default:
                        return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            apartment.close();
        }
    }

    public static String byDistrict(Scanner scanner) {
        String str = "";
            System.out.println("Enter preferred district:");
            String district = scanner.nextLine();
            if (!info.checkDistrict(district)) {
                System.out.println("The district not found");
            } else str = district;
        return str;
    }

    public static String byAddress(Scanner scanner) {
        String str = "";
            System.out.println("Enter preferred address:");
            String address = scanner.nextLine();
            if (!info.checkAddress(address)) {
                System.out.println("The address not found");
            } else str = address;
        return str;
    }

    public static Double bySquare(Scanner scanner) {
            System.out.println("Enter square:");
            String square = scanner.nextLine();
            return Double.parseDouble(square);
    }

    public static Integer byRooms(Scanner scanner) {
        Integer room = 1;
            System.out.println("Enter rooms:");
            String rooms = scanner.nextLine();
            if (!info.checkRooms(Integer.parseInt(rooms))) {
                System.out.println("Apartment with this quantity of rooms not found");
            } else room = Integer.parseInt(rooms);
        return room;
    }

    public static Double byPrice(Scanner scanner) {
        System.out.println("Enter price:");
        String price = scanner.nextLine();
        return Double.parseDouble(price);
    }
}
