package se.chalmers.dm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WebPage {
  private String url;
  private int author;
  private String content;
  private int popularity;

  public WebPage(String url, int author, String content, int popularity) {
    this.url = url;
    this.author = author;
    this.content = content;
    this.popularity = popularity;
  }

  /**
   * @throws SQLException database access error or other errors.
   */
  public void insertData(PreparedStatement prepST) throws SQLException {
    prepST.setString(1, url);
    prepST.setInt(2, author);
    prepST.setString(3, content);
    prepST.setInt(4, popularity);

    prepST.executeUpdate();
  }
}
