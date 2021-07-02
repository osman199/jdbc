package se.chalmers.dm;

import java.sql.*;

public class JDBCTestDriver {
    private static String DRIVER_CLASS = "org.postgresql.Driver";
    private static String DB_USER = "postgres";
    private static String DB_PASSWORD = "";
    private static String DB_URL = "jdbc:postgresql://localhost:5432/websitedb";
    private static int EXIT_FAILURE = 1;

    public static void main(String[] args) {
        Connection c = null;
        Statement ST = null;
        ResultSet RS = null;
        PreparedStatement prepST =null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            String query = "SELECT 15 AS retval;";
            ST = c.createStatement();
            RS = ST.executeQuery(query);

            while (RS.next()){
                int retval = RS.getInt("retval");
                System.out.println(retval);
            }
            RS.close();
            ST.close();
            c.close();
            System.out.println("Database opened");
        }catch(Exception ex){
            System.out.println(ex.getMessage());

        }
    }
}
