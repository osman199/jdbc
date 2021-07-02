package se.chalmers.dm;

import com.github.javafaker.Faker;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class Seeder {
    private static Faker faker;
    private static Connection connection = null;
    private static Random random;


    Seeder(Faker fk,Connection cn, Random rn){
        faker = fk;
        connection = cn;
        random = rn;
    }

    public static void createUserTable(){
        String query = QueryHelper.sqlQuery("create_user_table.sql");
        System.out.println(query);
        //PreparedStatement prepST = null;
        try {
            //prepST = connection.prepareStatement(query);
            //prepST.executeUpdate();
            connection.createStatement().executeUpdate(query);
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }
    public void insertFakeUsers(int numUsers) throws SQLException {
    PreparedStatement prepST = null;

    for (int i = 1; i <= numUsers; i++) {
        String query = QueryHelper.sqlQuery("insert_user.sql");
        prepST  = connection.prepareStatement(query);

        int ID = i+1;
        String Ssn = faker.idNumber().ssnValid();
        String FName = faker.name().firstName();
        String LName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        boolean isActive = faker.bool().bool();

        prepST.setInt(1, ID);
        prepST.setString(2, Ssn);
        prepST.setString(3, FName);
        prepST.setString(4, LName);
        prepST.setString(5, email);
        prepST.setBoolean(6, isActive);
        prepST.executeUpdate();

    }
    // Closing the statement
    prepST.close();
}

    public void insertFakeUsersWithWebPage(int numusers) throws SQLException {
        String query = QueryHelper.sqlQuery("./insert_webpage.sql");
        PreparedStatement prepST = connection.prepareStatement(query);
        //Creates fake users
        insertFakeUsers(numusers);

        for (int i = 1; i <= numusers; i++) {
            String URL = faker.internet().url();

            int author = i;
            String content = faker.chuckNorris().fact();
            int popularity = faker.number().numberBetween(0, 100); // in range [0, 100]

            WebPage webPage = new WebPage(URL, author, content, popularity);
            webPage.insertData(prepST);
        }

        prepST.close();
        connection.close();
    }
    public static void createWebPageTable() throws SQLException {
        String query = QueryHelper.sqlQuery("create_webpage_table.sql");
        connection.createStatement().executeUpdate(query);
    }
}

