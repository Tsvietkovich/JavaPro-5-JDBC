package com.tsvietkovich.Orders;

import com.tsvietkovich.DbProperties;

import java.sql.*;


public class OrderManager {
    private DbProperties properties = new DbProperties();
    private  static Connection connection;

    public void startActions() {
        try {
            connection = DriverManager.getConnection(properties.getUrl(), properties.getUser(), properties.getPassword());
            DropTables();
            initClients();
            initProducts();
            initOrders();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void endActions() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initClients() throws SQLException {
        Statement statement = connection.createStatement();
        try {
            statement.execute("CREATE TABLE Clients (Client_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, NameClient VARCHAR (20), Telephone VARCHAR (20))");
        }finally {
            statement.close();
        }
    }
    private void initProducts() throws SQLException {
        Statement statement = connection.createStatement();
        try {
            statement.execute("CREATE TABLE Products (Product_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, NameProduct VARCHAR (20), Price DOUBLE, Items INT)");
        }finally {
            statement.close();
        }
    }
    private void initOrders() throws SQLException {
        Statement statement = connection.createStatement();
        try {
            statement.execute("CREATE TABLE Orders (Order_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, OrderDate DATE, Product_ID INT, Client_ID INT, " +
                    "FOREIGN KEY (Client_ID) REFERENCES Clients (Client_ID), FOREIGN KEY (Product_ID) REFERENCES Products (Product_ID))");
        }finally {
            statement.close();
        }
    }
    public void addToClients(String name, String telephone) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT  INTO clients (NameClient, Telephone) VALUES (?,?)");
        try{
                ps.setString(1,name);
                ps.setString(2,telephone);
                ps.executeUpdate();
        }finally {
            ps.close();
        }
    }
    public void addToProducts(String name, Double price, Integer count) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT  INTO products (NameProduct, Price, Items) VALUES (?,?,?)");
        try{
            ps.setString(1,name);
            ps.setDouble(2,Utils.getPriceByItems(count,price));
            ps.setInt(3,count);
            ps.executeUpdate();
        }finally {
            ps.close();
        }
    }

    public void makeOrder (String nameClient, String nameProduct) throws SQLException {
        try (PreparedStatement psClient = connection.prepareStatement("SELECT Client_ID FROM clients WHERE NameClient = ?")) {
            try (PreparedStatement psProduct = connection.prepareStatement("SELECT Product_ID FROM products WHERE NameProduct= ?")) {
                psClient.setString(1, nameClient);
                psProduct.setString(1, nameProduct);
                Integer id_Client = getInt(psClient.executeQuery());
                Integer id_Product = getInt(psProduct.executeQuery());
                addToOrder(id_Client,id_Product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addToOrder (Integer idClient, Integer idProduct) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT  INTO orders (OrderDate,Product_ID,Client_ID) VALUES (?,?,?)");
        try{
            ps.setDate(1,new Date(1508450427536L));
            ps.setInt(2,idProduct);
            ps.setInt(3,idClient);
            ps.executeUpdate();
        }finally {
            ps.close();
        }
    }

    public  void veiwOrder() throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM products LEFT  JOIN orders USING (Product_ID) LEFT  JOIN clients USING (Client_ID) ORDER BY Order_ID");
        try {
            ResultSet rs = ps.executeQuery();
            try {
                ResultSetMetaData md = rs.getMetaData();
                for (int i = 1; i <= md.getColumnCount(); i++)
                    System.out.print(md.getColumnName(i) + "\t\t");
                System.out.println();
                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t\t");
                    }
                    System.out.println();
                }
            } finally {
                rs.close();
            }
        } finally {
            ps.close();
        }
    }

    private static Integer getInt(ResultSet rs){
        Integer integer = null;
        try {
            if(rs.next()){
                integer= rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return integer;
    }

    private void DropTables () throws SQLException {
        Statement statement = connection.createStatement();
        try {
            statement.execute("DROP TABLE IF EXISTS Orders");
            statement.execute("DROP TABLE IF EXISTS Products");
            statement.execute("DROP TABLE IF EXISTS Clients");

        }finally {
            statement.close();
        }
    }
    }

