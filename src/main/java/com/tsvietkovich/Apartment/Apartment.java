package com.tsvietkovich.Apartment;

import com.tsvietkovich.DbProperties;

import java.sql.*;

public class Apartment {
    private  static ApartmentInformation info = new ApartmentInformation();
    private static DbProperties properties = new DbProperties();
    private static Connection connection;

    public void getDB() {
        try {
                connection = DriverManager.getConnection(properties.getUrl(), properties.getUser(), properties.getPassword());
                initDataBase();
                createDataBase();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return;
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private  static void initDataBase() throws SQLException {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS Apartments");
            statement.execute("CREATE TABLE Apartments (ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, District VARCHAR (20), Address VARCHAR (50), Square DOUBLE , Room INT(5), Price DOUBLE)");
        }finally {
                statement.close();
        }
    }
    private  static void createDataBase() throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT  INTO apartments (District, Address, Square, Room, Price) VALUES (?,?,?,?,?)");
        int count = (int) (Math.random()*20);
        try{
        while (count>0){
            String district = getDistrictRandomly();
            Integer rooms = getRoomsRundomly();
            ps.setString(1,district);
            ps.setString(2,getAddressByDistrict(district));
            ps.setDouble(3,getSquareByRooms(rooms));
            ps.setInt(4,rooms);
            ps.setDouble(5,getPriceByRooms(rooms));
            ps.executeUpdate();
            count--;
            }
        }finally {
            ps.close();
        }
    }

    public void getApartmentByDistrict(String district) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM apartments WHERE District = ?");
        try {
            ps.setString(1, district);
            ResultSet rs = ps.executeQuery();
            viewApartments(rs);
        } finally {
            ps.close();
        }
    }
    public void getApartmentByAddress(String address) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM apartments WHERE Address = ?");
        try {
            ps.setString(1, address);
            ResultSet rs = ps.executeQuery();
            viewApartments(rs);
        } finally {
            ps.close();
        }
    }
    public void getApartmentBySquare(Double square) throws SQLException {
        PreparedStatement ps = null;
        try {
            if (!info.checkSquare(square)){
                System.out.println("Current square not found, there was selected next apartments:");
                ps = connection.prepareStatement("SELECT * FROM apartments WHERE (Square BETWEEN 30.0 AND ? )");
                ps.setDouble(1,square);
            } else {
                ps = connection.prepareStatement("SELECT * FROM apartments WHERE Square = ?");
                ps.setDouble(1, square);
            }
            ResultSet rs = ps.executeQuery();
            viewApartments(rs);
        } finally {
            ps.close();
        }
    }
    public void getApartmentByRooms(Integer rooms) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM apartments WHERE Room = ?");
        try {
            ps.setInt(1, rooms);
            ResultSet rs = ps.executeQuery();
            viewApartments(rs);
        } finally {
            ps.close();
        }
    }
    public void getApartmentByPrice(Double price) throws SQLException {
        PreparedStatement ps = null;
        try {
        if (!info.checkPrice(price)){
            System.out.println("Current price not found, there was selected next apartments:");
            ps = connection.prepareStatement("SELECT * FROM apartments WHERE (Price BETWEEN 75.0 AND ? )");
            ps.setDouble(1,price);
        } else {
            ps = connection.prepareStatement("SELECT * FROM apartments WHERE Price = ?");
            ps.setDouble(1, price);
        }
            ResultSet rs = ps.executeQuery();
            viewApartments(rs);
        } finally {
            ps.close();
        }
    }

    private void viewApartments(ResultSet rs) throws SQLException {
            ResultSetMetaData md = rs.getMetaData();
        try {
            for (int i = 1; i <= md.getColumnCount(); i++)
                System.out.print(md.getColumnName(i) + "\t\t\t");
            System.out.println();
            while (rs.next()) {
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    System.out.print(rs.getString(i) + "\t\t\t");
                }
                System.out.println();
            }
        } finally {
            rs.close();
        }
    }

    private  static String getDistrictRandomly(){
        return info.getDistricts().get((int) (Math.random()*5));
    }
    private static String getAddressByDistrict(String district){
        return info.getAddress(district);
    }
    private static Integer getRoomsRundomly(){
        return 1+(int)(Math.random()*(5-1));
    }
    private static Double getSquareByRooms(Integer rooms){
        return info.getSquare(rooms);
    }
    private static Double getPriceByRooms(Integer rooms){
        return info.getPrice(rooms);
    }
}
