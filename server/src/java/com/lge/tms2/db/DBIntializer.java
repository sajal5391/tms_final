/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lge.tms2.db;

/**
 *
 * @author ramesh.nagarajan
 */
public abstract class DBIntializer {

    static String DRIVER = "com.mysql.jdbc.Driver";
    public static String DATABASE = "tms";
   // static String[] DATABASE_CONFIG_LIST = {"android_mos", "android_nos"};
   // static String[] DATABASE_FUNCTION_LIST = {"LocalPlayer", "CodecParser", "Streaming", "SBP"};
    static String SCHEMA = "root";
    public static String PORT = "3306";
    private static boolean debug_db = false;
    public static String IP ;//= debug_db ? "localhost" :"si-truptipd3";
    public static String URL;// = "jdbc:mysql://"+IP+":" + PORT + "/";
    public static String USERNAME;// = debug_db ? "root" :"testtms";
    public static String PASSWORD;// = debug_db ?  "root123": "admin";

    static {
        init();
    }
  
    public static String getString() {
        return "DBIntializer: USERNAME: " + USERNAME + ", PASSWORD: " + PASSWORD + ", PORT: " + PORT + ", URL: " + URL; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public static void init() {
        IP = debug_db ? "localhost" :"si-truptipd3";
        URL = "jdbc:mysql://"+IP+":" + PORT + "/";
        USERNAME = debug_db ? "root" :"testtms";
        PASSWORD = debug_db ?  "LGsoft@123": "admin";
    }
    
    public static boolean getDebug() {
        return debug_db;
    }
    
    public static void toggle() {
        System.out.println("toggle called and currently Debug is " + debug_db);
        debug_db = !debug_db;
        init();
    }
   
}
