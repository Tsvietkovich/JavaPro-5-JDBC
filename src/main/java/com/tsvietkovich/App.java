package com.tsvietkovich;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        DbProperties props = new DbProperties();

        try (Connection conn = DriverManager.getConnection(props.getUrl(), props.getUser(), props.getPassword())) {
            System.out.println("Get connection:)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
