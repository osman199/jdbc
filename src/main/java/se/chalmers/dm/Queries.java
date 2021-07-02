package se.chalmers.dm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;

public class Queries {

  private List<User> users;
  private List<String> quotes;

  public Queries() {
    users = new ArrayList<>();
    quotes = new ArrayList<>();
  }


  public List<User> findInactiveUsers(Connection connection) throws SQLException {
    String query = QueryHelper.sqlQuery("./find_inactive_users.sql");

    ResultSet resultSet = getResult(connection, query);

    while (resultSet.next()) {
      int ID = resultSet.getInt("ID");
      String Ssn = resultSet.getString("Ssn");
      String FName = resultSet.getString("fistname");
      String LName = resultSet.getString("lname");
      String email = resultSet.getString("email");
      boolean isActive = resultSet.getBoolean("isActive");

      users.add(new User(ID, Ssn, FName, LName, email, isActive));
    }

    resultSet.close();
    return users;
  }


  public List<String> findSpecialQuotes(Connection connection) throws SQLException {
    String query = QueryHelper.sqlQuery("./find_special_quotes.sql");

    ResultSet resultSet = getResult(connection, query);

    while (resultSet.next()) {
      quotes.add(resultSet.getString("content"));
    }

    // close connections
    resultSet.close();
    connection.close();
    return quotes;
  }

  private ResultSet getResult(Connection connection, String query) throws SQLException {
    Statement statement = connection.createStatement();

    return statement.executeQuery(query);
  }
}
