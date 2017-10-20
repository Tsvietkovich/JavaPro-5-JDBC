package com.tsvietkovich.Apartment;

import java.util.ArrayList;
import java.util.List;

public class ApartmentInformation {

    private List<String> districts;
    private List<Double> squares;
    private List<Double> prices;
    private List<String> address;

    public ApartmentInformation() {
        districts = getListOfDistricts();
        squares = getListOfSquares();
        prices = getListOfPrices();
        address = getListOfAddress();
    }

    private static List<String> getListOfDistricts (){
        List<String> district = new ArrayList<>();
        district.add("Darnytskyi");
        district.add("Obolonsky");
        district.add("Solomensky");
        district.add("Shevchenkovskiy");
        district.add("Svyatoshinsky");
        return district;
    }

    private static List<Double> getListOfSquares(){
        List<Double> squares = new ArrayList<>();
        squares.add(30.0);
        squares.add(45.0);
        squares.add(65.0);
        squares.add(75.0);
        return squares;
    }

    private static List<Double> getListOfPrices(){
        List<Double> prices = new ArrayList<>();
        prices.add(75.0);
        prices.add(115.0);
        prices.add(160.0);
        prices.add(200.0);
        return prices;
    }
    private static List<String> getListOfAddress(){
        List<String> address = new ArrayList<>();
        address.add("Ahmatova str.02192, Kiev");
        address.add("G.Stalingrada str.04209, Kiev");
        address.add("Borislavskaya str.03209, Kiev");
        address.add("B.Zhytomyrskaya str. 02198, Kiev");
        address.add("Irpenskaya str. 03179, Kiev");
        return address;
    }

    public String getAddress(String district){
        if (district.equals("Darnytskyi")) return address.get(0);
        if (district.equals("Obolonsky")) return  address.get(1);
        if (district.equals("Solomensky")) return address.get(2);
        if (district.equals("Shevchenkovskiy")) return address.get(3);
        else return address.get(4);
    }

    public  Double getSquare(Integer rooms){
        if(rooms == 1) return squares.get(0);
        if (rooms == 2) return squares.get(1);
        if (rooms == 3) return squares.get(2);
        else return squares.get(3);
    }
    public  Double getPrice (Integer rooms){
        if(rooms == 1) return prices.get(0);
        if (rooms == 2) return prices.get(1);
        if (rooms == 3) return prices.get(2);
        else return prices.get(3);
    }
    public boolean checkDistrict(String district){
        boolean checker = false;
        for (String str:districts){
            if(district.trim().toLowerCase().equals(str.toLowerCase())) checker = true;
        }
        return checker;
    }
    public boolean checkAddress(String addrss){
        boolean checker = false;
        for (String str:address){
            if(addrss.trim().toLowerCase().equals(str.trim().toLowerCase())) checker = true;
        }
        return checker;
    }
    public boolean checkRooms(Integer rooms){
        boolean checker = true;
            if(rooms>4) checker = false;
        return checker;
    }
    public boolean checkPrice(Double price){
        boolean checker = false;
        for(Double p:prices){
            if(price.equals(p)) checker = true;
        }
        return checker;
    }
    public boolean checkSquare(Double square){
        boolean checker = false;
        for(Double p:squares){
            if(square.equals(p)) checker = true;
        }
        return checker;
    }






    public List<String> getDistricts() {
        return districts;
    }

    public List<Double> getSquares() {
        return squares;
    }

    public List<Double> getPrices() {
        return prices;
    }

    public List<String> getAddress() {
        return address;
    }
}

