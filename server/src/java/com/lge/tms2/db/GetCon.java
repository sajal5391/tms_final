package com.lge.tms2.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetCon extends DBIntializer {

    public static String CONNECTION_ERROR = "";
    private static Connection con = null;

    private GetCon() {
    }

    public static void init() {
        try {
            if (con == null) {
                Class.forName(DRIVER);
                con = DriverManager.getConnection(URL + DATABASE + "?autoReconnect=true", USERNAME, PASSWORD);                
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {

        if (con == null) {
            init();
        }
        return con;
    }
    
    public static void closeConnection() {
        if(con != null) {
            try {
                con.close();
            } catch (SQLException ex) {  
                ex.printStackTrace();
            }
            con = null;
        }
    }

}
