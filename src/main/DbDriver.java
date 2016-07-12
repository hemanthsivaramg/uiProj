package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbDriver {
    public static Connection connect(){
        Connection conn=null;
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("some problem with your driver settings");
        }
        try{
        conn = DriverManager.getConnection("jdbc:h2:~/test","sa","sa");
        } catch (SQLException e) {
            System.out.println("connection exception");
        }
        return conn;
    }


}
