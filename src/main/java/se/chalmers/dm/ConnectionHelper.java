package se.chalmers.dm;

import java.sql.Connection;
import java.util.Properties;
import java.sql.SQLException;
import java.sql.DriverManager;

public class ConnectionHelper {
    private static String DRIVER_CLASS = "org.postgresql.Driver";
    private static String DB_USER = "postgres";
    private static String DB_PASSWORD = "";
    private static String DB_URL = "jdbc:postgresql://localhost:5432/websitedb";

    public static Connection createPostgresConnection(){
        Connection c = null;
        try {
            Properties prop = new Properties();
            prop.setProperty("user", DB_USER);
            prop.setProperty("driver", DRIVER_CLASS);

            return DriverManager.getConnection(DB_URL, prop);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
