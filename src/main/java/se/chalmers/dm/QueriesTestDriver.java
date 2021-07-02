package se.chalmers.dm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class QueriesTestDriver {
    // IMPORTANT: Do NOT change this code, just uncomment it!
    public static void main(String[] args) throws SQLException {
        Connection con = ConnectionHelper.createPostgresConnection();
        Queries queries = new Queries();
        List<User> inactiveUsers = queries.findInactiveUsers(con);
        System.out.println("Found " + inactiveUsers.size() + " inactive users:");
        inactiveUsers.forEach(System.out::println);
        List<String> quotes = queries.findSpecialQuotes(con);
        System.out.println("Found " + quotes.size() + " special quotes:");
        quotes.forEach(System.out::println);
    }
}
